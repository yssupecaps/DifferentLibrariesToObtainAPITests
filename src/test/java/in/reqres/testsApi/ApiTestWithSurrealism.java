package in.reqres.testsApi;

import in.reqres.data.colors.Data;
import in.reqres.data.registration.Register;
import in.reqres.data.registration.SuccessUserReg;
import in.reqres.data.registration.UnsuccessUserReg;
import in.reqres.data.users.UserData;
import in.reqres.specs.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiTestWithSurrealism {


   /*
    По заданию.
      1) Переделайте ваши API тесты с учётом сюрреализации\десюрреализации.
      2) Самостоятельно изучите Git и научитесь туда(Github) выгружать проекты.
       Ожидаю увидеть задание по  API именно в нём.
   */

//проверка на совпадение названий аватаров
   @Test
   public void checUserAvatars(){
      String nameFile="128.jpg";
      Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
      List<UserData> userAvatar = given()
            .when()
            .get("api/users?page=2")
            .then()
            .log().body()
            .extract().body().jsonPath().getList("avatar",UserData.class);
      List<String> realPeopleAvatars =userAvatar.stream()
              .map(UserData::getAvatar)
              .collect(Collectors.toList());
      Assert.assertTrue(realPeopleAvatars.stream()
              .allMatch(value -> value.contains("128.jpg")), "не совпадают");
   }

   // проверка успешной регистрации
   @Test
   public void sucRegistrUser() {
      Integer UserId=4;
      String UserPassword="QpwL5tke4Pnpja7X4";
      Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
      Register peopleFirst = new Register("eve.holt@reqres.in","pistol");
      SuccessUserReg successUserReg = given()
              .body(peopleFirst)
              .when()
              .post("/api/register")
              .then()
              .log().all()
              .extract().as(SuccessUserReg.class);
      Assert.assertNotNull(successUserReg.getId());
      Assert.assertNotNull(successUserReg.getToken());
      Assert.assertEquals(UserId, successUserReg.getId());
      Assert.assertEquals(UserPassword, successUserReg.getToken());
   }


   // неуспешная регистрация(не введен пароль)
   @Test
   public void unSucRegistrUser(){
      Specifications.installSpecification(Specifications.requestSpec(),Specifications.responseSpecError4());
      Register peopleSecond = new Register("sydney@fife","");
      UnsuccessUserReg unSuccessUserReg = given()
              .body(peopleSecond)
              .when()
              .post("/api/register")
              .then()
              .log().body()
              .extract().as(UnsuccessUserReg.class);
      Assert.assertNotNull(unSuccessUserReg.getError());
      Assert.assertEquals("Missing password", unSuccessUserReg.getError());
   }


   //проверка сортировки по датам(годам)
   @Test
   public void checkSortDataByYears(){
      Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK2());
      List<Data> data = given()
              .when()
              .get("/api/unknown")
              .then()
              .log().all()
              .extract().body().jsonPath().getList("data", Data.class);
      List<Object> dataYears =data.stream().map(Data::getYear).collect(Collectors.toList());
      List sortedDataYears = new ArrayList(dataYears);
      Assert.assertEquals(dataYears, sortedDataYears);
      System.out.println(sortedDataYears);
   }
}
