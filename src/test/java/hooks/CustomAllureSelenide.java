package hooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.TakeScreenshot;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.SelenideLog;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import io.qameta.allure.util.ResultsUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


/**
 * Класс прикрепления скриншотов на все шаги селенида
 *
 * */

public class CustomAllureSelenide extends AllureSelenide {
    public static String screenMod = Configuration.getConfigurationValue("screenmod");
    private static final Logger LOGGER = LoggerFactory.getLogger(AllureSelenide.class);
    private boolean saveScreenshots;
    private boolean savePageHtml;
    private boolean includeSelenideLocatorsSteps;
    private final Map<LogType, Level> logTypesToSave;
    private final AllureLifecycle lifecycle;

    public CustomAllureSelenide(){this(Allure.getLifecycle());}

    public CustomAllureSelenide(AllureLifecycle lifecycle){
        this.saveScreenshots = true;
        this.savePageHtml = true;
        this.includeSelenideLocatorsSteps = true;
        this.logTypesToSave = new HashMap<>();
        this.lifecycle = lifecycle;
    }
    public static void setScreenMod(String screenMod){
        CustomAllureSelenide.screenMod = screenMod;
    }
    public void setSaveScreenshots(boolean saveScreenshots){
        this.saveScreenshots = saveScreenshots;
    }

    public void setSavePageHtml(boolean savePageHtml){
        this.savePageHtml = savePageHtml;
    }

    public void setIncludeSelenideLocatorsSteps(boolean includeSelenideLocatorsSteps){
        this.includeSelenideLocatorsSteps = includeSelenideLocatorsSteps;
    }

    public static Optional<byte[]> getScreenshotBytes(){
        try {
            return WebDriverRunner.hasWebDriverStarted() ? Optional.of(((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.BYTES)) : Optional.empty();
        } catch (WebDriverException val1){
            LOGGER.warn("Не удалось получить снимок экрана", val1);
            return Optional.empty();
        }
    }

    private static Optional<byte[]> getPageSourceBytes() {
        try {
            return WebDriverRunner.hasWebDriverStarted() ? Optional.of(getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)) : Optional.empty();
        } catch (WebDriverException val1) {
            LOGGER.warn("Не удалось получить страницу", val1);
            return Optional.empty();
        }
    }

    private static String getBrowserLogs(LogType logType, Level level){
        return String.join("\n\n", Selenide.getWebDriverLogs(logType.toString(), level));
    }

    @Override
    public void afterEvent(LogEvent event){
        sleep(400);
        try{
            if ((event.getStatus().equals(LogEvent.EventStatus.FAIL)|| screenMod.equals("full")))
            {
                this.lifecycle.getCurrentTestCaseOrStep().ifPresent((parentUuid) ->{
                    if (this.saveScreenshots){
                        getScreenshotBytes().ifPresent((bytes) ->{
                            this.lifecycle.addAttachment("Screenshot","image/png","png", bytes);
                        });
                    }
                    if (this.savePageHtml){
                        getScreenshotBytes().ifPresent((bytes)->{
                            this.lifecycle.addAttachment("Page Source","text/html","html", bytes);
                        });
                    }
                    if (!this.logTypesToSave.isEmpty()){
                        this.logTypesToSave.forEach((logType, level) ->{
                            byte[] content = getBrowserLogs(logType,level).getBytes(StandardCharsets.UTF_8);
                            this.lifecycle.addAttachment("Logs form" + logType, "application/json","txt",content);
                        });
                    }

                });
            }
            if (this.stepsShouldBeLogged(event)){
                this.lifecycle.getCurrentTestCaseOrStep().ifPresent((parentUuid)->{
                    switch (event.getStatus()){
                        case PASS:
                            this.lifecycle.updateStep((step)->{
                                step.setStatus(Status.PASSED);
                            });
                            break;
                        case FAIL:
                            this.lifecycle.updateStep((stepResult)->{
                                stepResult.setStatus((Status) ResultsUtils.getStatus(event.getError()).orElse(Status.BROKEN));
                                stepResult.setStatusDetails((StatusDetails) ResultsUtils.getStatusDetails(event.getError()).orElse(new StatusDetails()));
                            });
                            break;
                        default:
                            LOGGER.warn("Шаг завершен с неподдерживаемым статусом {}", event.getStatus());
                    }
                    this.lifecycle.stopStep();
                });
            }
        }catch (Exception e){}
    }

    private boolean stepsShouldBeLogged(LogEvent event){
        return this.includeSelenideLocatorsSteps || !(event instanceof SelenideLog);
    }

}
