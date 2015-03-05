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

    public List<LaundryRoom> parse(InputStream in) throws XmlPullParserException, IOException {
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

    public static class LaundryRoom {
        public final String location;
        public final String laundry_room_name;
        public final String status;

        private LaundryRoom(String location, String laundry_room_name, String status) {
            this.location = location;
            this.laundry_room_name = laundry_room_name;
            this.status = status;
        }
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
                location = readLocation(parser2);
                //return "success for location";
            } else if (name.equals("laundry_room_name")) {
                laundry_room_name = readRoomName(parser2);
                //return "success for laundry room";
            } else if (name.equals("status")) {
                status = readStatus(parser2);
                //return "success for status";
            } else {
                skip(parser2);
            }
        }

        //return "failure";
        return new LaundryRoom(location, laundry_room_name, status);

    }

    private String readLocation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "location");
        String location = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "location");
        return location;
    }

    private String readRoomName(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, ns, "laundry_room_name");
        String laundry_room_name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "laundry_room_name");
        return laundry_room_name;
    }

    private String readStatus(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, ns, "status");
        String status = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "status");
        return status;
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

}
