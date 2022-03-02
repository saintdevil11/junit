package steps.ui;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static PageObject.PageSteps.AutorizationSteps.autorisation;
import static PageObject.PageSteps.AutorizationSteps.open_url;

/**
 * Пример теста с гибким подключением методов
 * */
public class GitTest {

    @Test
    @Tag("111111")
    public void gitAut(){
        open_url(utils.Configuration.getConfigurationValue("main.GitHub"));
        autorisation("123","123");
    }
}
