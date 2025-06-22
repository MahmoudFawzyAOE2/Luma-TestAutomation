package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {

    public WebDriver driver ;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void hoverElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void enterTextInField(WebElement field, String text) {
        System.out.println("Entering text in field: " + field + " with text: " + text);
        scrollToElement(field);
        field.clear();
        field.sendKeys(text);
    }

    public void selectFromMenu(WebElement dropdown, String option) {
        System.out.println("Selecting option: " + option + " from dropdown: " + dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }


    public void clickElement(WebElement element) {
        scrollToElement(element);
        try {
            // Wait for visibility first
            wait.until(ExpectedConditions.visibilityOf(element));

            // Wait for clickability
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Try normal click
            element.click();
        } catch (ElementClickInterceptedException intercepted) {
            System.out.println("Element click intercepted. Retrying with JS click: " + element);

            // Fallback: JavaScript click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (TimeoutException e) {
            System.out.println("Element not clickable within timeout: " + element);
            throw e;
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking element: " + element);
            throw e;
        }
    }

    public String getTextFromElement(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        String text = element.getText().trim();
        System.out.println("Retrieved text: \"" + text + "\"");
        return text;
    }
    public void assertTextEquals(WebElement element, String expectedText) {
        String actualText = getTextFromElement(element);
        Assert.assertEquals(actualText, expectedText,
                "Text mismatch! Expected: \"" + expectedText + "\", but got: \"" + actualText + "\"");
    }

    public void assertTextContains(WebElement element, String expectedSubstring) {
        String actualText = getTextFromElement(element);
        Assert.assertTrue(actualText.contains(expectedSubstring),
                "Expected text to contain: \"" + expectedSubstring + "\", but got: \"" + actualText + "\"");
    }

    public void assertURLMatches(String expectedURL) {

        try {
            wait.until(driver -> driver.getCurrentUrl().equals(expectedURL));
        } catch (TimeoutException e) {
            String currentURL = driver.getCurrentUrl();
            Assert.fail("URL did not match within timeout.\nExpected: \"" + expectedURL + "\"\nActual:   \"" + currentURL + "\"");
        }

        // Optionally still assert for double safety
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, expectedURL,
                "URL mismatch! Expected: \"" + expectedURL + "\", but got: \"" + currentURL + "\"");
    }
}