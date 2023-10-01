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
        //ChromeOptions browserOptions = new ChromeOptions();
        //browserOptions.addArguments("--remote-allow-origins=*");
       // browserOptions.setBrowserVersion("latest");
        WebDriver driver = new ChromeDriver();

        // Now you can use 'driver' to automate browser actions
        driver.get("https://www.example.com");

        // Close the WebDriver session when done
        driver.quit();

    }
}






