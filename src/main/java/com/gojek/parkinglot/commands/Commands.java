package com.gojek.parkinglot.commands;


public enum Commands{

    CREATE,PARK,LEAVE,STATUS, REG_NUMBERS, SLOT_COLOUR, SLOT_REG;

    public static Commands getCommand(String comm){
        if(Command.CREATE.equals(comm))
            return CREATE;
        else if(Command.PARK.equals(comm))
            return PARK;
        else if(Command.LEAVE.equals(comm))
            return LEAVE;
        else if(Command.STATUS.equals(comm))
            return STATUS;
        else if(Command.REG_NUMBERS.equals(comm))
            return REG_NUMBERS;
        else if(Command.SLOT_COLOUR.equals(comm))
            return SLOT_COLOUR;
        else if(Command.SLOT_REG.equals(comm))
            return SLOT_REG;
        else
            return null;

    }
}
