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

public class GetMedicalById {
    static UUID medicalid = null;
    static JSONObject medical = _Utils.createMedicalJSONObject();

    @BeforeClass
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

    @Test
    public void GetMedicalById() {
        //Response response =
        when()
                .get(_Utils.baseURIMedicals + "/" + medical.get("id").toString().trim())
                .then()
                .statusCode(200)
                .assertThat()
                .body("", hasKey("id"))
                .body("", hasKey("name"));
    }
}
