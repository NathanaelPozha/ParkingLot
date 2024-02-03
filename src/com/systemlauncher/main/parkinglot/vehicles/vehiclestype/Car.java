package ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype;

import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.Vehicles;

public class Car extends Vehicles {

    String registrationNumber;
    String color;

    public Car(String registrationNumber, String color){
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    
    public String getColor(){
        return color;
    }
}
