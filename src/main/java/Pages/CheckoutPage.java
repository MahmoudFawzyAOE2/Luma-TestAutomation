package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CheckoutPage extends BasePage {

    /*-----------  Constructor  -----------*/

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*-----------  Locators  -----------*/

    By emailField = By.xpath("//html/body/div[2]/main/div[2]/div/div[2]/div[4]/ol/li[1]/div[2]/form[1]/fieldset/div/div/input");
    By formRequiredFields = By.cssSelector("[aria-required='true']");
    By nextLocator = By.cssSelector(".button.action.continue.primary");
    By shipmentMethodLocator = By.cssSelector("form#co-shipping-method-form input[type='radio'].radio");
    By placeOrderLocator = By.cssSelector(".action.primary.checkout");
    /*-----------  Actions  -----------*/

    public void fillRequiredFields(Map<String, String> data) {

        WebElement email = driver.findElement(emailField);
        System.out.println("Filling email field with: " + data.get("email"));
        enterTextInField(email, data.get("email"));

        List<WebElement> fields = driver.findElements(formRequiredFields);
        selectFromMenu(fields.get(8), data.get("country"));

        enterTextInField(fields.get(1), data.get("firstName"));
        enterTextInField(fields.get(2), data.get("lastName"));
        enterTextInField(fields.get(3), data.get("address"));
        enterTextInField(fields.get(4), data.get("city"));
        if (Objects.equals(data.get("province"), "") || data.get("province") == null) {
            System.out.println("Province is not provided, skipping selection.");
        } else {
            selectFromMenu(fields.get(5), data.get("province"));
        }
        enterTextInField(fields.get(7), data.get("zipCode")); // element 6 is not visible
        enterTextInField(fields.get(9), data.get("phoneNumber"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver -> {
            List<WebElement> radios = driver.findElements(shipmentMethodLocator);
            for (WebElement radio : radios) {
                if (radio.isSelected()) {
                    return true;
                }
            }
            return false;
        });
    }
    public void finalizeCheckout() {

        WebElement nextButtonElement = driver.findElement(nextLocator);
        clickElement(nextButtonElement);

        WebElement placeOrderElement = driver.findElement(placeOrderLocator);
        clickElement(placeOrderElement);

    }
}
