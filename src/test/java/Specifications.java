import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec(){
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
        return reqSpec;
    }

    public static ResponseSpecification responseSpecOK2(){
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        return resSpec;
    }
    public static ResponseSpecification responseSpecError4(){
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        return resSpec;
    }

    public static void installSpecification(RequestSpecification requestSpec ){
        RestAssured.requestSpecification =requestSpec;
    }
    public static void installSpecification(ResponseSpecification responseSpec){
        RestAssured.responseSpecification =responseSpec;
    }
    public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
