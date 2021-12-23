package testscript;


import Reusablecomponent.ExtentTestManager;
import Reusablecomponent.SeleniumCommon;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webpages.Homepage;
import webpages.Homepage1;
import webpages.Loginpage;

import java.awt.*;
import java.io.IOException;

@Test
public class LoginTest {



    @BeforeClass
    public void beforeClass() throws IOException {
        SeleniumCommon.launchBrowser();
        SeleniumCommon.openWebSite();

    }
    @Test(priority = 1,description = "verified valid user can able to login or not",timeOut = 120000)
    public void Login_Testcase1() throws IOException, AWTException {

        Loginpage.checktitle();
        ExtentTestManager.reporterLog("First Checked Title");
        Loginpage.login();
        ExtentTestManager.reporterLog("Logged in successfully");
        SeleniumCommon.waitForSecond(5);
        System.out.println("First Test Case Completed");


    }

}



