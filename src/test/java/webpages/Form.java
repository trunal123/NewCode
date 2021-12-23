package webpages;

import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.Log;
import Reusablecomponent.SeleniumCommon;
import org.openqa.selenium.By;

public class Form extends SeleniumCommon{

    public  static  void submitform(){

        driver.findElement(By.xpath("//a[@href=\"https://www.google.co.in/intl/en-GB/about/products?tab=mh\"]")).click();
        Log.info("Clicked on Google apps");
        ExtentTestManager.reporterLog("Clicked on Google apps");
        SeleniumCommon.waitForSecond(3);

        //switchto frame by passing index
        SeleniumCommon.switchtoframebyindex(1);
        System.out.println("Switched to different frame");
        ExtentTestManager.reporterLog("Switched to Frame");

        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//span[contains(text(),\"Forms\")]")).click();
        ExtentTestManager.reporterLog("Clicked On Forms");
        Log.info("Clicked On Forms");

        //switchtodifferenttab by passing index
        SeleniumCommon.switchtodifferenttab(1);
        ExtentTestManager.reporterLog("Switched to Google Forms Tab");
        Log.info("Switched to Google Forms Tab");

        //click on Contact Information Form
        driver.findElement(By.xpath("//img[@src=\"https://ssl.gstatic.com/docs/templates/thumbnails/1xQF3s6EP0d58H-XJ7R440OpREKo4KqEapa0mkw43RPE_400.png\"]")).click();
        ExtentTestManager.reporterLog("Clicked On Contact Information Form");
        Log.info("Clicked on Contact Information Form");

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScjnmUCoQQb_DC9_6UOdwD-xc5DZkUP0-3yjd9y5PpdcOQRmA/viewform?vc=0&c=0&w=1&flr=0");
        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("poonamghatage21@gmail.com");
        SeleniumCommon.waitForSecond(5);
        driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("89757210787656789");
        SeleniumCommon.waitForSecond(2);
        driver.findElement(By.xpath("//span[contains(text(),\"Submit\")]")).click();

    }


}
