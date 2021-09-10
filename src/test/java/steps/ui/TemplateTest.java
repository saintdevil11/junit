package steps.ui;

import PageObject.git.MainPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import hooks.WebHooks;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;

public class TemplateTest extends WebHooks {

    @Tag("1ui")
    @Test
    public void autorizeWebGit() {
        open(utils.Configuration.getConfigurationValue("main.GitHub"), MainPage.class)
                .clickSignInGit()
                .clickToValLoginGit("saintdevil11")
                .clickToValPassGit("udO2015!@")
                .clickButtonSignGit();
    }
}
