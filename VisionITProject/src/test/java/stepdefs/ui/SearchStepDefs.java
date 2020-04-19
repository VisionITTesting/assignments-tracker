package stepdefs.ui;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import context.TestBase;
import context.TestContextUI;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.CmnPageObjects;
import pageobjects.SearchPageObjects;

public class SearchStepDefs extends TestBase{

	TestContextUI testContextUI;
	Scenario scn;
	
	public SearchStepDefs(TestContextUI testContextUI) {
		this.testContextUI = testContextUI;
	}
	
	@Given("I have browser opened and url is navigated")
	public void i_have_browser_opened_and_url_is_navigated() {				
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		driver.get(serverUI);
		scn.write("Chrome Driver invoked and URL navigated as: " + serverUI);
		
		//Assign driver and set page Objects to Test Context 
		testContextUI.setDriver(driver);
		testContextUI.initializePageObjectClasses(driver, scn);
	}

	@When("I search for product as {string}")
	public void i_search_for_product_as(String product) {
		testContextUI.getCmnPageObjects().SetSearchTextBox(product);
		testContextUI.getCmnPageObjects().ClickOnSearchButton();
		scn.write("Search was sucessfull");
		
	}

	@Then("product list should appear pertaining to the product search as {string}")
	public void product_list_should_appear_pertaining_to_the_product_search_as(String productName) {
		testContextUI.getSearchPageObjects().ValidateProductList(productName);
	}
	
	@Before
	public void SetUp(Scenario s) {
		this.scn = s;
	}

	
	@After
	public void CleanUp(Scenario s) {
		
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)testContextUI.getDriver();
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.embed(data, "image/png");
		}
		
		testContextUI.getDriver().quit();
		scn.write("Browser is Closed");
	}
	
}
