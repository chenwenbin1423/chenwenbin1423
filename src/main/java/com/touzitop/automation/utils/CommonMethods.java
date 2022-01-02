package com.touzitop.automation.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import com.touzitop.automation.config.TestConfig;

//import cucumber.api.java.en.Then;

public class CommonMethods {

    private static Long runTimeSpeed;

    static {
        runTimeSpeed = null;

        try {
            runTimeSpeed = Long.parseLong(TestConfig.valueFor("runTimeSpeed"));
        } catch (Exception e) {
            logInfo("Failed to parse runTimespeed", e.getMessage());
        }
    }

    private static final String messageString = " Input: %s ~ Output: %s";

    /**
     * Logger - Common Methods
     */
    public static void logInfo(String status, String message) {
        CustomLogger.publish(Level.INFO, "", "", status, message);
    }

    public static void logDebug(String classname, String methodname, String input, String output) {
        CustomLogger.publish(Level.DEBUG, classname, methodname, "", String.format(messageString, input, output));
    }

    public static void logDebug(String classname, String methodname, String input, boolean output) {
        CustomLogger.publish(Level.DEBUG, classname, methodname, "", String.format(messageString, input, output));
    }

    public static void logDebug(String classname, String methodname, String input, int output) {
        CustomLogger.publish(Level.DEBUG, classname, methodname, "", String.format(messageString, input, output));
    }

    public static void logTestName(String testname, Collection<String> tags, long elapsedTime) {
        CustomLogger.publishTestsExecuted(testname, tags, elapsedTime);
    }

    /**
     * All Elements - Common Methods
     */

    public static void selectElementbyvisibletext(WebElement element, String text) throws Exception {

        Thread.sleep(runTimeSpeed);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public static boolean isElementEnabled(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return element != null && element.isEnabled();
    }

    public static boolean isElementDisplayed(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return element != null && refreshIfStale(element).isDisplayed();
    }

    public static void tabFromElement(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        refreshIfStale(element).sendKeys(Keys.TAB);
        //refreshIfStale(element).sendKeys(Keys.ENTER);
    }

    public static String getTextFromElement(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).getText();
    }

