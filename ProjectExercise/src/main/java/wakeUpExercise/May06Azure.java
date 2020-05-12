package Special;

import java.awt.AWTException;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class May06Azure {

	public static void main(String[] args) throws InterruptedException, AWTException {
				//
				//1) 1) Go to https://azure.microsoft.com/en-in/
				//--------------------------------------------
				
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			      //  chromePrefs.put("profile.default_content_settings.popups", 0);
			        chromePrefs.put("plugins.always_open_pdf_externally", true);
			        chromePrefs.put("download.default_directory", "D:\\downloads");
			     //   chromePrefs.put("safebrowsing.enabled", "false");
			        ChromeOptions options = new ChromeOptions();
			        options.setExperimentalOption("prefs", chromePrefs);
				
				
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
				ChromeDriver driver = new ChromeDriver();
			
				driver.get("https://azure.microsoft.com/en-in/");
				
				driver.manage().window().maximize();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				Thread.sleep(2000);
				
				WebDriverWait wait = new WebDriverWait(driver,30);
				

				//	2) Click on Pricing
				//------------------------------------------------------
				
				//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@class='optanon-allow-all accept-cookies-button']")));
				
				driver.findElementById("navigation-pricing").click();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				Thread.sleep(1000);
				
				//3) Click on Pricing Calculator
				//------------------------------------------------------
				
				driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				//4) Click on Containers
				//---------------------
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[text()='Containers']")));
				
				driver.findElementByXPath("//button[text()='Containers']").click();
				
				//5) Select Container Instances
				//----------------------------
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[@class='service-heading' and text()='Container Instances'])[2]")));
				
				driver.findElementByXPath("(//span[@class='service-heading' and text()='Container Instances'])[2]").click();
				
				//6) Click on Container Instance Added View
				//-----------------------------------------
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='View']")));
				
				driver.findElementByXPath("//a[text()='View']").click();
				

				//7) Select Region as "South India"
				//---------------------------------
				
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@aria-label='Region']")));
				
				WebElement ele_Region = driver.findElementByXPath("//select[@aria-label='Region']");
				
				Select slt_Region = new Select(ele_Region);
				
				slt_Region.selectByVisibleText("South India");
				
				
				//8) Set the Duration as 180000 seconds
				//------------------------------------
				
				driver.findElementByXPath("//input[@aria-label='Seconds']").clear();
				
				driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys(Keys.BACK_SPACE);
				driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys(Keys.HOME);
				
				driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys("18000");
				
				//9) Select the Memory as 4GB
				//=-------------------------
				
				
				WebElement ele_Memory = driver.findElementByXPath("//select[@aria-label='Memory']");
				
				Select slt_Memory = new Select(ele_Memory);
				
				slt_Memory.selectByVisibleText("4 GB");
				
				//10) Enable SHOW DEV/TEST PRICING
				//-------------------------------
				
				driver.findElementByXPath("//label[@for='devtest-toggler']").click();
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='toggler-slide active']")));
				
				//11) Select Indian Rupee  as currency
				//------------------------------------
				
				WebElement ele_Currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
				
				Select slt_Currency = new Select(ele_Currency);
				
				slt_Currency.selectByVisibleText("Indian Rupee (₹)");
				
				
				//12) Print the Estimated monthly price
				//------------------------------------
				
				String estMonthlyPrice = driver.findElementByXPath("(//div[@class='column large-3 text-right total']//span[@class='numeric']/span)[2]").getText();
				estMonthlyPrice = estMonthlyPrice.replaceAll("[^0-9.]", "");
				
				System.out.println("Est monthly price = "+estMonthlyPrice);
				
				//13) Click on Export to download the estimate as excel
				//-----------------------------------------------------
				
				driver.findElementByXPath("//button[text()='Export']").click();
				
				
				//14) Verify the downloded file in the local folder
				//---------------------------------------------------
				File file = new File("C:\\Users\\ADMIN\\Downloads\\ExportedEstimate.xlsx");
				if(file.exists())
				{
					System.out.println("Estimate Downloaded successfully");
				}
				else
				{
					System.out.println("Estimate does not exists in the expected folder");
				}
				
				//15) Navigate to Example Scenarios and Select CI/CD for Containers
				//--------------------------------------------------------------
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();
				
				Thread.sleep(1000);
				driver.findElementByXPath("//a[text()='Example Scenarios']").click();
				
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@title='CI/CD for Containers']")));
				
				driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();
				
				//16) Click Add to Estimate
				//---------------------------
				
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[text()='Add to estimate']")));
				
				action.moveToElement(driver.findElementByXPath("//button[text()='Add to estimate']")).click().build().perform();
				
				//driver.findElementByXPath("//button[text()='Add to estimate']").click();
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='lp_close-icon lp_icon-white'])[2]")));
				
				driver.findElementByXPath("(//div[@class='lp_close-icon lp_icon-white'])[2]").click();
				
				
				//17) Change the Currency as Indian Rupee
				//--------------------------------------
				Thread.sleep(2000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@class='select currency-dropdown']")));
				
				ele_Currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
				
				slt_Currency = new Select(ele_Currency);
				
				slt_Currency.selectByVisibleText("Indian Rupee (₹)");
				
				
				//18) Enable SHOW DEV/TEST PRICING
				//----------------------------------
				Thread.sleep(2000);
				
				try {
					if(!driver.findElementByXPath("//div[@class='toggler-slide active']").isEnabled())
					{
						wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[@for='devtest-toggler']")));
						
						action.moveToElement(driver.findElementByXPath("//label[@for='devtest-toggler']")).perform();
						
						driver.findElementByXPath("//label[@for='devtest-toggler']").click();
						
						wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='toggler-slide active']")));
					}
					else
					{
						wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='toggler-slide active']")));
					}
				} catch (NoSuchElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				//19) Export the Estimate
				//-------------------------
				
				Thread.sleep(2000);
				driver.findElementByXPath("//button[text()='Export']").click();
				
				file = new File("C:\\Users\\ADMIN\\Downloads\\ExportedEstimate.xlsx");
				if(file.exists())
				{
					System.out.println("Estimate Downloaded successfully");
				}
				else
				{
					System.out.println("Estimate does not exists in the expected folder");
				}
				
				
				driver.quit();
				
			}


	}
