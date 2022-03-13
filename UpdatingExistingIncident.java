package servicenNow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdatingExistingIncident {

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
		
		//Search for the existing incident and click on the incident
		driver.switchTo().frame(0);
		String inciId = driver.findElement(By.xpath("(//td[@class='vt']/a)[2]")).getText();
		System.out.println(inciId);
		WebElement searchBox = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchBox.sendKeys(inciId);
		Thread.sleep(2000);
		searchBox.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).click();
		driver.switchTo().defaultContent();
		
		// Update the incidents with Urgency as High and State as In Progress & Verify the priority and state 
		driver.switchTo().frame(0);
		WebElement urgencyDrop = driver.findElement(By.xpath("(//select[@id='incident.urgency']/option)[3]"));
		urgencyDrop.click();
		System.out.println("URGENCY IS SELECTED AS : "+urgencyDrop.getText()+" "+"("+urgencyDrop.isSelected()+")");
		WebElement stateDrop = driver.findElement(By.xpath("(//select[@id='incident.state']/option)[2]"));
		stateDrop.click();
		System.out.println("URGENCY IS SELECTED AS :"+stateDrop.getText()+" "+"("+stateDrop.isSelected()+")");
		driver.findElement(By.xpath("(//button[text()='Update'])[1]")).click();
		
		Thread.sleep(3000);
		driver.close();
	}

}
