package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateSelect = $("#react-select-3-input"),
            citySelect = $("#react-select-4-input"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public PracticeFormPage setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public PracticeFormPage setHobby(String value) {
        hobbyInput.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage uploadPicture(String path) {
        uploadPicture.uploadFromClasspath(path);

        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public PracticeFormPage setState(String state) {
        stateSelect.setValue(state).pressEnter();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        citySelect.setValue(city).pressEnter();

        return this;
    }

    public PracticeFormPage sendForm() {
        submitButton.shouldBe(Condition.visible);              // ждем визуализацию кнопки
        Selenide.executeJavaScript("arguments[0].focus();", submitButton);
        submitButton.click();

        return this;
    }

}