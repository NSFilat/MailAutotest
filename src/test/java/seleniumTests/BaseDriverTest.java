package seleniumTests;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriverTest {
    protected WebDriver driver;
    @Rule
    public TestWatcher watcher;

    {
        watcher = new TestWatcher() {
            @Override
            protected void starting(Description description) {
                DesiredCapabilities desire = null;
                ChromeOptions options = new ChromeOptions();

                URL hub = null;
                try {
                    hub = new URL("http://localhost:9515");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver = new RemoteWebDriver(hub, options);
                driver.manage().window().maximize();

                // Запуск на браузера на втором мониторе
                // driver.manage().window().setPosition(new Point(3000, 0));
                // Открытие браузера на весь экран
                // driver.manage().window().maximize();
            }

            @Override
            protected void succeeded(Description description) {

            }

            @Override
            protected void failed(Throwable e, Description description) {

            }

            @Override
            protected void finished(Description description) {
                driver.quit();
                if (driver != null) {
                    driver.quit();
                }
            }
        };
    }

    public WebDriver getDriver() {
        return driver;
    }
}
