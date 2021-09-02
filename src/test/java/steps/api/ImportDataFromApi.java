package steps.api;


import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportDataFromApi {

    @Test
    public void importDataFromFile () throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/1.json"))));


        String import11 = "$";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import11));  // from(String.valueOf(body) ПРАВИЛЬНО?
                                                                     // как взять сразу responce?


        String import12 = "class";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import12));

        String import13 = "class[0]";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import13));

        String import14 = "class[-1]";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import14));

        String import15 = "class[-2]";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import15));

        String import16 = "class[0..1]";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import16));

        String import17 = "class[1..2]";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import17));

        String import18 = "class[0].surname";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import18));

        String import19 = "class.name";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import19));

        String import102 = "class[0].address.house";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import102));
        System.out.println(JsonPath.from(String.valueOf(body)).getString("class[0].address.house"));

        String import103 = "class[-1].'some.value'";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import103));

        System.out.println("    2. ОПЕРАЦИИ");

        String import21 = "class.find {it.name == 'Ivan'}";     //ОШИБКА
        System.out.println("++++"+JsonPath.from(String.valueOf(body)).getString(import21));

        String import24 = "class.findAll {it.name}";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import24));

        String import25 = "class.findAll {it.'some.value'}";  //Возвращает null, а должен объект Сидорова
        System.out.println("-------"+JsonPath.from(String.valueOf(body)).getString(import25));

        System.out.println("    3. СТАНДАРТНЫЕ МАТЕМАТИЧЕСКИЕ ФУНКЦИИ");

        String import31 = "class[0].marks.min()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import31));

        String import32 = "class.address.house.max()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import32));

        String import33 = "class.max {it.house}";  //К функциям min/max можно также применить выражение {it.поле}
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import33));

        String import34 = "class.address.street.sum()"; //Как вывести через пробел? (LeninMarxPobedy)
        System.out.println("==========="+JsonPath.from(String.valueOf(body)).getString(import34));

        String import35 = "class[-1].marks.sum()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import35));

        String import36 = "class.marks.flatten().sum()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import36));
        System.out.println(JsonPath.from(String.valueOf(body)).getString("class.marks.sum().sum()"));

        System.out.println("    4. ОБЩИЕ ФУНКЦИИ");

        String import41 = "class.size()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import41));

        String import42 = "class[0].name.size()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import42));

        String import43 = "class[0].address.size()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import43));

        String import44 = "class.name.unique()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import44));

        String import45 = "class.unique()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import45));

        String import46 = "class.marks.flatten().unique()";
        System.out.println(JsonPath.from(String.valueOf(body)).getString(import46));

        System.out.println("    4. ФУНКЦИИ РАБОТЫ СО СТРОКАМИ");




    }
/*
    @Test
    public void importDataFromResponce () {

        Response response2 = given()
                .baseUri("https://rickandmortyapi.com/api/character/2")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();

        String resp = response2.getBody().asString();

        String import1 = "class";
        System.out.println(JsonPath.from(String.valueOf(resp)).getString(import1));
    }


 */

}
