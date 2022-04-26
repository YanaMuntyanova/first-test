package pages.lesson4;

import com.codeborne.selenide.Condition;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationFormPage {


    public void openPage() {
        open("/automation-practice-form");
    }
    public void setFirstName(String firstName) { $("[id=firstName]").setValue(firstName);}
    public void setLastNamee(String lastName) {  $("[id=lastName]").setValue(lastName); }
    public void setUserEmail(String userEmail) {  $("[id=userEmail]").setValue(userEmail); }
    public void setUserNumber(String userNumber) {  $("[id=userNumber]").setValue(userNumber); }
    public void setCurrentAddress(String currentAddress) {  $("[id=currentAddress]").setValue(currentAddress); }
    public void setGender(String gender) { $("#genterWrapper").$(byText(gender)).click(); }
    public void setSubjects(String subjects) {$("#subjectsInput").setValue(subjects).pressEnter(); }

    public void setHobbiesWrapper(String hobbies) { $("#hobbiesWrapper").$(byText(hobbies)).click();}

    public void setDateOfBirth(String month, String year, String day) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();

    }
    public void setFileinput() {$x("//input[@type='file']").uploadFile(new File("src/test/resources/20.jpg"));}
    public void setState(String state, String city) {
        $("[id=state]").click();
        $("[id=stateCity-wrapper]").$(byText(state)).click();
        $("[id=city]").click();
        $("[id=stateCity-wrapper]").$(byText(city)).click();
    }

    public void setButtonClick() { $("#submit").click();}

    public void setCheckResult(String firstName, String lastName, String userEmail, String gender, String month, String  year, String day,
                               String userNumber, String hobbies, String currentAddress)
    {
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail),
                text(gender), text(String.valueOf(userNumber)),text(day + " "+ month + "," + year), text(hobbies),text(currentAddress));
    }



}
