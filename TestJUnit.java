import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.*;
import java.util.*;
import java.io.File;
import org.openqa.selenium.support.ui.*;

public class TestJUnit {

	static WebDriver driver;
	static WebElement num1Field;
	static WebElement num2Field;
	static Dictionary<String, WebElement> radioBtns;
	static WebElement submitBtn;

	@BeforeClass
	public static void beforeClass(){
	}

	//reset to default
	@Before 
	public void beforeEach(){
		//set up driver
		File file = new File("chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.get("http://sod73.asu.edu/~jbalasoo/cse464/hw5/modified/math1.php");
		
		//set up common elements
		num1Field = driver.findElement(By.name("num1"));
		num2Field = driver.findElement(By.name("num2"));
		radioBtns = new Hashtable<String, WebElement>();
		List<WebElement> operations = (List<WebElement>)driver.findElements(By.name("operation"));
		for( WebElement operation : operations )
		{
			radioBtns.put(operation.getAttribute("value"),operation);
		}
		submitBtn = driver.findElement(By.name("submit"));

	}

	@After
	public void after(){
		driver.quit();
	}

	@AfterClass
	public static void afterClass(){
		driver.quit();
	}

	@Test
	public void test_001() throws InterruptedException {
		assertEquals(2,sendNumbers(4,2,"SUB"),1e-10);
	}

	@Test
	public void test_002() throws InterruptedException {
		assertEquals(4,sendNumbers(2,2,"ADD"),1e-10);
	}

	@Test
	public void test_003() throws InterruptedException {
		assertEquals(6,sendNumbers(3,2,"MUL"),1e-10);
	}

	@Test
	public void test_004() throws InterruptedException {
		assertEquals(8,sendNumbers(16,2,"DIV"),1e-10);
	}

	@Test
	public void test_005() throws InterruptedException {
		assertEquals(0.33333333333333,sendNumbers(1,3,"DIV"),1e-10);
	}

	@Test
	public void test_006() throws InterruptedException {
		assertEquals(-1,sendNumbers(1,-1,"MUL"),1e-10);
	}

	@Test
	public void test_007() throws InterruptedException {
		assertEquals(1,sendNumbers(-1,-1,"MUL"),1e-10);
	}

	@Test
	public void test_008() throws InterruptedException {
		assertEquals(0,sendNumbers(1,0,"MUL"),1e-10);
	}

	@Test
	public void test_009() throws InterruptedException {
		assertEquals(2.25,sendNumbers(4.5,2,"DIV"),1e-10);
	}

	@Test
	public void test_010() throws InterruptedException {
		assertEquals(0,sendNumbers(0,1,"DIV"),1e-10);
	}

	@Test
	public void test_011() throws InterruptedException {
		assertEquals(1,sendNumbers(0,1,"ADD"),1e-10);
	}

	@Test
	public void test_012() throws InterruptedException {
		assertEquals(1,sendNumbers(1,0,"SUB"),1e-10);
	}

	@Test
	public void test_013() throws InterruptedException {
		assertEquals(1,sendNumbers(0,-1,"SUB"),1e-10);
	}

	@Test
	public void test_014() throws InterruptedException {
		assertEquals(1,sendNumbers(1,1,"DIV"),1e-10);
	}

	@Test
	public void test_015() throws InterruptedException {
		assertEquals(1,sendNumbers(1,1,"MUL"),1e-10);
	}

	@Test
	public void test_016() throws InterruptedException {
		assertEquals(-1,sendNumbers(-2,1,"ADD"),1e-10);
	}

	@Test
	public void test_017() throws InterruptedException {
		assertEquals(3,sendNumbers(2,-1,"SUB"),1e-10);
	}

	private static double sendNumbers(double a, double b, String op){

		num1Field.sendKeys(a+"");
		num2Field.sendKeys(b+"");
		radioBtns.get(op).click();
		submitBtn.click();

		return getResult();

	}

	private static double getResult(){

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("result")));
		WebElement resultField = driver.findElement(By.name("result"));
		return Double.parseDouble(resultField.getAttribute("value"));

	}

}

