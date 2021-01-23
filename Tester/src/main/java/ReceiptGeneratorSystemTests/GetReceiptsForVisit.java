package ReceiptGeneratorSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

public class GetReceiptsForVisit {
    ///@GetMapping(path = "/visit/{id}")
    //@GetMapping(path = "/patient/{id}")
    static UUID receiptid = null;
    static JSONObject receipt = ReceiptGeneratorSystemTests._Utils.createReminderJSONObject();

    @BeforeClass
    public void CreateAnyReceipt(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(receipt.toString())
                .post(ReceiptGeneratorSystemTests._Utils.baseURIReceipts)
                .then()
                .statusCode(200)
                .extract()
                .response();

        receiptid = UUID.fromString(response.jsonPath().get("id").toString().trim());
        receipt.put("id", receiptid.toString());
        //System.out.println("Visit with " + visitid + " generated!");
    }

    @Test
    public void GetReceiptsForVisit() {
        //Response response =
        when()
                .get(_Utils.baseURIReceipts + "/visit/" + receipt.get("visitId").toString().trim())
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0]", hasKey("id"))
                //.body("id", equalTo( receipt.get("id").toString()))
                .body("[0]", hasKey("patientId"))
                //.body("patientId", equalTo(receipt.get("patientId").toString()))
                .body("[0]", hasKey("visitId"))
                //.body("visitId", equalTo(receipt.get("visitId").toString()))
                .body("[0]", hasKey("medicalIds"));
        //.body("medicalId", equalTo(receipt.get("medicalId").toString()));
    }
}
