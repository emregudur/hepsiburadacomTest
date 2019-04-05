package elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class hepsiBurada {
    private WebElement homeLoginButtonHover;
    private WebElement homeLoginButton;
    private driver_ d;
    private WebDriver driver;
    private WebElement eposta;//id:email
    private WebElement password;//id:password
    private WebElement loginButton;//class:btn-login-submit

    public void hepsiBurada() {
        //load page elements
        this.goHome();

        System.out.println(driver.getTitle());
    }
    private WebDriver getDriver() {
        driver_ d = new driver_();
        WebDriver driver = d.getDriver();
        return driver;
    }
    public void goHome() {
        driver = getDriver();
        driver.get("https://www.hepsiburada.com");//
        String str="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assert.assertEquals(str,driver.getTitle());
        System.out.println("Anasayfaya bağlanıldı.");
    }
    public void goLoginPage() {
        homeLoginButtonHover = driver.findElement(By.id("myAccount"));
        homeLoginButton = driver.findElement(By.id("login"));
        Actions action = new Actions(driver);
        action.moveToElement()
    }
    public void login() {
        eposta = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("password"));
        loginButton= driver.findElement(By.className("btn-login-submit"));

        eposta.sendKeys("emre.gudur@gmail.com");
        password.sendKeys("password123!");
        loginButton.click();

        String str="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assert.assertEquals(str,driver.getTitle());
        System.out.println("Giriş işlemi başarılı");
    }
    public void deneme(){

    }
    public void closeDriver(){
        driver.quit();
    }
}
