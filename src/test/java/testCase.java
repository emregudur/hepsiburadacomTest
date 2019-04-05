import elements.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class testCase {
    hepsiBurada hepsiburadacom;
    @Test
    public void login(){
        hepsiburadacom= new hepsiBurada();
        hepsiburadacom.goHome();
        hepsiburadacom.login();

    }
    @After
    public void close() throws InterruptedException {
        Thread.sleep(5000);
        hepsiburadacom.closeDriver();
    }
}
