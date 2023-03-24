package widgets;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class ProgressbarTest extends BaseTest {

    @Test
    @DisplayName("Progressbar1")
    @Tag("Widgets")
    public void progressbarTest1() {
        driver.get("http://www.seleniumui.moderntester.pl/progressbar.php");
        log.info("The page has been opened");
        WebElement progressbar = driver.findElement(By.cssSelector(".progress-label"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(progressbar,"Complete!"));
        String text = progressbar.getText();

        Assertions.assertThat(text).isEqualTo("Complete!");
        log.info("Test completed!");
    }


    @Test
    @DisplayName("Progressbar2")
    @Tag("Widgets")
    public void progressbarTest2() {
        driver.get("http://www.seleniumui.moderntester.pl/progressbar.php");
        log.info("The page has been opened");
        WebElement progressbar = driver.findElement(By.cssSelector("div.ui-progressbar-value"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(progressbar, "class", "ui-progressbar-complete"));

        Assertions.assertThat(progressbar.getAttribute("class")).contains("ui-progressbar-complete");
        log.info("Test completed!");
    }
}
