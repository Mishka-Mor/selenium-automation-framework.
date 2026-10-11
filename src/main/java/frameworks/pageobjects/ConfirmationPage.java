package frameworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	public ConfirmationPage(WebDriver driver) // This is the first method to execute
	{
		// initialization
		super(driver); // have to do this in every child class
		this.driver = driver;
		
		PageFactory.initElements(driver, this); // initElements method gives access to driver
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmElement;
	
	public String validateMessage() 
	{
		return confirmElement.getText();
	}
}
