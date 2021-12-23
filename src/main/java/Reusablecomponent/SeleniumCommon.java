package Reusablecomponent;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static Reusablecomponent.Log.info;

public class SeleniumCommon {

    public static WebDriver driver = null;
    private static WebElement element;


    public static void launchBrowser() {


        initTest();  // invoke logger object and load config file in the property object

        try {
            if (driver == null) {
                String browser = PropertiesReading.getProperty("Config", "browser");

                if (browser.equals("Chrome")) {
                    info("Chrome invoked");


                    DesiredCapabilities cap = DesiredCapabilities.chrome();
                    //Accept the insecure connection, Accept SSL Certificate
                    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    ChromeOptions options = new ChromeOptions();
                    options.merge(cap);
                    System.setProperty("webdriver.chrome.driver", "src/Drivers/chromedriver.exe");
                    options.addArguments("ignore-certificate-errors");
//                    options.addArguments("--headless");
//                    options.addArguments("window-size=1366x768");
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    info("Chrome browser instantiated");


                } else if (browser.equals("Firefox")) {
                    System.setProperty("webdriver.firefox.driver",
                            PropertiesReading.getProperty("Config", "firefoxDriverPath"));
                    driver = new FirefoxDriver();
                    Log.getLogger().info("Firefox browser instantiated");
                } else if (browser.equals("IE")) {
                    System.setProperty("webdriver.ie.driver", PropertiesReading.getProperty("Config", "ieDriverPath"));
                    driver = new InternetExplorerDriver();
                    Log.getLogger().info("IE browser instantiated");
                }

                driver.manage().window().maximize();

            }
        } catch (Exception ex) {
            Log.getLogger().error(ex.getMessage());

        }

    }


