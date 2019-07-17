package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.quickstart.entities.MonitorPostazione;
import org.acme.quickstart.entities.MonitorPostazionePK;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


@QuarkusTest
public class TestCaseTest {

    @Test
    public void testOk() {

        testWithValue(false);
    }

    @Test
    public void testKo() {

        testWithValue(true);
    }

    private void testWithValue(boolean abilitata) {
        MonitorPostazione monitorPostazione = given().get("test/persist/"+abilitata).then().extract().as(MonitorPostazione.class);

        monitorPostazione.setAbilitata(!abilitata);

        MonitorPostazione monitorPostazioneUpdated = given().body(monitorPostazione).contentType("application/json").get("test/merge").then().extract().as(MonitorPostazione.class);

        MonitorPostazione monitorPostazioneGet = given()
                .body(monitorPostazioneUpdated)
                .contentType("application/json")
                .get("/test").then().extract().as(MonitorPostazione.class);

        assertEquals(!abilitata, monitorPostazioneGet.isAbilitata());
    }

}
