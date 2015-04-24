package com.mycompany.wfulaundryview.Remote;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjdavis0201 on 3/4/15.
 */
public class XMLParser {
    private static final String ns = null;

    public List<LaundryRoom> parseRooms(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            Log.i("info", "testing");
            //while ()
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser2) throws XmlPullParserException, IOException {
        List<LaundryRoom> entries = new ArrayList<>();

        parser2.require(XmlPullParser.START_TAG, ns, "school");
        while (parser2.next() != XmlPullParser.END_TAG) {
            if (parser2.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser2.getName();
            if (name.equals("laundry_rooms")) {
                entries = readEntry(parser2);
                Log.i("info", "object added");
            } else {
                skip(parser2);
            }
        }

        return entries;
    }

    private List<LaundryRoom> readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "laundry_rooms");
        List<LaundryRoom> entries = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("laundryroom")) {
                entries.add(readRoom(parser));
                //return readRoom(parser);
            } else {
                skip(parser);
            }
        }

        //return room;
        //return "failure";
        return entries;

    }

    private LaundryRoom readRoom(XmlPullParser parser2) throws XmlPullParserException, IOException {
        parser2.require(XmlPullParser.START_TAG, ns, "laundryroom");
        String location = null;
        String laundry_room_name = null;
        String status = null;

        while(parser2.next() != XmlPullParser.END_TAG) {
            if (parser2.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser2.getName();
            if (name.equals("location")) {
                location = readInfo(parser2, "location");
                //return "success for location";
            } else if (name.equals("laundry_room_name")) {
                laundry_room_name = readInfo(parser2, "laundry_room_name");
                //return "success for laundry room";
            } else if (name.equals("status")) {
                status = readInfo(parser2, "status");
                //return "success for status";
            } else {
                skip(parser2);
            }
        }

        //return "failure";
        return new LaundryRoom(location, laundry_room_name, status);

    }

    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }

    }


    public List<MachineList> parseBuilding(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            Log.i("info", "testing");
            //while ()
            return readFeed2(parser);
        } finally {
            in.close();
        }
    }

    private List<MachineList> readFeed2(XmlPullParser parser2) throws XmlPullParserException, IOException {
        List<MachineList> entries = new ArrayList<>();

        parser2.require(XmlPullParser.START_TAG, ns, "laundry_room");
        while (parser2.next() != XmlPullParser.END_TAG) {
            if (parser2.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser2.getName();
            if (name.equals("appliances")) {
                entries = readEntry2(parser2);
                Log.i("info", "object added");
            } else {
                skip(parser2);
            }
        }

        return entries;
    }

    private List<MachineList> readEntry2(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "appliances");
        List<MachineList> entries = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("appliance")) {
                entries.add(readRoom2(parser));
                //return readRoom(parser);
            } else {
                skip(parser);
            }
        }

        //return room;
        //return "failure";
        return entries;

    }

    private MachineList readRoom2(XmlPullParser parser2) throws XmlPullParserException, IOException {
        parser2.require(XmlPullParser.START_TAG, ns, "appliance");
        String machineCode = null;
        String roomStatus = null;
        String type = null;
        String machineStatus = null;
        String label = null;
        String cycleTime = null;
        String timeRemaining = null;

        while(parser2.next() != XmlPullParser.END_TAG) {
            if (parser2.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser2.getName();
            if (name.equals("appliance_desc_key")) {
                machineCode = readInfo(parser2, "appliance_desc_key");
                //return "success for location";
            } else if (name.equals("lrm_status")) {
                roomStatus = readInfo(parser2, "lrm_status");
                //return "success for laundry room";
            } else if (name.equals("appliance_type")) {
                type = readInfo(parser2, "appliance_type");
                //return "success for status";
            } else if (name.equals("status")) {
                machineStatus = readInfo(parser2, "status");
            } else if (name.equals("label")) {
                label = readInfo(parser2, "label");
            } else if (name.equals("avg_cycle_time")) {
                cycleTime = readInfo(parser2, "avg_cycle_time");
            } else if (name.equals("time_remaining")) {
                timeRemaining = readInfo(parser2, "time_remaining");
            }
            else {
                skip(parser2);
            }
        }

        //return "failure";
        return new MachineList(machineCode, roomStatus, type, machineStatus, label, cycleTime, timeRemaining);

    }

    private String readInfo(XmlPullParser parser, String keyName) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, ns, keyName);
        String status = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, keyName);
        return status;
    }
}
