package in.reqres.specs.requests;

import in.reqres.pojo.common.ReqresRequest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestSpecifications {


    public static <T> T postRequest(ReqresRequest body, String endpoint, Class<T> respClass) {
        return given()
                .body(body)
                .post(endpoint)
                .then()
                .log().all()
                .extract().as(respClass);

    }

    public static <T> List<T> getListRequest(String endpoint, String path, Class<T> respClass) {
        return given()
                .get(endpoint)
                .then()
                .log().all()
                .extract().body().jsonPath().getList(path, respClass);
    }

}
