package com.open.hotel.work;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Work {

    public static void main(String[] args){

        //WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "/Users/krishnareddymanubolu/Documents/WorkSpace/Agile_BDD/src/test/resources/drivers/chromedriver");
        // Initialize the ChromeDriver instance
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.addArguments("--remote-allow-origins=*");
       // browserOptions.setBrowserVersion("latest");
        WebDriver driver = new ChromeDriver(browserOptions);

        // Now you can use 'driver' to automate browser actions
        driver.get("https://www.example.com");

        // Close the WebDriver session when done
        driver.quit();

    }
}



    String browser = System.getProperty("Browser");
if (browser == null) {
        browser = Config.properties.getProperty("Browser");
        }
        String ExecutionMode = System.getProperty("ExecutionMode");
        if (ExecutionMode == null) {
        ExecutionMode = Config.properties.getProperty("ExecutionMode");
        }
        String platformName = System.getProperty("PlatformName");
        if (platformName == null) {
        platformName = Config.properties.getProperty("PlatformName");
        }
        String RemoteURL = System.getProperty("RemoteURL");
        if (RemoteURL == null) {
        RemoteURL = Config.properties.getProperty("RemoteURL");
        }
        String testCaseID = VariableManager.getInstance().getVariables().getVar("testCaseID").toString();
        String testName = Config.properties.getProperty("BuildName") + "- Test Case Id : '" + testCaseID + "'";
        String buildId = Config.properties.getProperty("BuildId");
        driver = ManagerDriver.getInstance().getDriver(ExecutionMode, browser, platformName, RemoteURL, testName,
        buildId);
        VariableManager.getInstance().getVariables().setVar("driver", driver);


        package com.dt.services;

        import java.util.HashMap;
        import java.util.Map;
        import com.dt.threadVariables.VariableManager;
        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import org.json.simple.parser.ParseException;

public class Authorization extends Base {

    String AuthEndPoint;
    String grant_type;
    String username;
    String password;
    String BasicAuthorization;
    String AuthContentType;
    Response response = null;
    public Authorization() {
        super();
        this.AuthEndPoint = prop.getProperty("WMS_Auth_EndPoint");
        this.BasicAuthorization = prop.getProperty("WMS_Auth_Basic_Authorization");
        this.AuthContentType = prop.getProperty("WMS_Auth_ContentType");
        this.grant_type = prop.getProperty("WMS_Auth_FormParams_GrantType");
        this.username = prop.getProperty("WMS_Auth_FormParams_UserName");
        this.password = prop.getProperty("WMS_Auth_FormParams_Password");
    }

    public Map<String, String> buildFormParameters() {

        Map<String, String> form = new HashMap<>();
        form.put("grant_type", grant_type);
        form.put("username", username);
        form.put("password", password);
        return form;
    }

    public Map<String, String> buildHeaderParameters() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", BasicAuthorization);
        //header.put("Content-Type", AuthContentType);
        return header;
    }

    public Authorization send() throws ParseException {
        RequestSpecification sp = RestAssured.given().relaxedHTTPSValidation().baseUri(authBaseURL);
        response = sp.headers(buildHeaderParameters()).formParams(buildFormParameters()).post(AuthEndPoint);
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed : HTTP error code :" + statusCode);
        }
        this.accessToken = response.body().jsonPath().getString("access_token");
        VariableManager.getInstance().getVariables().setVar("response", response);
        VariableManager.getInstance().getVariables().setVar("access_token", accessToken);
        return this;
    }


    public Authorization showResponse() throws ParseException {
        assertions.getLogger().info("======== Authorization Microservice Response : " + response.getBody().asString());
        assertions.getLogger().info("======== Access Token :                                         " + response.body().jsonPath().getString("access_token"));
        return this;
    }

}



package com.dt.services;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Properties;

        import com.dt.assertions.Assertions;
        import com.dt.config.Config;
        import com.dt.threadVariables.VariableManager;
        import com.dt.threadVariables.Variables;

