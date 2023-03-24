package interactions;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class DraggableTest extends BaseTest {

    @Test
    @DisplayName("Draggable")
    @Tag("Interactions")
    public void draggableTest() {
        driver.get("http://www.seleniumui.moderntester.pl/draggable.php");
        WebElement element = driver.findElement(By.cssSelector("#draggable"));
        WebElement screen = driver.findElement(By.cssSelector("html"));

        int heightOfElement = element.getSize().getHeight();
        int widthOfElement = element.getSize().getWidth();
        int heightOfWindow = driver.manage().window().getSize().getHeight();
        int widthOfWindow = driver.manage().window().getSize().getWidth();
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        Actions actions = new Actions(driver);

        actions.dragAndDropBy(element, (widthOfWindow - widthOfElement - x), -y).perform();
        log.info("The square has been dragged to upper right corner of page");
        actions.dragAndDropBy(element, 0, heightOfWindow - y - heightOfElement / 2).perform();
        log.info("The square has been dragged to bottom right corner of page");
        actions.dragAndDropBy(element, -(widthOfWindow / 2 - widthOfElement / 2),
                -(heightOfWindow / 2 - heightOfElement / 2)).perform();
        log.info("The square has been dragged to centre corner of page");
        actions.dragAndDropBy(element, -(widthOfWindow / 2 - widthOfElement / 2),
                (heightOfWindow / 2 - heightOfElement / 2)).perform();
        log.info("The square has been dragged to bottom left corner of page");
    }
}

