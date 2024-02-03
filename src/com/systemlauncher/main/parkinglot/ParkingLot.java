package ParkingLot.src.com.systemlauncher.main.parkinglot;

import ParkingLot.src.com.systemlauncher.main.Main;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.Vehicles;

public class ParkingLot {

    private int floors;
    private int slots;
    private Vehicles[][] vehiclesArray;
    private Main main;

    public void vehiclesArray(){
        floors = main.getNumberOfFloors();
        slots = main.getNumberOfSlots();
        vehiclesArray = new Vehicles[floors][slots];
    }

    public void setParkingLot(Main main) {
        this.main = main;
        this.floors = main.getNumberOfFloors();
        this.slots = main.getNumberOfSlots();
        vehiclesArray = new Vehicles[floors][slots];
    }

    public Vehicles[][] getVehiclesArray(){
        return vehiclesArray;
    }
}