public class Base {

    String authBaseURL;
    String baseURL;
    public String organization;

    public String locations;

    public String contentType;
    String accessToken = null;

    public String authrization;
    Properties prop = Config.properties;
    String Env = prop.getProperty("Environment");
    Assertions assertions = new Assertions();

    public Base() {
        this.baseURL = prop.getProperty(Env + "_WMS_BaseURL");
        this.authBaseURL = prop.getProperty(Env + "_WMS_Auth_BaseURL");
        this.organization = prop.getProperty("Organization");
        this.locations = prop.getProperty("Location");
        this.contentType = prop.getProperty("WMS_ContentType");
        this.authrization = "Bearer " + VariableManager.getInstance().getVariables().getVar("access_token");
    }

    public Map<String, String> buildHeaderMap() {
        Map<String, String> header = new HashMap<>();
        header.put("Organization", organization);
        header.put("Location", locations);
        header.put("Content-Type", contentType);
        header.put("Authorization", authrization);
        return header;
    }

}



package com.dt.services;

        import java.io.FileReader;
        import java.util.Map;
        import java.util.Properties;

        import com.dt.config.Config;
        import com.dt.threadVariables.VariableManager;
        import com.jayway.jsonpath.DocumentContext;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;

        import com.jayway.jsonpath.JsonPath;
        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import net.minidev.json.JSONArray;

public class PurchaseOrderSearch extends Base {

    JSONObject jsonObject = null;
    String EndPoint;
    Response response = null;
    JSONParser parser = new JSONParser();
    JSONObject responseJSON;
    String payload = null;

    public PurchaseOrderSearch() {
        super();
        this.EndPoint = prop.getProperty("WMS_PurchaseOrder_Search_EndPoint");
    }

