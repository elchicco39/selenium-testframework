package listener;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class MyITestListener implements ITestListener, ISuiteListener {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onFinish(ISuite suite) {
		System.out.println("onFinish der Suite " + suite.getName());
		extent.flush();
	}

	public void onStart(ISuite suite) {
		System.out.println("onStart der Suite " + suite.getName());

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
		
		// Die Configuration des Berichtes kann entweder 
		// über die XML oder über die htmlReporter.config()-Parameter geschehen
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "me");
		extent.setSystemInfo("läuft gut?", "super !");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Testing"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setLevel(Status.INFO);
		htmlReporter.config().setTimeStampFormat("dd.MM.yyyy HH:mm:ss");
		htmlReporter.config().setEncoding("Cp1252");
		htmlReporter.config().setTheme(Theme.DARK);

	}

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart bei: " + result.getInstanceName() + " - " + result.getName());
	}

	public void onStart(ITestContext testContext) {
		System.out.println("onStart bei: " + testContext.getName());
	}

	public void onTestSuccess(ITestResult tr) {
		System.out.println("onTestSuccess bei: " + tr.getInstanceName() + " - " + tr.getName());
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
																							// information to the report
																							// with GREEN color
																							// highlighted
	}

	public void onTestFailure(ITestResult tr) {
		System.out.println("onTestFailure bei: " + tr.getInstanceName() + " - " + tr.getName());
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
																							// to the report with GREEN
																							// color highlighted

		String screenshotPath = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + tr.getName() + ".png";
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult tr) {
		System.out.println("onTestSkipped bei: " + tr.getInstanceName() + " - " + tr.getName());
		logger = extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {

		System.out.println("onFinish bei: " + testContext.getName());
	}

}
