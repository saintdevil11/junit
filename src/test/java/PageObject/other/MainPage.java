package PageObject.other;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign in')]")
    private SelenideElement buttonSignInGit;

    @FindBy(how = How.XPATH, using = "//div[@class='auth-form-body mt-3']//input[@name='login']")
    private SelenideElement buttonLoginGit;

    @FindBy(how = How.XPATH, using = "//div[@class='auth-form-body mt-3']//input[@name='password']")
    private SelenideElement buttonPasswordGit;

    @FindBy(how = How.XPATH, using = "//div[@class='auth-form-body mt-3']//input[@name='commit']")
    private SelenideElement buttonButtonSignGit;

    @Step("Кликаем на поле логина")
    public HHBVJHB clickSignInGit(){
        buttonSignInGit.click();
        return page(HHBVJHB.class);
    }
    @Step("Вводим в поле логина значение {val1}")
    public HHBVJHB clickToValLoginGit(String val1){
        buttonLoginGit.click();
        buttonLoginGit.sendKeys(val1);
        return page(HHBVJHB.class);
    }
    @Step("24546457")
    public HHBVJHB clickToValPassGit(String val2){
        buttonPasswordGit.click();
        buttonPasswordGit.sendKeys(val2);
        return page(HHBVJHB.class);
    }
    @Step("87987089080")
    public void clickButtonSignGit(){
        buttonButtonSignGit.click();
    }
}
