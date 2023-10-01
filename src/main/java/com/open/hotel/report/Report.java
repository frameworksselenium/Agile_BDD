package com.open.hotel.report;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import com.open.hotel.config.Config;

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

