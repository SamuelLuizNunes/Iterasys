package br.com.iterasys.apiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    String json = "application/json";
    String uriUser = "https://petstore.swagger.io/v2/user/";


    public String lerArquivo(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }


    @Test
    @Order(1)
    public void testCreateUser() throws IOException {

        String userId = "3970512";
        String jsonBody = lerArquivo("src/test/resources/json/user1.json");

        given()
                .contentType(json)
                .body(jsonBody)
                .log().all()
        .when()
                .post(uriUser)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        ;
    }


    @Test
    @Order(2)
    public void testQueryUser(){
        String userName = "haseo";
        int userId = 3970512;
        String email = "delphino@hotmail.com";
        String phone = "11977660512";
        String password = "123456";

        given()
                .contentType(json)
                .log().all()
        .when()
                .get( uriUser + userName)
        .then()
                .log().all()
                .body("id", is(userId))
                .body("email", is(email))
                .body("password", is(password))
                .body("phone", is(phone))
        ;
    }

    @Test
    @Order(3)
    public void testUpdateUser() throws IOException {
        String jsonBody = lerArquivo("src/test/resources/json/user2.json");
        String userName = "haseo";
        String userId = "3970512";
        String email = "delphino@hotmail.com";
        String phone = "1199999999";
        String password = "abcdef";

        given()
                .contentType(json)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriUser + userName)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))
        .log().all();
    }

    @Test
    @Order(4)
    public void testDeleteUser(){
        String userName = "haseo";

        given()
                .contentType(json)
                .log().all()
        .when()
                .delete(uriUser + userName)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userName))
        .log().all();
    }

    @Test
    public void testLogin(){
        String userName = "haseo";
        String password = "abcdef";

        Response response = given()
                .contentType(json)
                .log().all()
        .when()
                .get(uriUser + "login?username=" + userName + "&password=" + password)
        .then()
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", containsString("logged in user session"))
                .body("message", hasLength(36))
                .extract().response()
        ;

        String token = response.body().jsonPath().getString("message").substring(23);
        System.out.println(token);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/csv/massaUser.csv", numLinesToSkip = 1, delimiter = ',')
    public void testCreateUserCSV(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) throws JsonProcessingException {

        Map<String, String> mapBody = new HashMap<>();
        mapBody.put("id", id);
        mapBody.put("username", username);
        mapBody.put("firstName", firstName);
        mapBody.put("lastName", lastName);
        mapBody.put("email", email);
        mapBody.put("password", password);
        mapBody.put("phone", phone);
        mapBody.put("userStatus", userStatus);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(mapBody);

        given()
                .contentType(json)
                .body(jsonBody)
                .log().all()
                .when()
                .post(uriUser)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(id))
        ;
    }
}











