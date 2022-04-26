package tests.Lesson7;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage(){
        Selenide.open("https://github.com");
    }

    @Step("Ищем репозиторий")
    public void searchRepository(String nameRepository){
        $x(" //input[@name='q']").click();
        $x(" //input[@name='q']").setValue(nameRepository);
        $x(" //input[@name='q']").submit();
    }
    @Step("Переходим в найденный репозиторий")
    public void goToRepository(){
        $(By.linkText("YanaMuntyanova/first-test")).click();

    }

    @Step("Переходим на вкладку Issue")
    public void openIssueTab() {
       $x("//a[@id='issues-tab']").click();
    }

    @Step("Проверяем наличие Issue с номером {numberIssue}")
    public void checkIssueNumber(int numberIssue){
        $(withText("#" + numberIssue)).should(Condition.visible);

    }


}
