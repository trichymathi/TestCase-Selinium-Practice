package servicenNow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewIncident {

	public static void main(String[] args) throws InterruptedException {
		//Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver =new ChromeDriver();
		driver.get("https://dev98106.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//. Login with valid credentials username as admin and password as vOIqreWIwH47
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("vOIqreWIwH47");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		driver.switchTo().defaultContent();
		
		//Enter Incident in filter navigator and press enter
		Thread.sleep(3000);
		WebElement filterSearch = driver.findElement(By.xpath("//input[@name='filter']"));
		filterSearch.sendKeys("incident");
		Thread.sleep(2000);
		filterSearch.sendKeys(Keys.ENTER);
		
		//Click on Create new option and fill the manadatory fields
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//button[text()='New'])")).click();
		driver.switchTo().defaultContent();
		
		//to get the inccident number and verify for future
		driver.switchTo().frame(0);
		WebElement getInciNo = driver.findElement(By.xpath("//input[@id='incident.number']"));
		String attribute = getInciNo.getAttribute("value");
		System.out.println(attribute);
		
		//fill the manadatory fields
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("ALL IS WELL");
		driver.findElement(By.xpath("(//button[text()='Submit'])[1]")).click();
		driver.switchTo().defaultContent();
		
		/*copy the incident number and paste it in search field and enter then verify the
		instance number created or not)*/
		driver.switchTo().frame(0);
		WebElement searchInciNo = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchInciNo.sendKeys(attribute);
		Thread.sleep(2000);
		searchInciNo.sendKeys(Keys.ENTER);
		
		//to get the inccident number for verification
		WebElement verifyNo = driver.findElement(By.xpath("//td[@class='vt']//a"));
		String iccNo = verifyNo.getText();
		System.out.println(iccNo);
		driver.switchTo().defaultContent();
		
		// verify the instance number created or not)
		if(attribute.equals(iccNo)) {
			System.out.println("The Instance Is Created");
		}else {
			System.out.println("The Instance Is Not Created");
		}
		
		//To close the window
		Thread.sleep(2000);
		driver.close();
	}
}