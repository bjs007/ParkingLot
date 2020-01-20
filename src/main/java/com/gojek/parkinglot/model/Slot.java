package com.gojek.parkinglot.model;

public class Slot {
    private int parkingLotId;
    private int number;
    Vehicle vehicle;
    private int distance;

    public Slot(int number, int distance) {
        this.number = number;
        this.distance = distance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
