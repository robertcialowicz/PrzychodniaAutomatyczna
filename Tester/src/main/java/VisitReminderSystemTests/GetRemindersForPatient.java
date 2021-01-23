package VisitReminderSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class GetRemindersForPatient {
    static UUID reminderid = null;
    static JSONObject reminder = VisitReminderSystemTests._Utils.createReminderJSONObject();

    @BeforeClass
    public void CreateAnyReceipt(){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(reminder.toString())
                .post(VisitReminderSystemTests._Utils.baseURI)
                .then()
                .statusCode(200)
                .extract()
                .response();

        reminderid = UUID.fromString(response.jsonPath().get("id").toString().trim());
        reminder.put("id", reminderid.toString());
        //System.out.println("Visit with " + visitid + " generated!");
    }

    @Test
    public void GetRemindersForPatient() {
        //Response response =
        when()
                .get(_Utils.baseURI + "/patient/" + reminder.get("patientID").toString().trim())
                .then()
                .statusCode(200)
                .assertThat()
                .body("[0]", hasKey("id"))
                //.body("id", equalTo( reminder.get("id").toString()))
                .body("[0]", hasKey("patientID"))
                //.body("patientID", equalTo(reminder.get("patientID").toString()))
                .body("[0]", hasKey("doctorID"))
                //.body("doctorID", equalTo(reminder.get("doctorID").toString()))
                .body("[0]", hasKey("visitID"))
                //.body("visitID", equalTo(reminder.get("visitID").toString()))
                .body("[0]", hasKey("datetime"));
                //.body("datetime", equalTo(reminder.get("datetime").toString()));
    }
}
