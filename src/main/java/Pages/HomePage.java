package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    /*-----------  Constructor  -----------*/

    public WebDriver driver ;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*-----------  Locators  -----------*/

    public By menCategory = By.id("ui-id-5");
    public By topsSubCategory = By.id("ui-id-17");
    public By teesItemType = By.id("ui-id-21");
    public By searchBox = By.id("search");
    public By itemsSection = By.className("product-items");
    public By Items = By.className("product-item");
    By firstSizeRelative = By.xpath(".//div/div/div[3]/div[1]/div/div[1]");
    By firstColorRelative = By.xpath(".//div/div/div[3]/div[2]/div/div[1]");
    By addToCartRelative = By.className("tocart");
    By message = By.className("messages");
    By messageText = By.cssSelector(".message-success > div, .message-error > div");
    By cartLinkRelative = By.xpath(".//div/div/a");

    /*-----------  Actions  -----------*/

    public WebElement getItemsSectionElement() {
        return driver.findElement(itemsSection);
    }

    public WebElement getItemElement(int index) {
        return driver.findElements(Items).get(index);
    }

    public void addItemToCart(WebElement item) {
        WebElement firstSize = item.findElement(firstSizeRelative);
        WebElement firstColor = item.findElement(firstColorRelative);
        WebElement addToCart = item.findElement(addToCartRelative);

        clickElement(firstSize);
        clickElement(firstColor);
        clickElement(addToCart);
    }
    public WebElement getMessageText() {
        return wait.until(driver -> {
            try {
                List<WebElement> messages = driver.findElements(message);
                if (messages.size() > 1) {
                    WebElement messageElement = messages.get(1);
                    return messageElement.findElement(messageText);
                }
                return null;
            } catch (StaleElementReferenceException e) {
                return null; // retry
            }
        });
    }
    public void navigateToCartFromSuccessBar() {
        WebElement messageElement = driver.findElements(message).get(1);
        WebElement cartLinkElement = messageElement.findElement(cartLinkRelative);

        clickElement(cartLinkElement);
    }
}