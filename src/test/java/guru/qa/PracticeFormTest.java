package guru.qa;

import com.codeborne.selenide.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PracticeFormTest {


    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = ("1920x1080");
      /*  System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/first-test/build/downloads/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);*/

    }

    @Test
    void fillFromTest(){

        RegistrationFormTest  registrationFormTest = new RegistrationFormTest();

        registrationFormTest.openPage();
        registrationFormTest.setFirstName();
        registrationFormTest.setLastNamee();
        registrationFormTest.setUserEmail();
        registrationFormTest.setUserNumber();
        registrationFormTest.setCurrentAddress();
        registrationFormTest.setGender();
        registrationFormTest.setSubjects();
        registrationFormTest.setHobbiesWrapper();
        registrationFormTest.setDateOfBirth();
        registrationFormTest.setFileinput();
        registrationFormTest.setState();

        registrationFormTest.setSubmit();

        registrationFormTest.setAssert();


       }




   }
