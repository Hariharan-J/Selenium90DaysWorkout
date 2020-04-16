package wakeUpExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NykaaDay2 {

	public static void main(String[] args) throws InterruptedException {
		/*Browser load, maximize, and implicitly wait
		 * added chromeoptions to disable notifications */
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.nykaa.com/");
		
		/*Added webdriver wait to wait for brands to be visible after browser load */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='brands']")));
		
		/*Navigating from brands - popular - Loreal paris using ACTIONS*/
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()='brands']")).build().perform();
		builder.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).build().perform();
		//WebDriverWait wait1 = new WebDriverWait(driver, 30);
		//wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]"))).click();
		driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]").click();
		
		/*Switching to new window - Loreal paris*/
		Set<String> wins = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(wins);
		driver.switchTo().window(ls.get(1));
		if (driver.getTitle().contains("L'Oreal Paris"))
			System.out.println("Browser title contains L'Oreal Paris");
		else
			System.out.println("Browser title does not contain L'Oreal Paris");
		
		/*Webdriver wait for selecting sort by filter from popularity to customer rated*/
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='pull-left']"))).click();
		//driver.findElementByXPath("//span[@class='pull-left']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		Thread.sleep(3000);
		
		/* Select category - shampoo*/
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//input[@id='chk_Shampoo_undefined']//following::label//div").click();
		
		/* Fetching filter text gives shampooclose
		 * so used getchars to fetch only shampoo string and again converted chars to string and verified */
		String filtertext = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']//li").getText();
		//System.out.println(filtertext);
		char ch[] = new char[7];
		filtertext.getChars(0, 7, ch, 0);
		//System.out.println(ch);
		String filterapplied = new String(ch);
		if (filterapplied.equalsIgnoreCase("shampoo"))
			System.out.println("Filter applied is Shampoo");
		else
			System.out.println("Filter applied is not Shampoo");
		
		/* Switching to next window - Loreal paris color protect shampoo */
		driver.findElementByXPath("//img[@alt=\"L'Oreal Paris Colour Protect Shampoo\"]").click();
		driver.switchTo().window(ls.get(0));
		Set<String> windows = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(windows);
		driver.switchTo().window(winlist.get(2));
		
		/* Click 175ml and print price after excluding rupee symbol using replaceall*/
		driver.findElementByXPath("//span[text()='175ml']").click();
		String price = driver.findElementByXPath("(//div[@class='price-info']//span/span)[2]").getText();
		System.out.println("Price of the shampoo is Rs."+price.replaceAll("[^0-9]", ""));
		
		 /* click add to bag - click shopping bag and print grand total after excluding rupee symbol using replaceall*/
		driver.findElementByXPath("//button[@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  ']").click();
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		Thread.sleep(3000);
		String grandtotal = driver.findElementByXPath("//div[text()='Grand Total']//following::div").getText();
		System.out.println("Grand total of the shampoo is Rs."+grandtotal.replaceAll("[^0-9]", ""));
		driver.findElementByXPath("//span[text()='Proceed']//parent::button").click();
		/* Continue as guest button - print warning message and close all browser*/
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		String warning = driver.findElementByXPath("//div[@class='message']").getText();
		System.out.println("Warning displayed is "+warning);
		driver.quit();
	}
}
