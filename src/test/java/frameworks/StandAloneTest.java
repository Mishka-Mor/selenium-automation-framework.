package frameworks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import frameworks.pageobjects.CartPage;
import frameworks.pageobjects.CheckOutPage;
import frameworks.pageobjects.ConfirmationPage;
import frameworks.pageobjects.LandingPage;
import frameworks.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest{

		@Test(dataProvider = "getData", groups = "Purchase")
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
		{
		// Log In
		
		landingPage = launchApplication();
				
		ProductCatalogue prodCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
		
		// Select the product
		
		List<WebElement> products = prodCatalogue.getProductList();;
		
		
		prodCatalogue.addProductToCart(input.get("product"));
		
		// click checkout		
		
		CartPage cartPage = prodCatalogue.goToCart();
		
		// validate that the product is in the checkout
		
		boolean match =	cartPage.VerifyProductDisplay(input.get("product"));
		
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		
		// Fill the forms
						
		checkOutPage.checkOut("india");
		ConfirmationPage confirmPage = checkOutPage.submitOrder();
		
		String confirmText = confirmPage.validateMessage();
		
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
	}
		
	// Hashmap
		
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//HashMap<String, String> map = new HashMap<String, String>();
		//map.put("email", "mmoralishvili50@gmail.com");
		//map.put("password", "Mishkamishka123");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String, String> map1 = new HashMap<String, String>();
		//map1.put("email", "anshika@gmail.com");
		//map1.put("password", "Iamking@000");
		//map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseData.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}

	
	// Multi-dimensional array 
	
	//@DataProvider
	//public Object[][] getData()
	//{
	//	return new Object[][] {{"mmoralishvili50@gmail.com", "Mishkamishka123", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", 
	//		"ADIDAS ORIGINAL"}};
	//	
	//}
	
	
	// Screenshot utility / takes a screenshot and then returns the path of the screenshot
	
	
}
