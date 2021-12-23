package ru.geekbrains.lesson6.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CatalogPage extends BasePage{


    @FindBy(xpath = "//div[@class='multifilter multifilter_price']")
    private WebElement priceFilterButton;

    @FindBy(xpath = "//input[@class='text-field range__value range__value_left']")
    private WebElement leftPriceInput;

    @FindBy(xpath = "//input[@class='text-field range__value range__value_right']")
    private WebElement rightPriceInput;

    @FindBy(xpath = "//div[@class='multifilter multifilter_price']//span[contains(text(),'Применить')]")
    private WebElement applyButton;




    public CatalogPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @SneakyThrows
    @Step("Установить фильтр цены на значение: {0} ")
public CatalogPage makePriceFilter(String price){
    priceFilterButton.click();
    leftPriceInput.sendKeys(price);
    rightPriceInput.sendKeys(price);
    applyButton.click();
    return this;
}

    @Step("Проверка значения цены каждого товара по запросу")
    public CatalogPage checkPriceFilterEqual(String expectedPrice) { //TODO Учесть, что цена пишется через пробел

        SoftAssertions softAssertions = new SoftAssertions();
        webDriver.findElements(By.xpath("//div[@class='products-list-item']//span[@class='price__action js-cd-discount ']"))
                .forEach(price -> softAssertions.assertThat(price.getText()).isEqualTo("1 000"));
        softAssertions.assertAll();


  /*      List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='products-list-item']"));
        Iterator<WebElement> iterator = products.iterator();
        SoftAssertions softAssertions =new SoftAssertions();
        while (iterator.hasNext()) {
            String actualValue = iterator.next().findElement(By.xpath(".//span[@class='price__action js-cd-discount ']")).getText();
            System.out.println(actualValue);
            softAssertions.assertThat(actualValue).isEqualTo(expectedPrice);
        }
        softAssertions.assertAll();

   */
        return this;
    }

    @SneakyThrows
    @Step("Установить фильтр цены на значение : 1 руб")
public CatalogPage PriceFilterNothingFound(){

   priceFilterButton.click();
    leftPriceInput.sendKeys("1");
    rightPriceInput.sendKeys("1");
    applyButton.click();
    return this;
    }

    @SneakyThrows
    @Step("Проверка наличия сообщения: 'В выбранной категории ничего не найдено'")
    public CatalogPage checkPriceFilterNothingFound(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(.,'В выбранной категории ничего не найдено')]")));
        return this;
    }

}

