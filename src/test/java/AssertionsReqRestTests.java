import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AssertionsReqRestTests {
    //Method Post
    @Test
    public void loginTest() {

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"email\": \"eve.holt@reqres.in\",\n" +
                        "   \"password\": \"cityslicka\"\n" +
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("token", notNullValue());
    }

    //Method Get
    @Test
    public void getSingleUserTest() {

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }
}
