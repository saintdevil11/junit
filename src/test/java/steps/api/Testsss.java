package steps.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Testsss {
    //чтобы тесты работали очень важно чтобы клаас имел имя с заглавной буквы


    @Tag("1api")
    @Test
    @DisplayName("Характер Морти")
    public void morti(){
        Response response1 = given()
                .baseUri("https://rickandmortyapi.com/api/character/2")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();
        String resp = response1.getBody().asString();
        System.out.println(resp);
    }

    @Tag("2api")
    @Test
    public void morti1(){
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        Response response = request.get("https://rickandmortyapi.com/api/character/2");
        int statuCode = response.statusCode();
        String boddy = response.getBody().asString();
        System.out.println(statuCode);
        System.out.println(boddy);
    }
}
