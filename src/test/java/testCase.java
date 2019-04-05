import elements.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class testCase {
    hepsiBurada hepsiburadacom;
    @Test
    public void login() throws InterruptedException {
        hepsiburadacom = new hepsiBurada();
        hepsiburadacom.goHome();

        hepsiburadacom.login();

        hepsiburadacom.seacrh("iphone");

    }
    @After
    public void close() throws InterruptedException {
        Thread.sleep(5000);
        hepsiburadacom.logOut();
    }
}
