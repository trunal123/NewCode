package testscript;

import org.testng.annotations.Test;
import webpages.Drive;
import webpages.Form;

import java.io.IOException;

public class FormTest {

    @Test(priority = 1,description = "Verified user can able Submit the form")
    public static void Testcase1_SubmitForm() throws IOException {
        Form.submitform();
    }
}
