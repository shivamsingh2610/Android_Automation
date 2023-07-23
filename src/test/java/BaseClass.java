import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class BaseClass {
    public static AndroidDriver driver;
    public static ExtentReports extent;
    public static ThreadLocal <ExtentTest> test= new ThreadLocal<>();

    public static ExtentTest logger() {
        return test.get();
    }
    public static  UiAutomator2Options options;

    @BeforeSuite
    public void Setup() throws MalformedURLException, InterruptedException {
        options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setDeviceName("andriod automation");
        //options.setApp("C:/Users/mrshi/IdeaProjects/Andriod_automate/app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        options.setApp("C:/Users/mrshi/IdeaProjects/Andriod_automate/app/General-Store.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(10000);
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("report/page.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Andriod Automation");
            spark.config().setReportName("shivam");
            extent.attachReporter(spark);
        }

        //driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        //driver.findElement(By.xpath("//*[text()='India]")).click();
    }

    public static String screenShot() throws IOException {
        Date d = new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screen/pic_" + d1 + ".png");
        FileUtils.copyFile(src, dest);
        String finalpath = dest.getAbsolutePath();
        return finalpath;
    }
    public static void addScreemshot() throws IOException {
        String screen = screenShot();
        logger().log(Status.INFO, "Screenshot of this page", MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
        //logger(.log(Status.INFO, MediaEntity.MediaEntityBuilder.<String>createScreenCaptureFromPath(screen).build());
    }

    public static void addScreemshot_with_somedetails(String details) throws IOException {
        String screen = screenShot();
        logger().log(Status.INFO, details, MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
        //logger(.log(Status.INFO, MediaEntity.MediaEntityBuilder.<String>createScreenCaptureFromPath(screen).build());
    }


    public static void Scroll(String scrollvalue)
    {
        driver.findElement
                (AppiumBy.androidUIAutomator
                        ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" +
                                 scrollvalue+
                                "\"));"));
    }

   @AfterSuite
    public void teardown()
    {
        driver.quit();
        extent.flush();
    }
}
