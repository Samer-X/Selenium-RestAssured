package FE_BE_TCS;

import Driver.DriverSetup;
import commonHelper.CommonHelper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import openWeatherMap.OpenWeatherMapHelper;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class londonCity extends DriverSetup {
    String london = "London";
    String londonWeather;
    String location;
    Integer maxWeatherFE;
    Integer minWeatherFE;
    Integer headerWeather;
    String longitude;
    String latitude;
    String cityId;
    String fullWeather;

    @BeforeTest()
    void initiate() {
        DriverSetup.driverConf();
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    void londonCityWeather() {
        OpenWeatherMapHelper.openWebSite(driver);
        OpenWeatherMapHelper.searchForCity(london, driver);
        location = OpenWeatherMapHelper.getLongAndLat(driver);
        fullWeather = OpenWeatherMapHelper.fullWeather(driver);
        OpenWeatherMapHelper.clickFirstCityOption(london, driver);
        CommonHelper.refreshPage(driver);
        londonWeather = OpenWeatherMapHelper.getFirstForeCastWeather(driver);
        String Weather = londonWeather.replace("°", "").replace("C", "");
        String maxWeatherFESt = Weather.substring(0, londonWeather.indexOf("/") - 1);
        String minWeatherFESt = Weather.substring(londonWeather.indexOf("/") + 2);
        String LonAndLat = location.replace("[", "").replace("]", "");
        latitude = LonAndLat.substring(0, LonAndLat.indexOf(","));
        longitude = LonAndLat.substring(LonAndLat.indexOf(",") + 2);
        maxWeatherFE = Integer.parseInt(maxWeatherFESt);
        minWeatherFE = Integer.parseInt(minWeatherFESt);
        String headerWeatherSt = OpenWeatherMapHelper.getHeaderWeather(driver).replace("°C", "");
        headerWeather = Integer.parseInt(headerWeatherSt);
    }

    @Test(priority = 2)
    public void searchByName() {

        RestAssured.baseURI = "https://openweathermap.org/data/2.5/";
        RequestSpecification request = RestAssured.given();

        Response response = request
                .queryParam("q", london)
                .queryParam("type", "like")
                .queryParam("sort", "population")
                .queryParam("units", "metric")
                .queryParam("cnt", "30")
                .queryParam("units", "metric")
                .queryParam("appid", "439d4b804bc8187953eb36d2a8c26a02")
                .queryParam("_", "1608587095903")
                .get("/find");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Double maxWeatherKelvin = Double.parseDouble(jsonPathEvaluator.get("list.main[0].temp_max").toString());
        Double minWeatherKelvin = Double.parseDouble(jsonPathEvaluator.get("list.main[0].temp_min").toString());
        cityId = jsonPathEvaluator.get("list.id[0]").toString();
        DecimalFormat value = new DecimalFormat("#.#");

        Double maxWeatherC = Double.parseDouble(value.format(maxWeatherKelvin)) - 273.15;
        Double minWeatherC = Double.parseDouble(value.format(minWeatherKelvin)) - 273.15;

        String maxWeather = new DecimalFormat("##").format(maxWeatherC);
        String minWeather = new DecimalFormat("##").format(minWeatherC);

        Assert.assertTrue(fullWeather.contains(maxWeather));
        Assert.assertTrue(fullWeather.contains(minWeather));

    }

    @Test(priority = 3)
    public void searchByLocation() {

        RestAssured.baseURI = "https://openweathermap.org/data/2.5/";
        RequestSpecification request = RestAssured.given();

        Response response = request
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("units", "metric")
                .queryParam("appid", "439d4b804bc8187953eb36d2a8c26a02")
                .get("/onecall");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Integer maxLocationWeather = CommonHelper.approximation(Double.parseDouble(jsonPathEvaluator.get("daily.temp[0].max").toString()), driver);
        Integer minLocationWeather = CommonHelper.approximation(Double.parseDouble(jsonPathEvaluator.get("daily.temp[0].min").toString()), driver);


        Assert.assertEquals(maxLocationWeather, maxWeatherFE);
        Assert.assertEquals(minLocationWeather, minWeatherFE);
    }

    @Test(priority = 4)
    public void searchByID() {

        RestAssured.baseURI = "https://openweathermap.org/data/2.5/";
        RequestSpecification request = RestAssured.given();

        Response response = request
                .queryParam("id", cityId)
                .queryParam("units", "metric")
                .queryParam("appid", "439d4b804bc8187953eb36d2a8c26a02")
                .get("/weather");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Integer weatherID = CommonHelper.approximation(Double.parseDouble(jsonPathEvaluator.get("main.temp").toString()), driver);

        Assert.assertEquals(weatherID, headerWeather);
    }

    @AfterTest()
    void closure() {
        driver.close();
        driver.quit();
    }
}
