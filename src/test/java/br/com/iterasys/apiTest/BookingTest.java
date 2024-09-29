package br.com.iterasys.apiTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingTest {

    private String uri = "https://restful-booker.herokuapp.com/";
    private String ct = "application/json";
    private static String token = "";
    private static int id = 0;

    public String readFileJson(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @BeforeEach
    public void setToken() throws IOException {
        String jsonBody = readFileJson("src/test/resources/json/bookingToken.json");

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
        .when()
                .post(uri + "auth")
        .then()
                .extract().response();

        token = response.jsonPath().getString("token");
    }

    @Test
    @Order(1)
    public void createBooking() throws IOException {
        String jsonBody = readFileJson("src/test/resources/json/booking.json");

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
        .when()
                .post(uri + "booking")
        .then()
                .statusCode(200)
                .body("booking.firstname", is("Luiz"))
                .body("booking.lastname", is("Alberto"))
                .body("booking.totalprice", is(1000))
                .body("booking.bookingdates.checkin", is("2025-10-26"))
                .body("booking.bookingdates.checkout", is("2025-11-26"))
                .extract().response();
        id = response.jsonPath().getInt("bookingid");

    }

    @Test
    @Order(2)
    public void getBooking(){
        given()
                .accept(ct)
        .when()
                .get(uri + "booking/" + id)
        .then()
                .statusCode(200)
                .body("firstname", is("Luiz"))
                .body("lastname", is("Alberto"))
                .body("totalprice", is(1000))
                .body("depositpaid", is(true))
                .body("bookingdates.checkin", is("2025-10-26"))
                .body("bookingdates.checkout", is("2025-11-26"))
                .body("additionalneeds", is("Dinner"))
        ;
    }

    @Test
    @Order(3)
    public void updateBookingPut() throws IOException {
        String jsonBody = readFileJson("src/test/resources/json/bookingUpdade.json");

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ct);
        header.put("Cookie", "token=" + token);

        given()
                .headers(header)
                .body(jsonBody)
        .when()
                .put(uri + "booking/" + id)
        .then()
                .statusCode(200)
                .body("lastname", is("Caiado"))
                .body("additionalneeds", is("Lunch"))
        ;
    }

    @Test
    @Order(4)
    public void updateBookingPatch() throws IOException {
        String jsonBody = readFileJson("src/test/resources/json/bookingUpdatePartial.json");

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ct);
        header.put("Cookie", "token=" + token);

        given()
                .headers(header)
                .body(jsonBody)
        .when()
                .patch(uri + "booking/" + id)
        .then()
                .statusCode(200)
                .body("firstname", is("Bruno"))
                .body("lastname", is("Delphino"))
        ;
    }

    @Test
    @Order(5)
    public void deleteBooking(){
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", ct);
        header.put("Cookie", "token=" + token);

        given()
                .headers(header)
        .when()
                .delete(uri + "booking/" + id)
        .then()
                .statusCode(201)
        ;
    }

}
