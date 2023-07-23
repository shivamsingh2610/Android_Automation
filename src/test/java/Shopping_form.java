import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class Shopping_form extends BaseClass{
    @Test()
    public void fillForm() throws InterruptedException, IOException {
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        Scroll("India");
        addScreemshot_with_somedetails("choose india from dropdown");
        driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.className("android.widget.EditText")).sendKeys("rahul");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        addScreemshot_with_somedetails("fill name on name field");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }
}
