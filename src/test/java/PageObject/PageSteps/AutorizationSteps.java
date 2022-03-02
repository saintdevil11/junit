package PageObject.PageSteps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;


import static PageObject.PageElements.AutorizationElem.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class AutorizationSteps {

    @Step("Открываем страницу по ссылке {url}")
    public static void open_url(String url){
        open(url);
    }

    @Step("Авторизуемся в системе пользователем {log}")
    public static void autorisation(String log, String pass){
        Allure.getLifecycle().updateStep(stepResult -> stepResult.getParameters().remove(1));
        $x(buttonSignInGit).click();
        $x(buttonLoginGit).sendKeys(log);
        setPassword(pass);
        $x(buttonButtonSignGit).click();
    }

    @Step("Вводит пароль")
    public static Parameter setPassword(String pass){
        $x(buttonPasswordGit).sendKeys(pass);
        Allure.getLifecycle().updateStep(stepResult -> stepResult.setName(buttonPasswordGit));
        return null;
    }
}
