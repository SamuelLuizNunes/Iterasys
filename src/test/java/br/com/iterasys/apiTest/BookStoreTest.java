package br.com.iterasys.apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreTest {

    public static String ct = "application/json";
    public static String uri = "https://bookstore.toolsqa.com/Account/v1/";
    public static String userID;
    public static String token;
    public static String jsonBody;
    public static Account account;


    @Test
    @Order(1)
    public void testCreateAccount(){
        Gson gson = new Gson();
        account = new Account();
        account.userName = "Samuel200";
        account.password = "Senha@123";

        jsonBody = gson.toJson(account);

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .log().all()
        .when()
                .post(uri + "User")
        .then()
                .statusCode(201)
                .body("username", is("Samuel200"))
                .log().all()
                .extract().response()
        ;
        userID = response.jsonPath().getString("userID");
    }

    @Test
    @Order(2)
    public void testCreateToken(){
       Response response = given()
               .contentType(ct)
               .body(jsonBody)
               .log().all()
       .when()
               .post(uri + "GenerateToken")
       .then()
               .statusCode(200)
               .log().all()
               .extract().response()
       ;

       token = response.jsonPath().getString("token");

    }

    @Test
    @Order(3)
    public void testGetAccount(){
        given()
                .contentType(ct)
                .header("Authorization", "Bearer " + token)
                .log().all()
        .when()
                .get(uri + "User/" + userID)
        .then()
                .log().all()
                .statusCode(200)
                .body("username", is("Samuel200"))
        ;
    }

    @Test
    @Order(4)
    public void testDeleteAccount(){
        given()
                .header("Authorization", "Bearer " + token)
                .log().all()
        .when()
                .delete(uri + "User/" + userID)
        .then()
                .log().all()
                .statusCode(204)
        ;
    }
}
