package guru.qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverLogs;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {


    @Test
    void assertTest(){
        Selenide.open("https://yandex.ru");
        WebDriverRunner.closeWindow();
           }

      }
