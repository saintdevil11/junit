package steps.ui;

import PageObject.git.HHBVJHB;
import PageObject.git.Jjisdrgujhbdrt;
import PageObject.git.MainPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import hooks.WebHooks;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static BaseApi.Ferst.ferrrst;
import static com.codeborne.selenide.Selenide.open;

public class TemplateTest extends WebHooks {

    @Tag("1ui")
    @Test
    public void autorizeWebGit() {
        open("https://github.com/", MainPage.class)
     //   open(utils.Configuration.getConfigurationValue("main.GitHub"), MainPage.class)
                .clickSignInGit()
                .clickToValLoginGit("saintdevil11")
                .clickToValPassGit("123")
                .clickButtonSignGit()
                .clickSignInGit1()
                .clickToValLoginGit1("")
                .clickSignInGit2()
                .clickToValLoginGit2("");
    }
}
