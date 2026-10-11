package frameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

// POM
public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productsToValidate;
	
	@FindBy(css = ".totalRow button")
	WebElement button;
	
	
	public CartPage(WebDriver driver) // This is the first method to execute
	{
		// initialization
		super(driver); // have to do this in every child class
		this.driver = driver;
		
		PageFactory.initElements(driver, this); // initElements method gives access to driver
	}
	
	public boolean VerifyProductDisplay(String productName) 
	{
		boolean match =	productsToValidate.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() 
	{
		button.click();
		return new CheckOutPage(driver);
	}
}
