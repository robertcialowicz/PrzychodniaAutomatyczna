package BookVisitSystemTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

public class DeleteVisit {
    //@DeleteMapping(path = "/{id}")
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
    public void DeleteVisit() {
        //Response response =
        when()
                .delete(_Utils.baseURI + "/" + visitid.toString().trim())
        .then()
                .assertThat()
                .statusCode(200);
    }

}
