package com.gojek.parkinglot.model;

public class Ticket {
    private Car car;
    private Slot slot;

    public Ticket(Car car, Slot slot) {
        this.car = car;
        this.slot = slot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
