package guru.qa;
import com.github.javafaker.Faker;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationFormTest {


    Faker faker = new Faker();


    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userNumber = faker.numerify("##########");
    String currentAddress =faker.address().fullAddress();

    public void openPage() {
        open("/automation-practice-form");
    }

    public void setFirstName() {  $("[id=firstName]").setValue(firstName); }
    public void setLastNamee() {  $("[id=lastName]").setValue(lastName); }
    public void setUserEmail() {  $("[id=userEmail]").setValue(userEmail); }
    public void setUserNumber() {  $("[id=userNumber]").setValue(userNumber); }
    public void setCurrentAddress() {  $("[id=currentAddress]").setValue(currentAddress); }
    public void setGender() { $("#genterWrapper").$(byText("Male")).click(); }

    public void setSubjects() {$("#subjectsInput").setValue("English").pressEnter(); }
    public void setHobbiesWrapper() { $("#hobbiesWrapper").$(byText("Reading")).click();}

    public void setDateOfBirth() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("2022");
        $("[aria-label$='December 2nd, 2022']").click();
    }
    public void setFileinput() {$x("//input[@type='file']").uploadFile(new File("src/test/resources/20.jpg"));}
    public void setState() {
        $("[id=state]").click();
        $("[id=stateCity-wrapper]").$(byText("NCR")).click();
        $("[id=city]").click();
        $("[id=stateCity-wrapper]").$(byText("Delhi")).click();
    }


    public void setSubmit() { $("#submit").click();}

    public void setAssert() {
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail),
        text("Male"), text(String.valueOf(userNumber)),text("02 December,2022"), text("English"),text(currentAddress));
    }


}
