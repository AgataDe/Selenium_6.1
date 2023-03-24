package interactions;

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

import java.util.List;

@Slf4j
public class SelectableTest extends BaseTest {

    @Test
    @DisplayName("Selectable")
    @Tag("Interactions")
    public void selectableTest() {
        driver.get("http://www.seleniumui.moderntester.pl/selectable.php");
        List<WebElement> elements = driver.findElements(By.cssSelector(".ui-selectee"));

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).click(elements.get(0)).click(elements.get(2)).click(elements.get(3)).perform();

        String text = driver.findElement(By.cssSelector("#feedback")).getText();
        String expectedText = "You've selected: #1 #3 #4.";

        Assertions.assertThat(elements.size()).isGreaterThan(0);
        Assertions.assertThat(text).isEqualTo(expectedText);
        log.info("Test completed!");
    }
}
