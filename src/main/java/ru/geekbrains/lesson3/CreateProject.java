package ru.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CreateProject {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.get("https://crm.geekbrains.space");

        new Login(webDriver).login("Applanatest1","Student2020!");

        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(By.xpath("//span[.='Проекты']/ancestor::a")))
                        .build().perform();
        webDriver.findElement(By.xpath("//a[./span[text()='Проекты']]")).click();

        //Явное ожидание:
        new WebDriverWait(webDriver,10,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Создать проект']")));

        webDriver.findElement(By.xpath("//a[text()='Создать проект']")).click();
        webDriver.findElement(By.xpath("//input[contains(@id,'crm_project_name')]")).sendKeys("Corporation"+ UUID.randomUUID());


        new ChooseOrganization(webDriver).chooseOrganization("GeekBrains1");

       /*  webDriver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();
        new WebDriverWait(webDriver,5,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.,'Все организации')]")));
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]/../preceding-sibling::div/input")).sendKeys("GeekBrains1");
        webDriver.findElement(By.xpath("//li[contains(.,'GeekBrains1')]")).click();
        */

        new Select(webDriver.findElement(By.name("crm_project[businessUnit]"))).selectByVisibleText("Зайдуллин Рустам");
        new Select(webDriver.findElement(By.name("crm_project[rp]"))).selectByVisibleText("Воденеев Денис");
        new Select(webDriver.findElement(By.name("crm_project[manager]]"))).selectByVisibleText("Козлов Илья");

        webDriver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();

        new WebDriverWait(webDriver,10,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Проект сохранен')]")));






        webDriver.quit();

    }

   /* public static void login(WebDriver webDriver) {
        webDriver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        webDriver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        webDriver.findElement(By.id("_submit")).click();
    }

    */
}

