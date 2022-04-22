package tests.Lesson7;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    public void testGithubIssue(){

        Selenide.open("https://github.com");
        $x(" //input[@name='q']").click();
        $x(" //input[@name='q']").setValue("YanaMuntyanova/first-test");
        $x(" //input[@name='q']").submit();
        $x("//a[@href = '/YanaMuntyanova/first-test']").click();
        $x("//a[@id='issues-tab']").click();
        $$x("//div//span").find(text("#1")).shouldBe(visible);


    }


}
