package ReceiptGeneratorSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class PostMedical {
    static UUID medicalid = null;
    static JSONObject medical = _Utils.createMedicalJSONObject();

    @Test
    public void CreateAnyMedical(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(medical.toString())
                .post(_Utils.baseURIMedicals)
                .then()
                .statusCode(200)
                .extract()
                .response();

        medicalid = UUID.fromString(response.jsonPath().get("id").toString().trim());
        medical.put("id", medicalid.toString());
        //System.out.println("Visit with " + visitid + " generated!");
    }
}
