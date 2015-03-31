package com.mycompany.wfulaundryview.Remote;

/**
 * Created by kjdavis0201 on 3/31/15.
 */
public class LaundryRoom {

    public final String location;
    public final String laundry_room_name;
    public final String status;

    public LaundryRoom(String location, String laundry_room_name, String status) {
        this.location = location;
        this.laundry_room_name = laundry_room_name;
        this.status = status;
    }
}
