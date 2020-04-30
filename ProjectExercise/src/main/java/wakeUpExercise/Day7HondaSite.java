package wakeUpExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day7HondaSite {

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
		driver.get("https://www.honda2wheelersindia.com/");
		
		/* Pop up close and click on scooter	 */
		driver.findElementByXPath("//button[@class='close']").click();
		driver.findElementById("link_Scooter").click();
		
		/* Webdriver wait - element to be clickable to click on dio scooter	 */
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@href='/dio-BS-VI/']//parent::div"))).click();
		
		/* Click on specifications	*/
		driver.findElementByXPath("//a[text()='Specifications']").click();
		
		/* Using Actions- move to element-Pause for 3 seconds - click on engine	 */
		Actions builder = new Actions(driver);
		builder.pause(3000).moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).build().perform();
		//builder.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).build().perform();
		
		/* used regex to take only the decimal part and store and print as double */
		double diodisplacement = Double.parseDouble(driver.findElementByXPath("//div[@class='engine part-2 axx']//li[3]//span[text()='Displacement']//following::span").getText().replaceAll("[^.?0-9]+", ""));
		System.out.println("Displacement of Dio is "+diodisplacement);
		
		/* Click scooter - activa 125 - specifications */
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("//div[@class='owl-item']//a[@href='/activa125-BS-VI/']").click();
		driver.findElementByXPath("//a[text()='Specifications']").click();
		
		/* Using Actions- move to element-Pause for 3 seconds - click on engine	 */
		builder.pause(3000).moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).build().perform();
		
		/* used regex to take only the decimal part and store and print as double *//* 	 */
		double activadisplacement = Double.parseDouble(driver.findElementByXPath("//div[@class='engine part-4 axx']//li[3]//span[text()='Displacement']//following::span").getText().replaceAll("[^.?0-9]+", ""));
		System.out.println("Displacement of Dio is "+activadisplacement);
		
		/* Compare displacement of dio to activa and print the displacement which is greater */
		if(diodisplacement > activadisplacement)
			System.out.println("Dio displacement "+diodisplacement+" is greater than Activa");
		else
			System.out.println("Activa displacement "+activadisplacement+" is greater than Dio");
		
		/* Click FAQ - Active 125 on browser by product - vehicle price */
		driver.findElementByXPath("//div[@id='bs-example-navbar-collapse-1']//a[text()='FAQ']").click();
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
		driver.findElementByXPath("//a[text()[contains(.,'Vehicle Price')]]").click();
		
		/* 	Get the dropdown value of product, if  Activa 125, then click on submit to retrieve price */
		String bikemodel = driver.findElementByXPath("//select[@id='ModelID6']//option[@selected='selected']").getText();
		if(bikemodel.equalsIgnoreCase("Activa 125 BS-VI"))
			driver.findElementById("submit6").click();
		else
			System.out.println("Bike model is not matching to "+bikemodel);
		
		/* Click on price link - navigate to new window	 */
		driver.findElementByXPath("//table[@id='tblPriceMasterFilters']//tbody//tr//td[3]//a").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winls = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winls.get(1));
		
		/* select state dropdown value using visible text and select class	 */
		WebElement statedrpdwn = driver.findElementById("StateID");
		Select statedd= new Select(statedrpdwn);
		statedd.selectByVisibleText("Tamil Nadu");
		
		/* select city dropdown value using visible text and select class and click search */
		WebElement citydrpdwn = driver.findElementById("CityID");
		Select citydd= new Select(citydrpdwn);
		citydd.selectByVisibleText("Chennai");
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(3000);
		
		/* Identify table element, using findelements, get row and columns, iterate through it and print the value except chennai	 */
		WebElement table = driver.findElementByXPath("//table[@id='gvshow']//following::tbody");		
		List<WebElement> rowlist = table.findElements(By.tagName("tr"));
		for (int i = 0; i < rowlist.size(); i++) {
			List<WebElement> collist = rowlist.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < collist.size(); j++) {
				if(!collist.get(j).getText().equalsIgnoreCase("Chennai"))
					System.out.print(collist.get(j).getText());
			}
			System.out.print("\n");
		}

	}

}
