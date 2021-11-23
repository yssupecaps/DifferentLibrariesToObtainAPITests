package tests.junit;

import in.reqres.data.Reqres_Data;
import in.reqres.pojo.colors.Data;
import in.reqres.pojo.registration.Register;
import in.reqres.pojo.registration.SuccessUserRegister;
import in.reqres.pojo.registration.UnsuccessUserRegister;
import in.reqres.pojo.users.UserData;
import in.reqres.specs.Specifications;
import in.reqres.specs.requests.RequestSpecifications;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest_Junit extends Reqres_Data {


    //TODO: проверка на совпадение названий аватаров
    @Test
    @Order(1)
    @Tag("SMOKE")
    @DisplayName(" Test 1 | `Check User Avatars` ")
    void checkUserAvatars() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        final List<UserData> userAvatar = given()
                //sometimes you can put queryParams directly in endpoint
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
    @Order(4)
    @Tag("NF")
    @DisplayName(" Test 2.1 | `Successful register user` ")
    void successfulRegisterUser() {
        final Register peopleFirst = new Register(USER_EMAIL_1, USER_PASSWORD);

        Specifications.installSpecification(
                Specifications.requestSpec(), Specifications.responseSpecOK200());
        final SuccessUserRegister successUserReg =
                RequestSpecifications.postRequest(peopleFirst, ENDPOINT_REGISTER, SuccessUserRegister.class);

        assertNotNull(successUserReg.getId(), "`id` is missing in response");
        assertNotNull(successUserReg.getToken(), "`token` is missing in response");
        assertEquals(USER_ID, successUserReg.getId(), "`id` is different in response");
        assertEquals(USER_TOKEN, successUserReg.getToken(), "`token` is different in response");
    }

    //TODO: неуспешная регистрация(не введен пароль)
    @Test
    @Order(2)
    @Tag("NF")
    @DisplayName(" Test 2.2 | `Unsuccessful register user` ")
    void unSuccessfulRegisterUser() {
        final Register peopleSecond = new Register(USER_EMAIL_2, "");

        Specifications.installSpecification(
                Specifications.requestSpec(), Specifications.responseSpecError400());
        final UnsuccessUserRegister unSuccessUserReg =
                RequestSpecifications.postRequest(peopleSecond, ENDPOINT_REGISTER, UnsuccessUserRegister.class);

        assertNotNull(unSuccessUserReg.getError());
        assertEquals(ERROR_MESSAGE, unSuccessUserReg.getError(), "`Error` is different in response");
    }


    //TODO: проверка сортировки по датам(годам)
    @Test
    @Order(3)
    @Tag("REGRESS")
    @DisplayName(" Test 3 | `check sort `data` by years` ")
    void checkSortDataByYears() {
        Specifications.installSpecification(
                Specifications.requestSpec(), Specifications.responseSpecOK200());
        final List<Data> data =
                RequestSpecifications.getListRequest(ENDPOINT_UNKNOWN, PATH_DATA, Data.class);

        final List<Object> dataYears = data.stream().map(Data::getYear).collect(Collectors.toList());
        final List<Object> sortedDataYears = new ArrayList<>(dataYears);
        assertEquals(dataYears, sortedDataYears, "`data.year` isn`t sorted incrementally");
    }

}
