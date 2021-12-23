package webpages;

import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.Log;
import Reusablecomponent.SeleniumCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Send extends SeleniumCommon {
    public static void sendmail() throws InterruptedException {
        String ActualTitle=driver.getTitle();
        Log.info("Actual title:" +ActualTitle);
        String ExceptedTitle = "Inbox - ouchdemo1@gmail.com - Gmail";
        ExtentTestManager.reporterLog("Expected Title:" + ExceptedTitle);
        ExtentTestManager.reporterLog("Actual Title:" +ActualTitle);
        Log.info("Title verified");
        ExtentTestManager.reporterLog("Step 1 Pass");
        driver.findElement(By.xpath("//div[@class = 'T-I T-I-KE L3']")).click();
        Log.info("Click on Compose button");
        SeleniumCommon.switchtodifferenttab(0);
        String ActualTitle1 = driver.getTitle();
        ExtentTestManager.reporterLog("ActualTitle"+ActualTitle1);
        String ExceptedTitle1 = "Inbox - ouchdemo1@gmail.com - Gmail";
        ExtentTestManager.reporterLog("Expected Title:"+ExceptedTitle1);
        if (ActualTitle1.equals(ExceptedTitle1)) {
            Log.info("Open New mail");
            ExtentTestManager.reporterLog("New mail");
        } else {
            // Assert.fail();
            ExtentTestManager.reporterLog("No New mail");
            Log.info("No new Email");
        }
        SeleniumCommon.waitForSecond(5);
        Log.info("Enter Receiver mail ID trunal.thakre@gmail.com");
        SeleniumCommon.waitForSecond(5);
        WebElement E1 = driver.findElement(By.xpath("//div[@class = 'fX aiL']"));
        SeleniumCommon.waitForWebElement(E1);
        E1.sendKeys("trunal.thakre@gmail.com");
        ExtentTestManager.reporterLog("Receiver mailid");
        Log.info("Enter Subject Testmail");
        driver.findElement(By.xpath("//div[@id = ':9']")).sendKeys("TestMail");
        ExtentTestManager.reporterLog("Subject TestMail");
        Log.info("Descrition send");
        driver.findElement(By.xpath("//div[@id = ':aq']")).sendKeys("Testing mail");
        ExtentTestManager.reporterLog("Description content");
        Log.info("Click on Send Buttom");
        driver.findElement(By.xpath("//div[@id = ':9a']")).click();
        ExtentTestManager.reporterLog("Mail send");


    }
}
