package ru.geekbrains.lesson6;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.lesson6.blocks.HeaderBlock;

public class LamodaCatalogPageTest extends BasicTest {


    private String basicUrl = "https://www.lamoda.ru/";


    @SneakyThrows
    @Test
    @DisplayName("Позитивный тест на корректность работы фильтра товаров по цене 1000 руб")

    void PositiveProductFilterPrice1000RubTest(){
     //   webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait)
                .hoverHeaderMenuCatalogElement("Одежда")
                .clickToChooseElement("Брюки")
                .makePriceFilter("1000")
                .checkPriceFilterEqual("1 000"); //TODO Учесть, что цена пишется через пробел
 }


    @SneakyThrows
    @Test
    @DisplayName("Позитивный тест на корректность работы фильтра, если товар по запросу не найден")

    void PositiveProductFilterIfNothingFoundTest(){
        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait)
                .hoverHeaderMenuCatalogElement("Одежда")
                .clickToChooseElement("Брюки")
                .PriceFilterNothingFound()
                .checkPriceFilterNothingFound();
        }


    }

