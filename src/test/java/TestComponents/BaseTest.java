package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frameworks.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
		
	
	public WebDriver initializeDriver() throws IOException 
	{
		
		// Properties class
		// remember to include .properties
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") 
				+ "//src//test//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		// If else or switch case
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
								
		return driver;

	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE); // takes the screenshot
		
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"); // the path and file name
		
		FileUtils.copyFile(source, file); // creates the file in the path specified above
		
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException 
	{
		// Read json to string
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(new File(filePath));
		
		
		// String to HashMap jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String,String>>>() {});
		
		// this data is a list now with two arguments (two HashMaps)
		
		return data;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException 
	{
		System.out.println("Launch");
		driver = initializeDriver();
		
		landingPage = new LandingPage(driver);
		
		landingPage.goTo();
		
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() 
	{
		driver.close();
	}
	
	
}
