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


public class VidMeFollow {

	public static final String menu =
	"Menu:\n"+
	"0. quit\n"+
	"1. display number of follow buttons\n"+
	"2. follow all users on page\n";
	static WebDriver driver;
	static List<WebElement> followButtons;

	public static void main(String[] args){
		//set up driver
		File file = new File("chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.get("https://vid.me/users");
		
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		do{
			System.out.println(menu);
			int input = scan.nextInt();
			System.out.println();
			
			switch(input){

				case 1:
					follow();
					break;

				case 2:
					followUsers();
					break;

				case 0:
				default:
					run = false;
			}

		}
		while(scan.hasNext() && run);
	} 

	public static void follow(){

		//set up common elements
		followButtons = driver.findElements(By.xpath("//*[contains(text(), 'Follow')]"));
		System.out.println(followButtons.size());
	}
	
	public static void followUsers(){

		followButtons = driver.findElements(By.xpath("//a[contains(@class, 'user_follow') and not(contains(@class, 'following'))]"));
		for (WebElement followBtn : followButtons ){
			try{
				followBtn.click();
				System.out.println("Follow success");
			}
			catch(WebDriverException e){
				System.err.println(e);
			}
		}

	}

}
