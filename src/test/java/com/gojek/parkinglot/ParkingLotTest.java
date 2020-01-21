package com.gojek.parkinglot;

import com.gojek.parkinglot.model.ParkingLot;
import com.gojek.parkinglot.model.Slot;
import com.gojek.parkinglot.processors.ParkingLotProcessor;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.Assert;
import org.junit.*;

import java.util.List;

public class ParkingLotTest {
    static ParkingLotService parkingLotService = null;
    @Before
    public void init(){
       parkingLotService = new ParkingLotService();
    }

    @Test
    public void createParkingLot() throws Exception{
        parkingLotService.addParkingLot(6);
        ParkingLot parkingLot = parkingLotService.get(-1); // -1 default parking lot
        Assert.assertEquals( 6, parkingLot.getSize());
    }

    @Test
    public void nearestParkingLotCarAllocation() throws Exception{
        parkingLotService.addParkingLot(6);
        parkingLotService.parkCar("ABCD", "WHITE", -1);
        List<Slot> slots = parkingLotService.getSlotsOfCarsWithColor("WHITE");
        Assert.assertEquals(1, slots.get(0).getNumber());
    }

    @Test
    public void SearchSlotOfCar() throws Exception{
        parkingLotService.addParkingLot(6);
        parkingLotService.parkCar("ABCD", "WHITE", -1);
        Slot slot = parkingLotService.searchCar("ABCD");
        if(slot != null)
        Assert.assertEquals("ABCD", slot.getVehicle().getRegistrationNumber());
        else Assert.fail();
    }

    @Test
    public void checkEmptySlots() throws Exception{
        parkingLotService.addParkingLot(6);
        parkingLotService.parkCar("ABCD", "WHITE", -1);
        int emptySlots = parkingLotService.getNumberOfFreeSlot(-1);
        Assert.assertEquals(5, emptySlots);
    }
}
