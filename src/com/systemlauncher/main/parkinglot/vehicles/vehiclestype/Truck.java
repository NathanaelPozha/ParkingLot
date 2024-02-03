package ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype;

import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.Vehicles;

public class Truck extends Vehicles {

    String registrationNumber;
    String color;

    public Truck(String registrationNumber, String color){
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public String getRegistrationNumber(){
        return registrationNumber;
    }
    
    @Override
    public String getColor(){
        return color;
    }
}