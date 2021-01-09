package BookVisitSystemTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;

public class GetAllVisits {

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
    public void GetAllVisitsRequest() {
        //Response response =
        when()
                .get(_Utils.baseURI)
        .then()
                .statusCode(200)
                .assertThat()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("date"))
                .body("[0]", hasKey("notes"))
                .body("[0]", hasKey("doctorID"))
                .body("[0]", hasKey("patientID"))
                .body("[0]", hasKey("specializationID"))
                .body("[0]", hasKey("medicalsID"));
    }


}
