package steps.api;

import hooks.ApiHooks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import stash.Stash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@ExtendWith({ApiHooks.class})
public class Testsss {
    //чтобы тесты работали очень важно чтобы класс имел имя с заглавной буквы


    @Tag("1api")
    @Test
    @DisplayName("Характер Морти")
    public void morti() {
        Response response1 = given()
                .baseUri("https://rickandmortyapi.com/api/character/2")
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

    @Tag("2api")
    @Test
    @DisplayName("Характер Морти ещё")
    public void morti1() {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        Response response = request.get("https://rickandmortyapi.com/api/character/2");
        int statuCode = response.statusCode();
        String boddy = response.getBody().asString();
//        log.println(statuCode);
//        log.println(boddy);
    }

    @Tag("3api")
    @Test
    @DisplayName("Характер Морти ещё +")
    public void GetCharacter() {

        Response response1 = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when().get("/character/2")
                .then()
                .extract().response();
        String name = new JSONObject(response1.body().asString()).get("name").toString();
        String location1 = new JSONObject(response1.body().asString()).getJSONObject("location").get("name").toString();
        int lst = (new JSONObject(response1.body().asString()).getJSONArray("episode").length() - 1);
        int episode = Integer.parseInt(new JSONObject(response1.body().asString()).getJSONArray("episode").get(lst).toString().replaceAll("[^0-9]", ""));
//        log.println(name);
//        log.println(location1);
//        log.println(episode);
        Stash.put("q", episode);

        Response response2 = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/episode/" + episode)
                .then()
                .extract().response();
        int lst1 = (new JSONObject(response2.body().asString()).getJSONArray("characters").length() - 1);
        int character = Integer.parseInt(new JSONObject(response2.body().asString()).getJSONArray("characters").get(lst1).toString().replaceAll("[^0-9]", ""));
//        log.println(character);
        System.out.println(character);
    }

    public void getGet() {
        Stash.get("q");
        Response response2 = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/episode/" + "q")
                .then()
                .extract().response();
        int lst1 = (new JSONObject(response2.body().asString()).getJSONArray("characters").length() - 1);
        int character = Integer.parseInt(new JSONObject(response2.body().asString()).getJSONArray("characters").get(lst1).toString().replaceAll("[^0-9]", ""));
//        log.println(character);
        System.out.println(character);
    }

    @Tag("4api")
    @Test
    public void GetLocation() {

        Response response3 = given()
                .baseUri("https://rickandmortyapi.com/api")
                .when().get("character/362")
                .then()
                .extract().response();
        /**
         * вывод следующей строки это для просмотра того что же нам возвращает ответ
         */
        String key1 = new JSONObject(response3.getBody().asString()).getJSONObject("info").get("count").toString();


        String resp3 = response3.getBody().asString();
        String name = new JSONObject(response3.body().asString()).get("name").toString();
        String location2 = new JSONObject(response3.body().asString()).getJSONObject("location").get("name").toString();
//        log.println(name);
//        log.println(location2);
    }

    @Test
    public void jsontest() throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/1.json"))));
        //При доступе к массиву
        body.getJSONObject("class").put("name", "vanya");

        //При достпуе без массива
        body.put("base", "2");
        body.put("id", "13");
        System.out.println(body);

        given()
                .baseUri("https://rickandmortyapi.com/api")
                .contentType("application/json;charset=UTF-8")
                .log().all()
                .when()
                .body(body)
                .post("character/362")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

    }

    @Test
    public void etst() throws IOException {
        Response resp = given()
                .baseUri("https://rickandmortyapi.com/api")
                .log().all()
                .when()
                .get("character/362")
                .then()
                .log().all()
                .extract()
                .response();


        System.out.println(resp.getBody().asString());
    }
}

