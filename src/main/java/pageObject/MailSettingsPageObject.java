package pageObject;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailSettingsPageObject extends Base {

    public MailSettingsPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//div[@class='navigationItem-0-2-69 normal-0-2-75' and @data-test-id = 'navigation-menu-item:general']")
    WebElement generalSettingsButton;

    @FindBy(xpath = ".//button[@data-test-id = 'edit']")
    WebElement editSingButton;

    @FindBy(xpath = ".//div[@spellcheck='true']")
    WebElement signInput;

    @FindBy(xpath = ".//button[@data-test-id = 'save' and @type = 'submit']")
    WebElement saveSignButton;

    @FindBy(xpath = ".//button[@data-test-id = 'to-inbox']")
    WebElement backToMailButton;

    /**
     * Нажатие на кнопку общих настроек.
     */
    public void clickGeneralSettingsButton() {
        click(generalSettingsButton);
    }

    /**
     * Нажатие на кнопку изменения подписи.
     */
    public void clickEditSingButton() {
        click(editSingButton);
    }

    /**
     * Установка новой подписи.
     */
    public void setSignInput(String text) {
        signInput.clear();
        setText(signInput, text);
    }

    /**
     * Сохранение подписи.
     */
    public void clickSaveSignButton() {
        click(saveSignButton);
    }

    /**
     * Нажатие на кнопку, которая возвращает в почту из настроек.
     */
    public void clickBackToMailButton() {
        click(backToMailButton);
    }
}
