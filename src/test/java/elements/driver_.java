package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class driver_ {
    private WebDriver driver = new FirefoxDriver();
    public WebDriver getDriver(){
        System.setProperty("webdriver.gecko.driver","geckodriver.exe");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }
}
