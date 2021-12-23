package webpages;

import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.Log;
import Reusablecomponent.SeleniumCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.awt.*;
import java.util.ArrayList;

public class Homepage extends SeleniumCommon {

    public static void verifyUser() {
        //driver.findElement(By.xpath("hgfhgfhgh"));

        String ActualTitle=driver.getTitle();
        Log.info("Actual title:" +ActualTitle);
        String ExceptedTitle = "Inbox - ouchdemo1@gmail.com - Gmail";
        ExtentTestManager.reporterLog("Expected Title:" + ExceptedTitle);
        ExtentTestManager.reporterLog("Actual Title:" +ActualTitle);

        if (ActualTitle.equals(ExceptedTitle)) {
            Log.info("user logged in  is matching");
            ExtentTestManager.reporterLog("Compared:current Title Matches with Expected Title");
            ExtentTestManager.reporterLog("User Verified Successfully");
        } else {

            ExtentTestManager.reporterLog("Compared:current Title Not Matches with Expected Title");
            Assert.fail();
           // ExtentTestManager.reporterLog("Compared:current Title Not Matches with Expected Title");
            Log.info("user logged in is matching");
        }

    }

    public static void searchByID() throws AWTException {
        WebElement w1= driver.findElement(By.xpath("//input[@placeholder=\"Search mail\"]"));
        w1.sendKeys("trunal.thakre@gmail.com");
        w1.sendKeys(Keys.ENTER);
        ExtentTestManager.reporterLog("Entered EmailId For search: trunal.thakre@gmail.com");
        Log.info("Searched by Id trunal.thakare@gmail.com");
        ExtentTestManager.reporterLog("Clicked On Enter");
    }

    public static void checkMail() throws AWTException {

        SeleniumCommon.waitForSecond(2);
        SeleniumCommon.findElement("xpath", "//div[@class=\"au\"] //div[@class=\"av\"]").click();
        ExtentTestManager.reporterLog("Clicked on Email");
        SeleniumCommon.waitForSecond(3);
        SeleniumCommon.findElement("xpath", "//a[@href=\"https://www.flickonclick.com/checkout-these-exciting-latest-offers-from-jiomart/\"]").click();
        ExtentTestManager.reporterLog("Clicked on link available in the mail");
        Log.info("Click on link available in the mail");

       SeleniumCommon.switchtodifferenttab(1);
        String ActualTitle1 = driver.getTitle();
        ExtentTestManager.reporterLog("ActualTitle"+ActualTitle1);
        String ExceptedTitle1 = "Checkout These Exciting Latest Offers From JioMart";
        ExtentTestManager.reporterLog("Expected Title:"+ExceptedTitle1);
        if (ActualTitle1.equals(ExceptedTitle1)) {
            Log.info("link is matching");
            ExtentTestManager.reporterLog("Link Matched");
        } else {
            // Assert.fail();
            ExtentTestManager.reporterLog("Link Not Matched");
            Log.info("link is not matching");
        }
        SeleniumCommon.waitForSecond(5);
        SeleniumCommon.switchtodifferenttab(0);
       // driver.switchTo().window(allTabs.get(0));
        ExtentTestManager.reporterLog("Navigated to First Tab");
        SeleniumCommon.waitForSecond(5);
        //driver.findElement(By.xpath("//span[@class=\"nU n1\"] //*[contains(text(),'Inbox')]")).click();
        driver.findElement(By.xpath("//a[@href = \"https://mail.google.com/mail/u/0/#inbox\"]")).click();
        ExtentTestManager.reporterLog("Clicked on Inbox");

    }


    public static void verifydropdown() {

        //xpath=parent+space+child

        WebElement w1=driver.findElement(By.xpath("//div[@class=\"J-J5-Ji J-JN-M-I-Jm\"] //div[@class=\"G-asx T-I-J3 J-J5-Ji\"]"));
        SeleniumCommon.waitForSecond(2);
        WebElement w2=driver.findElement(By.xpath("//div[@selector='unread']"));
        SeleniumCommon.check_dropdown(w1,w2);

    }

    public static void veriftooltip() {
        //WebElement w1=driver.findElement(By.xpath("//span[@class=\"nU n1\"] //*[contains(text(),'Inbox')]"));//xpath=parent+space+child
        WebElement w1=driver.findElement(By.xpath("//a[@href = \"https://mail.google.com/mail/u/0/#inbox\"]"));
        SeleniumCommon.waitForSecond(5);
        String expected="Inbox";
        SeleniumCommon.tooltip(w1,expected);
    }


    public static void verifyicon() {
        WebElement element=driver.findElement(By.xpath("//div[@class=\"aKp aKe-aLf\"]"));
        SeleniumCommon.verify_image(element);
    }
}
