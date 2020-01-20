package com.gojek.parkinglot.storage;

import com.gojek.parkinglot.model.SlotDistanceMapperClass;

import java.util.Comparator;

public class NearestSlotIdComparator  implements Comparator<SlotDistanceMapperClass> {
    public int compare(SlotDistanceMapperClass o1, SlotDistanceMapperClass o2) {
        return o1.getDistanceId() - o2.getDistanceId();
    }
}
