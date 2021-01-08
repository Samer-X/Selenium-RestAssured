package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
    protected static WebDriver driver;

    public static void driverConf() {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
    }


    public static WebDriver parallelDriver(WebDriver parallelDriver) {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        parallelDriver = new ChromeDriver();
        return parallelDriver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
