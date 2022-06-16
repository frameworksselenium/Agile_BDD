package com.open.hotel.runner;

import com.open.hotel.config.Config;
import com.open.hotel.report.Report;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@CucumberOptions(
		plugin={
				"pretty",
				"html:target/cucumberReport",
				"json:target/cucumberReport/cucumber.json",
		},
		tags={"@UI"},
		features = "src/test/java/com/open/hotel/features",
		glue={"com.open.hotel.stepdefinitions", "com.open.hotel.hooks"},
		strict = true,
		dryRun = false
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {

		return super.scenarios();
	}

	@BeforeSuite()
	public void setup(){
		Config.createFolder(Config.properties.getProperty("resultFolder"));
		Config.createFolder(Config.properties.getProperty("resultFolderName"));
	}
	@AfterSuite()
	public void tearDown(){
		List<String> reports = new LinkedList<>();
		reports.add("target/cucumberReport/cucumber.json");
		Report.generateCucumberReport(reports);
	}

}
