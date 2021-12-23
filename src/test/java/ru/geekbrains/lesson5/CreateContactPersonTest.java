package ru.geekbrains.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.lesson3.ChooseOrganization;
import ru.geekbrains.lesson3.Login;

import java.util.concurrent.TimeUnit;

public class CreateContactPersonTest {

    WebDriver webDriver;
    WebDriverWait webDriverWait;
    String url = "";

    @BeforeEach
    void setUp() {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 6);

    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }


    @Test
    void createContactPersonTest() {
        new Login(webDriver).login("Applanatest1", "Student2020!");

        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//span[@class='title' and .='Контрагенты']")))
                .build().perform();
        new WebDriverWait(webDriver, 10, 500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class=\"dropdown-menu menu_level_1\"]//a[contains(@href, '/contact/')]")));
        webDriver.findElement(By.xpath("//ul[@class=\"dropdown-menu menu_level_1\"]//a[contains(@href, '/contact/')]")).click();
        webDriver.findElement(By.linkText("Создать контактное лицо")).click();
        webDriver.findElement(By.xpath("//input[@name=\"crm_contact[lastName]\"]")).sendKeys("Иванов");
        webDriver.findElement(By.xpath("//input[@name=\"crm_contact[firstName]\"]")).sendKeys("Иван");


        new ChooseOrganization(webDriver).chooseOrganization("GeekBrains1");


        webDriver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("менеджер");
        webDriver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        new WebDriverWait(webDriver, 10, 500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message']"))); //TODO проверить xpath элемента
    }
}
