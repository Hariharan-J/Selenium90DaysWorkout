package wakeUpExercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyntraDay1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver =new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.myntra.com/");
		Actions builder = new Actions(driver);
		//Thread.sleep(5000);
		builder.moveToElement(driver.findElementByXPath("(//a[text()='Women'])[1]")).build().perform();;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='Jackets & Coats']"))).click();
		//builder.click(driver.findElementByXPath("//a[text()='Jackets & Coats']")).build().perform();
		String totalcount = driver.findElementByXPath("//h1[text()='Coats & Jackets For Women']//following::span").getText();
		String total=totalcount.replaceAll("[^0-9]", "");
		//System.out.println(totalcount);
		System.out.println(total);
		int totalitems = Integer.parseInt(total);
		String countjackets = driver.findElementByXPath("//input[@value='Jackets']//following::span").getText();
		int totaljackets = Integer.parseInt(countjackets.replaceAll("[^0-9]", ""));
		System.out.println(totaljackets);
		String countcoats = driver.findElementByXPath("//input[@value='Coats']//following::span").getText();
		int totalcoats = Integer.parseInt(countcoats.replaceAll("[^0-9]", ""));
		System.out.println(totalcoats);
		if(totalitems == (totaljackets + totalcoats))
			System.out.println("Total items match with the categories");
		else
			System.out.println("Total items does not match with the categories");
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@value='Coats']//following::div").click();
		driver.findElementByXPath("//div[@class='brand-more']").click();
		driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("mango");
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//ul[@class='FilterDirectory-list']//label"))).click();
		//driver.findElementByXPath("//input[@value='MANGO']//parent::label").click();
		//driver.findElementByXPath("//ul[@class='FilterDirectory-list']//label").click();
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(3000);
		List<WebElement> brandlist = driver.findElementsByXPath("//h3[@class='product-brand']");
		//System.out.println(brandlist.get(0).getText());
		boolean flag=true;
		for (WebElement ls : brandlist) {
			if(!ls.getText().equalsIgnoreCase("mango")) {
				flag=false;
				break;
			}
		}
		if(flag=true)
			System.out.println("All brands are mango");
		else
			System.out.println("All brands are not mango");
		builder.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).build().perform();
		builder.click(driver.findElementByXPath("(//ul[@class='sort-list']//li/label)[3]")).build().perform();
		Thread.sleep(3000);
		List<WebElement> pricelist = driver.findElementsByXPath("//div[@class='product-price']//span/span");
		String firstprice = pricelist.get(0).getText();
		System.out.println("price of first item is " +Integer.parseInt(firstprice.replaceAll("[^0-9]", "")));
		builder.moveToElement(driver.findElementByXPath("//picture[@class='img-responsive']//following::img")).build().perform();
		builder.click(driver.findElementByXPath("//span[@class='product-actionsButton product-wishlist product-prelaunchBtn']")).build().perform();
		driver.close();
		
	}

}
