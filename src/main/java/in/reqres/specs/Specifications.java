package in.reqres.specs;

import in.reqres.data.Reqres_Data;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class Specifications extends Reqres_Data {



    public static RequestSpecification requestSpec(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(REQRES_URL)
                .setContentType(JSON)
                .log(LogDetail.ALL)
                .build();
        return requestSpecification;
    }

    public static ResponseSpecification responseSpecOK200(){
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
        return resSpec;
    }
    public static ResponseSpecification responseSpecError400(){
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
