package pageObject;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class MailMainPageObject extends Base {

    public MailMainPageObject(WebDriver driver) {
        super(driver);
    }

    public String counterMessage;

    @FindBy(xpath = ".//span[@class = 'compose-button__wrapper']")
    WebElement writeButton;

    @FindBy(xpath = ".//div[@class = 'focus-zone focus-zone_fluid']")
    WebElement messagePopup;

    @FindBy(xpath = ".//input[@tabindex = '100']")
    WebElement targetMailInput;

    @FindBy(xpath = ".//input[@name = 'Subject']")
    WebElement themeInput;

    @FindBy(xpath = ".//div[@role = 'textbox']")
    WebElement messageInput;

    @FindBy(xpath = ".//input[@type = 'file' and contains(@class, 'desktopInput')]")
    WebElement fileInput;

    @FindBy(xpath = ".//span[contains(@class, 'ph-notify svelte-syffyg')]")
    WebElement counterMessageText;

    @FindBy(xpath = ".//span[@class = 'button2 button2_base button2_primary button2_hover-support js-shortcut']")
    WebElement sendButton;

    @FindBy(xpath = ".//span[@class = 'button2__wrapper button2__wrapper_centered' and @tabindex = '1000']")
    WebElement closeNotificationPopup;

    @FindBy(xpath = ".//a[@href='/tomyself/']")
    WebElement incomingMessagesButton;

    @FindBy(xpath = ".//a[contains(@class, 'llc_first')]")
    WebElement messageGrid;

    @FindBy(xpath = "(.//span[@class = 'll-sj__normal'])[1]")
    WebElement incomingFirstMessageThemeText;

    @FindBy(xpath = "(.//span[@class = 'll-sj__normal'])[2]")
    WebElement incomingSecondMessageThemeText;

    @FindBy(xpath = ".//h2[@class = 'thread-subject']")
    WebElement inMessageThemeText;

    @FindBy(xpath = ".//div[@class = 'js-helper js-readmsg-msg']/div/div/div")
    WebElement messageText;

    @FindBy(xpath = ".//div[@data-test-id = 'thumb']")
    WebElement viewAttachmentLink;

    @FindBy(xpath = ".//div[@data-test-id = 'overlay']//small")
    WebElement attachmentNameText;

    @FindBy(xpath = ".//a[@class = 'attach-list__controls-element-download']")
    WebElement downloadAttachmentLink;

    @FindBy(xpath = ".//span[@class = 'widget__ico widget__ico_cloud']")
    WebElement toCloudLink;

    @FindBy(xpath = ".//div[@class = 'sidebar-widgets__footer__item  js-promo-id_menu-item_settings']")
    WebElement settingsButton;

    @FindBy(xpath = ".//div[@data-test-id = 'more']")
    WebElement allSettingsButton;

    @FindBy(xpath = ".//div[@data-signature-widget = 'content']")
    WebElement signText;

    @FindBy(xpath = "(.//span[@class = 'll-av__img'])[1]")
    WebElement firstMessageCheckbox;

    @FindBy(xpath = "(.//span[@class = 'll-av__img'])[2]")
    WebElement secondMessageCheckbox;

    @FindBy(xpath = ".//span[@data-title-shortcut = 'Del']")
    WebElement deleteMessageButton;

    /**
     * Нажатие на кнопку, которая открывает форму создания письма.
     */
    public void clickWriteButton() {
        click(writeButton);
    }

    /**
     * Проверка открытия формы создания письма.
     */
    public void assertMessagePopup() {
        assert waitVisibleElement(messagePopup);
    }

    /**
     * Введение адресата.
     */
    public void setTargetMailInput(String text) {
        setText(targetMailInput, text);
    }

    /**
     * Введение темы письма.
     */
    public void setThemeInput(String text) {
        setText(themeInput, text);
    }

    /**
     * Введение текста письма.
     */
    public void setMessageInput(String text) {
        setText(messageInput, text);
    }

    /**
     * Прикрепление файла к письму.
     */
    public void uploadFileInput(String filePath) {
        uploadFile(fileInput, By.xpath(".//input[@type = 'file' and contains(@class, 'desktopInput')]"), filePath);
    }

    /**
     * Получение количества непрочитанных писем.
     */
    public void getCounterMessageText() {
        if (elementIsEmpty(By.xpath(".//span[contains(@class, 'ph-notify svelte-syffyg')]"))) {
            counterMessage = "0";
        }
        else {
            counterMessage = getText(counterMessageText);
        }
    }

    /**
     * Нажатие на кнопку отправки письма.
     */
    public void clickSendButton() {
        click(sendButton);
    }

    /**
     * Проверка получения нового письма.
     */
    public void assertCounterMessageText() {
        assert waitTextElement(counterMessageText, String.valueOf(Integer.parseInt(counterMessage) + 1));
    }

    /**
     * Проверка загрузки писем в разделе входящих писем.
     */
    public void assertMessageGrid(String attribute, String value) {
        assert waitAttributeElement(messageGrid, attribute, value);
    }

    /**
     * Закрытие оповещения об отправке письма.
     */
    public void clickCloseNotificationPopup() {
        click(closeNotificationPopup);
    }

    /**
     * Нажатие на кнопку, которая открывает раздел входящих писем.
     */
    public void clickIncomingMessagesButton() {
        click(incomingMessagesButton);
    }

    /**
     * Проверка темы первого письма в списке в разделе входящих писем.
     */
    public void assertFirstThemeText(String text) {
        assert waitTextElement(incomingFirstMessageThemeText, text);

    }

    /**
     * Проверка темы второго письма в списке в разделе входящих писем.
     */
    public void assertSecondThemeText(String text) {
        assert waitTextElement(incomingSecondMessageThemeText, text);

    }

    /**
     * Нажатие на кнопку открытия входящего письма.
     */
    public void clickIncomingMessageThemeButton() {
        click(incomingFirstMessageThemeText);
    }

    /**
     * Проверка темы внутри письма.
     */
    public void assertInMessageThemeText(String text) {
        assert waitTextElement(inMessageThemeText, text);
    }

    /**
     * Проверка текста внутри письма.
     */
    public void assertMessageText(String text) {
        assert waitTextElement(messageText, text);
    }

    /**
     * Нажатие на кнопку скачивания аттача
     */
    public void clickDownloadAttachmentLink() {
        click(downloadAttachmentLink);
    }

    /**
     * Кнопка, которая открывает новое окно облачного хранилища.
     */
    public void clickToCloudLink() {
        click(toCloudLink);
    }

    /**
     * Нажатие на кнопку раскрытия меню настроек.
     */
    public void clickSettingsButton() {
        click(settingsButton);
    }

    /**
     * Нажатие на кнопку, которая открывает окно со всеми настройками.
     */
    public void clickAllSettingsButton() {
        click(allSettingsButton);
    }

    /**
     * Проверка подписи.
     */
    public void assertSignText(String text) {
        assert waitTextElement(signText, text);
    }

    /**
     * Нажатие на чекбокс первого письма в списке в разделе входящих писем.
     */
    public void clickFirstMessageCheckbox() {
        click(firstMessageCheckbox);
    }

    /**
     * Нажатие на чекбокс второго письма в списке в разделе входящих писем.
     */
    public void clickSecondMessageCheckbox() {
        click(secondMessageCheckbox);
    }

    /**
     * Нажатие на кнопку удаления выбранных писем.
     */
    public void clickDeleteMessageButton() {
        click(deleteMessageButton);
    }

    /**
     * Наведение на аттач.
     */
    public void moveToViewAttachmentLink() {
        moveToElement(viewAttachmentLink);
    }

    /**
     * Проверка наименования аттача.
     */
    public void assertAttachmentNameText(String text) {
        waitTextElement(attachmentNameText, text);
    }

    /**
     * Наведение на кнопку скачивания аттача.
     */
    public void moveToDownloadAttachmentLink() {
        moveToElement(downloadAttachmentLink);
    }

    /**
     * Проверка отсутствия первого письма в списке в разделе входящих писем.
     */
    public void assertMessageCheckbox(String text) {
        assert waitInvisibleElement(By.xpath("(.//span[text() = '"+ text +"'])[1]"));
    }

    /**
     * Проверка отсутствия второго письма в списке в разделе входящих писем.
     */
    public void assertSecondMessageCheckbox() {
        assert waitInvisibleElement(By.xpath("(.//span[@class = 'll-av__img'])[2]"));
    }
}

