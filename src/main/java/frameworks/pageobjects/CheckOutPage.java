package frameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

// POM
public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
		
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	By SelectCountry = By.xpath("(//button[contains(@class,'ta-item')])[2]");
	
	@FindBy(css = ".action__submit")
	WebElement actionSubmitButton;
	
	public CheckOutPage(WebDriver driver) // This is the first method to execute
	{
		// initialization
		super(driver); // have to do this in every child class
		this.driver = driver;
		
		PageFactory.initElements(driver, this); // initElements method gives access to driver
	}
	
	public void checkOut(String countryName) 
	{
		Actions a = new Actions(driver);
		
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(SelectCountry);
		driver.findElement(SelectCountry).click();
	}
	
	public ConfirmationPage submitOrder() 
	{
		actionSubmitButton.click();
		return new ConfirmationPage(driver);
	}
}
