package other;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class DemoQaTest extends BaseTest {

    @Test
    @DisplayName("DemoQa")
    @Tag("Other")
    public void demoQaTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement subjectInput = driver.findElement(By.cssSelector("input#subjectsInput"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        subjectInput.sendKeys("m");

        WebElement mathElement = driver.findElement(By.cssSelector("div#react-select-2-option-0"));
        wait.until(ExpectedConditions.visibilityOf(mathElement));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", mathElement);


        subjectInput.sendKeys("a");
        WebElement artElement = driver.findElement(By.cssSelector("div#react-select-2-option-2"));
        wait.until(ExpectedConditions.visibilityOf(artElement));
        executor.executeScript("arguments[0].click();", artElement);

        String selectedOptions = driver.findElement(By.cssSelector("div.subjects-auto-complete__value-container"))
                .getText();

        Assertions.assertThat(selectedOptions).contains("Maths", "Arts");
        log.info("Elements are visible: Maths and Arts");
    }
}
