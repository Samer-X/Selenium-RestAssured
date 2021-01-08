package FE_TCS;

import Driver.DriverSetup;
import openWeatherMap.OpenWeatherMapHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class twoCities {
    String cairo = "Cairo";
    String cairoWeather;
    String paris = "Paris";
    String parisWeather;
    WebDriver driver1;
    WebDriver driver2;

    @BeforeClass()
    void initiate() {
        driver1 = DriverSetup.parallelDriver(driver1);
        driver2= DriverSetup.parallelDriver(driver2);
        driver1.manage().window().maximize();
        driver2.manage().window().maximize();
    }

    @Test()
    void cairoCityWeather() {

        OpenWeatherMapHelper.openWebSite(driver1);
        OpenWeatherMapHelper.searchForCity(cairo, driver1);
        OpenWeatherMapHelper.clickFirstCityOption(cairo, driver1);
        cairoWeather = OpenWeatherMapHelper.getFirstForeCastWeather(driver1);
        System.out.println(cairoWeather);
    }

    @Test()
    void parisCityWeather() {

        OpenWeatherMapHelper.openWebSite(driver2);
        OpenWeatherMapHelper.searchForCity(paris, driver2);
        OpenWeatherMapHelper.clickFirstCityOption(paris, driver2);
        parisWeather = OpenWeatherMapHelper.getFirstForeCastWeather(driver2);
        System.out.println(parisWeather);
    }

    @AfterClass()
    void closure() {
        driver1.close();
        driver1.quit();
        driver2.close();
        driver2.quit();
    }
}
