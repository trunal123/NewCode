package testscript;

import org.testng.annotations.Test;
import webpages.Homepage1;

public class Homepage1test {

    @Test(description = "Verified new metting link shared or not",priority = 1,enabled = false)
    public void Homepage1_Testcase1(){
        Homepage1.new_meeting();

    }



    @Test(description ="Verified draft message moved into inbox or not",priority = 2,enabled = false)
    public  void Homepage1_Testcase2(){
        Homepage1.draft_move_into_inbox();
    }

    @Test(description = "verified that user can able to chat or not",priority = 3)
    public void Homepage1_Testcase3(){
        Homepage1.chat();
    }



    @Test(description = "verified Logout",priority = 4,enabled = false)
    public void Homepage1_Testcase4(){
        Homepage1.LogOut();
        }



}
