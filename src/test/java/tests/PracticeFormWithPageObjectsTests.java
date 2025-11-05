package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.components.CheckComponent;
import pages.PracticeFormPage;
import testData.TestData;
import helpers.AttachForAllure;

import static io.qameta.allure.Allure.step;

public class PracticeFormWithPageObjectsTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    CheckComponent checkComponent = new CheckComponent();
    TestData testData = new TestData();

    @AfterEach
    void addAttachments() {
        AttachForAllure.screenshotAs("Last screenshot");
        AttachForAllure.pageSource();
        AttachForAllure.browserConsoleLogs();
        AttachForAllure.addVideo();

    }
    @Test
    @Description("Проверка заполнения всех полей формы_позитивная")
    @Tag("RegressFormTest")
    void positiveAllFormTest() {
        step("Открыть страницу регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Заполнить все поля формы", () -> {
            practiceFormPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setEmail(testData.email)
                    .setGender(testData.gender)
                    .setUserNumber(testData.userNumber)
                    .setDateOfBirth(testData.day, testData.month, testData.year)
                    .setSubject(testData.subject)
                    .setHobby(testData.hobby)
                    .uploadPicture(testData.image)
                    .setCurrentAddress(testData.currentAddress)
                    .setState(testData.state)
                    .setCity(testData.city)
                    .sendForm();
        });

        step("Проверить правильность введённых данных", () -> {
            checkComponent.checkModalIsOpen()
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Student Email", testData.email)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.userNumber)
                    .checkResult("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                    .checkResult("Subjects", testData.subject)
                    .checkResult("Hobbies", testData.hobby)
                    .checkResult("Picture", testData.image)
                    .checkResult("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    @Description("Проверка заполнения только обязательных полей формы_позитивная")
    @Tag("SmokeFormTest")
    void positiveMinimalFormTest() {
        step("Открыть страницу регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Заполнить минимально необходимые поля", () -> {
            practiceFormPage.setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.gender)
                    .setUserNumber(testData.userNumber)
                    .sendForm();
        });
        step("Проверить минимальные введённые данные", () -> {
            checkComponent.checkModalIsOpen()
                    .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                    .checkResult("Gender", testData.gender)
                    .checkResult("Mobile", testData.userNumber);
        });
    }
    @Test
    @Description("Проверка отправки пустой формы_негативная")
    @Tag("NegativeFormTest")
    void NegativeFormTest() {
        step("Открыть страницу регистрации", () -> {
            practiceFormPage.openPage();
        });
        step("Отправить пустую форму", () -> {
            practiceFormPage.sendForm();
        });
        step("Проверить отсутствие подтверждения успешной отправки", () -> {
            checkComponent.checkModalIsNotOpen();
        });
    }
}