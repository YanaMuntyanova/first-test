package tests.lesson5;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class SearchFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = ("1920x1080");
        WebDriver driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterAll
    static void close() {
        WebDriverRunner.getWebDriver().quit();
    }

    @DisplayName("CsvSource. Проверка наличия билетов на сайте https://www.rzd.ru в один конец на выбранную дату с указанного вокзала")
    @CsvSource({"Moscow, Saint-Petersburg, 30.05.2022, Moskva Oktiabrskaia", "Moscow, Kazan, 30.05.2022, Moskva Kazanskaia"})
    @ParameterizedTest(name = "Вводим слова {0},{1},{2}, ожидаем результат {3}")
    void ticketSearchTest(String cityOf, String cityTo, String dateOf, String searchResult) {
        Selenide.open("https://eng.rzd.ru/en/9446");
        $("#direction-from").setValue(cityOf);
        $x("//input[@id='direction-to']").setValue(cityTo);
        $x("//input[@placeholder='Depart']").setValue(dateOf);
        $x("//a[@tabindex='5']").click();
        Selenide.$$x(String.format("//div[contains(text(),'%s') and @class='card-route__station']", searchResult))
                .should(CollectionCondition.sizeGreaterThan(0), Duration.ofMillis(150000));
    }

    @DisplayName("ValueSource. looking for a dress on  https://www.amazon.com")
    @ValueSource(strings = "dress")
    @ParameterizedTest(name = "Вводим слово {0}, ожидаем {0}")
    void jeansSearchTest(String thing) {
        Selenide.open("https://www.amazon.com");
        $x("//input[@name='field-keywords']").setValue(thing).pressEnter();
        $x("//*[text() = 'Tommy Hilfiger']").click();
        $$x("//div[@class='sg-col-inner']//h2/a/span").find(Condition.text(thing)).shouldBe(visible);
    }

    static Stream<Arguments> methodSourseTest() {
        return Stream.of(Arguments.of("Muntyanova", "0897643", "01.06.2022"),
                Arguments.of("Muntyanov", "0893263", "01.06.2022"));
    }

    @DisplayName("MethodSource")
    @MethodSource("methodSourseTest")
    @ParameterizedTest
    void methodSourseTest(String name, String num, String dateof) {
        Selenide.open("https://checkin-online.ru/aeroflot/?yadclid=72535376&yadordid=30903672&yclid=633305469141123071");
        $x("//input[@id='fl_fio']").setValue(name);
        $x("//input[@id='fl_num']").setValue(num);
        $x("//input[@id='fl_num']").setValue(dateof);
        $x("//button[@class='of_main_form__submit']").click();
    }

    @DisplayName("EnumSource. Смотрим новости и развлечения на yahoo.com ")
    @EnumSource(MenuItem.class)
    @ParameterizedTest()
    void yandexSearchMenuTest(MenuItem testData) {
        Selenide.open("https://www.yahoo.com");
        Selenide.$x(String.format("//a[contains(text(),'%s') " + "and @class='_yb_14cv7  rapid-noclick-resp']", testData.rusName)).click();
        Assertions.assertEquals(
                1,
                WebDriverRunner.getWebDriver().getWindowHandles().size()
        );
    }
}












