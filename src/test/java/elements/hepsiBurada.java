package elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class hepsiBurada {
    private driver_ d;
    private WebDriver driver;
    private WebElement homeLoginButtonHover;//id:myAcount
    private WebElement eposta;//id:email
    private WebElement password;//id:password
    private WebElement loginButton;//class:btn-login-submit
    private WebElement searchBox;//id:productSearch
    private WebElement searchPageButton;//class:page-2
    private WebElement searchPageTwoProduct;//class:page-2
    // class:keyword
    private List<WebElement> products;//class:product-detail
    private WebElement productDetailFavButton;//linktext:Favori Listeme Ekle

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
    private void goLoginPage() throws InterruptedException {
        homeLoginButtonHover = driver.findElement(By.id("myAccount"));
        Actions action = new Actions(driver);
        action.moveToElement(homeLoginButtonHover).perform();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
    }
    public void login() throws InterruptedException {
        goLoginPage();
        eposta = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("password"));
        loginButton= driver.findElement(By.className("btn-login-submit"));

        eposta.sendKeys("emre.gudur@gmail.com");
        password.sendKeys("password123!");
        loginButton.click();
    }
    public void seacrh(String searchText){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("footer-end-wrapper")));

        String str="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assert.assertEquals(str,driver.getTitle());
        System.out.println("Giriş işlemi başarılı");

        searchBox = driver.findElement(By.id("productSearch"));
        searchBox.sendKeys(searchText);
        searchBox.submit();
        goSearchPageTwoAndClickProduct();
    }
    private void goSearchPageTwoAndClickProduct(){
        //iphone kelimesi arama kontrolü
        String str="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assert.assertEquals("\"iphone\"",driver.findElement(By.className("keyword")).getText());
        System.out.println("Arama işlemi doğru");

        searchPageButton = driver.findElement(By.className("page-2"));
        searchPageButton.click();
        Assert.assertEquals("page-2 active",driver.findElement(By.className("page-2")).getAttribute("class"));
        System.out.println("2. Sayfaya tıklandı.");

        products = driver.findElements(By.className("product-detail"));
        products.get(2).click();

        productDetailFavButton = driver.findElement(By.linkText("Favori Listeme Ekle"));
        productDetailFavButton.click();
        goFavPage();
    }
    private void goFavPage(){
        homeLoginButtonHover = driver.findElement(By.id("myAccount"));
        Actions action = new Actions(driver);
        action.moveToElement(homeLoginButtonHover).perform();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
    }
    public void logOut(){

        driver.quit();
    }
}
