package com.gojek.parkinglot.processors;


import com.gojek.parkinglot.commands.Commands;
import com.gojek.parkinglot.exceptions.ParkingLotException;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.service.BaseService;
import com.gojek.parkinglot.commands.Command;
import com.gojek.parkinglot.service.ParkingLotService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import static com.gojek.parkinglot.commands.Command.CREATE;
import static com.gojek.parkinglot.commands.Command.LEAVE;

public class ParkingLotProcessor {
    private ParkingLotService parkingLotService;
    private Commands commands;



    public void execute(String input) throws Exception{

        File file = new File(input);
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;

        while ((str = br.readLine()) != null){
            String[] inputs = str.split(" ");
            String firstArgument = inputs[0];
            final Commands command = commands.getCommand(firstArgument);
            switch (command){
                case CREATE:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    int size = Integer.parseInt(inputs[1]);
                    parkingLotService.addParkingLot(6);
                    System.out.println("Created a parking lot with " +size+" slots");
                    break;
                case PARK:
                    if(inputs.length < 3) throw new ParkingLotException("Invalid command");
                    String vehicleRegistration = inputs[1];
                    String vehicleColor = inputs[2];
                    parkingLotService.parkCar(vehicleRegistration, vehicleColor, -1);
                    Slot slot = parkingLotService.searchCar(vehicleRegistration);
                    System.out.println("Allocated slot number: " + slot.getNumber());
                    break;
                case STATUS:
                    List<Slot> list = parkingLotService.getAavilableSlot(-1);
                    int freeSlots  = 0;
                    for(Slot slot1 : list){
                        if(slot1.getVehicle() == null){
                            System.out.println("Slot number "+slot1.getNumber()+" is free");
                        }else {
                            freeSlots++;
                        }
                    }
                    if (freeSlots > 0) {
                        System.out.println("Slot No.\tRegistration No.\tColor\n");
                        for(Slot slot1 : list){
                            if(slot1.getVehicle() != null){
                                System.out.println(slot1.getNumber() + "\t" + slot1.getVehicle().getRegistrationNumber() + "\t" +
                                        slot1.getVehicle().getColor());
                            }
                        }
                    }
                case LEAVE:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    int slotId = Integer.parseInt(inputs[1]);
                    parkingLotService.freeSlot(-1, slotId);
                    System.out.println("Slot number" + slotId + "is free");

                default:
                    break;
            }
        }
    }


    public void SetService(ParkingLotService parkingLotService) {
       this.parkingLotService = parkingLotService;

    }
}
