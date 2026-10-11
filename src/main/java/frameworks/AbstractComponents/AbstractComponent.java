package frameworks.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworks.pageobjects.CartPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
	}

	// For reusing explicit wait
	public void waitForElementToAppear(By findBy)
	{
		// Don't forget to use the super keyword
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement element)
	{
		// Don't forget to use the super keyword
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitForElementToDisappear(WebElement spinner) throws InterruptedException
	{
		// Don't forget to use the super keyword
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(spinner));
		Thread.sleep(1000);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
	
	public CartPage goToCart() // return cartPage and avoid writing script in the StandAloneTest
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
}
