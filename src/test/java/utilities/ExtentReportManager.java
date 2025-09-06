package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;

public class ExtentReportManager implements ITestListener
{
     public ExtentSparkReporter sparkReporter;
     public ExtentReports extentReports;
     public ExtentTest test;
     
     String repName;
     
     public void onStart(ITestContext testContext) {

         // Time stamp
         String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
         repName = "Test-Report-" + timeStamp + ".html";

         // specify location of the report
         sparkReporter = new ExtentSparkReporter("reports/" + repName);

         sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
         sparkReporter.config().setReportName("OpenCart Functional Testing");
         sparkReporter.config().setTheme(Theme.DARK);

         extentReports = new ExtentReports();
         extentReports.attachReporter(sparkReporter);
         extentReports.setSystemInfo("Application", "OpenCart");
         extentReports.setSystemInfo("Module", "Admin");
         extentReports.setSystemInfo("Sub Module", "Customers");
         //extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
         extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
         extentReports.setSystemInfo("Environment", "QA");
         //extentReports.setSystemInfo("user", "sherif");
         
         String os = testContext.getCurrentXmlTest().getParameter("os");
         extentReports.setSystemInfo("Operating System", os);
         
         String browser = testContext.getCurrentXmlTest().getParameter("browser");
         extentReports.setSystemInfo("Browser", browser);
         
         List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
         if(!includeGroups.isEmpty())
         {
         	extentReports.setSystemInfo("Groups", includeGroups.toString());
         }
     }
     
     public void onTestSuccess(ITestResult result) {

         test = extentReports.createTest(result.getName());
         test.assignCategory(result.getMethod().getGroups());
         test.createNode(result.getName());
         test.log(Status.PASS, "Test Passed");
     }
     
     public void onTestFailure(ITestResult result) {

         test = extentReports.createTest(result.getName());
         test.createNode(result.getName());
         test.assignCategory(result.getMethod().getGroups());
         test.log(Status.FAIL, "Test Failed");
         test.log(Status.FAIL, result.getThrowable().getMessage());
         
         try {
         	String imgpath= new BaseClass().captureScreen(result.getName());
         	System.out.println(imgpath);
         	test.addScreenCaptureFromPath(imgpath);
         }
         catch(IOException e1)
         {
         	e1.printStackTrace();
         }
     }
     
     public void onTestSkipped(ITestResult result) {

         test = extentReports.createTest(result.getName());
         test.assignCategory(result.getMethod().getGroups());
         test.createNode(result.getName());
         test.log(Status.SKIP, "Test Skipped");
         test.log(Status.SKIP, result.getThrowable().getMessage());
     }
     
     public void onFinish(ITestContext testContext) {

         extentReports.flush();
         
         String pathOfExtentReport = System.getProperty("user.dir")+"/reports/"+repName;
         File extentReport = new File(pathOfExtentReport);
         try
         {
         	Desktop.getDesktop().browse(extentReport.toURI());
         }
         catch (IOException e) {
         	e.printStackTrace();
 			// TODO: handle exception
 		}
         //MailSender.sendReportByEmail("receiveremail@example.com", pathOfExtentReport);
     }

}
