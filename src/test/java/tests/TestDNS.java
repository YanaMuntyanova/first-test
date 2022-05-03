package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.selenide.AllureSelenide;
import net.jodah.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Lesson8.MyException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class TestDNS {

//    @BeforeAll
//    static void setUpDriver() {
//        //    Configuration.holdBrowserOpen = true;
//        System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/first-test/build/downloads/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverRunner.setWebDriver(driver);
//    }

    @BeforeAll
    static void setUpDriver() {
        ChromeOptions chromeOptionsHeadless = new ChromeOptions();
        chromeOptionsHeadless.setHeadless(true);
        chromeOptionsHeadless.addArguments("--disable-gpu");
        chromeOptionsHeadless.addArguments("--window-size=1249,1248");
        chromeOptionsHeadless.addArguments("--ignore-certificate-errors");
        chromeOptionsHeadless.setAcceptInsecureCerts(true);
        chromeOptionsHeadless.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/first-test/build/downloads/chromedriver.exe");

       WebDriver driver = new ChromeDriver(chromeOptionsHeadless);
        WebDriverRunner.setWebDriver(driver);

    }

    @Test
    public void testHeadless() throws InterruptedException {

        WebDriverRunner.getWebDriver().get("https://www.dns-shop.ru/");
        System.out.println("ПРОВЕРКА  " + WebDriverRunner.getWebDriver().getCurrentUrl());
        WebDriverRunner.getWebDriver().quit();
    }

//   @CsvSource ({"Москва, микроволновка, 22, дороже", "Новосибирск, холодильник, 25, дешевле"})
    @CsvSource({"Москва, чехол, 5, дешевле"})
    @Disabled
    @ParameterizedTest
    public void superTest(String city, String nameEl, Integer countEl, String costEl) throws InterruptedException {

        open("https://www.dns-shop.ru/");

// Выбрали город
        $x("//div[@class='dropdown-city']/a[text()='Выбрать другой']").click();
        Thread.sleep(7000);
        $x("//input[@placeholder='Найти город']").click();
        $x("//input[@placeholder='Найти город']").setValue(city).pressEnter();
        Thread.sleep(7000);

// Ввели искомый элемент
        $x("//div[@class='header-menu-wrapper']//form//input").click();
        $x("//div[@class='header-menu-wrapper']//form//input").setValue(nameEl).pressEnter();

// выбираем сортировку
        if (costEl.equals("дороже")) {
            $x("//a//span[contains(text(), 'Сначала')]").click();
            WebDriverRunner.getWebDriver().findElement
                    (By.xpath("//div//label//span[text()= 'Сначала дорогие']")).click();
            Thread.sleep(7000);
        }
        if (costEl.equals("дешевле")) {
            $x("//a//span[contains(text(), 'Сначала')]").click();
            WebDriverRunner.getWebDriver().findElement
                    (By.xpath("//div//label//span[text()= 'Сначала недорогие']")).click();
            Thread.sleep(7000);
        }
// Пролистываем страницу вниз
        WebDriverRunner.getWebDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL, Keys.END);
        Thread.sleep(7000);

// Проверяем, видим ли мы нужное нам число элементов. Если нет, то жмем кнопку "показать еще"
        while (WebDriverRunner.getWebDriver().findElements(By.xpath("//div[@data-id='product']")).size() < countEl) {
            $x("//button[text()='Показать ']").click();
            WebDriverRunner.getWebDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL, Keys.END);
            Thread.sleep(7000);
        }

//Создаем лист элементов, чтобы дальше с ним работать.
        List<WebElement> el = new ArrayList<>(WebDriverRunner.getWebDriver().
                findElements(By.xpath("//div[@class='product-buy__price']")));

// Выводим цены и записываем их в лист price, убираем лишние символы, переводим в инты.
        ArrayList<String> price = new ArrayList<>();
        for (WebElement webElement : el) {
//        price.add(webElement.getText().substring(0, 6).replace("₽", "").replaceAll("\\s", ""));
            price.add(webElement.getText().replace("₽", "").replaceAll("\\s", ""));
        }
// Выбираем нужное число элементов
        List<String> priceFinal = price.subList(0, countEl);
        System.out.println("Получили цены: " + priceFinal);
        System.out.println("проверяем количество. Передано - " + countEl + "  Получено - " + priceFinal.size());

        ArrayList<Integer> priceInt = priceFinal.stream().map(Integer::valueOf).collect(Collectors.toCollection(ArrayList::new)); // не понятная строчка

// Проверяем, что цены отсортированы по убыванию.Берем полученный список priceInt, копируем его в priceIntCopy, сортируем по убываеию. Сравниваем.
        ArrayList<Integer> priceIntCopy = new ArrayList<>(priceInt);

        if (costEl.equals("дешевле")) {
            Collections.sort(priceIntCopy);
        }
        if (costEl.equals("дороже")) {
            priceIntCopy.sort(Collections.reverseOrder());
        }

        if (priceIntCopy.equals(priceInt)) {   //equals используем т..к важен порядок элементов.
            System.out.println(" Все верно, элементы отсортированы ");
        } else System.out.println("Сортировка на странице не сработала");

// Ищем минимальную цену
        System.out.println("Минимальная цена элемента " + nameEl + " в городе " + city + " - " +  Collections.min(priceInt));


    }



}


