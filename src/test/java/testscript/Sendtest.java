package testscript;

import org.testng.annotations.Test;
import webpages.Drive;
import webpages.Send;

import java.io.IOException;

public class Sendtest {


    @Test(priority = 1, description = "Verified send mail functionality")
    public static void sendmail() throws IOException, InterruptedException {
        Send.sendmail();
    }
}


