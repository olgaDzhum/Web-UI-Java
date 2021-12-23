package ru.geekbrains.lesson6.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.geekbrains.lesson6.blocks.HeaderBlock;

public class BasePage extends BaseView{

@Getter
private HeaderBlock headerBlock =new HeaderBlock(webDriver,webDriverWait);

    public BasePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

}
