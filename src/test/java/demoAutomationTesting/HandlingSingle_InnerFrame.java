package demoAutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingSingle_InnerFrame {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.automationtesting.in/Frames.html");
		driver.manage().window().maximize();
	}

	@Test
	public void singleInnerFrame() {
		WebElement singleFrame = driver.findElement(By.xpath("//iframe[@id='singleframe']"));
		driver.switchTo().frame(singleFrame);
		String frameName = driver.findElement(By.xpath("//h5[normalize-space()='iFrame Demo']")).getText();
		System.out.println("This is : " + frameName);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Monkey D Luffy");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
