package ru.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContactPerson {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().create();
        WebDriver webDriver = new ChromeDriver();

        new Login(webDriver).login("Applanatest1","Student2020!");

        new Actions(webDriver).moveToElement(webDriver.findElement(By.xpath("//span[@class='title' and .='Контрагенты']")))
                .build().perform();
        new WebDriverWait(webDriver,10,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class=\"dropdown-menu menu_level_1\"]//a[contains(@href, '/contact/')]")));
        webDriver.findElement(By.xpath("//ul[@class=\"dropdown-menu menu_level_1\"]//a[contains(@href, '/contact/')]")).click();
        webDriver.findElement(By.linkText("Создать контактное лицо")).click();
        webDriver.findElement(By.xpath("//input[@name=\"crm_contact[lastName]\"]")).sendKeys("Иванов");
        webDriver.findElement(By.xpath("//input[@name=\"crm_contact[firstName]\"]")).sendKeys("Иван");


        new ChooseOrganization(webDriver).chooseOrganization("GeekBrains1");
        /*
        webDriver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        new WebDriverWait(webDriver,5,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.,'Все организации')]"))) ;
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]/../preceding-sibling::div/input")).sendKeys("GeekBrains1");
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]")).click();
         */

        webDriver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("менеджер");
        webDriver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        new WebDriverWait(webDriver,10,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message']"))); //TODO проверить xpath элемента





webDriver.quit();

    }
}
