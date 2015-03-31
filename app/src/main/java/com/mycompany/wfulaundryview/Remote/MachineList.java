package com.mycompany.wfulaundryview.Remote;

/**
 * Created by kjdavis0201 on 3/31/15.
 */
public class MachineList {

    public final String machineCode;
    public final String roomStatus;
    public final String type;
    public final String machineStatus;
    public final String label;
    public final String cycleTime;
    public final String timeRemaining;

    public MachineList() {
        machineCode = null;
        roomStatus = null;
        type = null;
        machineStatus = null;
        label = null;
        cycleTime = null;
        timeRemaining = null;
    }

    public MachineList(String machineCode, String roomStatus, String type, String machineStatus, String label, String cycleTime, String timeRemaining) {
        this.machineCode = machineCode;
        this.roomStatus = roomStatus;
        this.type = type;
        this.machineStatus = machineStatus;
        this.label = label;
        this.cycleTime = cycleTime;
        this.timeRemaining = timeRemaining;
    }
}
