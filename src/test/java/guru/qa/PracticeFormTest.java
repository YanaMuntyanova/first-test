package guru.qa;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = ("1920x1080");
       /* System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/first-test/build/downloads/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);*/

    }

    @Test
    void fillFromTest(){
        open ("/automation-practice-form");

        $("[id=firstName]").setValue("Yana");
        $("[id=lastName]").setValue("Muntyanova");
        $("[id=userEmail]").setValue("Muntyanova@ya.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("[id=userNumber]").setValue("9130000000");
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("2022");
        $("[aria-label$='December 2nd, 2022']").click();
        $x("//input[@type='file']").uploadFile(new File("C:/Users/User/IdeaProjects/first-test/src/resources/20.jpg"));
        $("[id= currentAddress]").setValue("AnyWhere...");
        $("[id=state]").click();
        $("[id=stateCity-wrapper]").$(byText("NCR")).click();
        $("[id=city]").click();
        $("[id=stateCity-wrapper]").$(byText("Delhi")).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("Yana"), text("Muntyanova"), text("Muntyanova@ya.ru"),
                text("Male"), text("9130000000"),text("02 December,2022"), text("English"),text("AnyWhere..."));



       }




   }
