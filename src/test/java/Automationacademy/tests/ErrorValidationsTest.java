package Automationacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automationacademy.TestComponents.BaseTest;
import Automationacademy.TestComponents.Retry;
import Automationacademy.pageobjects.CartPage;
import Automationacademy.pageobjects.CheckoutPage;
import Automationacademy.pageobjects.ConfirmationPage;
import Automationacademy.pageobjects.LandingPage;
import Automationacademy.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups ={"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		Thread.sleep(3000);
		String ProductName = "QWERTY";
		
		landingpage.loginApplication("gowthamiups@gmail.com","Vidya@1");
		Assert.assertEquals("Incorrect email or password." ,landingpage.getErrorMessage());
	}
		
	@Test
		public void ProductErrorValidatio() throws IOException, InterruptedException
		{
			Thread.sleep(3000);
			String ProductName = "QWERTY";
			
			ProductCatalog productCatalog = landingpage.loginApplication("gowthamiupsc@gmail.com","Vidya@01");
			
			List<WebElement>products= productCatalog.getproductsList();
			productCatalog.addProductToCart(ProductName);
			CartPage cartpage =productCatalog.goToCartPage();
			
			Boolean match = cartpage.VerifyProductDisplay("QWERTY");
			Assert.assertFalse(match);
	

          
	}

}
