package Automationacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automationacademy.TestComponents.BaseTest;
import Automationacademy.pageobjects.CartPage;
import Automationacademy.pageobjects.CheckoutPage;
import Automationacademy.pageobjects.ConfirmationPage;
import Automationacademy.pageobjects.LandingPage;
import Automationacademy.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {

	
	public LandingPage landingpage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
			
		landingpage =launchApplication();
	}

	
	@Given("^Logged in with username (.+) and passowrd (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		ProductCatalog productCatalog = landingpage.loginApplication(username,password);
	}
	
	
	@When("I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement>products= productCatalog.getproductsList();
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	 public void Checkout_submit_order(String productName)
	 {
		
		CartPage cartpage =productCatalog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage =  checkoutPage.submitOrder();
	 }
	
	
	 @Then("{string} message is displayed on confirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {
		
		 String confirmMessage = confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		 
	 }
	
	
	
	
	
	
}
