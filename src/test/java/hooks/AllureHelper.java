package hooks;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Класс добавления скриншотов перед закрытием драйвера
 * */

public class AllureHelper implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution (ExtensionContext extensionContext){
        Boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed){
            CustomAllureSelenide.getScreenshotBytes().ifPresent((bytes)->{
                Allure.getLifecycle().addAttachment("Screenshot","image/png","png", bytes);
            });
        }
    }

    public void afterTestExecution () {
        CustomAllureSelenide.getScreenshotBytes().ifPresent((bytes)-> {
            Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", bytes);
        });
    }
}
