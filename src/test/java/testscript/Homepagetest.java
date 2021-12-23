package testscript;

import Reusablecomponent.SeleniumCommon;
import org.testng.annotations.Test;
import webpages.Homepage;

import java.awt.*;

public class Homepagetest {


    @Test(priority = 1,description = "Verify Correct User Logged In or Not")
    public void HomePage_Testcase1(){
        SeleniumCommon.waitForSecond(5);
        Homepage.verifyUser();
    }

    @Test(priority = 2,description = "Verified user can abe to search email ID")

    public void HomePage_Testcase2() throws AWTException {
        Homepage.searchByID();
    }

    @Test(priority = 3,description = "verified user can able to open searched emailId")
    public void Homepage_Testcase3() throws AWTException {
        Homepage.checkMail();
    }


   @Test(priority = 4,description = "verified icon is displayed or not",enabled = false)
    public void Homepage_Testcase4() throws AWTException {
    Homepage.verifyicon();
    }

    @Test(priority = 5,description = "verified tooltip is displayed as per expectation or not",enabled = false)
    public void Homepage_Testcase5() throws AWTException {
        Homepage.veriftooltip();
    }

    @Test(priority = 6,description = "verified dropdown",enabled = false)
    public void Homepage_Testcase6() throws AWTException {
   Homepage.verifydropdown();
    }




}


