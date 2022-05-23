package pageObject;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailHomePageObject extends Base {
    public MailHomePageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//button[@data-testid = 'enter-mail-primary']")
    WebElement enterMailButton;

    @FindBy(xpath = ".//iframe[@class = 'ag-popup__frame__layout__iframe']")
    WebElement authenticationFrame;

    @FindBy(xpath = ".//input[@name = 'username']")
    WebElement usernameInput;

    @FindBy(xpath = ".//button[@data-test-id = 'next-button']")
    WebElement passwordButton;

    @FindBy(xpath = ".//input[@name = 'password']")
    WebElement passwordInput;

    @FindBy(xpath = ".//button[@data-test-id = 'submit-button']")
    WebElement enterButton;

    /**
     * Нажатие на кнопку, которая открывает форму авторизации.
     */
    public void clickEnterMailButton() {
        click(enterMailButton);
    }

    /**
     * Переключение на форму авторизации.
     */
    public void switchAuthenticationFrame() {
        switchFrame(authenticationFrame);
    }

    /**
     * Ввод логина в форму авторизации.
     */
    public void setUsernameInput(String text) {
        setText(usernameInput, text);
    }

    /**
     * Нажатие на кнопку, которая включает инпут для пароля.
     */
    public void clickPasswordButton() {
        click(passwordButton);
    }

    /**
     * Ввод пароля в форму авторизации.
     */
    public void setPasswordInput(String text) {
        setText(passwordInput, text);
    }

    /**
     * Нажатие на кнопку входа в почту.
     */
    public void clickEnterButton() {
        click(enterButton);
    }
}
