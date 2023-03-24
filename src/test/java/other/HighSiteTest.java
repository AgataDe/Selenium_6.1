package other;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

@Slf4j
public class HighSiteTest extends BaseTest {

    @Test
    @DisplayName("HighSite1")
    @Tag("Other")
    public void shouldScrollWithActionTest() throws IOException {
        driver.get("http://www.seleniumui.moderntester.pl/high-site.php");
        scrollUntilVisibleByAction();
        takeScreenshot();
    }

    @Test
    @DisplayName("HighSite2")
    @Tag("Other")
    public void shouldScrollByJETest() throws IOException {
        driver.get("http://www.seleniumui.moderntester.pl/high-site.php");
        scrollUntilVisibleByJE();
        takeScreenshot();
    }

    private void scrollUntilVisibleByAction() {
        int heightOfWindow = driver.manage().window().getSize().getHeight();
        Actions actions = new Actions(driver);

        for (int i = 0; i < heightOfWindow; i++) {
            try {
                driver.findElement(By.cssSelector("input#scroll-button")).isDisplayed();
                break;
            } catch (NoSuchElementException e) {
                actions.scrollByAmount(0, 100).perform();
            }
        }
        log.info("'Submit' button is visible");
    }

    private void scrollUntilVisibleByJE() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        int heightOfWindow = driver.manage().window().getSize().getHeight();

        for (int i = 0; i < heightOfWindow; i++) {
            try {
                driver.findElement(By.cssSelector("input#scroll-button")).isDisplayed();
                break;
            } catch (NoSuchElementException e) {
                executor.executeScript("window.scrollBy(0,100)", "");
            }
        }
        log.info("'Submit' button is visible");
    }

    private void takeScreenshot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot.png"));
        log.info("Screenshot has been taken");
    }
}
