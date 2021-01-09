package ReceiptGeneratorSystemTests;

import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class _Utils {

    public static String baseURIReceipts = "http://localhost:9096/api/receipt";
    public static String baseURIMedicals = "http://localhost:9096/api/medicals";

    public static JSONObject createReminderJSONObject(){
        //System.out.println("Creating new random receipt: ");
        JSONObject newReceipt = new JSONObject();

        UUID newRandomPatientID = UUID.randomUUID();
        UUID newRandomVisitID = UUID.randomUUID();
        UUID newRandomMedicalsID = UUID.randomUUID();

        newReceipt.put("patientId",newRandomPatientID.toString());
        newReceipt.put("visitId",newRandomVisitID.toString());
        newReceipt.put("medicalId", newRandomMedicalsID.toString());

        //System.out.println(newReceipt.toString());

        return newReceipt;
    }

    public static JSONObject createMedicalJSONObject(){
        //System.out.println("Creating new random receipt: ");
        JSONObject newReceipt = new JSONObject();

        String newName = "THIS IS TEST NAME OF MED";

        newReceipt.put("name", newName);

        //System.out.println(newVisit.toString());

        return newReceipt;
    }

}
