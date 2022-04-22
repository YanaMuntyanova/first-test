package tests.Lesson7;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class AnnotatedStepTest {

    String nameRepository = "YanaMuntyanova/first-test";
    int numberIssue = 1;

    @Test
    public void testGithubIssue(){

        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchRepository(nameRepository);
        steps.goToRepository();
        steps.openIssueTab();
        steps.checkIssueNumber(numberIssue);
    }

}
