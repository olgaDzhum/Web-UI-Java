package ru.geekbrains.lesson6;

import jdk.jfr.Description;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.lesson6.blocks.HeaderBlock;


public class LamodaLoginPageTest extends BasicTest{



    private String validLogin = "olga.mailfortests@gmail.com";
    private String validPassword = "testtest";
    private String unValidLogin = "olga@gmail.com";
    private String unValidPassword = "test12345";
    private String invalidPasswordLessThen6symbols = "123";
    private String invalidLoginNotEmail = "notEmail";


    private String basicUrl = "https://www.lamoda.ru/";



    @SneakyThrows
    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    @Description("Проверка успешной вторизации")
    void positiveAuthorization() {

        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait).clickEnterButton()
                .positiveAuthorization(validLogin, validPassword)
                .checkSuccessfulAuthorization();

    }

    @SneakyThrows
    @Test
    @DisplayName("Авторизация с невалидным логином и валидным паролем")
    @Description("Наличие сообщения о некорректном логине")
    void negativeLoginAuthorization() {

        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait).clickEnterButton()
                .invalidLoginAuthorization(unValidLogin,validPassword)
                .checkInvalidLoginAuthorization();
    }

    @SneakyThrows
    @Test
    @DisplayName("Авторизация с валидным логином и невалидным паролем больше 6 символов")
    @Description("Наличие сообщения о некорректном пароле")
    void negativePasswordAuthorization() {

        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait).clickEnterButton()
                .invalidPasswordAuthorization(validLogin,unValidPassword)
                .checkInvalidPasswordAuthorization();
    }


    @Test
    @SneakyThrows
    @DisplayName("Ввод в поле пароля меньше 6 символов")
    @Description("Наличие сообщения о том, что пароль должен быть больше 6 символов")
    void negativePasswordAuthorizationShortPassword() {

        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait).clickEnterButton()
                .inputPasswordLessThenSixSymbols(validLogin,invalidPasswordLessThen6symbols)
                .checkInputPasswordLessThenSixSymbols();
    }

    @Test
    @SneakyThrows
    @DisplayName("Ввод в поле не e-mail")
    @Description("Наличие сообщения о том, что логином должна быть электронная почта")
    void negativeLoginNotEmailAuthorization() {

        webDriver.get(basicUrl);
        new HeaderBlock(webDriver,webDriverWait).clickEnterButton()
                .inputLoginNotEmail(invalidLoginNotEmail,validPassword)
                .checkInputLoginNotEmail();
    }
}
