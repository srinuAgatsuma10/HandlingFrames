package globalsQA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingInnerFrame {

	/* Test Cases 
	 * 1) Select 'SOFTWARE TESTING' in the dropdown and get the List of courses. 
	 * 2) Click on Manual Testing course and Get the Fees Plans.
	 */
	
	WebDriver driver;
	Actions act;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.globalsqa.com/demo-site/frames-and-windows/");
		driver.manage().window().maximize();
		// Initiate Actions Class
		act = new Actions(driver);
	}

	@Test
	public void handlingDropDown() {
		driver.findElement(By.xpath("//li[@id='iFrame']")).click();
		WebElement frame = driver.findElement(By.xpath("//iframe[@name='globalSqa']"));
		// Switch to Farme
		driver.switchTo().frame(frame);
		// DropDown
		WebElement myDrp = driver.findElement(By.xpath("//span[@id='current_filter']"));
		WebElement softwareTesting = driver.findElement(By.xpath("//div[@data-option-value='.softwaretesting']"));
		act.moveToElement(myDrp).moveToElement(softwareTesting).click().perform();
		List<WebElement> courses = driver.findElements(By.xpath("//img[@decoding='async']"));
		System.out.println("Total Courses : " + courses.size());
		for (WebElement course : courses) {
			String courseName = course.getAttribute("alt");
			System.out.println(courseName);
		}
	}

	@Test
	public void MT_CpursePrice() {
		driver.findElement(By.xpath("//li[@id='iFrame']")).click();
		WebElement frame = driver.findElement(By.xpath("//iframe[@name='globalSqa']"));
		// Switch to Farme
		driver.switchTo().frame(frame);
		// Click on Manual Testing Course
		driver.findElement(By.xpath("//img[@alt='Manual Online Testing Training']")).click();
		driver.findElement(By.xpath("//li[@id='Batch Schedule']")).click();
		List<WebElement> coursePriceList = driver.findElements(By.xpath("//div[@class=\"price_column \"]//ul//li"));
		for (WebElement cpl : coursePriceList) {
			String coloumn = cpl.getText();
			System.out.println(coloumn);
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
