package seleniumTests.firstTests;

import org.junit.Test;
import pageObject.CloudPageObject;
import pageObject.MailHomePageObject;
import pageObject.MailMainPageObject;
import pageObject.MailSettingsPageObject;
import seleniumTests.BaseDriverTest;
import utils.PropertyLoader;

import java.io.File;

public class ExamTest extends BaseDriverTest {

    public static String mailKey = System.getProperty("MailKey");
    public static String mailURL = PropertyLoader.loadProperty(mailKey);

    final String USERNAME = "test.q.testov@mail.ru";
    final String PASSWORD = "PassForMail";

    public String firstTheme = "Первая тема";
    public String secondTheme = "Вторая тема";

    public String firstMessageText = "Сообщение вернётся через некоторое время...";
    public String secondMessageText = "Это тоже скоро придёт...";

    public String sign = "С уважением,\n" +
            "Тест Тестов";

    public File file = new File(getClass().getClassLoader().getResource("java.jpg").getFile());

    @Test
    public void startTest() {
        getDriver().get(mailURL);

        MailMainPageObject mmpo = new MailMainPageObject(getDriver());
        MailSettingsPageObject mspo = new MailSettingsPageObject(getDriver());
        MailHomePageObject mhpo = new MailHomePageObject(getDriver());
        CloudPageObject cpo = new CloudPageObject(getDriver());

        //авторизация
        mhpo.clickEnterMailButton();
        mhpo.switchAuthenticationFrame();
        mhpo.setUsernameInput(USERNAME.replaceAll("@.*",""));
        mhpo.clickPasswordButton();
        mhpo.setPasswordInput(PASSWORD);
        mhpo.clickEnterButton();

        //написание и отправка письма
        mmpo.clickWriteButton();
        mmpo.assertMessagePopup();
        mmpo.setTargetMailInput(USERNAME);
        mmpo.setThemeInput(firstTheme);
        mmpo.setMessageInput(firstMessageText);
        mmpo.uploadFileInput(file.getPath());
        mmpo.getCounterMessageText();
        mmpo.clickSendButton();
        mmpo.clickCloseNotificationPopup();

        //ожидание получения письма
        mmpo.assertCounterMessageText();

        //переход во входящие письма, проверка содержимого
        mmpo.clickIncomingMessagesButton();
        mmpo.assertMessageGrid("class", "llc_first");
        mmpo.assertFirstThemeText(firstTheme);
        mmpo.clickIncomingMessageThemeButton();
        mmpo.assertInMessageThemeText(firstTheme);
        mmpo.assertMessageText(firstMessageText);
        mmpo.moveToViewAttachmentLink();
        mmpo.assertAttachmentNameText(file.getName());
        mmpo.moveToDownloadAttachmentLink();
        mmpo.clickDownloadAttachmentLink();

        //проверка полученного файла в директории загрузки (в данном случае в облаке)
        mmpo.clickToCloudLink();
        mmpo.switchWindow();
        cpo.clickFromMailButton();
        cpo.clickExpandMessageButton();
        cpo.clickIncomingMessageButton();
        cpo.assertFileNameText(file.getName().replaceAll("[.].*", ""));
        cpo.closeWindow();

        //переход в настройки, установка новой подписи
        mmpo.switchWindow();
        mmpo.clickSettingsButton();
        mmpo.clickAllSettingsButton();
        mmpo.switchWindow();
        mspo.clickGeneralSettingsButton();
        mspo.clickEditSingButton();
        mspo.setSignInput(sign);
        mspo.clickSaveSignButton();
        mspo.clickBackToMailButton();

        //написание и отправка письма
        mmpo.clickWriteButton();
        mmpo.assertMessagePopup();
        mmpo.assertSignText(sign);
        mmpo.setTargetMailInput(USERNAME);
        mmpo.setThemeInput(secondTheme);
        mmpo.setMessageInput(secondMessageText);
        mmpo.getCounterMessageText();
        mmpo.clickSendButton();
        mmpo.clickCloseNotificationPopup();

        //ожидание получения письма
        mmpo.assertCounterMessageText();

        //переход во входящие письма, проверка содержимого
        mmpo.clickIncomingMessagesButton();
        mmpo.assertMessageGrid("class", "llc_first");
        mmpo.assertFirstThemeText(secondTheme);
        mmpo.clickIncomingMessageThemeButton();
        mmpo.assertInMessageThemeText(secondTheme);
        mmpo.assertMessageText(secondMessageText);
        mmpo.assertSignText(sign);

        //переход во входящие письма, удаление полученных писем, проверка отсутствия
        mmpo.clickIncomingMessagesButton();
        mmpo.assertFirstThemeText(secondTheme);
        mmpo.assertSecondThemeText(firstTheme);
        mmpo.clickFirstMessageCheckbox();
        mmpo.clickSecondMessageCheckbox();
        mmpo.clickDeleteMessageButton();
        mmpo.assertMessageCheckbox(secondTheme);
        mmpo.assertMessageCheckbox(firstTheme);
    }
}
