package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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

    /**
     * Метод аллюр менеджер: для отображения в аллюре селенидовских шагов при наличии в проекте параллелизации
     */
    @BeforeAll
    public static void allureSubThreadParallel(){
        String listenerName = "AllureSelenide";

        if(!(SelenideLogger.hasListener(listenerName)))
            SelenideLogger.addListener(listenerName,
                    new AllureSelenide().
                            screenshots(true).
                            savePageSource(false));
    }

    @AfterEach()
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
