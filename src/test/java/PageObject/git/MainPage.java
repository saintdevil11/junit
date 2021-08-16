package PageObject.git;

import com.codeborne.selenide.SelenideElement;
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


    public MainPage clickSignInGit(){
        buttonSignInGit.click();
        return page(MainPage.class);
    }

    public MainPage clickToValLoginGit(String val1){
        buttonLoginGit.click();
        buttonLoginGit.sendKeys(val1);
        return page(MainPage.class);
    }

    public MainPage clickToValPassGit(String val2){
        buttonPasswordGit.click();
        buttonPasswordGit.sendKeys(val2);
        return page(MainPage.class);
    }

    public MainPage clickButtonSignGit(){
        buttonButtonSignGit.click();
        return page(MainPage.class);
    }
}
