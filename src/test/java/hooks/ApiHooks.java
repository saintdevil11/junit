package hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ApiHooks implements BeforeAllCallback {
    /**
    * Тут мы включаем фильтрацию запросов и ответов api чтобы в отчете отображалось
     */

    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.filters(new AllureRestAssured());
    }
}
