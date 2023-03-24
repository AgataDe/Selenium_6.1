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

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
public class DatePickerTest extends BaseTest {

    Calendar calendar = Calendar.getInstance();
    String today = new SimpleDateFormat("MM/dd/yyy").format(new Date());
    Random random = new Random();


    @Test
    @DisplayName("DatePicker")
    @Tag("Widgets")
    public void shouldPickDateTest() {
        driver.get("http://www.seleniumui.moderntester.pl/datepicker.php");
        int currentMonth = calendar.get(Calendar.MONTH);
        int nextMonth = currentMonth + 2;
        int currentYear = calendar.get(Calendar.YEAR);
        int nextYear = currentYear + 1;


        clickCalendar();
        WebElement todayPicker = (driver.findElement(By.cssSelector(".ui-datepicker-today")));
        todayPicker.click();
        WebElement datePicker = driver.findElement(By.cssSelector("#datepicker"));
        datePicker.getAttribute("value");
        Assertions.assertThat(datePicker.getAttribute("value")).isEqualTo(today);
        log.info("Step 'Today' completed");

        clickCalendar();
        waitForElement();
        pickTheNextMonth();
        pickTheDay("1").click();
        Assertions.assertThat(datePicker.getAttribute("value")).isEqualTo("0" + nextMonth
                + "/01/" + currentYear);
        log.info("Step '1st day from next month' completed");

        clickCalendar();
        waitForElement();
        pickTheNextYearMonth();
        pickTheDay("31").click();
        Assertions.assertThat(datePicker.getAttribute("value")).isEqualTo("01/31/" + nextYear);
        log.info("Step 'Last day from January next year' completed");

        clickCalendar();
        pickTheDay("31").click();
        Assertions.assertThat(datePicker.getAttribute("value")).isEqualTo("01/31/" + nextYear);
        log.info("Step 'Select the same date again' completed");

        clickCalendar();
        previousMonthRandomDay();

        clickCalendar();
        waitForElement();
        pickRandomDateLastYear();
        pickRandomDay();
    }


    private LocalDate calculateNextYear() {
        return LocalDate.now().plusYears(1);
    }

    private LocalDate calculatePrevYear() {
        return LocalDate.now().minusYears(1);
    }

    private void clickCalendar() {
        WebElement datePicker = driver.findElement(By.cssSelector("#datepicker"));
        datePicker.click();
    }

    private void waitForElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement nextMonth = driver.findElement(By.cssSelector(".ui-datepicker-next"));
        wait.until(ExpectedConditions.visibilityOf(nextMonth));
        nextMonth.click();
    }

    private WebElement pickTheDay(String selectedDay) {
        List<WebElement> listOfDays = driver.findElements(By.cssSelector("tr>td"));
        return listOfDays.stream().filter(e -> !e.getAttribute("class")
                        .contains("ui-datepicker-other-month") && (e.getText()
                        .equals(selectedDay)))
                .findFirst().orElse(null);
    }

    private void pickTheNextMonth() {
        int currentYear = calendar.get(Calendar.YEAR);
        if (!driver.findElement(By.cssSelector(".ui-datepicker-year")).getText().equals(String.valueOf(currentYear))) {
            currentYear += 1;
        }
    }

    private void pickTheNextYearMonth() {
        int year = calculateNextYear().getYear();
        while (!(driver.findElement(By.cssSelector(".ui-datepicker-month"))).getText().equals("January") &&
                !(driver.findElement(By.cssSelector(".ui-datepicker-year"))).getText().equals(String.valueOf(year))) {
            (driver.findElement(By.cssSelector(".ui-datepicker-next"))).click();
        }
    }

    private void previousMonthRandomDay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement prevMonth = driver.findElement(By.cssSelector(".ui-datepicker-prev"));
        wait.until(ExpectedConditions.visibilityOf(prevMonth));
        prevMonth.click();
        WebElement e = null;
        while (e == null) {
            int day = random.nextInt(28);
            e = pickTheDay(String.valueOf(day));
        }
        e.click();
    }

    private String randomMonth() {
        int monthNumber = random.nextInt(12);
        return Month.of(monthNumber).toString();
    }


    private void pickRandomDateLastYear() {
        int year = calculatePrevYear().getYear();
        String month = randomMonth();
        while (!(driver.findElement(By.cssSelector(".ui-datepicker-month"))).getText().toUpperCase().equals(month)
                && !(driver.findElement(By.cssSelector(".ui-datepicker-year"))).getText().equals(String.valueOf(year))) {
            (driver.findElement(By.cssSelector(".ui-datepicker-prev"))).click();
        }
    }

    private void pickRandomDay() {
        int day = random.nextInt(28);
        WebElement e = pickTheDay(String.valueOf(day));
        e.click();
    }
}
