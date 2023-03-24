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
import java.util.List;

@Slf4j
public class MenuTest extends BaseTest {

    @Test
    @DisplayName("Menu")
    @Tag("Widgets")
    public void menuTest() {
        driver.get("http://www.seleniumui.moderntester.pl/menu-item.php");
        driver.findElement(By.cssSelector("#ui-id-9")).click();
        WebElement jazzElement = driver.findElement(By.cssSelector("#ui-id-13"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jazzElement)).click();

        WebElement modernElement = driver.findElement(By.cssSelector("#ui-id-16"));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.elementToBeClickable(modernElement)).click();
        log.info("Items have been clicked");
    }

    @Test
    @DisplayName("ModalDialog")
    @Tag("Widgets")
    public void modalDialogTest() {
        driver.get("http://www.seleniumui.moderntester.pl/modal-dialog.php");
        driver.findElement(By.cssSelector("#create-user")).click();

        WebElement inputName = driver.findElement(By.cssSelector("#name"));
        inputName.clear();
        inputName.sendKeys("Bruce Willis");

        WebElement inputEmail = driver.findElement(By.cssSelector("#email"));
        inputEmail.clear();
        inputEmail.sendKeys("bwillis@gmail.com");

        WebElement inputPassword = driver.findElement(By.cssSelector("#password"));
        inputPassword.clear();
        inputPassword.sendKeys("foreverwillis");

        driver.findElement(By.xpath(".//button[text()='Create an account']")).click();

        List<WebElement> addedData = driver.findElements(By.xpath("//tbody/tr[last()]/td"));
        String addedName = addedData.get(0).getText();
        String addedEmail = addedData.get(1).getText();
        String addedPassword = addedData.get(2).getText();

        Assertions.assertThat(addedData.size()).isGreaterThan(0);
        Assertions.assertThat(addedName).isEqualTo("Bruce Willis");
        Assertions.assertThat(addedEmail).isEqualTo("bwillis@gmail.com");
        Assertions.assertThat(addedPassword).isEqualTo("foreverwillis");
        log.info("Data has been added: " + addedName + " " + addedEmail + " " + addedPassword);
    }
}
