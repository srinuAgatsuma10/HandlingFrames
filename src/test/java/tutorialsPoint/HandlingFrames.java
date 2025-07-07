package tutorialsPoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingFrames {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.tutorialspoint.com/selenium/practice/frames.php");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void innerFrame1() {
		WebElement Iframe1 = driver.findElement(By.xpath("//body//main//iframe[1]"));
		driver.switchTo().frame(Iframe1);

		driver.findElement(By.xpath("//a[@title='back to Selenium Tutorial']")).click();
		Set<String> newTab = driver.getWindowHandles();
		List<String> tabs = new ArrayList(newTab);
		String parentTab = tabs.get(0);
		String tutorialTab = tabs.get(1);

		driver.switchTo().window(tutorialTab);
		System.out.println("New Tab Name : " + driver.getTitle());
		driver.switchTo().window(parentTab);
	}

//	@Test(priority = 2)
	public void innerFrame2() {

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
