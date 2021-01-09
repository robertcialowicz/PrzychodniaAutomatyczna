package BookVisitSystemTests;

import org.apache.tools.ant.util.DateUtils;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public final class _Utils {

    public static String baseURI = "http://localhost:9094/api/visit";

    public static JSONObject createBlankVisitJSONObject(){
        //System.out.println("Creating new random visit: ");
        JSONObject newVisit = new JSONObject();

        String date = "2022-02-02 10:00:00.0";
        UUID newRandomDoctorID = UUID.randomUUID();
        UUID newRandomPatientID = UUID.randomUUID();
        String newSpecializationID = "5d16c66a-db11-4a1e-b864-78b35f245699";

        newVisit.put("date",date);
        newVisit.put("notes", JSONObject.NULL);
        newVisit.put("doctorID",newRandomDoctorID.toString());
        newVisit.put("patientID",newRandomPatientID.toString());
        newVisit.put("specializationID",newSpecializationID);
        newVisit.put("medicalsID", JSONObject.NULL);

        //System.out.println(newVisit.toString());

        return newVisit;
    }

    public static JSONObject createBlankVisitJSONObject(String date){
        JSONObject newVisit = createBlankVisitJSONObject();
        newVisit.put("date", date);
        return newVisit;
    }

}
