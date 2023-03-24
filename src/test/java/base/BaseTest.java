package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
        log.info("WebDriver setup");
    }

    @BeforeEach
    public void setupTest(){
        ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void teardown(){
        if (driver != null){
            driver.quit();
        }
        log.info("Driver quit");
    }
}
