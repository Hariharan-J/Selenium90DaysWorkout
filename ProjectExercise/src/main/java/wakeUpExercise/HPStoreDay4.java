package wakeUpExercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HPStoreDay4 {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Browser load, maximize, and implicitly wait added chromeoptions to disable
		 * notifications
		 */
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://store.hp.com/in-en/");
		
		/* Click on the close icon on the pop up asking for Sign up email */
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='optly-modal-close close-icon']"))).click();
		
		/* Click on the close icon on the cookies banner alert */
		driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		
		/* Click on laptops - pavilion */
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[@id='ui-id-2']//span[text()='Laptops']")).build().perform();
		driver.findElementByXPath("//a[@id='ui-id-43']//span[text()='Pavilion']").click();
		
		/* Click on Dont miss out close icon */
		WebDriverWait wait4 = new WebDriverWait(driver, 30);
		wait4.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']"))).click();
		
		/* Click on processor - Intel core i7 */
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		//(//span[text()='Processor']//parent::dt)[2]
		driver.findElementByXPath("//span[text()='Intel Core i7']//preceding-sibling::input").click();
		Thread.sleep(5000);
		
		/* Click on  More than 1 TB */
		driver.findElementByXPath("//span[text()='More than 1 TB']//preceding-sibling::input").click();
		Thread.sleep(5000);
		
		/* Click on  Sort by dropdown and select 'low to high' value */
		WebElement sortby = driver.findElementById("sorter");
		sortby.click();
		Select list = new Select(sortby);
		list.selectByValue("price_asc");
		Thread.sleep(5000);
		
		/* Print lowest priced laptop name and laptop price */
		System.out.println("Lowest priced laptop name is "+driver.findElementByXPath("//a[@class='product-item-link']").getText());
		String price = driver.findElementByXPath("//span[@data-price-type='finalPrice']//span").getText();
		int originalprice = Integer.parseInt(price.replaceAll("[^0-9]", ""));
		System.out.println("Lowest priced laptop price is "+price);
		
		/* Click on Add to cart */
		driver.findElementByXPath("//button[@title='Add To Cart']").click();
		//driver.findElementByXPath("//span[@class='counter-number']").getText();
		
		/* Click on shopping bag if bag number has 1 entry added and then click on 'View and edit' */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElementByXPath("//span[@class='counter-number']"), "1"));
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		
		/* Verification on pincode and confirming whether pincode is valid or not */
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOf(driver.findElementByName("pincode"))).sendKeys("600126");
		driver.findElementByXPath("//button[text()='check']").click();
		if(driver.findElementByXPath("//div[@class='delivery-days']//div").getText().contains("Delivery estimate"))
			System.out.println("Pincode is valid and shipment can be processed");
		else
			System.out.println("Pincode is not valid and shipment cannot be processed");
		
		/* Comparison of grand totals and original price and click on 'Proceed to checkout' */
		String total = driver.findElementByXPath("//tr[@class='grand totals']//span[@class='price']").getText();
		int  grandtotal = Integer.parseInt(total.replaceAll("[^0-9]", ""));
		if(grandtotal == originalprice)
			driver.findElementByXPath("//button[@title='Proceed to Checkout']").click();
		else
			System.out.println("Grandtotal is not same as laptop price");
		
		/* Click on place order */
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Place Order']"))).click(); 
	}

}
