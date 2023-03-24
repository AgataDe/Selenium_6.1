package basic;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class FormTest extends BaseTest {

    @Test
    @DisplayName("Form")
    @Tag("Basic")
    public void shouldFillFormWithData() {
        driver.get("http://www.seleniumui.moderntester.pl/form.php");
        log.info("The page has been opened");

        driver.findElement(By.cssSelector("#inputFirstName3")).sendKeys("Agata");
        driver.findElement(By.cssSelector("#inputLastName3")).sendKeys("De");
        driver.findElement(By.cssSelector("#inputEmail3")).sendKeys("agatha.de@gmail.com");

        List<WebElement> sexList = driver.findElements(By.cssSelector("[name='gridRadiosSex']"));
        Random random = new Random();
        sexList.get(random.nextInt(sexList.size())).click();

        driver.findElement(By.cssSelector("#inputAge3")).sendKeys("34");

        List<WebElement> experienceList = driver.findElements(By.cssSelector("[name='gridRadiosExperience']"));
        experienceList.get(random.nextInt(experienceList.size())).click();

        driver.findElement(By.cssSelector("#gridCheckAutomationTester")).click();

        Select continents = new Select(driver.findElement(By.cssSelector("#selectContinents")));
        List<WebElement> listOfOptions = driver.findElements(By.cssSelector("#selectContinents>option"));
        continents.selectByIndex(random.nextInt(listOfOptions.size()));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfOptions));

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(driver.findElement(By.cssSelector("option[value='switch-commands']")))
                .click(driver.findElement(By.cssSelector("option[value='wait-commands']")))
                .perform();

        File file = new File("src/main/resources/file.txt");
        driver.findElement(By.cssSelector("#chooseFile")).sendKeys(file.getAbsolutePath());

        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        String fileSendInfo = driver.findElement(By.cssSelector("#validator-message")).getText();
        Assertions.assertThat(fileSendInfo).isEqualTo("Form send with success");

        Assertions.assertThat(sexList.size()).isGreaterThan(0);
        Assertions.assertThat(experienceList.size()).isGreaterThan(0);
        Assertions.assertThat(listOfOptions.size()).isGreaterThan(0);
        log.info("Form send with success");
    }
}
