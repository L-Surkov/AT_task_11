package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement yearSelector = $(".react-datepicker__year-select"),
    monthSelector = $(".react-datepicker__month-select");
    //private String daySelector = ".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)";
    private final String daySelector = ".react-datepicker__day--0%s\\:not(\\.react-datepicker__day--outside-month)";
    public void setDate(String day, String month, String year) {
        yearSelector.selectOption(year);
        monthSelector.selectOption(month);
        $(daySelector).shouldBe(Condition.visible);
        $(String.format(daySelector, day)).click();
    }
}