package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


@QuarkusTest
public class TestFlussoOrdiniBackChiosco {


    @Test
    public void testErroreConSaveOrUpdate() {

        String body = "{\n" +
                "  \"asporto\": false,\n" +
                "  \"attesa\": {\n" +
                "    \"indiceDiAttesa\": 1,\n" +
                "    \"minutiDiAttesa\": 0\n" +
                "  },\n" +
                "  \"bar\": {\n" +
                "    \"descrizione\": \"BEC BAR\",\n" +
                "    \"filiale\": \"1\",\n" +
                "    \"id\": \"U-491\",\n" +
                "    \"numero\": 571,\n" +
                "    \"sigla\": \"BECB\",\n" +
                "    \"societa\": \"D\"\n" +
                "  },\n" +
                "  \"canale\": \"CHIOSCO\",\n" +
                "  \"dataAggiornamento\": \"1562841457449\",\n" +
                "  \"dataChiusura\": \"1562841457449\",\n" +
                "  \"dataInserimento\": \"1562841457101\",\n" +
                "  \"idCatalogoOriginale\": 1,\n" +
                "  \"idMonitor\": 1,\n" +
                "  \"menus\": [],\n" +
                "  \"numeroComanda\": \"C1\",\n" +
                "  \"piattiOrdinati\": [\n" +
                "    {\n" +
                "      \"abbinamenti\": [\n" +
                "        {\n" +
                "          \"dataAggiornamento\": 1562841457122,\n" +
                "          \"dataInserimento\": 1562841457122,\n" +
                "          \"idAbbinamentoOriginale\": 1,\n" +
                "          \"nome\": \"fredda\",\n" +
                "          \"stato\": \"APERTO\",\n" +
                "          \"version\": 0\n" +
                "        },\n" +
                "        {\n" +
                "          \"dataAggiornamento\": 1562841457124,\n" +
                "          \"dataInserimento\": 1562841457124,\n" +
                "          \"idAbbinamentoOriginale\": 1,\n" +
                "          \"nome\": \"fredda\",\n" +
                "          \"stato\": \"APERTO\",\n" +
                "          \"version\": 0\n" +
                "        }\n" +
                "      ],\n" +
                "      \"dataAggiornamento\": 1562841457450,\n" +
                "      \"dataInserimento\": 1562841457122,\n" +
                "      \"id\": 2,\n" +
                "      \"ordine\": {\n" +
                "        \"asporto\": false,\n" +
                "        \"bar\": {\n" +
                "          \"descrizione\": \"MILANO FAMAGOSTA BAR\",\n" +
                "          \"filiale\": \"1\",\n" +
                "          \"id\": \"U-491\",\n" +
                "          \"numero\": 571,\n" +
                "          \"sigla\": \"BECB\",\n" +
                "          \"societa\": \"D\"\n" +
                "        },\n" +
                "        \"barcode\": \"9999000003000001110719123731\",\n" +
                "        \"canale\": \"CHIOSCO\",\n" +
                "        \"dataAggiornamento\": 1562841457449,\n" +
                "        \"dataInserimento\": 1562841457101,\n" +
                "        \"numeroComanda\": \"C1\",\n" +
                "        \"prioritario\": false,\n" +
                "        \"stato\": \"CHIUSO\",\n" +
                "        \"version\": 1\n" +
                "      },\n" +
                "      \"piatto\": {\n" +
                "        \"descrizioneStampa\": \"COCA\",\n" +
                "        \"ean\": \"800000009\",\n" +
                "        \"idArticoloOriginale\": 10,\n" +
                "        \"nome\": \"COCA\",\n" +
                "        \"preparazione\": \"A_VISTA\",\n" +
                "        \"prezzo\": \"3.0\"\n" +
                "      },\n" +
                "      \"quantita\": 2,\n" +
                "      \"stato\": \"APERTO\",\n" +
                "      \"version\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"prioritario\": false,\n" +
                "  \"stato\": \"APERTO\",\n" +
                "  \"version\": 1\n" +
                "}\n";

        RestAssured.port = 8080;

        String responseOrdine = given().auth().preemptive().basic("barbecd04", "barbecd04")
            .body(body)
            .when()
            .contentType(JSON)
            .post("/ordini")
            .then().log().all()
            .statusCode(200).extract().asString();

        given().auth().preemptive().basic("barbecd04", "barbecd04")
                .body(responseOrdine).log().all()
                .when()
                .contentType(JSON)
                .post("/ordini")
                .then()
                .statusCode(200);
    }

}
