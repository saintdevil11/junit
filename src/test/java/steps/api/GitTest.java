package steps.api;

import hooks.ApiHooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static BaseApi.Ferst.ferrrst;
import static io.restassured.RestAssured.given;

@ExtendWith({ApiHooks.class})
public class GitTest {

    @Tag("9api")
    @Test
    @DisplayName("Тест")
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
//        log.println(response1);
    }

    @Test
    @Tag("01api")
    public void git1(){
        ferrrst();
    }

}
