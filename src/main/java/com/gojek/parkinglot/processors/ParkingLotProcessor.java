package com.gojek.parkinglot.processors;


import com.gojek.parkinglot.commands.Commands;
import com.gojek.parkinglot.exceptions.ParkingLotException;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.service.ParkingLotService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

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
            if(command == null){
                continue;
            }
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
                    Slot slot = parkingLotService.parkCar(vehicleRegistration, vehicleColor, -1);
                    if(slot != null)
                    System.out.println("Allocated slot number: " + slot.getNumber());
                    else
                        System.out.println("Sorry, parking is full");
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
                        System.out.println("Slot No.\tRegistration No.\tColor");
                        for(Slot slot1 : list){
                            if(slot1.getVehicle() != null){
                                System.out.println(slot1.getNumber() + "\t\t\t" + slot1.getVehicle().getRegistrationNumber() + "\t\t" +
                                        slot1.getVehicle().getColor());
                            }
                        }
                    }else {
                        System.out.println("Parking is full");
                    }
                    break;
                case REG_NUMBERS:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    String color = inputs[1];
                    List<String> listOfWhicleWithColor = parkingLotService.getRegistrationNumberOfCarsWithColor(color);
                    if(listOfWhicleWithColor.size() > 0){
                        for(int i = 0; i < listOfWhicleWithColor.size(); i++){
                            System.out.print(listOfWhicleWithColor.get(i));
                            if(i != listOfWhicleWithColor.size() -1)
                            System.out.print(", ");
                        }
                    }else {
                        System.out.println("There is no vehicle with color " + color);
                    }


                    break;
                case SLOT_REG:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    String reg = inputs[1];
                    Slot slot1 = parkingLotService.searchCar(reg);
                    if(slot1 != null){
                        System.out.println("\nSlot number for vehcile with registration " + reg +" is "+slot1.getNumber());
                    }else{
                        System.out.println("NOT FOUND");
                    }
                    break;
                case SLOT_COLOUR:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    String colorOfSlots = inputs[1];
                    List<Slot> slotList = parkingLotService.getSlotsOfCarsWithColor(colorOfSlots);
                    System.out.println();
                    if(slotList.size() > 0){
                        for(int i = 0; i < slotList.size(); i++){
                            System.out.print(slotList.get(i).getNumber());
                            if(i != slotList.size() -1)
                                System.out.print(", ");
                        }
                    }else {
                        System.out.println("There is no slot with color " + colorOfSlots);
                    }
                    break;
                case LEAVE:
                    if(inputs.length < 2) throw new ParkingLotException("Invalid command");
                    int slotId = Integer.parseInt(inputs[1]);
                    parkingLotService.freeSlot(-1, slotId);
//                    System.out.println("Slot number" + slotId + "is free");
                    break;
                default:
                    break;
            }
        }
    }



    public void SetService(ParkingLotService parkingLotService) {
       this.parkingLotService = parkingLotService;

    }
}
