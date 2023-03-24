package interactions;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class DroppableTest extends BaseTest {

    @Test
    @DisplayName("Droppable")
    @Tag("Interactions")
    public void droppableTest() {
        driver.get("http://www.seleniumui.moderntester.pl/droppable.php");
        WebElement drag = driver.findElement(By.cssSelector("#draggable"));
        WebElement drop = driver.findElement(By.cssSelector("#droppable"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(drag).moveToElement(drop).release().perform();

        String dropInfo = driver.findElement(By.cssSelector("#droppable>p")).getText();
        Assertions.assertThat(dropInfo).isEqualTo("Dropped!");
        log.info("Item has been dropped!");
    }
}
