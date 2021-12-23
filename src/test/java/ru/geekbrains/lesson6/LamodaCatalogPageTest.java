package ru.geekbrains.lesson6;

import jdk.jfr.Description;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.geekbrains.lesson6.blocks.HeaderBlock;

public class LamodaCatalogPageTest extends BasicTest {


    private String basicUrl = "https://www.lamoda.ru/";


    @SneakyThrows
    @ParameterizedTest(name = "Рубрика {0}")
    @ValueSource(strings = {"Брюки", "Джинсы", "Комбинезоны"})
    //TODO  Разобраться, как должен работать параметризированный тест
    @DisplayName("Позитивный тест на корректность работы фильтра товаров по цене 1000 руб")
    @Description("Проверка, что все найденные товары соответствуют запрошенной цене")
    void positiveProductFilterPrice1000RubTest() {
        //   webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.get(basicUrl);
        new HeaderBlock(webDriver, webDriverWait)
                .hoverHeaderMenuCatalogElement("Одежда")
                .clickToChooseElement("Брюки")
                .makePriceFilter("1000")
                .checkPriceFilterEqual("1 000"); //TODO Учесть, что цена пишется через пробел
    }


    @SneakyThrows
    @Test
    @DisplayName("Позитивный тест на корректность работы фильтра, если товар по запросу не найден")
    @Description("Проверка сообщения о том, что товар не найден")
    void positiveProductFilterIfNothingFoundTest() {
        webDriver.get(basicUrl);
        new HeaderBlock(webDriver, webDriverWait)
                .hoverHeaderMenuCatalogElement("Одежда")
                .clickToChooseElement("Брюки")
                .PriceFilterNothingFound()
                .checkPriceFilterNothingFound();
    }


    }

