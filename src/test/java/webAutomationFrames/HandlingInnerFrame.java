package webAutomationFrames;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingInnerFrame {
	public static WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://ui.vision/demo/webtest/frames/");
		driver.manage().window().maximize();
	}

	@Test
	public void nestedFrames() {
		// Action Class
		Actions act = new Actions(driver);

		// 1) Handling normal frames [frame 1]
		WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//input[@name=\"mytext1\"]")).sendKeys("Luffy is Joy boy..	");
		driver.switchTo().defaultContent(); // Go back to the main page

		// 2) Handling another frame [frame 2]
		WebElement frame2 = driver.findElement(By.xpath("//frame[@src='frame_2.html']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("One Piece is real..");
		driver.switchTo().defaultContent();

		// 3) Handling inner frames which is the part of frame3
		WebElement frame3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("Sai Charan");
		WebElement inner = driver.findElement(By.xpath(
				"//iframe[@src=\"https://docs.google.com/forms/d/1yfUq-GO9BEssafd6TvHhf0D6QLDVG3q5InwNE2FFFFQ/viewform?embedded=true\"]"));
		driver.switchTo().frame(inner);
		driver.findElement(By.xpath("//span[normalize-space()='I am a human']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Web Testing']")).click();
		driver.switchTo().defaultContent();

		// 4) Handling another frame [frame 4]
		
		// 5) Handling another frame [frame 5]
		WebElement frame5 = driver.findElement(By.xpath("//frame[@src='frame_5.html']"));
		driver.switchTo().frame(frame5);
		driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("Sai Charan");
		// Opening Frame5 link in another tab using Action class
		WebElement f5Link = driver.findElement(By.xpath("//a[normalize-space()='https://a9t9.com']"));
		act.keyDown(Keys.CONTROL).click(f5Link).keyUp(Keys.CONTROL).perform();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
//	public void fillingForm(WebDriver driver) {
//		
//	}
	
}
