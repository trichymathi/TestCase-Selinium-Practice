package servicenNow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteTheIncident {

	public static void main(String[] args) throws InterruptedException {
		//Launch ServiceNow application
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver =new ChromeDriver();
				driver.get("https://dev65267.service-now.com");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
				//Login with valid credentials username as admin and password as vOIqreWIwH47
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
				
				// Search for the existing incident and navigate into the incident
				driver.switchTo().frame(0);
				String inciId = driver.findElement(By.xpath("(//td[@class='vt']/a)[3]")).getText();
				System.out.println(inciId);
				WebElement searchBox = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
				searchBox.sendKeys(inciId);
				Thread.sleep(2000);
				searchBox.sendKeys(Keys.ENTER);
				driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).click();
				driver.switchTo().defaultContent();
				
				//Delete the incident
				driver.switchTo().frame(0);
				driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
				driver.findElement(By.xpath("(//button[text()='Delete'])[3]")).click();
				driver.switchTo().defaultContent();
				
				//Verify the deleted incident
				Thread.sleep(5000);
				driver.switchTo().frame(0);
				WebElement search = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
				search.sendKeys(inciId);
				Thread.sleep(2000);
				search.sendKeys(Keys.ENTER);
				
				String verify = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr/td")).getText();
		        System.out.println(verify);
		        String text = "No records to display";
		        if(verify.equals(text)) {
		        	System.out.println("The Incident"+" "+inciId+" "+"Is Deleted Sucessfully");
		        }else {
		        	System.out.println("The Incident"+inciId+"Is Not Deleted");
		        }
		        Thread.sleep(2000);
		        driver.close();
	}
}