package ru.geekbrains.lesson6.pages;

import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;


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
public CatalogPage makePriceFilter(String price){
    priceFilterButton.click();
    leftPriceInput.sendKeys(price);
    rightPriceInput.sendKeys(price);
    Thread.sleep(2000);
    applyButton.click();
    Thread.sleep(2000);
    return this;
}

    public CatalogPage checkPriceFilterEqual(String expectedPrice) { //TODO Учесть, что цена пишется через пробел
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='products-list-item']"));
        Iterator<WebElement> iterator = products.iterator();
        SoftAssertions softAssertions =new SoftAssertions();
        while (iterator.hasNext()) {
            String actualValue = iterator.next().findElement(By.xpath(".//span[@class='price__action js-cd-discount ']")).getText();
            System.out.println(actualValue);
            softAssertions.assertThat(actualValue).isEqualTo(expectedPrice);
        }
        softAssertions.assertAll();
        return this;
    }

@SneakyThrows
public CatalogPage PriceFilterNothingFound(){

   priceFilterButton.click();
    leftPriceInput.sendKeys("1");
    rightPriceInput.sendKeys("1");
    Thread.sleep(2000);
    applyButton.click();
    return this;
    }

    @SneakyThrows
    public CatalogPage checkPriceFilterNothingFound(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(.,'В выбранной категории ничего не найдено')]")));
        Thread.sleep(3000);
        return this;
    }

}

