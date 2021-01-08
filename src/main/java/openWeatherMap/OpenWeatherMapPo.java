package openWeatherMap;

import Driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenWeatherMapPo {

    static WebDriver driver = DriverSetup.getDriver();

    static WebElement searchField = driver.findElement(By.xpath("//input[@placeholder = 'Weather in your city']"));
    static WebElement longAndLat = driver.findElements(By.xpath("//td/p/a")).get(0);
    static WebElement fullWeather = driver.findElements(By.xpath("//td/p")).get(0);
    WebElement headerWeather = driver.findElement(By.className("heading"));


}
