package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_CartPage extends AbstractComponents{
	WebDriver driver;
	
	public Class2B_CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		
	    PageFactory.initElements(driver, this);
	}
	
//PageFactory	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProductList;
	
    @FindBy(css=".totalRow button")
     WebElement checkOut;
   
//CartProductList:	
	public boolean verifyProductDisplay(String productName)
	{
		boolean match = cartProductList.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
//CheckOut Button:	
	
	public Class2B_CheckOutPage checkOut()
	{
		checkOut.click();	
		
		Class2B_CheckOutPage checkOutPage = new Class2B_CheckOutPage(driver);
		 return checkOutPage;
		
	}


	
	
	
}
