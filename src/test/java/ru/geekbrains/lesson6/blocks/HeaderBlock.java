package ru.geekbrains.lesson6.blocks;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.lesson6.pages.BaseView;
import ru.geekbrains.lesson6.pages.CatalogPage;
import ru.geekbrains.lesson6.pages.LoginPage;

public class HeaderBlock extends BaseView {

    @FindBy(xpath = "//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")
    private WebElement enterButton;



    public HeaderBlock(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @SneakyThrows
    @Step("Нажатие кнопки 'Вход' ")
    public LoginPage clickEnterButton(){
        enterButton.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        return new LoginPage(webDriver,webDriverWait);
    }

    @SneakyThrows
    @Step("Навести мышь на элемент: {0} ")
    public HeaderBlock hoverHeaderMenuCatalogElement (String nameOfCatalog){
        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//div[@id='menu']//a[contains(text(),'"+nameOfCatalog+"')]")))
                .build()
                .perform();

        Thread.sleep(2000);
        return this;
    }


    @SneakyThrows
    @Step("Кликнуть на элемент: {0}")
    public CatalogPage clickToChooseElement (String nameOfCatalog) {
        // webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Брюки')]")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + nameOfCatalog + "')]"))); // второй вариант xpath: //a[contains(text(),'Категории')]//
        webDriver.findElement(By.xpath("//a[contains(text(),'" + nameOfCatalog + "')]")).click();
        return new CatalogPage(webDriver, webDriverWait);
    }



}
