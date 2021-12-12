package ru.geekbrains.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LamodaLoginPage {


    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private String validLogin = "olga.mailfortests@gmail.com";
    private String validPassword = "testtest";
    private String unValidLogin = "olga@gmail.com";
    private String unValidPassword = "test12345";
    private String typePasswordLessThen6symbols = "123";
    private String loginNotEmail = "notEmail";
    private String basicUrl = "https://www.lamoda.ru/";


    @SneakyThrows
    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    void positiveAuthorization() {
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.get(basicUrl);

        webDriver.findElement(By.xpath("//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")).click();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        webDriver.findElement(By.xpath("//div[@class='input__group']//input[@name='Электронная почта']")).sendKeys(validLogin);
        webDriver.findElement(By.xpath("//input[@name='Пароль']")).sendKeys(validPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Профиль')]")));
        webDriver.quit();
    }

    @SneakyThrows
    @Test
    @DisplayName("Авторизация с невалидным логином и валидным паролем")
    void negativeLoginAuthorization() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.get(basicUrl);
        webDriver.findElement(By.xpath("//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")).click();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        webDriver.findElement(By.xpath("//div[@class='input__group']//input[@name='Электронная почта']")).sendKeys(unValidLogin);
        webDriver.findElement(By.xpath("//input[@name='Пароль']")).sendKeys(validPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        Thread.sleep(4000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        webDriver.quit();
    }

    @SneakyThrows
    @Test
    @DisplayName("Авторизация с валидным логином и невалидным паролем больше 6 символов")
    void negativePasswordAuthorization() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.get(basicUrl);
        webDriver.findElement(By.xpath("//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")).click();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        webDriver.findElement(By.xpath("//div[@class='input__group']//input[@name='Электронная почта']")).sendKeys(validLogin);
        webDriver.findElement(By.xpath("//input[@name='Пароль']")).sendKeys(unValidPassword);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        Thread.sleep(1000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='d-modal__frame']//div[contains(text(),'Неверный логин или пароль')]")));
        Thread.sleep(1000);
        webDriver.quit();

    }


    @Test
    @SneakyThrows
    @DisplayName("Авторизация с валидным логином и валидным паролем меньше 6 символов")
    void negativePasswordAuthorizationShortPassword() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.get(basicUrl);
        webDriver.findElement(By.xpath("//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")).click();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        webDriver.findElement(By.xpath("//div[@class='input__group']//input[@name='Электронная почта']")).sendKeys(validLogin);
        webDriver.findElement(By.xpath("//input[@name='Пароль']")).sendKeys(typePasswordLessThen6symbols);
        Thread.sleep(2000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пароль должен содержать не менее 6 символов.')]")));
        Thread.sleep(1000);
        webDriver.quit();
    }

    @Test
    @SneakyThrows
    @DisplayName("Авторизация с валидным логином и валидным паролем меньше 6 символов")
    void negativeLoginNotEmailAuthorization() {
        webDriver = WebDriverManager.chromedriver().create();

        webDriver.get(basicUrl);
        webDriver.findElement(By.xpath("//div[@class='logo-line-wrapper width-wrapper']//a[contains(text(),'Войти')]")).click();
        webDriverWait = new WebDriverWait(webDriver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Вход')]")));
        webDriver.findElement(By.xpath("//div[@class='input__group']//input[@name='Электронная почта']")).sendKeys(loginNotEmail);
        webDriver.findElement(By.xpath("//input[@name='Пароль']")).sendKeys(validPassword);
        Thread.sleep(2000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Пожалуйста, проверьте, правильно ли указан адрес электронной почты.')] ")));
        Thread.sleep(1000);
        webDriver.quit();
    }
}
