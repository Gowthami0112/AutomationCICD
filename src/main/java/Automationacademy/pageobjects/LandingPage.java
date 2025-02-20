package Automationacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automationacademy.AbstractComponents.AbstractComponent;



public class LandingPage extends AbstractComponent   {
	
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		

	//WebElement useremail = driver.findElement(By.id("userEmail"));
	  

    //pageFactory
     @FindBy(id ="userEmail")
     WebElement userEmail;
     
     @FindBy(id="userPassword")
     WebElement passwordElement;
     
     @FindBy(id="login")
     WebElement submit;
     
     @FindBy(css= "[class*= 'flyInOut']")
     WebElement errorMessage;
     
     public ProductCatalog loginApplication(String Email, String password)
     {
    	 
    	 userEmail.sendKeys(Email);
    	 passwordElement.sendKeys(password);
    	 submit.click();
    	 ProductCatalog productCatalog = new ProductCatalog(driver);
    	 return productCatalog;
     }
     
     public String getErrorMessage()
     {
    	 waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    
     }
     
    	 public void goTO() {
    		 
    		 driver.get("https://rahulshettyacademy.com/client/");
    	 }
    	 
     }
     
     



