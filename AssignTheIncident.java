package servicenNow;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignTheIncident {

	public static void main(String[] args) throws InterruptedException {
		//Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver =new ChromeDriver();
		driver.get("https://dev65267.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//. Login with valid credentials username as admin and password as vOIqreWIwH47
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Servicenow@123");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		driver.switchTo().defaultContent();
		
		//Enter Incident in filter navigator and press enter
		Thread.sleep(5000);
		WebElement filterSearch = driver.findElement(By.xpath("//input[@name='filter']"));
		filterSearch.sendKeys("incident");
		Thread.sleep(2000);
		filterSearch.sendKeys(Keys.ENTER);
		
		//To get an Existing incident ID to apply for next step
		driver.switchTo().frame(0);
		String inciId = driver.findElement(By.xpath("(//td[@class='vt']/a)[2]")).getText();
		System.out.println(inciId);
		driver.switchTo().defaultContent();
		
		//click on open and Search for the existing incident and click on  the incident
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		driver.switchTo().frame(0);
		WebElement searchBox = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchBox.sendKeys(inciId);
		Thread.sleep(2000);
		searchBox.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).click();
		driver.findElement(By.xpath("(//button[@class='btn btn-default'])[5]")).click();
		driver.switchTo().defaultContent();
		
		//Assign the incident to  Software group
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		System.out.println(driver.getWindowHandle());
		WebElement search1 = driver.findElement(By.xpath("//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input"));
		search1.sendKeys("software");
		Thread.sleep(2000);
		search1.sendKeys(Keys.ENTER);
		WebElement getText1 = driver.findElement(By.xpath("//a[text()='Software']"));
		String text1 = getText1.getText();
		System.out.println(text1);
		getText1.click();
		
		driver.switchTo().window(parentWindow);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@id='lookup.incident.assigned_to']")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(window1.get(1));
		System.out.println(driver.getWindowHandle());
		WebElement getText2 = driver.findElement(By.xpath("//td//tr//a[text()='Don Goodliffe']"));
		String text2 = getText2.getText();
		System.out.println(text2);
		getText2.click();
		
		//Update the incident with Work Notes
		driver.switchTo().window(parentWindow);
		System.out.println(driver.getWindowHandle());
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("(//span[@class='sn-stream-input-decorator']/following-sibling::textarea)[3]")).sendKeys("HARD WORK NEVER FAILS");
		driver.findElement(By.xpath("(//button[text()='Update'])[1]")).click();
		
		//Verify the Assignment group and Assigned for the incident
		String text3 = driver.findElement(By.xpath("(//td[@class='vt']//a)[3]")).getText();
		String text4 = driver.findElement(By.xpath("(//td[@class='vt']//a)[4]")).getText();
		if(text1.equals(text3)) {
			System.out.println("ASSIGNMENT GROUP IS CHANGED TO :"+text1);
		}else{
			System.out.println("ASSIGNMENT GROUP IS NOT CHANGED TO :"+text1);
		}
		if(text2.equals(text4)) {
			System.out.println("INCCIDENT IS ASSIGNED TO :"+text2);
		}else {
			System.out.println("INCCIDENT IS NOT ASSIGNED TO :"+text2);
		}
		
		Thread.sleep(2000);
		driver.close();
	}
}
