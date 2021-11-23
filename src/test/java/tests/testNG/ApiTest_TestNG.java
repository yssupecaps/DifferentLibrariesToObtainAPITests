package tests.testNG;


import in.reqres.data.*;
import in.reqres.pojo.colors.Data;
import in.reqres.pojo.registration.Register;
import in.reqres.pojo.registration.SuccessUserRegister;
import in.reqres.pojo.registration.UnsuccessUserRegister;
import in.reqres.pojo.users.UserData;
import io.qameta.allure.Flaky;
import jdk.jfr.Description;
import org.testng.annotations.*;
import org.testng.xml.dom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static in.reqres.specs.Specifications.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class ApiTest_TestNG extends Reqres_Data {


    //TODO: проверка на совпадение названий аватаров
    @Test
    @Tag(name = "SMOKE")
    @Description(" Test 1 | `Check User Avatars` ")
    void checkUserAvatars() {
        installSpecification(requestSpec(), responseSpecOK200());
        final List<UserData> userAvatar = given()
                .queryParam(QUERY_PAGE, QUERY_VALUE)
                .when()
                .get(ENDPOINT_USERS)
                .then()
                .extract()
                .jsonPath().getList(PATH_AVATAR, UserData.class);
        final List<String> realPeopleAvatars = userAvatar.stream().map(UserData::getAvatar).collect(Collectors.toList());
        assertTrue(realPeopleAvatars.stream()
                .allMatch(value -> value.contains(AVATAR_EXTENSION)), "Extension `avatar` are not equals in response");
    }


    //TODO: проверка успешной регистрации
    @Test
    @Tag(name = "NF")
    @Description(" Test 2.1 | `Successful register user` ")
    void successfulRegisterUser() {
        installSpecification(requestSpec(), responseSpecOK200());
        final Register peopleFirst = new Register(USER_EMAIL_1, USER_PASSWORD);
        final SuccessUserRegister successUserReg = given()
                .body(peopleFirst)
                .when()
                .post(ENDPOINT_REGISTER)
                .then()
                .log().all()
                .extract().as(SuccessUserRegister.class);
        assertNotNull(successUserReg.getId(), "`id` is missing in response");
        assertNotNull(successUserReg.getToken(), "`token` is missing in response");
        assertEquals(USER_ID, successUserReg.getId(), "`id` is different in response");
        assertEquals(USER_TOKEN, successUserReg.getToken(), "`token` is different in response");
    }

    //TODO: неуспешная регистрация(не введен пароль)
    @Test
    @Flaky
    @Tag(name = "NF")
    @Description(" Test 2.2 | `Unsuccessful register user` ")
    void unSuccessfulRegisterUser() {
        installSpecification(requestSpec(), responseSpecError400());
        final Register peopleSecond = new Register(USER_EMAIL_2, "");
        final UnsuccessUserRegister unSuccessUserReg = given()
                .body(peopleSecond)
                .when()
                .post(ENDPOINT_REGISTER)
                .then()
                .log().body()
                .extract().as(UnsuccessUserRegister.class);
        assertNotNull(unSuccessUserReg.getError());
        assertEquals(ERROR_MESSAGE, unSuccessUserReg.getError(), "`Error` is different in response");
    }


    //TODO: проверка сортировки по датам(годам)
    @Test
    @Tag(name = "REGRESS")
    @Description(" Test 3 | `check sort `data` by years` ")
    void checkSortDataByYears() {
        installSpecification(requestSpec(), responseSpecOK200());
        final List<Data> data = given()
                .when()
                .get(ENDPOINT_UNKNOWN)
                .then()
                .log().all()
                .extract().body().jsonPath().getList(PATH_DATA, Data.class);
        final List<Object> dataYears = data.stream().map(Data::getYear).collect(Collectors.toList());
        final List<Object> sortedDataYears = new ArrayList<>(dataYears);
        assertEquals(dataYears, sortedDataYears, "`data.year` isn`t sorted incrementally");
    }

}
