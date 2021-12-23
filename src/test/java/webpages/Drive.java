package webpages;

import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.Log;
import Reusablecomponent.SeleniumCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Drive extends SeleniumCommon {



    public static void uploaddocument() throws IOException {
        driver.findElement(By.xpath("//a[@aria-label=\"Google apps\"]")).click();
        Log.info("Clicked on Google apps");
        ExtentTestManager.reporterLog("Clicked on Google apps");
        SeleniumCommon.waitForSecond(3);
        //switchto frame by passing index
        SeleniumCommon.switchtoframebyindex(1);
        System.out.println("Switched to different frame");
        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//span[contains(text(),\"Drive\")]")).click();
        ExtentTestManager.reporterLog("Clicked On Drive");

        //switchtodifferenttab by passing index
        SeleniumCommon.switchtodifferenttab(1);


        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//button[@guidedhelpid=\"new_menu_button\"]")).click();
        ExtentTestManager.reporterLog("Clicked On New");
        driver.findElement(By.xpath("//div[@aria-label=\"File upload\"]")).click();
        ExtentTestManager.reporterLog("Clicked On File upload");
        Runtime.getRuntime().exec("E:\\Myproject\\fileupload.exe");
        Log.info("Image uploaded");
        driver.close();

    }

    public static void translate() throws IOException {
        //driver.findElement(By.xpath("//a[@aria-label=\"Google apps\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"https://www.google.co.in/intl/en-GB/about/products?tab=mh\"]")).click();

        Log.info("Clicked on Google apps");
        ExtentTestManager.reporterLog("Clicked on Google apps");
        SeleniumCommon.switchtoframebyindex(1);
        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//span[contains(text(),'Translate')]")).click();
        Log.info("Clicked on Translate");
        SeleniumCommon.waitForSecond(5);

        SeleniumCommon.switchtodifferenttab(1);
        Log.info("Switched to Google Translate tab");
        SeleniumCommon.waitForSecond(15);

        //parent+space+child xpath
       //WebElement e1=driver.findElement(By.xpath("//span[@class=\"VfPpkd-AznF2e-LUERP-bN97Pc\"] //button[@id=\"i9\"]"));
      // SeleniumCommon.waitForWebElement(e1);
       //e1.click();
      // Log.info("Clicked on ENGLISH");


       WebElement w=driver.findElement(By.xpath("//textarea[@aria-label=\"Source text\"]"));
       SeleniumCommon.waitForWebElement(w);
       SeleniumCommon.waitForSecond(5);
       w.sendKeys("I can do");
       Log.info("Text Entered");
       SeleniumCommon.waitForSecond(5);
       driver.findElement(By.xpath("//button[@aria-label=\"More target languages\"]")).click();
       Log.info("Clicked on Down Drop Down Arrow");
       SeleniumCommon.waitForSecond(10);

        //WebElement w2=driver.findElement(By.xpath("(//input[@aria-label=\"Search languages\"])[1]"));
        //WebElement w2=driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[3]/c-wiz/div[2]/div/div[2]/input"));
        WebElement w2=driver.findElement(By.xpath("//div[@class=\"hRFt4b\"] //div[@class=\"ykTHSe\"] //div[@class=\"pEyuac X4hZJc\"] //div[@class=\"fMHXgc qkH7ie\"] //input[@class=\"yFQBKb\"]  "));
        SeleniumCommon.waitForWebElement(w2);
        w2.sendKeys("Marathi");
        w2.sendKeys(Keys.ENTER);
        Log.info("Text Entered");
        SeleniumCommon.waitForSecond(5);
        WebElement w3=driver.findElement(By.xpath("//span[@jsname=\"qKMVIf\"]"));
        System.out.println("Character Count:" +w3.getText());
        driver.close();
        SeleniumCommon.switchtodifferenttab(0);
    }


}
