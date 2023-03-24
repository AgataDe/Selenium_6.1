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
public class AccordionTest extends BaseTest {

    @Test
    @DisplayName("Accordion1")
    @Tag("Widgets")
    public void section1Test() {
        driver.get("http://www.seleniumui.moderntester.pl/accordion.php");
        WebElement section1 = driver.findElement(By.cssSelector("#ui-id-1"));
        WebElement section1Text = driver.findElement(By.cssSelector("#ui-id-2"));
        section1.click();
        System.out.println(section1Text.getText());
        log.info("The first text has been printed");
    }

    @Test
    @DisplayName("Accordion2")
    @Tag("Widgets")
    public void section2Test() {
        driver.get("http://www.seleniumui.moderntester.pl/accordion.php");
        WebElement section2 = driver.findElement(By.cssSelector("#ui-id-3"));
        WebElement section2Text = driver.findElement(By.cssSelector("#ui-id-4"));
        section2.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(section2Text));
        System.out.println(section2Text.getText());
        log.info("The second text has been printed");
    }

    @Test
    @DisplayName("Accordion3")
    @Tag("Widgets")
    public void section3Test() {
        driver.get("http://www.seleniumui.moderntester.pl/accordion.php");
        WebElement section3 = driver.findElement(By.cssSelector("#ui-id-5"));
        WebElement section3MainText = driver.findElement(By.cssSelector("div#ui-id-6>p"));
        List<WebElement> section3ListText = driver.findElements(By.cssSelector("div#ui-id-6>ul:first-of-type"));
        section3.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(section3ListText));
        System.out.println(section3MainText.getText());
        for (WebElement list3 : section3ListText) {
            System.out.println(list3.getText());
        }

        Assertions.assertThat(section3ListText.size()).isGreaterThan(0);
        log.info("The third text has been printed");
    }

    @Test
    @DisplayName("Accordion4")
    @Tag("Widgets")
    public void section4Test() {
        driver.get("http://www.seleniumui.moderntester.pl/accordion.php");
        WebElement section4 = driver.findElement(By.cssSelector("#ui-id-7"));
        List<WebElement> section4ListText = driver.findElements(By.cssSelector("div#ui-id-8>p"));
        section4.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(section4ListText));
        for (WebElement list4 : section4ListText) {
            System.out.println(list4.getText());
        }

        Assertions.assertThat(section4ListText.size()).isGreaterThan(0);
        log.info("The fourth text has been printed");
    }
}