    public static void openWebSite() throws IOException {

        String webURL = PropertiesReading.getProperty("Config", "baseURL");
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Log.getLogger().info("Implicit wait applied on the driver for 30 seconds");
            driver.get(webURL);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().refresh();

            info("Web application launched: " + driver.getCurrentUrl());
        } catch (Exception e) {
            info("Failed to launch the browser with:" + driver.getCurrentUrl());
        }

    }


    public static WebDriver getDriverInstance() {
        return driver;
    }

    public static void initTest() {
        Log.invokeLogger();
        info("Logger invoked");

    }


    public static void getPageLoadStatus() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pageLoadStatus = (String) js.executeScript("return document.readyState");
        Log.getLogger().info("Page Load Status:" + pageLoadStatus);

    }

    public static String getElementText(WebElement element) {
        String text = "";
       /* if (webElement != null) {
            if (webElement.isDisplayed()) {
                text = webElement.getText();
                Log.info("Text found on this locator: " + element);
                } else {
                Log.info("Failed to get the text on this locator: " + element);
            }
        }*/
        text = element.getText();
        return text;
    }

    public static void scrollToElement(WebElement element) {

        if (element != null) {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            Log.getLogger().info("Element not found");
        }
    }

    public static String GetCurrentPageTitle() {
        info("Current Page Title: " + driver.getTitle());
        return driver.getTitle();
    }


    public static void AssertCompare(String actualValue, String expectedValue) {
        Assert.assertEquals(actualValue, expectedValue, "Expected and Actual Values Matches");
    }

    public static void CompareAnyTitle(String expectedTitle) {
        String currentTitle = GetCurrentPageTitle();
        ExtentTestManager.reporterLog("Get Current Title:" + currentTitle);
        ExtentTestManager.reporterLog("Get Expected Title:" + expectedTitle);

        if (currentTitle.contentEquals(expectedTitle)) {
            info("Current Title Matches with Expected Title.");
            ExtentTestManager.reporterLog("Compared: Current Title Matches with Expected Title");
            ExtentTestManager.reporterLog("Completed");
        } else {
            Assert.fail("Test Case Failed");
            info("You are navigated to Wrong Title.");

            ExtentTestManager.reporterLog("Compared :navigated to Wrong Title");
        }


    }

    public static void RefreshCurrentPage() {
        driver.navigate().refresh();
        SeleniumCommon.waitForClosingLoader();
    }


    public static Boolean isElementExist(String locator_type, String element) {

        WebElement webElement;
        Boolean isExist = true;
        webElement = findElement(locator_type, element);
        if (webElement == null) {
            isExist = false;
        }

        return isExist;
    }

    public static WebElement findElement(String loctor_type, String locator_value) {
        WebElement webElement = null;
        By byLocator = null;

        try {

            info("Locator Type:- " + loctor_type + " Locator Value:-" + locator_value);

            if ("id".equals(loctor_type)) {
                byLocator = By.id(locator_value);
            } else if ("name".equals(loctor_type)) {
                byLocator = By.name(locator_value);
            } else if ("cssSelector".equals(loctor_type)) {
                byLocator = By.cssSelector(locator_value);
            } else if ("xpath".equals(loctor_type)) {
                byLocator = By.xpath(locator_value);

                // JavascriptExecutor je = (JavascriptExecutor) driver;
                // je.executeScript("arguments[0].scrollIntoView(true);", webElement);
            } else if ("className".equals(loctor_type)) {
                byLocator = By.className(locator_value);
            } else if ("linkText".equals(loctor_type)) {
                byLocator = By.linkText(locator_value);
            } else if ("partialLinkText".equals(loctor_type)) {
                byLocator = By.partialLinkText(locator_value);
            } else if ("tagName".equals(loctor_type)) {
                byLocator = By.tagName(locator_value);
            } else {
                info("Locator type does not match" + byLocator);
            }

            info("Locator: " + byLocator);
            getPageLoadStatus();
            long startTime = System.currentTimeMillis() / 1000;

            waitForElement().until(ExpectedConditions.visibilityOfElementLocated(byLocator));

            if (driver.findElement(byLocator).isDisplayed()) {
                webElement = driver.findElement(byLocator);
                info("Element found:-" + byLocator);

            } else {
                info("Element not displayed on this web page by " + byLocator);
            }

            long endTime = System.currentTimeMillis() / 1000;
            Log.getLogger().info("Time:" + (endTime - startTime) + " seconds");

        } catch (Exception e) {
            info("Locator Type:- " + loctor_type + " Locator Value:-" + locator_value);
            info("No element found on web page by " + byLocator);
            Log.error("Error occured ...");
            e.printStackTrace();

        }

        return webElement;
    }

    public static WebDriverWait waitForElement() {

        WebDriverWait wait = new WebDriverWait(driver, 60);
        return wait;
    }

    //Apply Implicite Wait
    public static void wait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    }

    //Hardcoded Sleep
    public static void waitForSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //Convert string into Integer
    public static int ConvertStringToInteger(String string) {
        String str1 = string;
        int ConvertedIntValue = Integer.parseInt(str1);
        return ConvertedIntValue;
    }

    public static double ConvertStringToDouble(String string) {
        String str1 = string;
        double ConvertedDoubleValue = Double.parseDouble(str1);
        return ConvertedDoubleValue;
    }

    //Get Element from Object Repository
    public static WebElement getElement(String object) {
        String locatorType = object.split(":")[1];
        String locatorValue = object.split(":")[1];

        if (locatorType.contentEquals("xpath")) {
            System.out.println(object);
            element = driver.findElement(By.xpath(locatorValue));
        }


        return element;
    }


    public static int GetCountFromTotal(String PageTotalCount) {
        String Count = StringUtils.substringBetween(PageTotalCount, "of ", " entries");
        int cnt = ConvertStringToInteger(Count);
        return cnt;
    }


    //Explicit wait method to wait till visibility of element
    public static void waitForWebElement(WebElement element) {
        WebDriverWait w = new WebDriverWait(driver, 30);
        w.until(ExpectedConditions.visibilityOf(element));
        info("Element Visible");
        w.until(ExpectedConditions.elementToBeClickable(element));
        info("Element Clickable");
    }

    //MouseHoverAction

    public static void MouseHoverAction(WebElement element, String elementName) {
        //Instantiate Action Class
        Actions actions = new Actions(driver);

        //Mouse hover on element
        actions.moveToElement(element);
        info("Mouse Hovered on Element- " + elementName);
    }

    //Get Last Modified File
    public static File getLastModified() {
        File directory = new File("C:\\Users\\perennial\\Downloads");
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;

    }

    public static void waitForClosingLoader() {
        WebDriverWait w = new WebDriverWait(driver, 90);
        w.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='/GspModel/resources/support/images/logo-in-circle.png']")));
    }

    public static void MoveToElement(WebElement element) {
        Actions WebDriverActions = new Actions(driver);
        WebDriverActions.moveToElement(element);


    }

    public static void IsElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static void toCheckTextIsPresent(String TextToCheck) throws AWTException {
        driver.getPageSource();
        SeleniumCommon.waitForSecond(2);
        if (driver.getPageSource().contains(TextToCheck)) {
            info("Pening records are present. Check error message- " + TextToCheck);
            SeleniumCommon.waitForSecond(2);
//            SeleniumCommon.MoveToElement(loc.YesBtnOnDiscardPopUp);
//            loc.YesBtnOnDiscardPopUp.click();
            SeleniumCommon.ClickEnter();
            SeleniumCommon.ClickEnter();
            info("Pending Invoices are Discarded successfully");

        } else {
            info("No pending records are present.");

        }
    }

    public static void ClickOnWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
        ExtentTestManager.reporterLog("Verified is button visible");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ExtentTestManager.reporterLog("Verified is button clickable");
        MoveToElement(element);
        ExtentTestManager.reporterLog("Moved cursor on Button");
        element.click();
        ExtentTestManager.reporterLog("Clicked");

    }

    public static void ClickEnter() throws AWTException {
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    //Pass WebElement To Select and What to Select as string
    public static void HandleAnyDropdown(WebElement element, String TemplateTypeSelect) throws Exception {
        Select StateInGenerateEWBDropDown = new Select(element);
        StateInGenerateEWBDropDown.selectByVisibleText(TemplateTypeSelect);
        info("Dropdown is selected");
    }

    public static void RefreshPage() {
        driver.navigate().refresh();
        SeleniumCommon.waitForClosingLoader();
    }

    public static boolean input(WebElement element, String str) {
        boolean flag = false;
        try {
            element.isDisplayed();
            element.clear();
            element.sendKeys(str);
            flag = true;
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag = true) {
                info("Element found");
            } else {
                info(" Element not found");
            }
        }
        return flag;
    }

    //Compare Page Title
    public static void ComparePageTitle(String expectedTitle) {
        if (GetCurrentPageTitle().contentEquals(expectedTitle)) {
            Log.info("Page title is equal to expected title.");
        } else
            Log.info("Page title is not equal to expected title.");
    }

    public static void comparetext(WebElement webele, String expect) {

        String actualtext = webele.getText();
        System.out.println("Actual Text:" + actualtext);
        ExtentTestManager.reporterLog("Get Actual Text:" + actualtext);

        String expected = expect;
        ;
        ExtentTestManager.reporterLog("Get Expected Text: " + expected);

        if (expected.equals(actualtext)) {
            System.out.println("Pass");
            ExtentTestManager.reporterLog("Text Compared:Correct");

        } else {
            Assert.fail("Test Case Failed");
            System.out.println("Fail");
        }
    }

    public static void getEnteredvalue(WebElement element1, String expect) {

        String actualvalue = element1.getAttribute("value");
        System.out.println("Actual Value:" + actualvalue);
        ExtentTestManager.reporterLog("Get Actual Value:" + actualvalue);

        String expectedvalue = expect;
        ExtentTestManager.reporterLog("Get Expected Value: " + expectedvalue);


        if (expectedvalue.equals(actualvalue)) {
            System.out.println("Pass");
            ExtentTestManager.reporterLog("value Compared:Correct");

        } else {
            Assert.fail("Test Case Failed");
            System.out.println("Fail");
        }
    }

    //Clear Text box
    public static void ClearTextBoxForAnyComponent(WebElement element){
        if(element.isDisplayed() && element.isEnabled())
            element.clear();
    }
    //Send Keys
    public static void SendKeysToAnyComponent(WebElement element,String stringToSend){
        if(element.isDisplayed() && element.isEnabled())
            element.sendKeys(stringToSend);
    }

    //Compare Content Equals method
    public static void CompareExpectedStringValueContentEquals(String CompareFrom, String CompareWith){
        if(CompareFrom.contentEquals(CompareWith))
            Log.info("Compare Successful for "+CompareFrom+" and "+CompareWith);
        else Log.info("Comparison not successful...!!!");
    }

    public static void verfify_checkbox(WebElement checkbox) {
        //first check is it seleceted or not?
        System.out.println("The checkbox is selection state is - " + checkbox.isSelected());
        if (!checkbox.isSelected()) {
            ExtentTestManager.reporterLog(checkbox.isSelected() + " :Not Already Selected");
            checkbox.click();
            ExtentTestManager.reporterLog("Checkbox Selected");
        } else {
            //if check box is already selected then it will be deselect
            checkbox.click();
            ExtentTestManager.reporterLog("Checkbox Selected");
        }

    }
    public static void close()
    {
        driver.close();
    }

    /*public static String screenShot(String filename)
    {
        String dateName= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takescreenshot=(TakesScreenshot) driver;
        File source=takescreenshot.getScreenshotAs(OutputType.FILE);
        String destination=System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return destination;
    }*/
    public static void clickOnWebElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        MoveToElement(element);
        element.click();

    }



    //Methods

    public static void check_dropdown(WebElement w1, WebElement w2) {
        SeleniumCommon.waitForSecond(10);
        SeleniumCommon.ClickOnWebElement(w1);
        //ExtentTestManager.reporterLog("Click On Menu");

        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@class='J-N-Jz']"));
        SeleniumCommon.waitForSecond(10);
        System.out.println(allOptions.size());
        ExtentTestManager.reporterLog("Number of Item In List:" + allOptions.size());

        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().contains("Unread")) {
                allOptions.get(i).click();
                ExtentTestManager.reporterLog("Clicked on Expected option");
                SeleniumCommon.waitForSecond(5);
                break;
            }
        }

    }

    public static void tooltip(WebElement element, String expectedTooltip) {
        SeleniumCommon.waitForSecond(5);
        ExtentTestManager.reporterLog("Expected Tooltip:" + expectedTooltip);
        Actions builder = new Actions(driver);
        builder.clickAndHold().moveToElement(element);
        ExtentTestManager.reporterLog("Cursor Moved on Element");
        builder.moveToElement(element).build().perform();
        SeleniumCommon.waitForSecond(10);
        String actualTooltip = element.getText();
        ExtentTestManager.reporterLog("Got Actual Text:" + actualTooltip);


        System.out.println("Actual Title of Tool Tip  " + actualTooltip);
        if (actualTooltip.equals(expectedTooltip)) {
            System.out.println("Test Case Passed");
            ExtentTestManager.reporterLog("Compared:Actual text matched with expected text");
        } else {
            ExtentTestManager.reporterLog("Compared:Actual text not matched with expected text");
            Assert.fail();
            ExtentTestManager.reporterLog("Fail");
        }
    }

    public static void verify_image(WebElement w1) {
        if (w1.isDisplayed()) {
            ExtentTestManager.reporterLog("Image Displayed");
        } else {
            Assert.fail();
            ExtentTestManager.reporterLog("Image Not Displayed");
        }

    }

    public static void openlinkinnewtab(String link, String expect_title) {
        SeleniumCommon.waitForSecond(5);
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        driver.findElement(By.linkText(link)).sendKeys(selectLinkOpeninNewTab);
        ExtentTestManager.reporterLog("Link Opened in new tab");
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(1));
        SeleniumCommon.waitForSecond(5);
        ExtentTestManager.reporterLog("Switched to active tab");
        SeleniumCommon.CompareAnyTitle(expect_title);
        SeleniumCommon.waitForSecond(5);
        //driver.close();
        //driver.quit();

        driver.switchTo().window(allTabs.get(0));
        ExtentTestManager.reporterLog("Switched to Base URL");
        SeleniumCommon.waitForSecond(5);
        //driver.close();
        //driver.quit();

    }

    public static void Verify_list() {
        SeleniumCommon.waitForSecond(5);
        WebElement select = driver.findElement(By.xpath("//span[contains(text(), 'Create account')]"));
        ExtentTestManager.reporterLog("Element found");
        select.click();
        SeleniumCommon.waitForSecond(5);
        List<WebElement> allOptions = select.findElements(By.tagName("li"));
        ExtentTestManager.reporterLog("tag found");
        SeleniumCommon.waitForSecond(5);

        for (WebElement option : allOptions) {
            System.out.println("start");
            System.out.println(String.format("Value is: %s", option.getAttribute("value")));
            String vaLue = "For myself";
            if (option.getAttribute("value").equals(vaLue)) {
                option.click();
                ExtentTestManager.reporterLog("Option present");
                //
                //  ExtentTestManager.reporterLog("Clicked on option For myself");
                System.out.println("Pass");
            } else {
                System.out.println("fail");
            }
        }
    }

    public static void clear(WebElement w1) {
        w1.clear();

    }

    //switchtoframe by index
    public static void switchtoframebyindex(int index) {
        driver.switchTo().frame(index);
        info("Switched to frame");
    }

    //switchtoframe by id or name
    public static void switchtoframebyidorname(String idorname) {
        driver.switchTo().frame(idorname);
        info("Switched to frame");
    }

    //switch to the particular tab by getting index
    public static void switchtodifferenttab(int index) {
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(index));
    }

    //verify  alert is present or not
    //not used........
    public static void alertispresentornot() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, 30);
            if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
                System.out.println("Alert Not Present");
                info("Alert Not Present");

            } else {
                driver.switchTo().alert().accept();
                driver.switchTo().alert().dismiss();
            }
        } catch (NoAlertPresentException a) {
            a.printStackTrace();
        }

    }

    //switch to window
    public static void switchtowindow() {
        try {
            SeleniumCommon.waitForSecond(5);
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
                info("Switched to window");
                info("Return:" + winHandle);
            }
        } catch (Exception e) {
        }
    }

    //maximize window
    public static void maximizewindow() {
        driver.manage().window().maximize();
    }

    //scroll down
    public static void scroll_down(int vertical) {
        int v = vertical;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,v)");
        info("Scrollled Down");
    }

    public String GetToolTip(WebElement element) {
        String tooltipText = element.getAttribute("title");
        return tooltipText;
    }

    //Scroll down till end of the page.
    public void ScrollDown() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        info("Window is scrolled to down of page.");
    }

    //Scroll Up till Title of the page.
    public void ScrollUp() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        info("Windows is scrolled to up of page.");

    }

    public static boolean JSClick(WebElement element) throws Throwable
    {
        boolean flag=false;
        try {
            JavascriptExecutor executor=(JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click(),", element);
            flag=true;

        }
        catch(Exception e)
        {
            flag=false;
            throw e;

        }
        finally {
            if(flag)
            {
                System.out.println("JSClick Performed");

            }
            else {
                System.out.println("JSClick not Performed ");
            }
        }
        return flag;

    }


    public static List<String> convertListOfWebElementToString(List<WebElement> element)
    {
        List<String> strings = new ArrayList<String>();
        for(WebElement e : element){
            strings.add(e.getText());
        }
        return strings;
    }

    public static boolean findElement(WebDriver driver, WebElement element)
    {
        boolean flag=false;
        try {
            element.isDisplayed();
            flag=true;

        }
        catch(Exception e) {
            flag=false;
        }
        finally {
            if(flag=true) {
                Log.info("Element"+element.getText()+"Displyed");
            }
            else {
                Log.info("Element"+element.getText()+"not Displyed");
            }
        }
        return flag;
    }





    public static boolean isDisplayed(WebElement element)
    {
        boolean flag=false;
        flag=findElement(driver,element);
        if(flag)
        {
            flag=element.isDisplayed();
            if(flag)
            {
                System.out.println("The element is Displayed");
            }
            else
            {
                System.out.println("The element is not Displayed");
            }

        }
        else
        {
            System.out.println("not Displayed");
        }
        return flag;

    }

    public static boolean isSelected(WebDriver driver, WebElement element)
    {
        boolean flag=false;
        flag=findElement(driver,element);
        if(flag)
        {
            flag=element.isSelected();
            if(flag)
            {
                System.out.println("The element is Selected");
            }
            else
            {
                System.out.println("The element is not Selected");
            }

        }
        else
        {
            System.out.println("not Selected");
        }
        return flag;

    }

    public static boolean isEnabled(WebDriver driver, WebElement element)
    {
        boolean flag=false;
        flag=findElement(driver,element);
        if(flag)
        {
            flag=element.isEnabled();
            if(flag)
            {
                System.out.println("The element is Enabled");
            }
            else
            {
                System.out.println("The element is not Enabled");
            }

        }
        else
        {
            System.out.println("not Enabled");
        }
        return flag;

    }
    public static boolean selectByIndex(WebElement element, int index)
    {
        boolean flag=false;
        try {
            Select s =new Select(element);
            s.selectByIndex(index);
            flag=true;

        }
        catch(Exception e)
        {

            flag=false;
        }
        finally {
            if(flag)
            {
                System.out.println("option selected by indexe");

            }
            else {
                System.out.println("option not selected by index ");
            }
        }
        return flag;

    }

    public static boolean selectByValue(WebElement element, String value)
    {
        boolean flag=false;
        try {
            Select s =new Select(element);
            s.selectByValue(value);
            flag=true;

        }
        catch(Exception e)
        {

            flag=false;
        }
        finally {
            if(flag)
            {
                System.out.println("option selected by Value");

            }
            else {
                System.out.println("option not selected by Value ");
            }
        }
        return flag;

    }


    public static boolean selectByVisibleText(WebElement element, String text) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByVisibleText(text);
            flag = true;

        } catch (Exception e) {

            flag = false;
        } finally {
            if (flag) {
                System.out.println("option selected by VisibleText");

            } else {
                System.out.println("option not selected by VisibleText ");
            }
        }
        return flag;

    }

    }
