package com.gojek.parkinglot.model;

import com.gojek.parkinglot.storage.InMemoryStorage;
import com.gojek.parkinglot.storage.NearestSlotIdComparator;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ParkingLot {
    private int id;
    Map<Integer, Slot>  parkingLotSlotMap;
    Map<String , Integer> parkingLotSlotAllocationMap;
    TreeSet<SlotDistanceMapperClass>  nearestAvailableSlotInParkingLotMapper;
    int size = 0;
    int currentSize = 0;
    public ParkingLot(int size){
        this.size = size;
        parkingLotSlotMap = new HashMap<Integer, Slot>();
        nearestAvailableSlotInParkingLotMapper = new TreeSet<SlotDistanceMapperClass>(new NearestSlotIdComparator());
        parkingLotSlotAllocationMap = new HashMap<String, Integer>();
        for(int i = 1; i <= size; i++){
            parkingLotSlotMap.put(i, new Slot(i, i));
            nearestAvailableSlotInParkingLotMapper.add(new SlotDistanceMapperClass(i, i));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Slot> getParkingLotSlotMap() {
        return parkingLotSlotMap;
    }

    public void setParkingLotSlotMap(Map<Integer, Slot> parkingLotSlotMap) {
        this.parkingLotSlotMap = parkingLotSlotMap;
    }

    public Map<String, Integer> getParkingLotSlotAllocationMap() {
        return parkingLotSlotAllocationMap;
    }

    public void setParkingLotSlotAllocationMap(Map<String, Integer> parkingLotSlotAllocationMap) {
        this.parkingLotSlotAllocationMap = parkingLotSlotAllocationMap;
    }

    public Slot getNearestAvailableSlot(){
        SlotDistanceMapperClass slotDistanceMapperClass = nearestAvailableSlotInParkingLotMapper.first();
        nearestAvailableSlotInParkingLotMapper.remove(slotDistanceMapperClass);
        if(slotDistanceMapperClass == null){
            return null;
        }
        return parkingLotSlotMap.get(slotDistanceMapperClass.getSlotId());
    }


    public void setSlotForAVehicle(String registration, int slotId){
        parkingLotSlotAllocationMap.put(registration, slotId);
    }
    public Slot getSlotForAVehicle(String registration){
        Integer slotId = parkingLotSlotAllocationMap.get(registration);
        if(slotId == null){
            return null;
        }
        return parkingLotSlotMap.get(slotId);
    }

    public Slot removeSlotForAVehicle(int slotId){
        Slot slot = parkingLotSlotMap.remove(slotId);
        return slot;
    }

    public void removeVehicleFromParking(String registrationName){
        parkingLotSlotAllocationMap.remove(registrationName);
    }
}
