package steps.ui;

import PageObject.other.MainPage;
import hooks.WebHooks;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class TemplateTest extends WebHooks {

    @Tag("1ui")
    @Test
    public void autorizeWebGit() {
        open("https://github.com/", MainPage.class)
     //   open(utils.Configuration.getConfigurationValue("main.GitHub"), MainPage.class)
                .clickSignInGit()
                .clickToValLoginGit(utils.Configuration.getConfigurationValue("login124534"))
                .clickToValPassGit("123");
        page(MainPage.class).clickButtonSignGit();
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
