package interactions;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class ResizableTest extends BaseTest {

    @Test
    @DisplayName("Resizable")
    @Tag("Interactions")
    public void resizableTest() {
        driver.get("http://www.seleniumui.moderntester.pl/resizable.php");
        WebElement element1 = driver.findElement(By.cssSelector(".ui-resizable-e"));
        WebElement element2 = driver.findElement(By.cssSelector(".ui-resizable-s"));
        WebElement element3 = driver.findElement(By.cssSelector(".ui-icon"));

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(element1, 10, 0).perform();
        log.info("Window resized to the right (10px)");
        actions.dragAndDropBy(element2, 0, 10).perform();
        log.info("Window resized to the bottom (10px)");
        actions.dragAndDropBy(element3, 10, 10).perform();
        log.info("Window resized to the right and bottom (10px,10px)");
    }
}
