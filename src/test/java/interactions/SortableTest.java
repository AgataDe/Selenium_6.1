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

import java.util.Collections;
import java.util.List;

@Slf4j
public class SortableTest extends BaseTest {

    @Test
    @DisplayName("Sortable")
    @Tag("Interactions")
    public void sortableTest() {
        driver.get("http://www.seleniumui.moderntester.pl/sortable.php");
        List<WebElement> listOfItems = driver.findElements(By.cssSelector(".ui-sortable-handle"));
        List<WebElement> shuffledList = driver.findElements(By.cssSelector(".ui-sortable-handle"));
        Actions actions = new Actions(driver);

        Collections.shuffle(shuffledList);

        for (WebElement element : shuffledList) {
            System.out.println(element.getText());
        }
        log.info("Table has been shuffled");

        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement drag = shuffledList.get(i);
            WebElement drop = driver.findElement(By.cssSelector("#sortable>li:nth-child(" + (i + 1) + ")"));
            actions.dragAndDrop(drag, drop).perform();
        }

        Assertions.assertThat(shuffledList.size()).isGreaterThan(0);
        log.info("Items have been moved on the list to match the shuffled table");
    }
}
