package com.gojek.parkinglot;

import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.service.ParkingLotService;

public class Driver {
    public static void main(String args[]){
        ParkingLotService parkingLotService = new ParkingLotService();
        parkingLotService.addParkingLot(6);
        parkingLotService.parkCar("ABCD","RED", 1);
        parkingLotService.parkCar("ABCE","RED", 1);
        Slot slot = parkingLotService.searchCar("ABCD");
        System.out.println(slot.getNumber());

        Slot slot1 = parkingLotService.getCar("ABCE");
        System.out.println(slot1.getNumber());
    }
}
