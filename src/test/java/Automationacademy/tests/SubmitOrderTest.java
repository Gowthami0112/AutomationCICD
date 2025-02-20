package Automationacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automationacademy.TestComponents.BaseTest;
import Automationacademy.pageobjects.CartPage;
import Automationacademy.pageobjects.CheckoutPage;
import Automationacademy.pageobjects.ConfirmationPage;
import Automationacademy.pageobjects.LandingPage;
import Automationacademy.pageobjects.OrderPage;
import Automationacademy.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "QWERTY";
	
	@Test(dataProvider="getData",groups ={"Purchase"})
	public void SubmitOrder(HashMap<String, String>input) throws IOException, InterruptedException
	{
		Thread.sleep(3000);
		
		
		ProductCatalog productCatalog = landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products= productCatalog.getproductsList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartpage =productCatalog.goToCartPage();
		Thread.sleep(3000);
		Boolean match = cartpage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("India");
		Thread.sleep(3000);
		ConfirmationPage confirmationPage =  checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	
		public void OrderHistoryTest() throws InterruptedException
		{
		ProductCatalog productCatalog = landingpage.loginApplication("gowthamiupsc@gmail.com","Vidya@01");
		
		OrderPage orderspage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(ProductName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
	  List<HashMap<String ,String>>data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\Automationacademy\\data\\PurchaseOrder.json");
	    return new Object [][] {{data.get(0)},{data.get(1)} };
     
	    		 }
	
{
   
          
	}
}




//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "gowthamiupsc@gmail.com");
//map.put("password","Vidya@01");
//map.put("product","QWERTY");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "gowthamiupsc@gmail.com");
//map1.put("password","Vidya@01");
//map1.put("product","IPHONE 13 PRO");
//



