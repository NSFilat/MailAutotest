package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    WebDriver driver;

    public Base(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Вставка текста.
     */
    public void setText(final WebElement element, String text) {
        waitVisibleElement(element);
        element.sendKeys(text);
    }

    /**
     * Считывание текста.
     */
    public String getText(final WebElement element) {
        waitVisibleElement(element);
        return element.getText();
    }

    /**
     * Нажатие на кнопку.
     */
    public void click(final WebElement element) {
        waitVisibleElement(element);
        element.click();
    }

    /**
     * Загрузка файла.
     */
    public void uploadFile(final WebElement element, By xpath, String filePath) {
        waitInvisibleElement(xpath);
        element.sendKeys(filePath);
    }

    /**
     * Проверка элемента на наличие в DOM.
     */
    public boolean elementIsEmpty(By xpath) {
        return driver.findElements(xpath).isEmpty();
    }

    /**
     * Наведение на элемент.
     */
    public void moveToElement(final WebElement element) {
        Actions actions = new Actions(driver);
        waitVisibleElement(element);
        actions.moveToElement(element).build().perform();
    }

    /**
     * Переключение окна на последнее открытое.
     */
    public void switchWindow() {
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
    }

    /**
     * Закрытие текущего окна.
     */
    public void closeWindow() {
        driver.close();
    }

    /**
     * Переключение на выбранный фрейм.
     */
    public void switchFrame(WebElement element) {
        waitVisibleElement(element);
        driver.switchTo().frame(element);
    }

    /**
     * Ожидание проверки, что элемент видимый.
     */
    public boolean waitVisibleElement(final WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ожидание проверки, что элемент либо невидим, либо отсутствует в DOM.
     */
    public boolean waitInvisibleElement(By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ожидание проверки, что элемент содержит атрибут с определённым значением.
     */
    public boolean waitAttributeElement(final WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Ожидание проверки, что элемент содержит определённый текст.
     */
    public boolean waitTextElement(final WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public WebDriver getDriver() {
        return driver;
    }
}
