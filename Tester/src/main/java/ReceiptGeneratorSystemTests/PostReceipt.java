package ReceiptGeneratorSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class PostReceipt {
    //@PostMapping
    static UUID receiptid = null;
    static JSONObject receipt = ReceiptGeneratorSystemTests._Utils.createReminderJSONObject();

    @Test
    public void PostReceipt() {
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

}
