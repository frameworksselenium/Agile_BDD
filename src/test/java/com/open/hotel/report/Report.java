package com.open.hotel.report;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

import java.io.File;
import java.util.List;

public class Report {


    public static void generateCucumberReport(List<String> jsonFiles){
        try{
            File reportOutputDirectory = new File("target/cucumber-html-full-report");
            String buildName = "20";
            String projectName = "Sample Project";
            Configuration configuration = new Configuration(reportOutputDirectory, projectName);
            configuration.setBuildNumber(buildName);
            configuration.addClassifications("Browser", "Chrome");
            configuration.setSortingMethod(SortingMethod.NATURAL);
            configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
            configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
            configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        }catch (Exception e){
            //LOGGER.error("Failed to generate HTML report, error: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
