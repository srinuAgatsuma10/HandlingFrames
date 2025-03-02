package webAutomationFrames;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
		fillingForm(driver);
		driver.switchTo().defaultContent();

		// 4) Handling another frame [frame 4]
		WebElement frame4 = driver.findElement(By.xpath("//frame[@src='frame_4.html']"));
		driver.switchTo().frame(frame4);
		driver.findElement(By.xpath("//input[@name='mytext4']")).sendKeys("You are my Treasure");
		driver.switchTo().defaultContent();
		
		// 5) Handling another frame [frame 5]
		WebElement frame5 = driver.findElement(By.xpath("//frame[@src='frame_5.html']"));
		driver.switchTo().frame(frame5);
		driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("Everything if Food");
		// Opening Frame5 link in another tab using Action class
		WebElement f5Link = driver.findElement(By.xpath("//a[normalize-space()='https://a9t9.com']"));
		act.keyDown(Keys.CONTROL).click(f5Link).keyUp(Keys.CONTROL).perform();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void fillingForm(WebDriver driver) {
		driver.findElement(By.xpath("//span[normalize-space()='I am a human']")).click();
		//driver.findElement(By.xpath("//Iv[@id='i21']//div[@class='uHMk6b fsHoPb']")).click();
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@role='list']//div[@role=\"list\"]//div[@role=\"listitem\"]"));
		for(WebElement cb : checkBoxes) {
			cb.click();
		}
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("My Name reached to the Heaven");
		driver.findElement(By.xpath("//div[@class='Pc9Gce Wic03c']")).sendKeys("To open a link in another tab using Selenium with Java, you can use one of the following approaches:");
		// Submitting the Form.
		//driver.findElement(By.xpath("//span[contains(text(),'Submit')]")).click();
		// Clearing the Form.
		driver.findElement(By.xpath("//span[contains(text(),'Clear form')]")).click();	
	}
	
}
