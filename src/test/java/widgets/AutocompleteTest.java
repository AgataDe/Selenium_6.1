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
import java.util.Random;

@Slf4j
public class AutocompleteTest extends BaseTest {

    @Test
    @DisplayName("Autocomplete")
    @Tag("Widgets")
    public void autocompleteTest() {
        driver.get("http://www.seleniumui.moderntester.pl/autocomplete.php");
        WebElement searchInput = driver.findElement(By.cssSelector("#search"));
        searchInput.sendKeys("a");
        List<WebElement> options = driver.findElements(By.cssSelector("div.ui-menu-item-wrapper"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(options));

        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        log.info("Printed all available options");

        Random random = new Random();
        WebElement randomElement = options.get(random.nextInt(options.size()));
        String textOfRandom = randomElement.getText();
        randomElement.click();
        log.info("Random option has been chosen");

        String value = searchInput.getAttribute("value");

        Assertions.assertThat(options.size()).isGreaterThan(0);
        Assertions.assertThat(textOfRandom).isEqualTo(value);
        log.info("Text in search input is the same as text of the selected option");
    }
}
