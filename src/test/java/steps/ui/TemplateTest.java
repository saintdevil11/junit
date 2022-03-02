package steps.ui;

import PageObject.other.MainPage;
import hooks.WebHooks;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Пример теста с жесткой привязкой к заранее известным страницам
 * */
public class TemplateTest extends WebHooks {


    @Test
    @Tag("1ui")
    public void autorizeWebGit() {
        open("https://github.com/", MainPage.class)
                .clickSignInGit()
                .clickToValLoginGit(utils.Configuration.getConfigurationValue("login124534"))
                .clickToValPassGit("123");
        page(MainPage.class).clickButtonSignGit();
    }
}
