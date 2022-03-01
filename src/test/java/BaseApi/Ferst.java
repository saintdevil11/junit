package BaseApi;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static BaseApi.Second.seccccond;
import static io.restassured.RestAssured.given;

public class Ferst {
    @Step("Шаг 1 проверка морти")
    public static void ferrrst(){
        seccccond();
        Response response1 = given()
                .baseUri(utils.Configuration.getConfigurationValue("rickandmorti"))
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();

        /**
         * вывод следующей строки это для просмотре того что же нам возвращает ответ
         */
        String resp = response1.getBody().asString();
//        log.println(resp);
    }

}
