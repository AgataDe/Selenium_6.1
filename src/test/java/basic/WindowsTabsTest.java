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
import java.util.Set;

@Slf4j
public class WindowsTabsTest extends BaseTest {

    @Test
    @DisplayName("Windows1")
    @Tag("Basic")
    public void newBrowserWindowTest() {
        driver.get("http://www.seleniumui.moderntester.pl/windows-tabs.php");
        driver.findElement(By.cssSelector("#newBrowserWindow")).click();

        String currentWindow = driver.getWindowHandle();
        Set<String> windowNames = driver.getWindowHandles();
        for (String window : windowNames) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
            }
        }

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

        driver.switchTo().window(currentWindow);
    }


    @Test
    @DisplayName("Windows2")
    @Tag("Basic")
    public void newMessageWindowTest() {
        driver.get("http://www.seleniumui.moderntester.pl/windows-tabs.php");
        driver.findElement(By.cssSelector("#newMessageWindow")).click();

        String currentWindow = driver.getWindowHandle();
        Set<String> windowNames = driver.getWindowHandles();
        for (String windowMessage : windowNames) {
            if (!windowMessage.equals(currentWindow)) {
                driver.switchTo().window(windowMessage);
            }
        }

        WebElement message = driver.findElement(By.cssSelector("body"));
        System.out.println(message.getText());
        log.info("The text saved in the new window has been printed");
        driver.switchTo().window(currentWindow);
    }


    @Test
    @DisplayName("Windows3")
    @Tag("Basic")
    public void newBrowserTabTest() {
        driver.get("http://www.seleniumui.moderntester.pl/windows-tabs.php");
        driver.findElement(By.cssSelector("#newBrowserTab")).click();

        String currentWindow = driver.getWindowHandle();
        Set<String> windowNames = driver.getWindowHandles();
        for (String windowTab : windowNames) {
            if (!windowTab.equals(currentWindow)) {
                driver.switchTo().window(windowTab);
            }
        }

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
        driver.switchTo().window(currentWindow);
    }
}
