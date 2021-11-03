package steps.ui;

import PageObject.git.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.logevents.SelenideLogger;
import hooks.WebHooks;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import static BaseApi.Ferst.ferrrst;
import static com.codeborne.selenide.Selenide.open;

public class TemplateTest extends WebHooks {

    @Tag("1ui")
    @Test
    public void autorizeWebGit() {
        open("https://github.com/", MainPage.class)
     //   open(utils.Configuration.getConfigurationValue("main.GitHub"), MainPage.class)
                .clickSignInGit()
                .clickToValLoginGit(utils.Configuration.getConfigurationValue("login124534"))
                .clickToValPassGit("123")
                .clickButtonSignGit();
//                .clickSignInGit1()
//                .clickToValLoginGit1("");
//                .clickSignInGit2()
//                .clickToValLoginGit2("");
    }

//    @Test
//    public void esss(){
//        String webDriverLocation = utils.Configuration.getConfigurationValue("webdriverlocationLOCALIE");
//        System.setProperty("webdriver.ie.driver", webDriverLocation);
//        System.setProperty("selenium.browser", "Internet Explorer");
//
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        cap.setCapability("ignoreProtectedModeSettings", true);
//        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        InternetExplorerDriver iedriver = new InternetExplorerDriver(cap);
//
//        iedriver.get("http://github.com/");
//
//    }
}
