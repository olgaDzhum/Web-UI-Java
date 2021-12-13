package ru.geekbrains.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

private WebDriver webDriver;


    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;


    }

    public void login(String login,String password) {
        webDriver.get("https://crm.geekbrains.space");
        webDriver.findElement(By.id("prependedInput")).sendKeys(login);
        webDriver.findElement(By.id("prependedInput2")).sendKeys(password);
        webDriver.findElement(By.id("_submit")).click();
    }
}
