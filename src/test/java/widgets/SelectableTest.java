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
public class SelectableTest extends BaseTest {

    Random random = new Random();
    String chosenText = "Some other file with a very long option text";
    int chosenIndex = 3;

    @Test
    @DisplayName("Selectable")
    @Tag("Widgets")
    public void selectMenuTest() {
        driver.get("http://www.seleniumui.moderntester.pl/selectmenu.php");
        WebElement speedButton = driver.findElement(By.cssSelector("#speed-button"));
        speedButton.click();
        List<WebElement> listOfSpeedOptions = driver.findElements(By.cssSelector("#speed-menu>li"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfSpeedOptions));
        selectRandomSpeed(listOfSpeedOptions);
        log.info("Random speed selected");

        WebElement filesButton = driver.findElement(By.cssSelector("#files-button"));
        filesButton.click();
        List<WebElement> listOfFilesOptions = driver.findElements(By.cssSelector("#files-menu>li.ui-menu-item"));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfFilesOptions));
        selectFileByText(listOfFilesOptions);
        log.info("Option from \"Select a file\" selected");

        WebElement numberButton = driver.findElement(By.cssSelector("#number-button"));
        numberButton.click();
        List<WebElement> listOfNumbers = driver.findElements(By.cssSelector("#number-menu>li"));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfNumbers));
        selectNumberByIndex(listOfNumbers);
        log.info("Option from \"Select a number\" selected");

        WebElement titleButton = driver.findElement(By.cssSelector("#salutation-button"));
        titleButton.click();
        List<WebElement> listOfTitles = driver.findElements(By.cssSelector("#salutation-menu>li"));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfTitles));
        selectRandomTitle(listOfTitles);
        log.info("Random option from \"Select a title\" selected");

        Assertions.assertThat(listOfSpeedOptions.size()).isGreaterThan(0);
        Assertions.assertThat(listOfFilesOptions.size()).isGreaterThan(0);
        Assertions.assertThat(listOfNumbers.size()).isGreaterThan(0);
        Assertions.assertThat(listOfTitles.size()).isGreaterThan(0);
    }

    private void selectRandomSpeed(List<WebElement> list) {
        WebElement chosenElement = list.get(random.nextInt(list.size()));
        clickOnOption(chosenElement);
    }

    private void selectFileByText(List<WebElement> list) {
        for (WebElement file : list) {
            if (file.getText().equals(chosenText)) {
                System.out.println(file.getText());
                file.click();
            }
        }
    }

    private void selectNumberByIndex(List<WebElement> list) {
        WebElement chosenNumber = list.get(chosenIndex);
        System.out.println(chosenNumber.getText());
        chosenNumber.click();
    }

    private void selectRandomTitle(List<WebElement> list) {
        WebElement chosenElement = list.get(random.nextInt(list.size()));
        clickOnOption(chosenElement);
    }

    private void clickOnOption(WebElement chosenElement) {
        System.out.println(chosenElement.getText());
        chosenElement.click();
    }
}
