package wakeUpExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTripDay3 {

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
		driver.get("https://www.makemytrip.com/");
		
		/* Top menu - hotels click */
		driver.findElementByXPath("//li[@class='menu_Hotels']//a").click();
		
		/* Selecting the city - using keyboard keys in sendkeys without using Actions*/
		driver.findElementById("city").click();
		WebElement city = driver.findElementByXPath("//input[@type='text' and @placeholder='Enter city/ Hotel/ Area/ Building']");
		city.sendKeys("Goa", Keys.ARROW_DOWN);
		Thread.sleep(2000);
		city.sendKeys(Keys.ENTER);
		
		/* Selecting checkin date as next month 15 - this code will run dynamically even if month changes */
		driver.findElementById("checkin").click();
		//driver.findElementByXPath("//p[@data-cy='checkInDate']").click();
		WebElement checkindate = driver.findElementByXPath("//div[@class='DayPicker-Months']//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Body']//div/div[text()='15']");
		checkindate.click();
		
		/* Selecting checkout date as next month 20 - this code will run dynamically even if month changes */
		int checkoutdate = (Integer.parseInt(checkindate.getText()))+5;
		driver.findElementByXPath("//div[@class='DayPicker-Months']//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Body']//div/div[text()='"+checkoutdate+"']").click();
		
		/* Selecting adults-2, children-1, apply and search button */
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		driver.findElementByXPath("//button[text()='APPLY']").click();
		driver.findElementById("hsw_search_button").click();
		
		/* Handled a black screen which covered the whole window using just finding element and click */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']"))).click();
		
		/* Select Baga, 5Star and click 2nd hotel */
		driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
		driver.findElementByXPath("//label[text()='5 Star']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("(//div[@class='flexOne appendLeft20'])[2]").click();
		
		/* Switching to new window using windowhandles */
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(windowHandles);
		driver.switchTo().window(ls.get(1));
		
		/* Print Hotel name */
		System.out.println("Top rated hotel is " +driver.findElementById("detpg_hotel_name").getText());
		
		/* Click More options, select 3months and close */
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		driver.findElementByXPath("//td[text()='3']//following-sibling::td[@class='textRight']//span[text()='SELECT']").click();
		driver.findElementByXPath("//span[@class='close']").click();
		
		/* Click Book this now and print total payable money */
		driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
		System.out.println("Total payable amount is "+driver.findElementById("revpg_total_payable_amt").getText());
		driver.quit();		
	}

}
