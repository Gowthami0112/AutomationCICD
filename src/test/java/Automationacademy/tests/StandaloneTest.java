package Automationacademy.tests;

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

import Automationacademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String ProductName = "QWERTY";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landpage = new LandingPage(driver);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("gowthamiupsc@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vidya@01");
		driver.findElement(By.id("login")).click();
		
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
          List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
      
          WebElement prod= products.stream().filter
          (product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
          
          
          prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
          
          
  
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
          wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
          driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        Thread.sleep(3000);
         List<WebElement> cartproducts= driver.findElements(By.xpath("//*[@class ='cartSection'] /h3"));
         Boolean match =cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
        Assert.assertTrue(match);
       Thread.sleep(3000);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
       
         driver.findElement(By.cssSelector(".action__submit")).click();
         Thread.sleep(2000);
      String ConfirmMessage  = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
          driver.close();
          
          
          
          
	}

}
