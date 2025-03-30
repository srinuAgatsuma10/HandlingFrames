package demoAutomationTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingNested_InnerFrame {
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
	public void nestedInnerFrame() {
		driver.findElement(By.xpath("//a[normalize-space()='Iframe with in an Iframe']")).click();
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(frame1);
		String frame1Text = driver.findElement(By.xpath("//h5[normalize-space()='Nested iFrames']")).getText();
		System.out.println("You are in : " + frame1Text);
		WebElement frame2 = driver
				.findElement(By.xpath("//iframe[normalize-space()='<p>Your browser does not support iframes.</p>']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Monkey D Luffy");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
