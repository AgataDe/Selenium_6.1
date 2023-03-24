package basic;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class TablesTest extends BaseTest {
    @Test
    @DisplayName("Tables")
    @Tag("Basic")
    public void tablesTest() {
        driver.get("http://www.seleniumui.moderntester.pl/table.php");
        List<WebElement> listOfRows = driver.findElements(By.cssSelector("tbody>tr"));

        for (WebElement row : listOfRows) {
            List<WebElement> elements = row.findElements(By.cssSelector("th, td"));
            if (elements.get(3).getText().contains("Switzerland") && Integer.parseInt(elements.get(4).getText()) > 4000) {
                System.out.println(elements.get(0).getText() + " " + elements.get(1).getText() + " " + elements.get(2)
                        .getText());
            }
        }
        Assertions.assertThat(listOfRows.size()).isGreaterThan(0);
        log.info("Elements from table are visible");
    }
}