    public PurchaseOrderSearch getPayload() {
        try {
            Properties prop = Config.properties;
            String jsonFilePath = System.getProperty("user.dir") + "//src//test/resources//templates//" + "PurchaseOrderSearch.json";
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonFilePath));
            this.jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PurchaseOrderSearch updatePayload(String poNumber) {
        try {
            JsonPath.parse(jsonObject).set("$.Query", "PurchaseOrderId in ('" + poNumber + "')");
            payload = jsonObject.toJSONString();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PurchaseOrderSearch send() {
        Map<String, String> header = buildHeaderMap();
        try {
            RequestSpecification rsp = RestAssured.given().relaxedHTTPSValidation().baseUri(baseURL);
            response = rsp.headers(header).body(jsonObject).post(EndPoint);
            int statusCode = response.getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed : HTTP error code :" + statusCode);
            }
            responseJSON = (JSONObject) parser.parse(response.getBody().asString());
            VariableManager.getInstance().getVariables().setVar("response", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PurchaseOrderSearch showPayload() {
        assertions.getLogger().info("======== Request : " + payload);
        return this;
    }

    public PurchaseOrderSearch showResponse() {
        assertions.getLogger().info("======== Response : " + response.getBody().asString());
        return this;
    }

    public String getVendorIDBasedOnPO(String poNumber) throws ParseException {
        String vendorID = ((JSONArray) JsonPath.parse(responseJSON).read("$.data[?(@.PurchaseOrderId=='" + poNumber + "')].VendorId")).get(0).toString();
        String vendorID1 =  JsonPath.parse(responseJSON).read("$.data[?(@.PurchaseOrderId=='" + poNumber + "')].VendorId").toString();
        return vendorID;
    }

}



package com.dt.database;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.ResultSetMetaData;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.HashMap;

        import com.dt.config.Config;
        import com.dt.logger.LoggerClass;
        import com.dt.security.Security;
        import com.dt.threadVariables.VariableManager;

public class DataBase {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    Statement smt = null;
    Connection conn = null;

    public DataBase() {

    }

    public Statement connectDB(String dbType, String dbName) {

        String env = System.getProperty("Environment");
        if (env == null) {
            env = Config.properties.getProperty("Environment");
        }
        String url = Config.properties.getProperty(env + "_" + dbName + "_DB_URL");
        String userName = Config.properties.getProperty(env + "_" + dbName + "_DB_username");
        String password = Config.properties.getProperty(env + "_" + dbName + "_DB_password");
        Security security = new Security();
        password = security.decryptPassword(password);
        String driverClassName = Config.properties.getProperty(dbType + "_DriverClass");
        try {
            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, userName, password);
            smt = conn.createStatement();
        } catch (Exception e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return smt;
    }

    public HashMap<String, String> getResultSet(String query) {
        HashMap<String, String> data = new HashMap<String, String>();
        try {
            ResultSet rs = smt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            rs.next();
            for (int i = 1; i <= columnCount; i++) {
                data.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
            }
        } catch (SQLException e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }

    public HashMap<String, ArrayList<String>> getMultipleRowData(String query) {
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        ArrayList<String> headings = new ArrayList<String>();
        try {
            ResultSet rs = smt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<ArrayList<String>> rowObj = new ArrayList<ArrayList<String>>();
            for (int i = 1; i <= columnCount; i++) {
                headings.add(rsmd.getColumnName(i));
                rowObj.add(new ArrayList<String>());
            }
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    rowObj.get(i - 1).add(rs.getString(i));
                }
            }
            for (int i = 0; i < columnCount; i++) {
                data.put(headings.get(i), rowObj.get(i));
            }
        } catch (SQLException e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }

    public void closeDB() {
        try {
            smt.close();
            conn.close();
        } catch (Exception e) {
            log.info("Thred ID:'" + Thread.currentThread().getId() + "Exception: " + e);
        }
    }
}



#***********************************************End point URL*************************************************

        QA_WMS_BaseURL                  = https://dtres.sce.manh.com
        QA_WMS_Auth_BaseURL             = https://dtres-auth.sce.manh.com
        WMS_Auth_EndPoint           = /oauth/token
        WMS_Auth_ContentType         = application/x-www-form-urlencode
        WMS_Auth_Basic_Authorization   = Basic b21uaWNvbXBvbmVudC4xLjAuMDpiNHM4cmdUeWc1NVhZTnVu
        WMS_Auth_FormParams_GrantType  = password
        WMS_Auth_FormParams_UserName   = kmanubol
        WMS_Auth_FormParams_Password   = India@124
        WMS_ContentType                = application/json

        WMS_Entity_Search_EndPoint    = /dmui-facade/api/dmui-facade/entity/search
        WMS_PurchaseOrder_Search_EndPoint= /receiving/api/receiving/purchaseOrder/search
        WMS_Asn_Search_EndPoint          = /receiving/api/receiving/asn/search
        #***********************************************Data Base*****************************************************
        Oracle_DriverClass          = oracle.jdbc.driver.OracleDriver
        GOLD_RMS_DB_URL                = jdbc:oracle:thin:@fdlxrmsdbtst1:1521:RMSTST
        GOLD_RMS_DB_username         = cogacct
        GOLD_RMS_DB_password         = qQhRdTUA7Cy1s6+gwkx5dQ==





        package com.dt.uiUtils;

        import java.io.FileReader;
        import java.io.IOException;
        import java.time.Duration;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;
        import java.util.Set;
        import com.dt.assertions.Assertions;
        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;
        import org.openqa.selenium.By;
        import org.openqa.selenium.ElementClickInterceptedException;
        import org.openqa.selenium.NoSuchWindowException;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.StaleElementReferenceException;
        import org.openqa.selenium.NoSuchElementException;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.FluentWait;
        import org.openqa.selenium.support.ui.Select;
        import org.openqa.selenium.support.ui.Wait;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.Assert;
        import com.dt.config.Config;
        import com.dt.logger.LoggerClass;
        import com.dt.threadVariables.VariableManager;
        import io.cucumber.java.Scenario;

public class UIUtils {
    WebDriver driver = null;
    Scenario scenario = null;
    Assertions assertions = new Assertions();
    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());
    ;

    public UIUtils() {
        this.scenario = (Scenario) VariableManager.getInstance().getVariables().getVar("scenario");
        this.driver = (WebDriver) VariableManager.getInstance().getVariables().getVar("driver");
    }

    public void type(WebElement element, String value, String elementName, String page) {

        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            element.clear();
            element.sendKeys(value);
            if (elementName.equals("Password")) {
                value = "";
            }
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box");
        } catch (Exception e) {
            if (elementName.equals("Password")) {
                value = "";
            }
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void typeJavascript(WebElement element, String value, String elementName, String page) {
        try {
            JavascriptExecutor ex = (JavascriptExecutor) driver;
            ex.executeScript("arguments[0].value='" + value + "';", element);
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box " + " on page " + page);

        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void clickElement(WebElement element, String elementName, String page) {
        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            // scrollToElement(element);
            element.click();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean WaitUntilClickable(WebElement element, int iWaitTime) throws Exception {

        boolean bFlag = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(iWaitTime));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            bFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
            bFlag = false;
        }
        return bFlag;
    }

    public void elementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void MouseMoveToElement(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(500);
        action.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void highlightElement(WebElement element) throws Exception {

        String highlightElements = Config.properties.getProperty("HighlightElements");
        if (highlightElements.equalsIgnoreCase("true")) {
            String attributevalue = "border:10px solid green;";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            String getattrib = element.getAttribute("style");
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
            Thread.sleep(100);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
        }
    }

    public void closeWindowByTitle(String title, String page) {
        try {
            Set<String> handle = driver.getWindowHandles();
            for (String mywindows : handle) {
                String myTitle = driver.switchTo().window(mywindows).getTitle();
                if (myTitle.equalsIgnoreCase(title)) {
                    driver.switchTo().window(mywindows);
                    driver.close();
                    break;
                }
            }

        } catch (NoSuchWindowException e) {
            log.info("Exception: " + e);
            Assert.fail("Window is not present in page");
        }
    }

    public void switchIntoWindow(String title) {

        String parent = driver.getWindowHandle();
        VariableManager.getInstance().getVariables().setVar("parent", parent);
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            String winTitle = driver.getTitle();
            if (winTitle.equals(title)) {
                break;
            }
        }

    }

    public void switchIntoWindow(String[] title) {

        String parent = driver.getWindowHandle();
        VariableManager.getInstance().getVariables().setVar("parent", parent);
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            driver.switchTo().window(window);
            String winTitle = driver.getTitle();
            if ((!winTitle.equals(title[0])) && (!winTitle.equals(title[1])) && (!winTitle.equals(title[2]))) {
                break;
            }
        }

    }

    public void elementToBeClickable(String xPath, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    public void switchToIFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void waitFor(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearTextBox(WebElement element, String value, String elementName, String page) {

        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            element.clear();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                            + elementName + "' text box",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Entered value '" + value + "' in '"
                    + elementName + "' text box");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForStaleElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForWebElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
        //.ignoring(ElementClickInterceptedException.class);

        return wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForStaleElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(driver -> {
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForWebElement(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))))
                .pollingEvery(Duration.ofMillis(1000))
                //.ignoring(NoSuchElementException.class);
                .ignoring(ElementClickInterceptedException.class);

        return wait.until(driver -> {
            try {
                element.isEnabled();
                element.isDisplayed();// simulate an operation on the element to detect staleness
                return element;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public void generalClickElement(WebElement element, String elementName, String page) {
        try {
            WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            element.click();
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void waitUntilElementInvisible(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(Config.properties.getProperty("LONGWAIT"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    }

    public void selectValue(WebElement element, String value, String elementName, String page) {
        try {
            //WaitUntilClickable(element, Integer.valueOf(Config.properties.getProperty("LONGWAIT")));
            highlightElement(element);
            MouseMoveToElement(element);
            // scrollToElement(element);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            ;
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Selected value '" + elementName + "' in dropdown",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Selected value '" + elementName
                    + "' in dropdown");
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thred ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<String> cardValues(List<WebElement> cards, List<WebElement> cardValues) throws InterruptedException {
        Thread.sleep(5000);
        List<String> actualValues = new ArrayList<>();
        if (cards.size() > 0) {
            for (WebElement element : cardValues) {
                actualValues.add(element.getText());
            }
        } else {
            assertions.assertStatus("Fail", "Data is not available");
        }
        return actualValues;
    }
    public boolean cardsStatus(List<WebElement> cards) throws InterruptedException {
        waitFor(5000);
        boolean cardsStatus;
        if (cards.size() > 0) {
            cardsStatus = true;
        } else {
            cardsStatus = false;
            assertions.assertStatus("Fail", "Data is not available");
        }
        return cardsStatus;
    }
    public WebElement createWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void javaScriptClickElement(WebElement element,String elementName, String page) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            scenario.attach(
                    "Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName + "' button",
                    "text/plain", DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS' Clicked on '" + elementName
                    + "' button" + "On Page " + page);
        } catch (Exception e) {
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            scenario.attach("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage(), "text/plain",
                    DateTimeFormatter.ofPattern("M-dd-yyyy hh:mm:ss").format(LocalDateTime.now()));
            log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForElement(String xPath) {
        WebElement element = waitForWebElement(By.xpath(xPath));
        element = waitForStaleElement(By.xpath(xPath));
        elementToBeClickable(element);
        return element;
    }

    public WebElement waitForElement(WebElement ele) {
        waitFor(2000);
        WebElement element = waitForWebElement(ele);
        element = waitForStaleElement(ele);
        elementToBeClickable(element);
        return element;
    }

    public WebElement expandRootElement (String xpath) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        WebElement element = (WebElement) js.executeScript("return " + xpath + "");
        return element;
    }
    public void validateAllListOfValues(List<String> actualValues, Map<String, String> expectedValues) {
        String expected = null;
        String actual = null;
        for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
            expected = entry.getKey();
            boolean status = false;
            for(String actualField:actualValues) {
                actual = actualField;
                if(expected.equals(actualField)) {
                    status = true;
                    assertions.assert2Values(actualField, expected);
                    break;
                }
            }
            if(!status){
                assertions.assertStatus("Fail", actual + " this field not available");
            }
        }
    }

    public Map<String, String> getAllKeysAndValuesFromJson(String module, String jsonFileName) throws IOException, ParseException {

        String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\templates\\" + module + "\\" + jsonFileName + ".json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(jsonFilePath));
        JSONObject jsonObject = (JSONObject) obj;
        ObjectMapper mapper = new ObjectMapper();
        String json = jsonObject.toString();
        Map<String, String> map = mapper.readValue(json, Map.class);
        return map;
    }

}



   package com.dt.report;

           import java.io.File;
           import java.sql.Timestamp;
           import java.util.List;

           import com.dt.config.Config;

           import net.masterthought.cucumber.Configuration;
           import net.masterthought.cucumber.ReportBuilder;
           import net.masterthought.cucumber.presentation.PresentationMode;
           import net.masterthought.cucumber.sorting.SortingMethod;

public class Report {

    public static void generateCucumberReport(List<String> jsonFiles) {
        String cucumberReportPath = null;
        String basePath = System.getProperty("user.dir");
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeStamp = String.valueOf(timestamp.getTime());
            cucumberReportPath = basePath + "/target/cucumberReport/cucumber-full-report" + "-" + timeStamp;
            File reportOutputDirectory = new File(cucumberReportPath);
            String buildNumber = Config.properties.getProperty("BuildId");
            String projectName = Config.properties.getProperty("BuildName");
            Configuration configuration = new Configuration(reportOutputDirectory, projectName);
            configuration.setBuildNumber(buildNumber);
            configuration.addClassifications("Browser", "Chrome");
            configuration.setSortingMethod(SortingMethod.NATURAL);
            configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
            configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
            configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}


