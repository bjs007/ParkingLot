package com.gojek.parkinglot.commands;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    private static Map<String, Integer> commandsParameterMap = new HashMap<String, Integer>();

   public Commands(){
       commandsParameterMap.put(Constants.CREATE_PARKING_LOT, 1);
       commandsParameterMap.put(Constants.PARK, 2);
       commandsParameterMap.put(Constants.LEAVE, 1);
       commandsParameterMap.put(Constants.STATUS, 0);
       commandsParameterMap.put(Constants.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
       commandsParameterMap.put(Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
       commandsParameterMap.put(Constants.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
    }

    public void addCommand(String command, int id){
        commandsParameterMap.put(command, id);
    }

    public int getCommand(String command){
       return commandsParameterMap.get(command);
    }
}
