package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.Class2B_CartPage;
import rahulshettyacademy.pageobjects.Class2B_OrderPage;

public class AbstractComponents {
	
	      WebDriver driver;
	
	     public AbstractComponents(WebDriver driver2)  //constructor
	       {
		
		     this.driver = driver2;
		     PageFactory.initElements(driver2, this);
		
	        }
	     
//PageFactory for Cart button:
	     @FindBy(css="button[routerlink*='cart']")
	     WebElement cartButton;
         @FindBy(css=" [routerlink*='myorders']")
         WebElement ordersButton;
	     
//Wait Genric MEthod:
	     
	public void waitForElementVisiblity(By findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	   
	}
	
	
	public void waitForElementinVisiblity(By findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
    wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	   
	}
	
	public void waitForWebElementToAppear(WebElement findBy )
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
//THis is the haeader which common to all page:	
		//ClickCart button to gotoCartPage:
			public Class2B_CartPage clickCartButton() throws InterruptedException
			{
				Thread.sleep(2000);
				
				    cartButton.click();
				
			//CartPage Object:	
				 Class2B_CartPage cartPage = new Class2B_CartPage(driver); 
				 return cartPage;
			}
		 
	  //Click Orders button to go to orderpage:
		 
		 public Class2B_OrderPage goToOrderPage()
		 {
			 ordersButton.click();   // Go to orer page
			 Class2B_OrderPage orderPage= new Class2B_OrderPage(driver);
			 return orderPage;
			 
		 }
	

}
