package com.gojek.parkinglot.model;

public class SlotDistanceMapperClass {
    private int slotId;
    private int distanceId;

    public SlotDistanceMapperClass(int slotId, int distanceId) {
        this.slotId = slotId;
        this.distanceId = distanceId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getDistanceId() {
        return distanceId;
    }

    public void setDistanceId(int distanceId) {
        this.distanceId = distanceId;
    }
}
