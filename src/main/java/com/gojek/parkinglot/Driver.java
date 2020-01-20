package com.gojek.parkinglot;

import com.gojek.parkinglot.exceptions.ParkingLotException;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.processors.ParkingLotProcessor;
import com.gojek.parkinglot.service.ParkingLotService;

import java.util.Scanner;

public class Driver {
    public static void main(String args[]) throws Exception {
        ParkingLotProcessor parkingLotProcessor = new ParkingLotProcessor();
        parkingLotProcessor.SetService(new ParkingLotService());
        parkingLotProcessor.execute(args[0]);
    }
}
