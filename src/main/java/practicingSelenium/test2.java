package practicingSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test2 {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sswami\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        // driver.findElement(By.xpath("//div[contains(text(),'Use another account')]")).click();
        driver.findElement(By.id("identifierId")).clear();
        driver.findElement(By.id("identifierId")).sendKeys("shivangiswami1303@gmail.com");
        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("7842s1313s");
        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

    }
}
