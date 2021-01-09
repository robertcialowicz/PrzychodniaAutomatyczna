package BookVisitSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class PutVisit {

    static UUID visitid = null;
    static JSONObject visit = _Utils.createBlankVisitJSONObject();

    @BeforeMethod
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
    public void PutVisit() {
        visit.put("notes", "THIS IS TEST NOTE");
        UUID newMedicalsID = UUID.randomUUID();
        visit.put("medicalsID", newMedicalsID);
        //Response response =
        given()
                .contentType(ContentType.JSON)
                .body(visit.toString())
                .put(_Utils.baseURI + "/" + visitid)
        .then()
                .statusCode(200)
                .assertThat()
                .body("", hasKey("id"))
                .body("id", equalTo(visit.get("id").toString()))
                .body("", hasKey("date"))
                .body("date", equalTo(visit.get("date").toString()))
                .body("", hasKey("notes"))
                .body("notes", equalTo(visit.get("notes").toString()))
                .body("", hasKey("doctorID"))
                .body("doctorID", equalTo(visit.get("doctorID").toString()))
                .body("", hasKey("patientID"))
                .body("patientID", equalTo(visit.get("patientID").toString()))
                .body("", hasKey("specializationID"))
                .body("specializationID", equalTo(visit.get("specializationID").toString()))
                .body("", hasKey("medicalsID"))
                .body("medicalsID", equalTo(visit.get("medicalsID").toString()));
    }

    @Test
    public void PutVisitWithNotesOnly() {
        visit.put("notes", "THIS IS TEST NOTE");
        //Response response =
        given()
                .contentType(ContentType.JSON)
                .body(visit.toString())
                .put(_Utils.baseURI + "/" + visitid)
        .then()
                .statusCode(200)
                .assertThat()
                .body("", hasKey("id"))
                .body("id", equalTo(visit.get("id").toString()))
                .body("", hasKey("date"))
                .body("date", equalTo(visit.get("date").toString()))
                .body("", hasKey("notes"))
                .body("notes", equalTo(visit.get("notes").toString()))
                .body("", hasKey("doctorID"))
                .body("doctorID", equalTo(visit.get("doctorID").toString()))
                .body("", hasKey("patientID"))
                .body("patientID", equalTo(visit.get("patientID").toString()))
                .body("", hasKey("specializationID"))
                .body("specializationID", equalTo(visit.get("specializationID").toString()))
                .body("", hasKey("medicalsID"));
    }

    @Test
    public void PutVisitWithMedicalsOnly() {
        UUID newMedicalsID = UUID.randomUUID();
        visit.put("medicalsID", newMedicalsID);
        //Response response =
        given()
                .contentType(ContentType.JSON)
                .body(visit.toString())
                .put(_Utils.baseURI + "/" + visitid)
        .then()
                .statusCode(200)
                .assertThat()
                .body("", hasKey("id"))
                .body("id", equalTo(visit.get("id").toString()))
                .body("", hasKey("date"))
                .body("date", equalTo(visit.get("date").toString()))
                .body("", hasKey("notes"))
                .body("notes", equalTo(visit.get("notes").toString()))
                //.body("", hasKey("doctorID"))
                .body("doctorID", equalTo(visit.get("doctorID").toString()))
                .body("", hasKey("patientID"))
                .body("patientID", equalTo(visit.get("patientID").toString()))
                .body("", hasKey("specializationID"))
                .body("specializationID", equalTo(visit.get("specializationID").toString()))
                .body("", hasKey("medicalsID"))
                .body("medicalsID", equalTo(visit.get("medicalsID").toString()));
    }

}
