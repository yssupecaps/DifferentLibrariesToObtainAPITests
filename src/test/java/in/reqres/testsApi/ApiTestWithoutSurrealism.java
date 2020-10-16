package in.reqres.testsApi;

import in.reqres.specs.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTestWithoutSurrealism {

    /*
    Тест 1
    1.Используя сервис https://reqres.in/ получить список пользователей со второй страницы
    2.Убедиться что имена файлов-аватаров пользоваталей совпадают
    */
    @Test
    public void checkUsersAvatars() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
        List<String> avatars;
        String UserAvatar;
        Response response = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .body("page", equalTo(2))
                .body("data.id.", notNullValue())
                .body("data.email.", notNullValue())
                .body("data.first_name.", notNullValue())
                .body("data.last_name.", notNullValue())
                .body("data.avatar.", notNullValue())
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        avatars = jsonResponse.get("data.avatar");
        Assert.assertTrue(avatars.stream()
                .allMatch(value -> value.contains("128.jpg")), "не совпадают");
    }

   /*
   Тест 2.1
            1.Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
            2.Необходимо создание 2 тестов:
            2.1 успешная регистрация
            2.2 регистрация с ошибкой из-за отсутствия пароля
    */
    @Test
    @Description(value ="успешная регистрация")
    public void sucRegUsers() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
        Integer id;
        //String token;
        Map<String, String> data = new HashMap<String, String>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "pistol");
        Response response = given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        //проверка по "id"
        id = jsonResponse.get("id");
        Assert.assertTrue(id == 4);
        /*  Проверка по token
        можно использовать одновременно обе проверки
        token = jsonResponse.get("token");
        Assert.assertEquals(token,"QpwL5tke4Pnpja7X4");
         */
    }
    
    /*
    Тест 2.2
    */
    @Test
    @Description(value ="неуспешная регистрация")
    public void unSucRegUsers() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecError4());
        String error;
        Map<String, String> data = new HashMap<>();
        data.put("email", "sydney@file");
        Response response = given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        error = jsonResponse.get("error");
        Assert.assertEquals(error, "Missing password");
    }

    /*
    Тест 3.1
    Используя сервис https://reqres.in/ убедиться, что операция LIST<RESOURCE> возвращает данные,
    отсортированные по годам
    */
    @Test
    public void checkSortDataByYears() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
        List<String> years;
        Response response = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .body("data.id.", notNullValue())
                .body("data.name.", notNullValue())
                .body("data.year", notNullValue())
                .body("data.color", notNullValue())
                .body("data.pantone_value", notNullValue())
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        years = jsonResponse.getList("data.year");
        //проверка дат(удалить)
        System.out.println(years);
        Assert.assertTrue(years.stream().sorted().collect(Collectors.toList())
                .equals(years),"Не отсортировано по годам");
    }
}
