package ru.geekbrains.lesson6.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//div[@class='input__group']//input[@name='Электронная почта']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordInput;


    public LoginPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @SneakyThrows
    public LoginPage positiveAuthorization(String validLogin, String validPassword) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(validPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    public LoginPage checkSuccessfulAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Профиль')]")));
        return this;
    }

    @SneakyThrows
    public LoginPage invalidPasswordAuthorization(String validLogin, String invalidPassword) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(invalidPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    @SneakyThrows
    public LoginPage checkInvalidPasswordAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        Thread.sleep(1000);
        return this;
    }

    @SneakyThrows
    public LoginPage invalidLoginAuthorization(String inValidLoginMoreThenSixSymbolsLong, String validPassword) {
        loginInput.sendKeys(inValidLoginMoreThenSixSymbolsLong);
        passwordInput.sendKeys(validPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    public LoginPage checkInvalidLoginAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        return this;
    }


    @SneakyThrows
    public LoginPage inputLoginNotEmail(String invalidLoginNotEMail, String validPassword) {
        loginInput.sendKeys(invalidLoginNotEMail);
        passwordInput.sendKeys(validPassword);
        Thread.sleep(2000);
        return this;
    }

    public LoginPage checkInputLoginNotEmail() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пожалуйста, проверьте, правильно ли указан адрес электронной почты.')] ")));
        return this;
    }

    @SneakyThrows
    public LoginPage inputPasswordLessThenSixSymbols(String validLogin, String passwordLessThenSixSymbolsLong) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(passwordLessThenSixSymbolsLong);
        Thread.sleep(2000);
        return this;
    }

    @SneakyThrows
    public LoginPage checkInputPasswordLessThenSixSymbols() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пароль должен содержать не менее 6 символов.')]")));
        Thread.sleep(1000);
        return this;
    }

}
