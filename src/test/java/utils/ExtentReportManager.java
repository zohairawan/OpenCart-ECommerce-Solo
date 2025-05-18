package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;
    String reportName;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "OpenCart");
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("Operating System", context.getCurrentXmlTest().getParameter("os"));
        reports.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            reports.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " passed");
    }

    public void onTestFailure(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " failed");
//        try {
//            String imgPath = new BaseTest().takeScreenshot(result.getName());
//            String imgPath = new BaseTest().captureScreenshot(result.getName());
            // Log custom messagez
            // Attach the screenshot to the Extent Report with custom button text
//            test.addScreenCaptureFromBase64String(imgPath, "Logging Screenshot: ");
//            System.out.println("Screenshot saved at: " + imgPath);
//            test.addScreenCaptureFromPath(imgPath);
//            test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void onTestSkipped(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        reports.flush();

        // Automatically open report in browser
    }
}