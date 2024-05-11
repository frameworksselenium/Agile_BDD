package com.open.hotel.webDriverFactory;

import com.open.hotel.config.Config;
import com.open.hotel.logger.LoggerClass;
import com.open.hotel.threadVariables.VariableManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class LocalMobileBrowserDriverFactory {

    org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(),
            VariableManager.getInstance().getVariables().getVar("testCaseID").toString());

    private static LocalMobileBrowserDriverFactory instance = new LocalMobileBrowserDriverFactory();

    private LocalMobileBrowserDriverFactory() {
    }

    public static LocalMobileBrowserDriverFactory getInstance() {
        return instance;
    }

    public WebDriver createNewDriver(String browser) {
        WebDriver driver = null;
        if (browser.toUpperCase().contains("CH")) {
            String downloadFolderPath = System.getProperty("user.dir") + "//target//DownloadFiles";
            Config.createFolder(downloadFolderPath);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String dateAndTimeStamp = String.valueOf(timestamp.getTime());
            String downloadFileFolder = downloadFolderPath + "_" + dateAndTimeStamp + "_" + Thread.currentThread().getId();
            Config.createFolder(downloadFileFolder);
            VariableManager.getInstance().getVariables().setVar("downloadFileFolder", downloadFileFolder);

            // Use File.separator as it will work on any OS
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.directory_upgrade", true);
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("profile.default_content_setting_values.automatic_downloads",1);
            prefs.put("download.default_directory", downloadFileFolder);

            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
            //browserOptions.setExperimentalOption("prefs", prefs);
            //browserOptions.setPlatformName(PlatformName);
           // browserOptions.addArguments("--remote-allow-origins=*");
            //browserOptions.setBrowserVersion("latest");
            //WebDriverManager.chromedriver().setup();
            try {
                driver = new ChromeDriver(browserOptions);
                //driver = new ChromeDriver();
                driver.manage().window().maximize();
            } catch (Exception e) {
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("FF")) {
            FirefoxOptions browserOptions = new FirefoxOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            try {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } catch (Exception e) {
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("ED")) {
            EdgeOptions browserOptions = new EdgeOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            try {
                driver = new EdgeDriver();
                driver.manage().window().maximize();
            } catch (Exception e) {
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        if (browser.toUpperCase().contains("SF")) {
            SafariOptions browserOptions = new SafariOptions();
            browserOptions.setPlatformName("windows");
            browserOptions.setBrowserVersion("latest");
            try {
                driver = new SafariDriver();
                driver.manage().window().maximize();
            } catch (Exception e) {
                log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'FAIL' " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
}
