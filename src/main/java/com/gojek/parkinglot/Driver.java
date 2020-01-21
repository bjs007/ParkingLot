package com.gojek.parkinglot;

import com.gojek.parkinglot.processors.ParkingLotProcessor;
import com.gojek.parkinglot.service.ParkingLotService;

public class Driver {
    public static void main(String args[]) throws Exception {
        ParkingLotProcessor parkingLotProcessor = new ParkingLotProcessor();
        parkingLotProcessor.SetService(new ParkingLotService());
        if(args[0] == null){
            System.out.println("Please provide input file path");
            System.exit(1);
        }
        parkingLotProcessor.execute(args[0]);
    }
}
