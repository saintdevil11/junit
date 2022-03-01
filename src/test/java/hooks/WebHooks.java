package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static com.sun.activation.registries.LogSupport.log;

/**
 * Класс с настройками драйвера.
 * */
public class WebHooks {

    @BeforeAll()
    private static void setDriverFromProps() {
        Configuration.startMaximized = true;
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
  //Следующие строки для работы с браузером IE
//            System.setProperty("webdriver.ie.driver", webDriverLocation);
//            System.setProperty("selenide.browser", "Internet Explorer");
//            Configuration.startMaximized = true;
//            DesiredCapabilities cap = new DesiredCapabilities();
//            cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
//            cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
//            cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//            cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
//            cap.setCapability("ignoreProtectedModeSettings", true);
//            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//            cap.setJavascriptEnabled(true);
//            cap.setCapability("requireWindowFocus", true);
//            cap.setCapability("enablePersistentHover", false);
//            Configuration.browserCapabilities = cap;

        }
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }

    @Attachment(type = "application/pdf")
    public byte[] attachPdfFile(String pdfLocation) throws IOException {
        File pdf = new File(pdfLocation);
        try {
            return Files.toByteArray(pdf);
        } catch (IOException e) {
            log("attachPdfFile() : не выполнено " + e.getMessage());
            return new byte[0];
        }
    }

    /**
     * Метод аллюр менеджер: для отображения в аллюре селенидовских шагов при наличии в проекте параллелизации
     */
    @BeforeAll
    public static void allureSubThreadParallel(){
//        String listenerName = "AllureSelenide";
        String listenerName = "CustomAllureSelenide";

        if(!(SelenideLogger.hasListener(listenerName)))
            SelenideLogger.addListener(listenerName,
                    new CustomAllureSelenide(Allure.getLifecycle())
            );
        /**
         * Ниже стандартное скриншотирование только на упавших тестах
         * */
//                    new AllureSelenide().
//                            screenshots(true).
//                            savePageSource(false));
    }

    @AfterEach()
    public void driverClose() {
        /**
         * Строка с AllureHelper нужна для прикрепления скриншота перед закрытием драйвера
         * */
        new AllureHelper().afterTestExecution();
        WebDriverRunner.closeWebDriver();
    }
}
