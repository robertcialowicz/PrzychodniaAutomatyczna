package VisitReminderSystemTests;

import org.json.JSONObject;

import java.util.UUID;

public class _Utils {

    public static String baseURI = "http://localhost:9095/api/reminder";

    public static JSONObject createReminderJSONObject(){
        //System.out.println("Creating new random receipt: ");
        JSONObject newReceipt = new JSONObject();

        String newDate = "2022-02-02 10:00:00.0";
        UUID newRandomPatientID = UUID.randomUUID();
        UUID newRandomVisitID = UUID.randomUUID();
        UUID newRandomDoctorID = UUID.randomUUID();

        newReceipt.put("patientID",newRandomPatientID.toString());
        newReceipt.put("doctorID",newRandomDoctorID.toString());
        newReceipt.put("visitID", newRandomVisitID.toString());
        newReceipt.put("datetime", newDate);

        //System.out.println(newVisit.toString());

        return newReceipt;
    }
}
