package basic;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class AlertsTest extends BaseTest {

    @Test
    @DisplayName("Alert1")
    @Tag("Basic")
    public void simpleAlertPopUp() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#simple-alert")).click();
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
        String buttonPressedInfo = driver.findElement(By.cssSelector("#simple-alert-label")).getText();
        Assertions.assertThat(buttonPressedInfo).isEqualTo("OK button pressed");
        log.info("Message -OK button pressed- visible");
    }

    @Test
    @DisplayName("Alert2")
    @Tag("Basic")
    public void promptAlertBox() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#prompt-alert")).click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("Lord Vader");
        promptAlert.accept();
        String buttonPressedInfo2 = driver.findElement(By.cssSelector("#prompt-label")).getText();
        Assertions.assertThat(buttonPressedInfo2).isEqualTo("Hello Lord Vader! How are you today?");
        log.info("Message -Hello Lord Vader! How are you today?- visible");
    }

    @Test
    @DisplayName("Alert3")
    @Tag("Basic")
    public void confirmAlertBox() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#confirm-alert")).click();
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.accept();
        String confirmInfo = driver.findElement(By.cssSelector("#confirm-label")).getText();

        driver.findElement(By.cssSelector("#confirm-alert")).click();
        Alert confirmAlert2 = driver.switchTo().alert();
        confirmAlert2.dismiss();
        String notConfirmInfo = driver.findElement(By.cssSelector("#confirm-label")).getText();

        Assertions.assertThat(confirmInfo).isEqualTo("You pressed OK!");
        Assertions.assertThat(notConfirmInfo).isEqualTo("You pressed Cancel!");
        log.info("Message -You pressed OK!- visible");
        log.info("Message -You pressed Cancel!- visible");
    }

    @Test
    @DisplayName("Alert4")
    @Tag("Basic")
    public void delayedAlert() {
        driver.get("http://www.seleniumui.moderntester.pl/alerts.php");
        driver.findElement(By.cssSelector("#delayed-alert")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert delayedAlert = driver.switchTo().alert();
        delayedAlert.accept();
        String confirm = driver.findElement(By.cssSelector("#delayed-alert-label")).getText();

        Assertions.assertThat(confirm).isEqualTo("OK button pressed");
        log.info("Message -OK button pressed- visible");
    }
}
