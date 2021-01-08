package openWeatherMap;

import commonHelper.CommonHelper;
import org.openqa.selenium.WebDriver;

public class OpenWeatherMapHelper {

    public static void openWebSite(WebDriver driver) {
        CommonHelper.openWebPage(OpenWeatherMapConstants.url, driver);
    }


    public static void clickFirstCityOption(String city, WebDriver driver) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonHelper.clickWhenReady(CommonHelper.firstResult(city, driver), driver);
    }

    public static String getLongAndLat(WebDriver driver) {
        return CommonHelper.getText(OpenWeatherMapPo.longAndLat, driver);
    }

    public static String fullWeather(WebDriver driver) {
        return CommonHelper.getText(OpenWeatherMapPo.fullWeather, driver);
    }

    public static String getFirstForeCastWeather(WebDriver driver) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String firstWeather = CommonHelper.retryingFind(OpenWeatherMapConstants.firstWeatherXpath, driver).getText();
        return firstWeather;
    }

    public static String getHeaderWeather(WebDriver driver) {
        OpenWeatherMapPo openWeatherMapPo = new OpenWeatherMapPo();
        String headerWeather = null;
        try {
            headerWeather = CommonHelper.getText(openWeatherMapPo.headerWeather, driver);

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        }
        return headerWeather;
    }

    public static void searchForCity(String city, WebDriver driver) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonHelper.retryingFind(OpenWeatherMapConstants.searchFieldXpath, driver).sendKeys(city);
        CommonHelper.clickEnter(CommonHelper.retryingFind(OpenWeatherMapConstants.searchFieldXpath, driver));
    }


}
