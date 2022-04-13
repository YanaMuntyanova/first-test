package guru.qa;

import com.codeborne.selenide.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PracticeFormTest {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
           lastName = faker.name().lastName(),
           userEmail = faker.internet().emailAddress(),
            userNumber = faker.numerify("##########"),
            currentAddress =faker.address().fullAddress(),
            gender = faker.demographic().sex(),
            subjects = "English",
            hobbies = "Reading",
            day = "02",
            month = "December",
            year = "2022",
            state = "NCR",
            city = "Delhi";

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
      //  Configuration.browserSize = ("1920x1080");
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/first-test/build/downloads/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }


    @Test
    void fillFromTest(){

        RegistrationFormPage  registrationFormPage = new RegistrationFormPage();

        registrationFormPage.openPage();
        registrationFormPage.setFirstName(firstName);
        registrationFormPage.setLastNamee(lastName);

        registrationFormPage.setUserEmail(userEmail);
        registrationFormPage.setUserNumber(userNumber);
        registrationFormPage.setCurrentAddress(currentAddress);
        registrationFormPage.setGender(gender);
        registrationFormPage.setSubjects(subjects);
        registrationFormPage.setHobbiesWrapper(hobbies);
        registrationFormPage.setDateOfBirth(month, year, day);
        registrationFormPage.setFileinput();
        registrationFormPage.setState(state, city);

        registrationFormPage.setButtonClick();

        registrationFormPage.setCheckResult(firstName, lastName, userEmail, gender,  month, year, day, userNumber, hobbies, currentAddress);


       }




   }
