package seleniumTests.firstTests;

import org.junit.Test;
import pageObject.CloudPageObject;
import pageObject.MailHomePageObject;
import pageObject.MailMainPageObject;
import pageObject.MailSettingsPageObject;
import seleniumTests.BaseDriverTest;
import utils.PropertyLoader;

import java.io.File;
import java.net.URL;

public class ExamTest extends BaseDriverTest {

    public static String mailKey = System.getProperty("MailKey");
    public static String mailURL = PropertyLoader.loadProperty(mailKey);

    final String USERNAME = "test.testov.qa@mail.ru";
    final String PASSWORD = "PassForMail";

    final String firstTheme = "Первая тема";
    final String secondTheme = "Вторая тема";

    final String firstMessageText = "Сообщение вернётся через некоторое время...";
    final String secondMessageText = "Сообщение вернётся через некоторое время...";

    final String sign = "С уважением,\n" +
            "Тест Тестов";

    public String filePath = new File(getClass().getClassLoader().getResource("java.jpg").getFile()).getAbsolutePath();

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
        mmpo.uploadFileInput(filePath);
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
        mmpo.assertAttachmentNameText(filePath.substring(filePath.lastIndexOf('\\') + 1).
                substring(filePath.lastIndexOf('/') + 1));
        mmpo.moveToDownloadAttachmentLink();
        mmpo.clickDownloadAttachmentLink();

        //проверка полученного файла в директории загрузки (в данном случае в облаке)
        mmpo.clickToCloudLink();
        mmpo.switchWindow();
        cpo.clickFromMailButton();
        cpo.clickExpandMessageButton();
        cpo.clickIncomingMessageButton();
        cpo.assertFileNameText(filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.')).
                substring(filePath.lastIndexOf('/') + 1));
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
