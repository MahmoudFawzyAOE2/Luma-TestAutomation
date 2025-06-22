package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    /*-----------  Constructor  -----------*/

    public CartPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*-----------  Locators  -----------*/

    public By proceedToCheckoutLocator = By.cssSelector("[data-role='proceed-to-checkout']");

    /*-----------  Actions  -----------*/

    public void proceedToCheckout() {
        WebElement proceedToCheckoutElement = driver.findElement(proceedToCheckoutLocator);
        clickElement(proceedToCheckoutElement);
    }
}
