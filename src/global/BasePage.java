package global;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BasePage {
	
	public final WebDriver driver;

	public BasePage() {
        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
	
	public void stopDriver(){
		driver.quit();
	}
	
	public void waitTime(long milSec ){
		try {
			Thread.sleep(milSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
