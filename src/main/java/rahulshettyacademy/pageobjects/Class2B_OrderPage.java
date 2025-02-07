package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_OrderPage extends AbstractComponents{
	WebDriver driver;
	
		public Class2B_OrderPage(WebDriver driver) 
		{
				super(driver);
				this.driver=driver;
				
			    PageFactory.initElements(driver, this);
			}
		
			
		//PageFactory	
		
				@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
				List<WebElement> orderList;
		
		
		//Actions Methods
		//CartProductList:	
	public boolean verifyOrderProductDisplay(String productName)
	{
		
		boolean orderMatch = orderList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		
		return orderMatch;
	}


	
	
	
}
