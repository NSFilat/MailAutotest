package seleniumTests.firstTests;

import org.junit.Test;
import pageObject.GooglePageObject;
import seleniumTests.BaseDriverTest;
import utils.PropertyLoader;

public class FirstClassTest extends BaseDriverTest {

    public static String standKey = System.getProperty("StandKey");
    public static String standURL = PropertyLoader.loadProperty(standKey);

    @Test
    public void startTest() {
        getDriver().get(standURL);
        GooglePageObject ggo = new GooglePageObject(getDriver());
        ggo.setSearchInput("привет");
        ggo.clickSearchButton();
        ggo.assertPicturesLink();
        ggo.clickOptionsButton();
        ggo.clickDarkThemeButton();
    }
}
