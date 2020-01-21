package com.gojek.parkinglot.service;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.storage.InMemoryStorage;

import java.util.*;

public class ParkingLotService implements BaseService{
    static int id = 1;
    static int defaultParkingLot = 1;
    static InMemoryStorage inMemoryStorage = InMemoryStorage.getInstance();

    public void addParkingLot(int size){
        inMemoryStorage.addParkingLot(id, new ParkingLot(id, size));
        id++;
    }

    public synchronized Slot parkCar(String registration, String color, int parkingLotId){
        ParkingLot parkingLot = inMemoryStorage.getParkingLot(parkingLotId == -1 ?defaultParkingLot: parkingLotId);
        if(parkingLot == null){
            System.out.println("Parking lot has not been initialized");
            System.exit(1);
        }
        Slot slot = parkingLot.getNearestAvailableSlot();
        if(slot == null){
//            System.out.println("No slot found");
            return null;
        }
        Vehicle vehicle = new Car(registration, color);
        slot.setVehicle(vehicle);
        slot.setParkingLotId(parkingLot.getId());
        parkingLot.setSlotForAVehicle(registration, slot.getNumber());
        return slot;
    }

    public Slot searchCar(String registration){
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Slot slot =  parkingLot.getSlotForAVehicle(registration);
            if( slot != null){
              //  parkingLot.removeSlotForAVehicle(slot.getNumber());
                return slot;
            }
        }

        return null;
    }

    public synchronized Slot getCar(String registration){
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Slot slot =  parkingLot.getSlotForAVehicle(registration);
            if( slot != null){
                slot.setVehicle(null);
                parkingLot.removeVehicleFromParking(registration);
                parkingLot.addNearestAvailableSlot(slot.getNumber());
                return slot;
            }
        }

        return null;
    }


    public List<Slot> getAavilableSlot(int parkingLotId){
        ParkingLot parkingLot = inMemoryStorage.getParkingLot(parkingLotId == - 1? defaultParkingLot : parkingLotId);
        Map<Integer, Slot> slotMap = parkingLot.getParkingLotSlotMap();
        List<Slot> list = new ArrayList<>();
        for(Slot sl : slotMap.values()){
            list.add(sl);
        }

        return list;
    }

    public void freeSlot(int parkingLotId, int slotId){
        ParkingLot parkingLot = inMemoryStorage.getParkingLot(parkingLotId == - 1? defaultParkingLot : parkingLotId);
        parkingLot.removeSlotForAVehicle(slotId);
    }

    public int getNumberOfFreeSlot(int parkingLotId){
        ParkingLot parkingLot = inMemoryStorage.getParkingLot(parkingLotId == - 1? defaultParkingLot : parkingLotId);
        return parkingLot.getNearestAvailableSlotInParkingLotMapper().size();
    }

    public List<String> getRegistrationNumberOfCarsWithColor(String color){
        List<String> list = new ArrayList<>();
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Map<Integer, Slot> slots = parkingLot.getParkingLotSlotMap();
            for(Slot slot : slots.values()){
                if(slot.getVehicle() != null && slot.getVehicle().getColor().equalsIgnoreCase(color)){
                    list.add(slot.getVehicle().getRegistrationNumber());
                }
            }
        }

        return list;
    }

    public List<Slot> getSlotsOfCarsWithColor(String color){
        List<Slot> list = new ArrayList<>();
        Map<Integer, ParkingLot> parkingLots = inMemoryStorage.getParkingLots();
        for(ParkingLot parkingLot : parkingLots.values()){
            Map<Integer, Slot> slots = parkingLot.getParkingLotSlotMap();
            for(Slot slot : slots.values()){
                if(slot.getVehicle() != null && slot.getVehicle().getColor().equalsIgnoreCase(color)){
                    list.add(slot);
                }
            }
        }

        return list;
    }

    public ParkingLot get(int id){
        return inMemoryStorage.getParkingLot( id == -1 ? defaultParkingLot : id);
    }
}
