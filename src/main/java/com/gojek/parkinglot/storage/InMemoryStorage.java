package com.gojek.parkinglot.storage;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.model.SlotDistanceMapperClass;

import java.util.*;

public class InMemoryStorage {
    private static InMemoryStorage inMemoryStorage = null;
    private InMemoryStorage(){}

    public static InMemoryStorage getInstance(){
        if(inMemoryStorage == null){
            synchronized (InMemoryStorage.class){
                if(inMemoryStorage == null)
                    inMemoryStorage = new InMemoryStorage();
            }
        }

        return inMemoryStorage;
    }

    Map<Integer, ParkingLot> map = new HashMap<Integer, ParkingLot>();

    public void addParkingLot(int id, ParkingLot parkingLot){
        map.put(id, parkingLot);
    }

    public ParkingLot getParkingLot(int id){
        return map.get(id);
    }
    public Map<Integer, ParkingLot> getParkingLots(){
        return map;
    }
}
