package webpages;

import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.Log;
import Reusablecomponent.SeleniumCommon;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Homepage1 extends SeleniumCommon {

    public static void new_meeting(){
        SeleniumCommon.waitForSecond(5);

        WebElement ele=driver.findElement(By.xpath("//span[@class=\"nU false\"]//*[contains(text(),'New meeting')]"));
        SeleniumCommon.waitForSecond(3);
        ele.click();

        SeleniumCommon.switchtowindow();
        SeleniumCommon.maximizewindow();

        SeleniumCommon.waitForSecond(5);
        ExtentTestManager.reporterLog("Clicked On New meeting ");

        driver.findElement(By.xpath("//*[contains(text(),'Send invitation')]")).click();
        ExtentTestManager.reporterLog("Clicked on Send invitation");

        driver.findElement(By.xpath("//*[contains(text(),'Share via email')]")).click();
        ExtentTestManager.reporterLog("Clicked on Share via email");
        SeleniumCommon.waitForSecond(5);
        driver.close();

        SeleniumCommon.switchtowindow();
        driver.findElement(By.xpath("//textarea[@name=\"to\"]")).sendKeys("trunal.thakre@gmail.com");
        SeleniumCommon.waitForSecond(10);
        driver.findElement(By.xpath("//div[@id=\":oy\"]")).click();
        SeleniumCommon.waitForSecond(10);

        SeleniumCommon.switchtodifferenttab(0);


    }

    public static void chat(){
        driver.findElement(By.xpath("//a[@aria-label=\"Google apps\"]")).click();
        Log.info("Clicked on Google apps");
        ExtentTestManager.reporterLog("Clicked on Google apps");
        SeleniumCommon.waitForSecond(5);
        /*
        // explicit wait
       WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"CgwTDb\"] //span[@pid=\"385\"]")));
        driver.findElement(By.xpath("//div[@class=\"CgwTDb\"] //span[@pid=\"385\"]")).click();*/
        /*

        //fluent wait
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
              WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){

            public WebElement apply(WebDriver driver ) {
                return driver.findElement(By.xpath("//div[@class=\\\"CgwTDb\\\"] //span[@pid=\\\"385\\\"]"));
            }
        });
        //click on the selenium link
        clickseleniumlink.click();*/
        // switch to specific frame and click on chat option

        //switchtoframe by index
        SeleniumCommon.switchtoframebyindex(1);
        SeleniumCommon.waitForSecond(5);

       // WebDriverWait wait = new WebDriverWait(driver, 10);
        //WebElement chat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href=\\\"https://chat.google.com/?authuser=4\\\"]")));
        //chat.click();

        //driver.findElement(By.xpath("//ul/li[9]")).click();
       // driver.findElement(By.xpath("([//ul/li[9])[1]")).click();
        //driver.findElement(By.xpath("//div[@class=\"qWuU9c\"] //*[contains(text(),'Chat')]")).click();
        //driver.findElement(By.xpath("//a[@href=\"https://chat.google.com/?authuser=4\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"EHzcec eejsDc\"] //*[contains(text(),'Chat')]")).click();




        Log.info("Clicked on Chat");
        ExtentTestManager.reporterLog("Clicked On Chat");

        //switch to window
        SeleniumCommon.switchtowindow();

        //add explicit wait
        WebDriverWait wait1 = new WebDriverWait(driver,30);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Not now')]")));
        Log.info("Visible");
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Not now')]")));
        Log.info("Clikable");
        driver.findElement(By.xpath("//*[contains(text(),'Not now')]")).click();
        Log.info("Clicked on Not now");
        ExtentTestManager.reporterLog("Clicked On Not now");

        SeleniumCommon.waitForSecond(2);
        driver.findElement(By.xpath("//div[@aria-label=\"Start a chat\"]")).click();
        SeleniumCommon.waitForSecond(3);
        Log.info("Clicked on start a chat");
        ExtentTestManager.reporterLog("Clicked on start a chat");


        //switchtoframebyname
        SeleniumCommon.switchtoframebyidorname("talk_flyout");
        SeleniumCommon.waitForSecond(2);

        driver.findElement(By.xpath("//input[@class=\"whsOnd zHQkBf\"]")).sendKeys("trunal.thakre@gmail.com");
        SeleniumCommon.waitForSecond(5);
        Log.info("Sent Receiver emailId ");
        ExtentTestManager.reporterLog("Entered Email Of Receiver:trunal.thakre@gmail.com");
        driver.findElement(By.xpath("//span[@class=\"njhDLd\"]")).click();
        SeleniumCommon.waitForSecond(5);
        Log.info("Clicked on poonamghatage21@gmail.com from suggestion");
        ExtentTestManager.reporterLog("Clicked on trunal.thakre@gmail.com from suggestion");


        String currenturl=driver.getCurrentUrl();
        ExtentTestManager.reporterLog("Get Current URL");
        SeleniumCommon.waitForSecond(2);

        driver.get(currenturl);
        Log.info("Opened new tab");
        ExtentTestManager.reporterLog("URL Openend Again ");
        SeleniumCommon.waitForSecond(15);

        SeleniumCommon.switchtoframebyidorname("hostFrame1");

        SeleniumCommon.waitForSecond(5);
        WebElement elem=driver.findElement(By.xpath("//div[@aria-label=\"Message trunal.thakre@gmail.com. History is on.\"]"));
        elem.sendKeys("Hi,Good Evening");
        ExtentTestManager.reporterLog("Entered meesage");

        SeleniumCommon.waitForSecond(5);
        elem.sendKeys(Keys.ENTER);
        Log.info("Pressed Enter Key ");
        ExtentTestManager.reporterLog("Pressed Enter Key");
        SeleniumCommon.waitForSecond(2);

        SeleniumCommon.switchtowindow();
        driver.findElement(By.xpath("//button[contains(text(),\"No, thanks\")]")).click();
        Log.info("Clicked on No,thanks");
        ExtentTestManager.reporterLog("Clicked On No,thanks");
        driver.close();
        SeleniumCommon.switchtodifferenttab(0);


    }

    public static  void draft_move_into_inbox() {
        ExtentTestManager.reporterLog("Invalid Test case");

    }


    public static void LogOut() {

        SeleniumCommon.findElement("xpath", "//img[@class=\"gb_Ca gbii\"]").click();
        Log.info("Clicking on User name for Log out");
        ExtentTestManager.reporterLog("clicked on Profile Icon");
        SeleniumCommon.waitForSecond(2);
        SeleniumCommon.findElement("xpath", "(//*[contains(text(),'Sign out')])[2]").click();
        ExtentTestManager.reporterLog("Clicked On Signout Option");
        Log.info("Clicked on logout button");
        SeleniumCommon.waitForSecond(2);
        SeleniumCommon.driver.quit();
        Log.info("browser is closed successfully");
        ExtentTestManager.reporterLog("Browser closed");

    }



}
