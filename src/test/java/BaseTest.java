import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseTest {

    //org.apache.lohhing.log4j
    private static final Logger logger = LogManager.getLogger(ClassBaseTests.class);

    @BeforeClass
    public static void setup() {
        logger.info("Iniciando la configuración");
        RestAssured.requestSpecification = defaultRequestSpecification();
        logger.info("Configuración exitosa");
    }

    private static RequestSpecification defaultRequestSpecification(){

        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        filters.add(new AllureRestAssured());

        //return  new RequestSpecBuilder().setBaseUri("https://reqres.in")
        return  new RequestSpecBuilder().setBaseUri(ConfVariables.getHost())
        //      .setBasePath("/api")
                .setBasePath(ConfVariables.getPath())
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();
    }

    private RequestSpecification prodRequestSpecification(){
        return new RequestSpecBuilder().setBaseUri("https://prod.reqres.in")
                .setBasePath("/api")
                .setContentType(ContentType.JSON).build();
    }

    public ResponseSpecification defaultResponseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
