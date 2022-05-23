package pageObject;

import base.Base;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePageObject extends Base {

    public GooglePageObject(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = ".//div//input[@name='q']")
    WebElement searchInput;

    @FindBy(xpath = "(.//div//input[@name='btnK'])[1]")
    WebElement searchButton;

    @FindBy(xpath = ".//a[contains(.,'Картинки')]")
    WebElement picturesLink;

    @FindBy(xpath = ".//div[@aria-label='Настройки']")
    WebElement optionsButton;

    @FindBy(xpath = ".//div[@class = 'main']//div[text() ='Тёмная тема']")
    WebElement darkThemeButton;

    public void setSearchInput(String text) {
        setText(searchInput, text);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void assertPicturesLink() {
        assert waitVisibleElement(picturesLink);
    }

    public void clickOptionsButton() {
        click(optionsButton);
    }

    public void clickDarkThemeButton() {
        click(darkThemeButton);
    }
}
