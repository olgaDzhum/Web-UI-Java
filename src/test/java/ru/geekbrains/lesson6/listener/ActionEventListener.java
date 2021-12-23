package ru.geekbrains.lesson6.listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.step;

public class ActionEventListener extends AbstractWebDriverEventListener {

    private static final Logger logger = LoggerFactory.getLogger("Action Logger");

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String stepName = "Нажать на элемент с текстом " + element.getText();
        step(stepName);
        logger.info(stepName);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        String stepName = "Успешно!";
        step(stepName);
        logger.info(stepName);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String stepName = "Ввести текст " + Arrays.toString(keysToSend) + " в поле  = " + element.getText(); //TODO Правильно?
        step(stepName);
        logger.info(stepName);

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String stepName = "Успешно!";
        step(stepName);
        logger.info(stepName);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Allure.addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}