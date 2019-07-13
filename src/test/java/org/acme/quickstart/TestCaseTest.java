package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class TestCaseTest {

    @Test
    public void testErroreConSaveOrUpdate() {
        given().get("test").then().statusCode(200);
    }

}
