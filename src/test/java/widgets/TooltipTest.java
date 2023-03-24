package widgets;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class TooltipTest extends BaseTest {

    @Test
    @DisplayName("Tooltip")
    @Tag("Widgets")
    public void tooltipTest() {
        driver.get("http://www.seleniumui.moderntester.pl/tooltip.php");
        WebElement tooltip = driver.findElement(By.cssSelector(".ui-helper-hidden-accessible"));
        WebElement inputAge = driver.findElement(By.cssSelector("#age"));

        Actions actions = new Actions(driver);
        actions.moveToElement(inputAge).build().perform();

        String tooltipText = tooltip.getText();
        System.out.println(tooltipText);
        log.info("The message from the tooltip has been printed");
    }
}
