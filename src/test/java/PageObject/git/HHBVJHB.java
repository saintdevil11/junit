package PageObject.git;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HHBVJHB extends MainPage{
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Sign in')]")
    private SelenideElement buttonSignInGit1;

    @FindBy(how = How.XPATH, using = "//div[@class='auth-form-body mt-3']//input[@name='login']")
    private SelenideElement buttonLoginGit1;

    public HHBVJHB clickSignInGit1(){
        buttonSignInGit1.click();
        return page(HHBVJHB.class);
    }

    public HHBVJHB clickToValLoginGit1(String val1){
        buttonLoginGit1.click();
        buttonLoginGit1.sendKeys(val1);
        return page(HHBVJHB.class);
    }
}
