package ru.geekbrains.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LamodaCatalogPageTest {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private String basicUrl = "https://www.lamoda.ru/";


    @SneakyThrows
    @Test
    @DisplayName("Позитивный тест на корректность работы фильтра товаров по цене 1000 руб")

    void PositiveProductFilterPrice1000RubTest(){
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.get(basicUrl);
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Одежда')]")))
                .build()
                .perform();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Брюки')]")));
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//a[contains(text(),'Брюки')]")).click();
        webDriver.findElement(By.xpath("//div[@class='multifilter multifilter_price']")).click();
        webDriver.findElement(By.xpath("//input[@class='text-field range__value range__value_left']")).sendKeys("1000");
        webDriver.findElement(By.xpath("//input[@class='text-field range__value range__value_right']")).sendKeys("1000");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[@class='multifilter multifilter_price']//span[contains(text(),'Применить')]")).click();
        Thread.sleep(2000);
        List<WebElement> products = webDriver.findElements(By.xpath(".//div[@class='products-list-item']"));
        Iterator<WebElement> iterator = products.iterator();
        SoftAssertions softAssertions =new SoftAssertions();
        while (iterator.hasNext()) {
          //  WebElement element = iterator.next();
            String actualValue = iterator.next().findElement(By.xpath("//span[@class='price__action js-cd-discount ']")).getText();
            System.out.println(actualValue);
            softAssertions.assertThat(actualValue).isEqualTo("1 000");
        }
softAssertions.assertAll();
        webDriver.quit();
    }

    @SneakyThrows
    @Test
    @DisplayName("Позитивный тест на корректность работы фильтра, если товар по запросу не найден")

    void PositiveProductFilterIfNothingFoundTest(){
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.get(basicUrl);
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Одежда')]")))
                .build()
                .perform();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Брюки')]")));
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//a[contains(text(),'Брюки')]")).click();
        webDriver.findElement(By.xpath("//div[@class='multifilter multifilter_price']")).click();
        webDriver.findElement(By.xpath("//input[@class='text-field range__value range__value_left']")).sendKeys("1");
        webDriver.findElement(By.xpath("//input[@class='text-field range__value range__value_right']")).sendKeys("1");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[@class='multifilter multifilter_price']//span[contains(text(),'Применить')]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(.,'В выбранной категории ничего не найдено')]")));
        Thread.sleep(3000);
        webDriver.quit();
        }


    }

