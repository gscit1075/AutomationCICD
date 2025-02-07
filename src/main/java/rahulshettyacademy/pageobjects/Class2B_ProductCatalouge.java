package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_ProductCatalouge extends AbstractComponents {
	
	            WebDriver driver;
	
				
//PConstructor:
				public Class2B_ProductCatalouge(WebDriver driver)
				{
					super(driver);
					this.driver = driver;
					PageFactory.initElements(driver, this);
					
				}
//PageFactory:
				
				@FindBy(css=".mb-3")    //need to define wait also before find this element: see AbstractComponents class
				List<WebElement> productsList;
				
				By products = By.cssSelector(".mb-3");
				By addToCart = By.cssSelector(".btn.w-10.rounded");
				By tostMessage = By.cssSelector("#toast-container");
				By lodingCircle = By.cssSelector(".ng-animating");
				
				
//Action Methods:
				    
	//1. Iterate over list method: 
			      	public WebElement iterateOnList(String productName)
						{
							
						 // Wait:
				      		waitForElementVisiblity(products);   ///work like  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			      		
			               //Then find a  product from the list:
							
							WebElement product1 = productsList.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
									
							return product1;
						}
					
 //2. Click Add to Cart:
			
						public void	addToCart(String productName)
						{
							
							WebElement Prod = iterateOnList(productName);
							
							Prod.findElement(addToCart).click();  //click Add to cart button.
							
							waitForElementVisiblity(tostMessage);
							waitForElementinVisiblity(lodingCircle);
							
														
						}
							
							
				
				

}
