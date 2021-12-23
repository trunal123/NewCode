package webpages;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.ExtentManager;
import Reusablecomponent.Listener;
import Reusablecomponent.Log;
import Reusablecomponent.PropertiesReading;
import Reusablecomponent.SeleniumCommon;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Loginpage extends SeleniumCommon {

    @FindBy(xpath = "//input[@id='identifierId']")
    public static WebElement email;
    @FindBy(xpath = "//input[@type='password']")
    public static WebElement passwd;
    @FindBy(xpath = "//div[text()='Use another account']")
    WebElement UseAnotherAccount;
    @FindBy(xpath = "//div[@class='VfPpkd-RLmnJb']")
    WebElement nextButton;
    @FindBy(xpath = "//img[@class='gb_uc']")
    WebElement gmailLogo;
    @FindBy(xpath = "//img[@class='gb_Da gbii']")
    WebElement userName;
    @FindBy(xpath = "//div[@class='gb_ob']")
    WebElement userEmail;



    public Loginpage() throws IOException  // Constructor to initialize Web elements using Page factory
    {

        driver = SeleniumCommon.getDriverInstance();
        Log.info("Chrome browser initiated");
        ExtentTestManager.reporterLog("Chrome Invoked");
        PageFactory.initElements(driver, this);
        ExtentTestManager.reporterLog("URL Opened");
    }


    public static void checktitle(){
        CompareAnyTitle("Gmail");

        ExtentTestManager.reporterLog("Title Checked");
    }
    public static void login() throws AWTException, IOException {
        String url = PropertiesReading.getProperty("Config", "baseURL");
        String username;
        String password;
        username = PropertiesReading.getProperty("Config", "qa_username");
        password = PropertiesReading.getProperty("Config", "qa_password");
        Log.info("Username: " + username);
        Log.info("Password: " + password);
        /*SeleniumCommon.findElement(By.email.sendKeys(username);*/

        SeleniumCommon.findElement("xpath", "//input[@id='identifierId']").sendKeys(username);
        SeleniumCommon.waitForSecond(2);
        ExtentTestManager.reporterLog("Email-ID Sent :" +username );
        /*passwd.click();*/
        SeleniumCommon.findElement("xpath", "//*[@id=\"identifierNext\"]").click();
        Log.info("Clicking on next Button");
        ExtentTestManager.reporterLog("Clicked On Next");

        SeleniumCommon.waitForSecond(2);
        SeleniumCommon.findElement("xpath", "//input[@type='password']").sendKeys(password);
        ExtentTestManager.reporterLog("Password Sent :" +password);
        Log.info("Entered username and password");
        SeleniumCommon.findElement("xpath", "//*[@id=\"passwordNext\"]").click();
        Log.info("Clicking on next Button");
        ExtentTestManager.reporterLog("Clicked on Next Button");

       /* try {

        WebDriverWait wait=new WebDriverWait(driver,30);
        if(wait.until(ExpectedConditions.alertIsPresent())==null)
        {
            System.out.println("Alert Not Present");
            Log.info("Alert Not Present");

            }
        else {
            driver.switchTo().alert().accept();
            driver.switchTo().alert().dismiss();
        }
        }catch (NoAlertPresentException a){

        a.printStackTrace();
        }/


      /*  SeleniumCommon.waitForSecond(5);
        driver.get("https://mail.google.com/mail/u/0/#inbox");
        Log.info("Again openend");
        SeleniumCommon.waitForSecond(5);
        driver.switchTo().frame(9);
        Log.info("Switched to frame");
        driver.findElement(By.xpath("//button[@aria-label=\"Close\"]")).click();
        Log.info("Closed Popup");*/

    }








}
