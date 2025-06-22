package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessPage extends BasePage {

    /*-----------  Constructor  -----------*/

    public SuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /*-----------  Locators  -----------*/

    By pageTitle = By.cssSelector(".base[data-ui-id='page-title-wrapper']");


    /*-----------  Actions  -----------*/

    public WebElement getPageTitleElement() {
        return driver.findElement(pageTitle);
    }
}
