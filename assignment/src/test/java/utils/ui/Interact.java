package utils.ui;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author akashtyagi
 * Library created to interact with the elements
 */
public class Interact {

	private static final Logger logger = LogManager.getLogger(Interact.class);
	private WebDriver driver;
	
	public WebDriver getDriver() {
		 return driver;
	}
	
	public void setDriver(WebDriver d) {
		this.driver=d;
	}
	
	public WebElement clickElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
		logger.info("Element is clicked. Element Description: " + by.toString());
		return element;
	}
	
	public WebElement setElement(By by, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.sendKeys(text);
		logger.info("Element is Set with text as: " + text + ". Element Description: " + by.toString());
		return element;
	}
	
	public String getAttribute(By by, String attName) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		String value = element.getAttribute(attName);
		logger.info("Get Attribute for element: " + by.toString() + " Attribute name: " + attName);
		return value;
	}
	
	public List<WebElement> getListOfWebElements(By by){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		logger.info("List of Elements returned for description: " + by.toString());
		return element.findElements(by);
	}
	
	public byte[] takeScreenShot() {
		TakesScreenshot shot = (TakesScreenshot)driver;
		logger.info("Screen Shot taken for full driver. ");
		return shot.getScreenshotAs(OutputType.BYTES);
	}
	
	public byte[] takeScreenShot(By by) {
		TakesScreenshot shot = (TakesScreenshot)driver.findElement(by);
		logger.info("Screen Shot taken for element: " + by.toString() );
		return shot.getScreenshotAs(OutputType.BYTES);
	}
	
	public File takeScreenShotAsFile() {
		TakesScreenshot shot = (TakesScreenshot)driver;
		logger.info("Screen Shot taken for full driver and returned as a file.");
		return shot.getScreenshotAs(OutputType.FILE);
	}
	
	public File takeScreenShotAsFile(By by) {
		TakesScreenshot shot = (TakesScreenshot)driver.findElement(by);
		logger.info("Screen Shot taken for element and returned as a file. By descp: " + by.toString());
		return shot.getScreenshotAs(OutputType.FILE);
	}
}
