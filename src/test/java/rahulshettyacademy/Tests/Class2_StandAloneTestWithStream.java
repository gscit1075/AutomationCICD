package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Class2_StandAloneTestWithStream {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
//Invoke the URL:		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String productType = "ADIDAS ORIGINAL";
		
//Login:
		driver.findElement(By.id("userEmail")).sendKeys("mahesh.gscit1075@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("P455w0rd@1");
		driver.findElement(By.cssSelector(".login-btn")).click();

//Products Selection Page:
		List<WebElement> productsList = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = productsList.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productType)).findFirst().orElse(null); // return web element
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		
//BottomText and loading circle:
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		//Cart button:
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
			
//Cart Page:
				
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
	//we just want cross verify that selected item is got or not.
	
	     // WebElement match = CartProducts.stream().filter(cartProduct->cartProduct.getText().equals(productType));
		
	//We don't want to web Element instead true/false conditions. So.......
		
		boolean match = CartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productType));
		
		//anyMatch:  out the 10 product,  if any product, test match with the product type then it return the boolen value true else flase.
		
	//Assertion:	
		Assert.assertTrue(match);
		
		
//CheckOut:		
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		 
//Place Order: //input[@placeholder='Select Country']		
	
	//Action class:
		 Actions a = new Actions(driver);
		  a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
	//Wait:
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//section[contains(@class,'ta-results ')])")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); //select india
		driver.findElement(By.cssSelector(".action__submit")).click(); //place order button:
		
//Thanks for Order page:		
		 String s =  driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		    Assert.assertTrue(s.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		    System.out.println("End to Code Successfull");
		    
            driver.close();
		   
	}

}
