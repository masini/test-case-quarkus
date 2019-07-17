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
    public void testErroreConSaveOrUpdate() {
        MonitorPostazione monitorPostazione = given().get("test/persist").then().extract().as(MonitorPostazione.class);

        monitorPostazione.setAbilitata(false);

        MonitorPostazione monitorPostazioneUpdated = given().body(monitorPostazione).contentType("application/json").get("test/merge").then().extract().as(MonitorPostazione.class);

        MonitorPostazione monitorPostazioneGet = given()
                .body(new MonitorPostazionePK(monitorPostazioneUpdated.getIdBar(), monitorPostazioneUpdated.getIdMonitor(), monitorPostazioneUpdated.getSottoPreparazione()))
                .contentType("application/json")
                .get("test").then().extract().as(MonitorPostazione.class);

        assertEquals(false, monitorPostazioneGet.isAbilitata());
    }

}
