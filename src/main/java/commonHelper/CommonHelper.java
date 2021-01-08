package commonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonHelper {


    public static void openWebPage(String url, WebDriver driver) {
        driver.navigate().to(url);
    }

    public static void clickWhenReady(WebElement po, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(po)).click();
    }

    public static void verifyElementAppears(WebElement Po,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(Po));
    }

    public static void verifyElementNotAppear(WebElement Po,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(Po));
    }

    public static void sendText(WebElement po, String Text,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(po)).sendKeys(Text);
    }

    public static String getText(WebElement Po,WebDriver driver) {
        WebElement element = Po;
        String text = element.getText();
        return text;
    }

    public static void clickEnter(WebElement po) {
        po.sendKeys(Keys.ENTER);
    }


    public static WebElement retryingFind(String locator,WebDriver driver) {
        boolean result = false;
        WebElement element = null;
        int attempts = 0;
        while (attempts < 2) {
            try {
                element = driver.findElement(By.xpath(locator));
                result = true;
                break;
            } catch (Exception e) {
            }
            attempts++;
        }
        return element;
    }

    public static int approximation(double x,WebDriver driver) {
        return (int) (Math.signum(x) * Math.min(Math.round(Math.abs(x)), Integer.MAX_VALUE));
    }

    public static String getCityID(WebDriver driver) {
        String url = driver.getCurrentUrl();
        return url.replace("https://openweathermap.org/city/", "");
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static WebElement firstResult(String city,WebDriver driver) {

        WebElement chosenCity = driver.findElements(By.xpath("//td/b/a[contains(text()," + city + ")]")).get(0);
        return chosenCity;
    }
}