package ru.geekbrains.lesson3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseOrganization {
    private WebDriver webDriver;


    public ChooseOrganization(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public void chooseOrganization (String organizationName){
        webDriver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        new WebDriverWait(webDriver,5,500).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(.,'Все организации')]"))) ;
        webDriver.findElement(By.xpath("//li[contains(.,'Все организации')]/../preceding-sibling::div/input")).sendKeys(organizationName);
        webDriver.findElement(By.xpath("//li[contains(.,'"+organizationName+"')]")).click();
    }

}
