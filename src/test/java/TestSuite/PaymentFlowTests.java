package TestSuite;

import Listeners.CustomListener;
import Pages.*;
import TestData.*;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(CustomListener.class)

public class PaymentFlowTests extends BaseTest {

    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private SuccessPage successPage;

    @BeforeMethod
    public void setUp() {
        // Connect WebDriver from Test with WebDriver from Page
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        successPage = new SuccessPage(driver);

        // Go to the main page
        driver.get(URLs.BASE_URL);
    }

    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        return new Object[][] {
                {TestData.secondItemIndex, TestData.getUseData_Valid(), URLs.SUCCESS_PAGE_URL},
                {TestData.firstItemIndex, TestData.getUseData_Valid(), URLs.SUCCESS_PAGE_URL},
                {TestData.secondItemIndex, TestData.getUseData_InvalidPhoneNumber(), URLs.SHIPPING_URL}
        };
    }

    @Test(dataProvider = "userDataProvider", priority = 1)
    @Description("Verify that the user can add an Item to the cart and proceed to checkout")
    public void payment(int itemIndex, Map<String, String> formData, String destinationURL) {

        homePage.scrollToElement(homePage.getItemsSectionElement());
        homePage.addItemToCart(homePage.getItemElement(itemIndex));
        homePage.assertTextContains( homePage.getMessageText(), TestData.addToCartMessageKeywordSuccess);
        homePage.navigateToCartFromSuccessBar();
        cartPage.proceedToCheckout();
        checkoutPage.fillRequiredFields(formData);
        checkoutPage.finalizeCheckout();
        successPage.assertURLMatches(destinationURL);
        successPage.assertTextEquals( successPage.getPageTitleElement(), TestData.successMessage );
    }
}