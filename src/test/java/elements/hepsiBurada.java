package elements;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private List<WebElement> products;//class:product-detail
    private WebElement productDetailFavButton;//linktext:Favori Listeme Ekle
    private String clickedProductName;

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
        action.moveToElement(homeLoginButtonHover).build().perform();

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
    public void seacrh(String searchText) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("footer-end-wrapper")));

        String str="Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assert.assertEquals(str,driver.getTitle());
        System.out.println("Giriş işlemi başarılı");

        searchBox = driver.findElement(By.id("productSearch"));
        searchBox.clear();
        searchBox.sendKeys(searchText);
        searchBox.submit();
        goSearchPageTwoAndClickProduct();
    }
    private void goSearchPageTwoAndClickProduct() throws InterruptedException {
        //iphone kelimesi arama kontrolü
        Assert.assertEquals("iphone",driver.findElement(By.className("keyword")).getText().replace("\"",""));
        System.out.println("Arama işlemi doğru");

        searchPageButton = driver.findElement(By.className("page-2"));
        searchPageButton.click();
        Assert.assertEquals("page-2 active",driver.findElement(By.className("page-2")).getAttribute("class"));
        System.out.println("2. Sayfaya tıklandı.");

        products = driver.findElements(By.className("product-detail"));
        products.get(2).click();

        productDetailFavButton = driver.findElement(By.linkText("Favori Listeme Ekle"));
        productDetailFavButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Alışverişe devam et"))).click();
        clickedProductName = driver.findElement(By.id("product-name")).getText();

        goFavPage();
    }
    private void goFavPage() throws InterruptedException {
        homeLoginButtonHover = driver.findElement(By.linkText("Hesabım"));
        Actions action = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-(window.scrollY))", "");
        action.moveToElement(homeLoginButtonHover).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Favori Listem"))).click();

        Assert.assertEquals("Favori Listem - Hepsiburada.com", driver.getTitle());
        System.out.println(driver.getTitle()+ " sayfası açıldı.");

        //Silinecek olan elemanın isimini tutuyorum
        //Bu isim ile favoriler sayfasında ürünü buluyorum
        //bulduğum ürünün id'si solundaki checkboxun idsine benzer o yüzden replace yapıyorum
        //Örneğin:
        //*[@id="ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_hplProductName"]: ürünün link idsi
        //*[@id="ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_chkSelect"]: checbox id'si
        String id = driver.findElement(By.linkText(clickedProductName)).getAttribute("id");
        id = id.replace("hplProductName","chkSelect");
        driver.findElement(By.id(id)).click();

        driver.findElement(By.linkText("Sil")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertEquals(0, driver.findElements(By.linkText(clickedProductName)).size());
        System.out.println(clickedProductName +" isimli ürün favorilerden çıkarıldı.");
        logOut();
    }
    private void logOut(){
        homeLoginButtonHover = driver.findElement(By.id("myAccount"));

        Actions action = new Actions(driver);
        action.moveToElement(homeLoginButtonHover).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Çıkış Yap"))).click();;
    }
    public void dquit(){
        driver.quit();
    }
}