    public static String getClassAttribute(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).getAttribute("class");
    }

    public static String getIdAttribute(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).getAttribute("id");
    }

    public static WebElement findParentOfWebElement(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).findElement(By.xpath("parent::*"));
    }

    public static List<WebElement> getAllChildElements(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).findElements(By.xpath("*"));
    }

    public static void click(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        try {
            /*WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submitButton")));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();*/
            refreshIfStale(element).click(); //can throw WebDriverException: Element is not clickable at point
        } catch (WebDriverException wx) {
            refreshIfStale(element).click();
        }
    }

    /**
     * For stability - on occasion web elements become stale (resulting most
     * likely from react DOM redraws) the call to isEnabled should throw the
     * StaleElementException if the element has detached from the DOM in which
     * case search again.
     *
     * @param webElement
     * @return
     */
    public static WebElement refreshIfStale(WebElement webElement) {
        try {

            //throws stale element exception if detached
            webElement.isEnabled();

        } catch (StaleElementReferenceException ex) {
            try {
                // find with 'current' xpath
                webElement = webElement.findElement(By.xpath("."));
            } catch (Exception e) {
                // couldn't refresh the element
            }
        } catch (Exception ex) {

        }
        return webElement;
    }

    /**
     * Dropdown - Common Methods
     */

    public static boolean isOptionDisplayedInDropdown(WebElement element, String text) throws Exception {

        Thread.sleep(runTimeSpeed);
        boolean b = false;
        Select select = new Select(element);
        List<WebElement> list = select.getOptions();
        if (list.contains(text)) {
            b = true;
        }
        return b;
    }

    public static String getSelectedTextFromDropdown(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        Select select = new Select(element);
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }

    /**
     * name - getAllOpotionsFromDropdown() -
     * parameters - dropdown web element
     * returns - list of text strings
     *
     * returns the list of all text values in dropdown
     */
    public static List<String> getAllOptionTextsOrValuesFromDropdown(WebElement element, String type) throws Exception {

        Thread.sleep(runTimeSpeed);
        List<String> result = new ArrayList<String>();
        Select select = new Select(element);
        for (WebElement we : select.getOptions()) {
            result.add(we.getAttribute(type));
        }
        return result;
    }

    /**
     * TextBox - Common Methods
     */

    public static void typeIntoTextbox(WebElement textBoxElement, String textToEnter) throws Exception {

        Thread.sleep(runTimeSpeed);
        textBoxElement.sendKeys("");
        textBoxElement.clear();
        textBoxElement.sendKeys(textToEnter);
    }

    public static boolean searchForSpecificTextValue(WebElement element, String textToSearch) throws Exception {

        Thread.sleep(runTimeSpeed);
        String textValue = refreshIfStale(element).getText();
        return textValue.contains(textToSearch);
    }

    public static String getTextboxValue(WebElement element) throws Exception {

        Thread.sleep(runTimeSpeed);
        return refreshIfStale(element).getAttribute("value");
    }

    /**
     * Accordion - Common Methods
     */

    public static boolean isAccordionExpanded(WebElement webElement) throws Exception {

        Thread.sleep(runTimeSpeed);
        List<WebElement> childAccordions = getAllChildElements(webElement);
        return childAccordions.get(1).getAttribute("class").contains("bodyExpanded");
    }

    public static boolean isAccordionCollapsed(WebElement webElement) throws Exception {

        Thread.sleep(runTimeSpeed);
        List<WebElement> childAccordions = getAllChildElements(webElement);
        return childAccordions.get(1).getAttribute("class").contains("bodyCollapsed");
    }

    /**
     * Validates a list of web elements that, if they are numbers, are numbers which include thw decimal places
     *
     * @param list List of WebElements which include numbers to validate
     * @return
     */
    public static boolean areNumberFormat2DP(List<WebElement> list) throws Exception {

        Thread.sleep(runTimeSpeed);
        for (WebElement w : list) {
            String t = w.getText();
            //Check if it is a number - if it is assert it has two decimal points
            if (t.matches("^[0-9,.]+")) {
                if (t.matches("^[0-9,]+\\.[0-9][0-9]")) {
                    //Valid Number Format
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getPositionAsIntegerStartingFromOne(String positioning) {
        if (positioning.equals("first"))
            return 1;
        else if (positioning.equals("second"))
            return 2;
        else if (positioning.equals("third"))
            return 3;
        else if (positioning.equals("fourth"))
            return 4;

        return 1;
    }

    public static int getPositionAsIntegerFromZero(String position) {
        if (position.equals("first"))
            return 0;
        else if (position.equals("second"))
            return 1;
        else if (position.equals("third"))
            return 2;
        else if (position.equals("fourth"))
            return 3;

        return 0;
    }

    public static void sleep() throws Exception {
        Thread.sleep(runTimeSpeed);
    }

    public static boolean isWindows() throws Exception {
        boolean flag = false;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            flag = true;
        }
        return flag;
    }

    public static boolean runningOnWindows() {
        String system = System.getProperty("os.name");
        if (system.indexOf("Windows") >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLinux() throws Exception {
        boolean flag = false;
        String system = System.getProperty("os.name");
        if (system.toLowerCase().contains("linux")) {
            flag = true;
        } else if (system.toLowerCase().contains("win")) {
            flag = false;
        } else {
            System.out.println("can not find system os type: [" + system + "].");
        }
        return flag;
    }

    public static String isWindowsOrLinuxOS() {
        String system = System.getProperty("os.name");
        if (system.toLowerCase().contains("linux")) {
            system = "linux";
        } else if (system.toLowerCase().contains("win")) {
            system = "windows";
        } else {
            System.out.println("can not find system os type: [" + system + "], pls check it!");
        }
        System.out.println("script is running on os [" + system + "].");
        return system;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(isWindows() + " , code is running on windows, isWindows()");;
        System.out.println(runningOnWindows() + " , code is running on windows, runningOnWindows()");
        System.out.println(isWindowsOrLinuxOS() + " , code is running on windows, isWindowsOrLinuxOS()");
    }
}
