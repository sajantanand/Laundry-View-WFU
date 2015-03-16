package com.mycompany.wfulaundryview.Remote;

import android.util.Log;

import com.mycompany.wfulaundryview.Settings;

import java.util.List;

/**
 * Created by @sajantanand on 1/16/2015.
 */

public class LaundryViewHelper {


    public List<XMLParser.LaundryRoom> getXML() throws Exception {
        String url = "http://api.laundryview.com/school/?api_key=8c31a4878805ea4fe690e48fddbfffe1&method=getRoomData";
        HTTPSCall call = new HTTPSCall(url);
        Log.i(Settings.LOG_TAG, url);
        List<XMLParser.LaundryRoom> rooms = new XMLParser().parseRooms(call.doRemoteCall());
        return rooms;
    }

    public List<XMLParser.MachineList> getMachines(String code) throws Exception {
        String url = "http://api.laundryview.com/room/?api_key=8c31a4878805ea4fe690e48fddbfffe1&method=getAppliances&location=" + code;
        HTTPSCall call = new HTTPSCall(url);
        Log.i(Settings.LOG_TAG, url);
        List<XMLParser.MachineList> building = new XMLParser().parseBuilding(call.doRemoteCall());
        return building;
    }

    public String getMachines2(String code) throws Exception {
        String url = "http://api.laundryview.com/room/?api_key=8c31a4878805ea4fe690e48fddbfffe1&method=getAppliances&location=" + code;
        HTTPSCall call = new HTTPSCall(url);

        return call.doRemoteCall2();
    }
}