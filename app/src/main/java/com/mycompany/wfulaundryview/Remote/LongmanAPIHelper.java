package com.mycompany.wfulaundryview.Remote;

import android.util.Log;

import com.mycompany.wfulaundryview.Settings;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by @sajantanand on 1/16/2015.
 */

public class LongmanAPIHelper {


    public String getXML() throws Exception
    {
        String url = "http://api.laundryview.com/school/?api_key=8c31a4878805ea4fe690e48fddbfffe1&method=getRoomData";
        HTTPSCall call = new HTTPSCall(url);
        Log.i(Settings.LOG_TAG, url);
        return (convertXMLtoJSON(call.doRemoteCall()));
        /*return call.doRemoteCall();*/
    }

    private String convertXMLtoJSON(String entryXMLasString)
    {

/*        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        com.google.gson.JsonObject jsonObject1 = jsonParser.parse(jsonObject.toString()).getAsJsonObject();*/

        /*JSONArray jsonArray = jsonObject.getJSONArray("school");*/

        /*com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        com.google.gson.JsonObject jsonObject = jsonParser.parse(entryXMLasString).getAsJsonObject();

        com.google.gson.JsonArray jsonArray = jsonObject.get("school").getAsJsonArray();
        return jsonArray.get(0).getAsString();*/
        /*return jsonObject.toString();*/
        return entryXMLasString;
    }



    /*private String headword;
    private boolean found = false;
    private com.google.gson.JsonArray multipleDef = new JsonArray();
		
	public String getDictionaryEntry(String headword) throws Exception {
		this.headword = headword;
        String URL_PREFIX =
                "https://api.pearson.com/v2/dictionaries/ldoce5/entries?headword=";

		String url = URL_PREFIX + headword + Settings.API_KEY;
		HTTPSCall call = new HTTPSCall(url);
		Log.i(Settings.LOG_TAG, url);
		return (parseDictionaryEntry(call.doRemoteCall()));
	}
	
	private String parseDictionaryEntry (String entryJSONAsString) {
        Integer i;
        String headWordObject;
        String comparison;
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
        com.google.gson.JsonObject jsonObject = jsonParser.parse(entryJSONAsString).getAsJsonObject();

        com.google.gson.JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();

        if (jsonArray.size() == 0) {
            return "No definition is available. Array size is zero.";
        }

        comparison = '"' + headword + '"';


        for (i = 0; i <= (jsonArray.size() - 1); i++) {
            jsonObject = jsonArray.get(i).getAsJsonObject();
            headWordObject = jsonObject.get("headword").toString();


            if (headWordObject.equalsIgnoreCase(comparison)) {
                found = true;
                multipleDef.add(jsonObject);
            }
        }
        if (!found) {
            return "No definition is available. No match found for headword.";
        }


        jsonObject = multipleDef.get(0).getAsJsonObject();


        jsonArray = jsonObject.get("senses").getAsJsonArray();
        jsonObject = jsonArray.get(0).getAsJsonObject();
        jsonArray = jsonObject.get("definition").getAsJsonArray();
        return (headword + ": " + jsonArray.get(0).getAsString());

    }

    public String getAlternateDefinition(short counter)
    {
        com.google.gson.JsonArray jsonArray;

        if (multipleDef.size() <= 1)
        {
            if (counter % 2 != 0) {
                return "No alternate definitions are available.";
            }
            else
            {
                com.google.gson.JsonObject jsonObject = multipleDef.get(0).getAsJsonObject();
                jsonArray = jsonObject.get("senses").getAsJsonArray();
                jsonObject = jsonArray.get(0).getAsJsonObject();
                jsonArray = jsonObject.get("definition").getAsJsonArray();
                return (headword + ": " + jsonArray.get(0).getAsString());
            }
        }
        int iteration = counter % multipleDef.size();

        com.google.gson.JsonObject jsonObject = multipleDef.get(iteration).getAsJsonObject();
        jsonArray = jsonObject.get("senses").getAsJsonArray();
        jsonObject = jsonArray.get(0).getAsJsonObject();
        jsonArray = jsonObject.get("definition").getAsJsonArray();
        return (headword + ": " + jsonArray.get(0).getAsString());

    }*/
}
