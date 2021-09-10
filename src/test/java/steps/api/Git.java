package steps.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Git {

    @Tag("9api")
    @Test
    public void git() {
        Response response1 = given()
                .baseUri(utils.Configuration.getConfigurationValue("github"))
                .contentType("application/x-www-form-urlencoded")
                .log().all()
                .when()
                .param("login", "Artem")
                .param("password", "123")
                .post("/session")
                .then()
                .statusCode(302)
                .log().all()
                .extract().response();
        log.println(response1);
    }

}
