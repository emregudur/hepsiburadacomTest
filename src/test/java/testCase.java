import elements.*;
import org.junit.After;
import org.junit.Test;

public class testCase {
    hepsiBurada hepsiburadacom;
    @Test
    public void startTest() throws InterruptedException {
        hepsiburadacom = new hepsiBurada();
        hepsiburadacom.goHome();

        hepsiburadacom.login();

        hepsiburadacom.seacrh("iphone");

    }
    @After
    public void close() throws InterruptedException {
        Thread.sleep(5000);
        hepsiburadacom.dquit();
    }
}
