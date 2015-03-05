package com.mycompany.wfulaundryview.Remote;

import android.util.Log;

import com.mycompany.wfulaundryview.Settings;

import java.util.List;

/**
 * Created by @sajantanand on 1/16/2015.
 */

public class LongmanAPIHelper {


    public String getXML() throws Exception {
        String url = "http://api.laundryview.com/school/?api_key=8c31a4878805ea4fe690e48fddbfffe1&method=getRoomData";
        HTTPSCall call = new HTTPSCall(url);
        Log.i(Settings.LOG_TAG, url);
        List<XMLParser.LaundryRoom> rooms = new XMLParser().parse(call.doRemoteCall());
        String list = "";

        for (int i = 0; i < rooms.size(); i++) {
            list += "hello " + rooms.get(i).toString();
        }
        return (rooms.size() + "");
    }
}
