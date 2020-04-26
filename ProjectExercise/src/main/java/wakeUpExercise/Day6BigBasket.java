package wakeUpExercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day6BigBasket {

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
		driver.get("https://www.bigbasket.com/");
		
		/* Mouse hover on shop by category menu using 'Visibility of elements' and move to elements using Actions */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions builder = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()[contains(.,' Shop by Category ')]]")));
		builder.moveToElement(driver.findElementByXPath("//a[text()[contains(.,' Shop by Category ')]]")).build().perform();
		
		/* Using 'Visibility of elements' and move to elements using Actions for foodgrains-oil-masala and rice & rice products*/
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//li[@data-submenu-id='foodgrains-oil-masala']//a)[2]")));
		builder.moveToElement(driver.findElementByXPath("(//li[@data-submenu-id='foodgrains-oil-masala']//a)[2]")).build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@id='foodgrains-oil-masala']//a[text()='Rice & Rice Products'])[2]")));
		builder.click(driver.findElementByXPath("(//div[@id='foodgrains-oil-masala']//a[text()='Rice & Rice Products'])[2]")).build().perform();
		
		/* Using webdriver wait-'elementtobe clickable' - Boiled & Steam Rice*/
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Boiled & Steam Rice']//ancestor::label//parent::a"))).click();
		
		/* Using webdriver wait-'elementtobe clickable' - bb Royal*/
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='bb Royal']"))).click();
		
		/* Using webdriver wait-'elementtobe clickable' - weight dropdown and select 5kg */
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']")));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']//parent::div//following::div[@class='btn-group btn-input clearfix ng-scope']"))).click();
		driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']//parent::div//following::div//span//li//span[text()='5 kg']//parent::a").click();
		
		/* get price of rice and convert into integer */
		int riceprice = Integer.parseInt(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']//parent::div//following-sibling::div[2]//div[@qa='price']//span[@class='discnt-price']//span").getText());
		System.out.println("Price of the rice is "+riceprice);
		
		/* Click on Add cart button */
		driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']//parent::div//following-sibling::div[2]//div[@class='delivery-opt']//div[2]//div[2]//button").click();
		
		/* Click on continue button on the location pop up */
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@qa='continueBtn']"))).click();
		//String success = driver.findElementByXPath("//div[@class='toast-title']").getText();
		//System.out.println(success);
		
		/* Enter dal in search and press enter */
		driver.findElementById("input").sendKeys("dal", Keys.ENTER);
		Thread.sleep(3000);
		
		/* Using webdriver wait-'Visibility of elements', click on dropdown, select 2kg, enter quantity as 2kg */
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']")));
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div//button").click();
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div//span[text()='2 kg']//parent::a").click();
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div[2]//div[@class='delivery-opt']//input").clear();
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div[2]//div[@class='delivery-opt']//input").sendKeys("2");
		
		/* get price of dal and convert into integer */
		int dalprice = Integer.parseInt(driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div[2]//span[@class='discnt-price']//span").getText());
		System.out.println("Price of the dal is "+dalprice);
		
		/* Click on Add cart button */
		driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']//parent::div//following-sibling::div[2]//div[@class='delivery-opt']//div[2]//div[2]//button").click();
		
		/* Using webdriver wait-'Visibility of elements', print success message and close the message */
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementByXPath("//div[@class='toast-title']")));
		String success = driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(success);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		/* Using webdriver wait-'Visibility of elements' and actions class - move to element - cart icon */
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElementByXPath("//a[@qa='myBasket']")));
		builder.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).build().perform();
		Thread.sleep(3000);
		
		/* get subtotal - convert string to double and then convert double to integer */
		String subtot1 = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		System.out.println("Subtotal price for 2dal and 1 rice is "+subtot1);
		double subtotal1 = Double.parseDouble(subtot1);
		int subtotalvalue1 = (int) subtotal1;
		
		/* Check subtotal is equal to 2 dal and 1 rice price */
		if(subtotalvalue1==(riceprice+(2*dalprice)))
			System.out.println("Subtotal is matching for 2 dal and 1 rice price");
		else
			System.out.println("Subtotal is not matching for 2 dal and 1 rice price");
		
		/* Decrease the quantity of dal as 1 */
		driver.findElementByXPath("//a[text()[contains(.,'bb Popular Toor/Arhar Dal 2 kg Pouch')]]//following::div[@class='btn-counter row']//button[@qa='decQtyMB']").click();
		Thread.sleep(3000);
		
		/* get subtotal - convert string to double and then convert double to integer */
		String subtot2 = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
		System.out.println("Subtotal price for 1dal and 1 rice is "+subtot2);
		double subtotal2 = Double.parseDouble(subtot2);
		int subtotalvalue2 = (int) subtotal2;
		
		/* Check subtotal is equal to 1 dal and 1 rice price */
		if(subtotalvalue2==(riceprice+(1*dalprice)))
			System.out.println("Subtotal is matching for 1 dal and 1 rice price");
		else
			System.out.println("Subtotal is not matching for 1 dal and 1 rice price");
		driver.close();
	}

}
