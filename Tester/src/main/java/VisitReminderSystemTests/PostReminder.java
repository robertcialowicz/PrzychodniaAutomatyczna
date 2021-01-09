package VisitReminderSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class PostReminder {
    static UUID reminderid = null;
    static JSONObject reminder = VisitReminderSystemTests._Utils.createReminderJSONObject();

    @Test
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
}
