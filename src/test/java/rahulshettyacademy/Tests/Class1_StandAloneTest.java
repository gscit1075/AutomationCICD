package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class1_StandAloneTest {

	public static void main(String[] args) throws Exception {
		
//First invoke the browser:
		
		// WebDriverManager.chromedriver().setup();
                // new comment are added for webhook test
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Documents\\chromedriver.exe");
		  WebDriver driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		   driver.manage().window().maximize();
		   
//Get the URL:
		   driver.get("https://rahulshettyacademy.com/client");
		   
//Login:
		   driver.findElement(By.id("userEmail")).sendKeys("mahesh.gscit1075@gmail.com");
		   driver.findElement(By.id("userPassword")).sendKeys("P455w0rd@1");
		   driver.findElement(By.cssSelector(".login-btn")).click();
		
//Add to Cart:
		   		   
		   List<WebElement> productsList = driver.findElements(By.cssSelector(".mb-3"));
		 //Iterate through each product:
		   
		   for(WebElement product:productsList)
		   {
			   String s = 	product.findElement(By.tagName("b")).getText();  
								
			   if(s.equalsIgnoreCase("ADIDAS ORIGINAL"))
			   {
				  product.findElement(By.cssSelector(".btn.w-10.rounded")).click();
				   break;
			   }
			   
		     }
		   
    //Bottom Text: .toast-bottom 
		   
	  //Explicitly Wait:
		   
		   WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		   
	  // loading circle: .ng-animating
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		   
		   driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
	
//CartPage to checkOut:
		   		   		   
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));
	 //iterate:
		   for(WebElement cartProduct:cartProducts)
		   {
			   
			   Boolean match  = cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL");
			   
			 if(cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"))
			 {
				 
				 //Click
				 driver.findElement(By.cssSelector(".totalRow button")).click();
				 break;
			 }
			 
		   }
		   
//Place Order:
		   
		 //Shiping Addres: 		   
            driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));                       
            driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
            
            driver.findElement(By.cssSelector(".action__submit")).click();
            String x = driver.findElement(By.cssSelector(".hero-primary")).getText();
      //Assertion:	          
            Assert.assertTrue(x.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
            System.out.println("End to Code Successfull");
            driver.close();
		   
		  
		   
		   
		
	}

}
