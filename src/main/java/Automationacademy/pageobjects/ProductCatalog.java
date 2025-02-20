package Automationacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automationacademy.AbstractComponents.AbstractComponent;



public class ProductCatalog extends AbstractComponent   {
	
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	  
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = (By.cssSelector(".card-body button:last-of-type"));
	By toastMessage = By.cssSelector("#toast-container");
	
	
    public List<WebElement> getproductsList() {
    	waitForElementToAppear(productsBy);
    	return products;
    }
    
   public WebElement getProductByName(String ProductName) {
	   WebElement prod= products.stream().filter
	(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	   return prod;
		          
   }
  public void addProductToCart(String productName) {
	
	WebElement prod = getProductByName(productName);
	 prod.findElement(addToCart).click();
	 waitForElementToAppear(toastMessage);
 waitForElementToDisappear(spinner);
   
   
  }
   
    	 
    
   
   public void waitForElementsToDisappear(WebElement ele) {
	   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
       wait.until(ExpectedConditions.invisibilityOf(ele));
       
 
   }
    	 
     }
     
     



