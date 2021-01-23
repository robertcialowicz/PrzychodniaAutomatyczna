package BookVisitSystemTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import static io.restassured.RestAssured.*;

public class GetVisitByID {

    static UUID visitid = null;
    static JSONObject visit = _Utils.createBlankVisitJSONObject();

    @BeforeClass
    public void CreateAnyVisit(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(visit.toString())
                .post(_Utils.baseURI)
                .then()
                .statusCode(200)
                .extract()
                .response();

        visitid = UUID.fromString(response.jsonPath().get("id").toString().trim());
        visit.put("id", visitid);
        //System.out.println("Visit with " + visitid + " generated!");
    }

    @Test
    public void GetVisitsByID() {
        //Response response =
        when()
                .get(_Utils.baseURI + "/" + visitid.toString().trim())
        .then()
                .statusCode(200)
                .assertThat()
                .body("", hasKey("id"))
                .body("id", equalTo(visit.get("id").toString()))
                .body("", hasKey("date"))
                .body("date", equalTo(visit.get("date").toString()))
                .body("", hasKey("notes"))
                //.body("notes", equalTo(JSONObject.NULL))
                .body("", hasKey("doctorID"))
                .body("doctorID", equalTo(visit.get("doctorID").toString()))
                .body("", hasKey("patientID"))
                .body("patientID", equalTo(visit.get("patientID").toString()))
                .body("", hasKey("specializationID"))
                .body("specializationID", equalTo(visit.get("specializationID").toString()))
                .body("", hasKey("medicalsID"));
                //.body("medicalsID", equalTo(JSONObject.NULL));
    }


}
