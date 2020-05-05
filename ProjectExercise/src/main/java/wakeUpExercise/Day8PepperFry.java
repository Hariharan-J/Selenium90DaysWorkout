package wakeUpExercise;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day8PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
		/*
		 * Browser load, maximize, and implicitly wait added chromeoptions to disable
		 * notifications
		 */
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		//opt.addArguments("--incognito");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.pepperfry.com/");
		
		/* Webdriver wait-elementtobe clickable for closing a covid alert pop up and sign up pop up */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@class='close']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@id='regPopUp']//a[@class='popup-close']"))).click();
		
		/* 50% off pop up is inside a frame, so moving into frame - since this pop up occurs intermittent, used try-catch block */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		try {
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("webklipper-publisher-widget-container-notification-frame"));
			driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
			
			/* Identify 50% off popup close element - using javascript executor to close the pop up */
			WebElement popup = driver.findElementByXPath("//div[@id='webklipper-publisher-widget-container-notification-container']//div//span");		
			js.executeScript("arguments[0].click();", popup);

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No 50% pop up displayed");
			e.printStackTrace();
		}
		
		/* Using actions - move to element 'furniture' - click office chairs */
		Actions build = new Actions(driver);
		build.moveToElement(driver.findElementByXPath("//a[text()='Furniture']")).build().perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		
		/* Webdriver wait-elementtobe clickable to click on executive chairs */
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@alt='Executive Chairs']//parent::div[@class='cat-wrap-img']"))).click();
		
		/* Webdriver wait-visibility of element - height dimension section - change height to 50 */
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@class='clip__filter-dimension']//input[@data-index='0']")));
		WebElement height = driver.findElementByXPath("//ul[@class='clip__filter-dimension']//input[@data-index='0']");
		height.clear();
		height.sendKeys("50", Keys.ENTER);
		Thread.sleep(5000);
		
		/* Click on wishlist for 'Poise Executive Chair in Black Colour' */
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		
		/* Using actions - move to element 'homeware' - using Webdriver wait-elementtobe clickable - click pressure cookers */
		build.moveToElement(driver.findElementByXPath("//a[text()='Homeware']")).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='Pressure Cookers']"))).click();
		
		/* Using Webdriver wait-elementtobe clickable - check prestige, check 1-3 ltr */
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for='brandsnamePrestige']"))).click();
		Thread.sleep(3000);
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		Thread.sleep(5000);
		
		/* Click on wishlist for 'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr' */
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		Thread.sleep(3000);
		
		/* Print number of Wishlist and click on wishlist  */
		System.out.println("Number of items in wishlist is "+driver.findElementByXPath("//div[@class='wishlist_bar']//span").getText());
		driver.findElementByXPath("//div[@class='wishlist_bar']//a").click();
		Thread.sleep(3000);
		
		/* Identify Nakshatra cooker element - using javascript executor - scroll upto that element and click on 'Add to cart' */
		WebElement cookercart = driver.findElementByXPath("//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By...']//following::a");
		js.executeScript("arguments[0].scrollIntoView();", cookercart);
		cookercart.click();
		
		/* Using Webdriver wait-elementtobe clickable - verify the pincode and click on proceed to pay */
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@class='srvc_pin_text']"))).sendKeys("600126");
		driver.findElementByXPath("//a[text()='Check']").click();
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		
		/* Identify cooker element and get the screenshot of that element only */
		WebElement cookerele = driver.findElementByXPath("//div[@class='ck-sku-row-top pf-white']");
		File src = cookerele.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/pepperfry.jpg");
		FileUtils.copyFile(src, dest);
	}

}
