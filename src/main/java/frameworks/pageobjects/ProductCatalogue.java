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
public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) // This is the first method to execute
	{
		// initialization
		super(driver); // have to do this in every child class
		this.driver = driver;
		
		PageFactory.initElements(driver, this); // initElements method gives access to driver
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".card-body");
	By cartBy = By.cssSelector(".card-body button:last-of-type");
	
	
	By toastBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() // returns the products list
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByElement(String productName) 
	{
		WebElement prod = getProductList().stream().filter(s -> 
		s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException 
	{
		WebElement prod = getProductByElement(productName);
		prod.findElement(cartBy).click();
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(spinner);
	}
}
