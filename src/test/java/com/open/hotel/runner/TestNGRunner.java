package com.open.hotel.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin={
				"pretty",
				"html:target/cucumberReport",
				"json:target/cucumberReport/cucumber.json",
		},
		tags={"@SmokeTest"},
		features = "src/test/java/com/open/hotel/features",
		glue="src/test/java/com/open/hotel/stepDefinitions",
		strict = true,
		dryRun = false
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {

		return super.scenarios();
	}

}
