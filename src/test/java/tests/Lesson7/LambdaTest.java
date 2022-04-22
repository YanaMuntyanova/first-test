package tests.Lesson7;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class LambdaTest {

    String nameRepository="YanaMuntyanova/first-test";
    int numberIssue=1;
    @Test
    public void testLambdaGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () ->
            Selenide.open("https://github.com"));

        step("Ищем репозиторий", () -> {
            $x(" //input[@name='q']").click();
            $x(" //input[@name='q']").setValue(nameRepository);
            $x(" //input[@name='q']").submit();
        });

        step("Переходим в найденный репозиторий", () ->
            $(By.linkText("YanaMuntyanova/first-test")).click());

        step("Переходим на вкладку Issue", ()->
            $x("//a[@id='issues-tab']").click());


        step("Проверяем наличие Issue с номером" + numberIssue, ()->{
            $(withText("#" + numberIssue)).should(Condition.visible);
            Allure.getLifecycle().addAttachment("Слепок страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
        });
    }

}

