package PageObject.other;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class Jjisdrgujhbdrt {

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign in')]")
    private SelenideElement buttonSignInGit2;

    @FindBy(how = How.XPATH, using = "//div[@class='auth-form-body mt-3']//input[@name='login']")
    private SelenideElement buttonLoginGit2;



    public Jjisdrgujhbdrt clickSignInGit2(){
        buttonSignInGit2.click();
        return page(Jjisdrgujhbdrt.class);
    }

    public HHBVJHB clickToValLoginGit2(String val1){
        buttonLoginGit2.click();
        buttonLoginGit2.sendKeys(val1);
        return page(HHBVJHB.class);
    }
}
