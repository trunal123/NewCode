package testscript;

import org.testng.annotations.Test;
import webpages.Drive;

import java.io.IOException;

public class Drive_Test {


    @Test(priority = 1,description = "Verified user can able to upload document on drive",enabled = false)
    public static void Testcase1_uploadfile() throws IOException {
        Drive.uploaddocument();
    }
    
    @Test(priority = 2,description = "Verified user can able to translate")
    public static void Testcase2_Translate() throws IOException {
        Drive.translate();
    }


}
