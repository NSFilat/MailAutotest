package pageObject;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CloudPageObject extends Base {
    public CloudPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//div[@data-name = '/attaches']")
    WebElement fromMailButton;

    @FindBy(xpath = ".//div[@data-name = '0']//div[contains(@class, 'TreeNode__expandControl')]")
    WebElement expandMessageButton;

    @FindBy(xpath = ".//div[@data-name = '0']//div[contains(@class, 'TreeNode__root--22m4E')]")
    WebElement incomingMessageButton;

    @FindBy(xpath = "(.//div[contains(@style, 'display: block; overflow: hidden;')])[1]")
    WebElement fileNameText;

    /**
     * Нажатие на кнопку открытия файлов из почты.
     */
    public void clickFromMailButton() {
        click(fromMailButton);
    }

    /**
     * Нажатие на кнопку раскрытия разделов писем (входящие, отправленные).
     */
    public void clickExpandMessageButton() {
        click(expandMessageButton);
    }

    /**
     * Нажатие на кнопку открытия раздела входящих писем.
     */
    public void clickIncomingMessageButton() {
        click(incomingMessageButton);
    }

    /**
     * Проверка названия файла.
     */
    public void assertFileNameText(String text) {
        assert waitTextElement(fileNameText, text);
    }
}
