package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.storage.InMemoryStorage;
import com.gojek.parkinglot.storage.InMemoryStorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLotService {
    static int id = 1;
    static int defaultParkingLot = 1;
    static InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();

    public void addParkingLot(int size){
        inMemoryStorage.addParkingLot(id, new ParkingLot(size));
        id++;
    }

    public void parkCar(String registration, String color, int parkingLotId){
        ParkingLot parkingLot = inMemoryStorage.getParkingLot(defaultParkingLot);
        Slot slot = parkingLot.getNearestAvailableSlot();
        if(slot == null){
            System.out.println("No slot found");
            return;
        }
        Vehicle vehicle = new Car(registration, color);
        slot.setVehicle(vehicle);
        slot.setParkingLotId(parkingLot.getId());
        parkingLot.setSlotForAVehicle(registration, slot.getNumber());

    }

    public Slot searchCar(String registration){
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Slot slot =  parkingLot.getSlotForAVehicle(registration);
            if( slot != null){
                System.out.println(slot.getNumber());
              //  parkingLot.removeSlotForAVehicle(slot.getNumber());
                return slot;
            }
        }

        return null;
    }

    public Slot getCar(String registration){
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Slot slot =  parkingLot.getSlotForAVehicle(registration);
            if( slot != null){
                slot.setVehicle(null);
                parkingLot.removeVehicleFromParking(registration);
                return slot;
            }
        }

        return null;
    }
}
