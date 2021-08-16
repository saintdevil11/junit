package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Класс с настройками драйвера.
 * */
public class WebHooks {

    @BeforeAll()
    private static void setDriverFromProps() {
        //Подключаем драйвер напрямую из test.properties
        String webDriverLocation = utils.Configuration.getConfigurationValue("webdriverlocationLOCAL");
        //Следуем по условию запуска на selenoid, есть строка в проперти закомменчена то запуск на локальном драйвере
        if (utils.Configuration.getConfigurationValue("remote.url") != null)
            Configuration.remote = utils.Configuration.getConfigurationValue("remote.url");
        //Указание selenide с каким браузером работать и с каким драйвером, фуллскрин драйвера
        //Нужно это только если не интернета. Selenide сам из интернета
        if (webDriverLocation != null) {
            System.setProperty("webdriver.chrome.driver", webDriverLocation);
            System.setProperty("selenide.browser", "Chrome");
            Configuration.startMaximized = true;
        }
    }

    @AfterEach()
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
