package frameworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;
import net.bytebuddy.implementation.bind.annotation.Super;

// POM
public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) // This is the first method to execute
	{
		// initialization
		super(driver); // sending from child to parent
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this); // initElements method gives access to driver
	}
	
	// WebElement email = driver.findElement(By.id("userEmail"));
	
	// PageFactory Design pattern // Do some research on it
	// Works just like the above code
	
	@FindBy(id = "userEmail") // will assign this selector to the variable declared below
	WebElement userEmail;
	
	// Can't use inside findElements
	// @FindBy runs driver.findElement(By.id) and assigns it to the webelement below it doesn't return a by selector
	@FindBy(id = "userPassword")
	WebElement passwordElement;
	
	@FindBy(id = "login")
	WebElement submitButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApp(String email, String password) // When you login you will be redirected to the productCatalogue page
	{																// So why not after logging in also return the POM
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submitButton.click();
		ProductCatalogue prodCatalogue = new ProductCatalogue(driver);
		return prodCatalogue;
	}
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
