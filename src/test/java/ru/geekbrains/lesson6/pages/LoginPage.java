package ru.geekbrains.lesson6.pages;

import io.qameta.allure.Step;
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
    @Step("Авторизоваться с логином {0} и паролем {1}")
    public LoginPage positiveAuthorization(String validLogin, String validPassword) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(validPassword);
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    @Step("Проверка наличия кнопки: 'Профиль'")
    public LoginPage checkSuccessfulAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Профиль')]")));
        return this;
    }

    @SneakyThrows
    @Step("Авторизоваться с логином {0} и паролем {1}")
    public LoginPage invalidPasswordAuthorization(String validLogin, String invalidPassword) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(invalidPassword);
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    @SneakyThrows
    @Step("Проверка наличия сообщения: 'Неверный логин или пароль'")
    public LoginPage checkInvalidPasswordAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        return this;
    }

    @SneakyThrows
    @Step("Авторизоваться с логином {0} и паролем {1}")
    public LoginPage invalidLoginAuthorization(String inValidLoginMoreThenSixSymbolsLong, String validPassword) {
        loginInput.sendKeys(inValidLoginMoreThenSixSymbolsLong);
        passwordInput.sendKeys(validPassword);
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        return this;
    }

    @Step("Проверка наличия сообщения: 'Неверный логин или пароль'")
    public LoginPage checkInvalidLoginAuthorization() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        return this;
    }


    @SneakyThrows
    @Step("Авторизоваться с логином {0} и паролем {1}")
    public LoginPage inputLoginNotEmail(String invalidLoginNotEMail, String validPassword) {
        loginInput.sendKeys(invalidLoginNotEMail);
        passwordInput.sendKeys(validPassword);
        return this;
    }

    @Step("Проверка наличия сообщения: 'Пожалуйста, проверьте, правильно ли указан адрес электронной почты'")
    public LoginPage checkInputLoginNotEmail() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пожалуйста, проверьте, правильно ли указан адрес электронной почты.')] ")));
        return this;
    }

    @SneakyThrows
    @Step("Авторизоваться с логином {0} и паролем {1}")
    public LoginPage inputPasswordLessThenSixSymbols(String validLogin, String passwordLessThenSixSymbolsLong) {
        loginInput.sendKeys(validLogin);
        passwordInput.sendKeys(passwordLessThenSixSymbolsLong);
        return this;
    }

    @SneakyThrows
    @Step("Проверка наличия сообщения: 'Пароль должен содержать не менее 6 символов'")
    public LoginPage checkInputPasswordLessThenSixSymbols() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пароль должен содержать не менее 6 символов.')]")));
        return this;
    }

}
