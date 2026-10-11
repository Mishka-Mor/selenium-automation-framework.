package frameworks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;
import frameworks.pageobjects.CartPage;
import frameworks.pageobjects.CheckOutPage;
import frameworks.pageobjects.ConfirmationPage;
import frameworks.pageobjects.LandingPage;
import frameworks.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

		@Test
		public void loginErrorValidation() throws IOException, InterruptedException
		{
		// Log In
		
		LandingPage landingPage = launchApplication();
				
		landingPage.loginApp("mmoralishvilid50@gmail.com", "Mishkamishka123");
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		}
		
		@Test(retryAnalyzer = Retry.class)
		public void productErrorValidation() throws IOException, InterruptedException
		{
		
			landingPage = launchApplication();
			
			ProductCatalogue prodCatalogue = landingPage.loginApp("mmoralishvili50@gmail.com", "Mishkamishka123");
			
			// Select the product
			
			List<WebElement> products = prodCatalogue.getProductList();;
			
			String productName = "ZARA COAT 33";
			
			prodCatalogue.addProductToCart(productName);
			
			// click checkout		
			
			CartPage cartPage = prodCatalogue.goToCart();
			
			// validate that the product is in the checkout
			
			boolean match =	cartPage.VerifyProductDisplay(productName);
			
			Assert.assertFalse(match);

		
		}

}
