package widgets;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

@Slf4j
public class SliderTest extends BaseTest {

    @Test
    @DisplayName("Slider")
    @Tag("Widgets")
    public void sliderTest() {
        driver.get("http://www.seleniumui.moderntester.pl/slider.php");
        WebElement slider = driver.findElement(By.cssSelector("#custom-handle"));
        moveSlider("50");
        Assertions.assertThat(slider.getText()).isEqualTo("50");
        log.info("The current value of the slider is 50");

        moveSlider("80");
        Assertions.assertThat(slider.getText()).isEqualTo("80");
        log.info("The current value of the slider is 80");

        moveSlider("80");
        Assertions.assertThat(slider.getText()).isEqualTo("80");
        log.info("The current value of the slider is 80");

        moveSlider("20");
        Assertions.assertThat(slider.getText()).isEqualTo("20");
        log.info("The current value of the slider is 20");

        moveSlider("0");
        Assertions.assertThat(slider.getText()).isEqualTo("0");
        log.info("The current value of the slider is 0");
    }

    private void moveSlider(String value) {
        WebElement slider = driver.findElement(By.cssSelector("#custom-handle"));
        slider.click();
        while (!slider.getText().equals(value)) {
            if (Integer.parseInt(slider.getText()) < Integer.parseInt(value)) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            } else {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        slider.getText();
    }
}
