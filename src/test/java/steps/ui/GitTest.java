package steps.ui;

import hooks.AllureHelper;
import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static PageObject.PageSteps.AutorizationSteps.autorisation;
import static PageObject.PageSteps.AutorizationSteps.open_url;

/**
 * Пример теста с гибким подключением методов
 * */
@ExtendWith(AllureHelper.class)
public class GitTest extends WebHooks {

    @Test
    @Tag("111111")
    @DisplayName("Авторизация на ГитХаб")
    public void gitAut(){
        open_url(utils.Configuration.getConfigurationValue("main.GitHub"));
        autorisation("123","123");
    }
}
