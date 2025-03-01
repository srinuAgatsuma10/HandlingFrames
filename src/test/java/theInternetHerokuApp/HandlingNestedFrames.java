package theInternetHerokuApp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingNestedFrames {

	public static WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.manage().window().maximize();
	}

	@Test
	public void nestedFrames() {

		// Accessing Top order frames
		WebElement topFrames = driver.findElement(By.xpath("//frame[@name='frame-top']"));
		driver.switchTo().frame(topFrames);

		// Accessing leftFrame
		WebElement leftFrame = driver.findElement(By.xpath("//frame[@name='frame-left']"));
		driver.switchTo().frame(leftFrame);
		String leftFrameText = driver.findElement(By.xpath("//body")).getText();
		System.out.println("This is " + leftFrameText + " Frame");
		driver.switchTo().defaultContent(); // Go back to the main page
		driver.switchTo().frame(topFrames);

		// Accessing middleFrame
		WebElement middleFrame = driver.findElement(By.xpath("//frame[@name='frame-middle']"));
		driver.switchTo().frame(middleFrame);
		String middleFrameText = driver.findElement(By.xpath("//body")).getText();
		System.out.println("This is " + middleFrameText + " Frame");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(topFrames);

		// Accessing rightFrame
		WebElement rightFrame = driver.findElement(By.xpath("//frame[@name='frame-right']"));
		driver.switchTo().frame(rightFrame);
		String rightFrameText = driver.findElement(By.xpath("//body")).getText();
		System.out.println("This is " + rightFrameText + " Frame");
		driver.switchTo().defaultContent();

		// Accessing bottomFrame
		WebElement bottomFrame = driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
		driver.switchTo().frame(bottomFrame);
		String bottomFrameText = driver.findElement(By.xpath("//body")).getText();
		System.out.println("This is " + bottomFrameText + " Frame");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
