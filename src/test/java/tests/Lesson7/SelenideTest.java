package tests.Lesson7;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    public void testGithubIssue(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("https://github.com");
        $x(" //input[@name='q']").click();
        $x(" //input[@name='q']").setValue("YanaMuntyanova/first-test");
        $x(" //input[@name='q']").submit();
        $x("//a[@href = '/YanaMuntyanova/first-test']").click();
        $x("//a[@id='issues-tab']").click();
        $$x("//div//span").find(text("#1")).shouldBe(visible);


    }


}
