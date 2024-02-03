package ParkingLot.src.com.systemlauncher.main.parkinglot;

import ParkingLot.src.com.systemlauncher.main.Main;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.Vehicles;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Bike;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Car;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Truck;

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

    public void parkVehicle(String vehicleType, String registrationNumber, String color){
        Vehicles[][] array = getVehiclesArray();
        boolean parked = false;

        for (int i = 0; i < floors; i++){
            for (int j = 0; j < slots && !parked; j++){
                if(isVehicleAllowed(i, j, vehicleType)){
                    if("TRUCK".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Truck(registrationNumber, color);
                    
                    } else if("BIKE".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Bike(registrationNumber, color);
                    
                    } else if("CAR".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Car(registrationNumber, color);
                    }

                String ticketId = main.getParkingLotId() + "_" + (i + 1) + "_" + (j + 1);
                System.out.println("Parked vehicle. Ticket ID:" + ticketId);
                parked = true;
                }
            }
        }

        if (!parked){
            System.out.println("Parking Lot Full");
        }
    }

    public void unparkVehicle(String ticketId){
        Vehicles[][] array = getVehiclesArray();
        boolean unparked = false;

        for (int i = 0; i < floors; i++){
            for (int j = 0; j < slots && !unparked; j++){
                Vehicles vehicle = array[i][j];
                if(vehicle != null && generateTicketId(i, j).equals(ticketId)){
                    String registrationNumber = vehicle.getRegistrationNumber();
                    String color = vehicle.getColor();

                    array[i][j] = null;              
                    System.out.println("Unparked vehicle with Registration Number: " + registrationNumber + " and Color: "+ color);
                    unparked = true;
                }
            }
        }

        if (!unparked){
            System.out.println("Invalid Ticket");
        }
    }

    public void displayFreeSlots(String vehicleType) {
        
        for (int i = 0; i < floors; i++) {
            System.out.print("Free slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            
            boolean isFirstSlot = true;
        
            for (int j = 0; j < slots; j++) {
                if (isVehicleAllowed(i, j, vehicleType)) {
                    if (!isFirstSlot) {
                        System.out.print(", ");
                    }
                    System.out.print(j + 1);
                    isFirstSlot = false;
                }
            }
    
            System.out.println();
        }
    }

    public void displayFreeCount(String vehicleType) {

        for (int i = 0; i < floors; i++) {
            int freeSlotCount = 0;

            for (int j = 0; j < slots; j++) {
                if (isVehicleAllowed(i, j, vehicleType)) {
                    freeSlotCount++;
                }
            }

            System.out.println("No. of free slots for " + vehicleType + " on Floor " + (i + 1) + ": " + freeSlotCount);
        }
    }

    public void displayOccupiedSlots(String vehicleType) {
        
        for (int i = 0; i < floors; i++) {
            System.out.print("Occupied slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            
            boolean isFirstSlot = true;
        
            for (int j = 0; j < slots; j++) {
                if (isVehicleParked(i, j, vehicleType)) {
                    if (!isFirstSlot) {
                        System.out.print(", ");
                    }
                    System.out.print(j + 1);
                    isFirstSlot = false;
                }
            }
    
            System.out.println();
        }
    }
    
    private boolean isVehicleAllowed(int floorIndex, int slotIndex, String vehicleType) {
        Vehicles[][] array = getVehiclesArray();
        if ("TRUCK".equalsIgnoreCase(vehicleType) && slotIndex == 0 && array[floorIndex][slotIndex] == null) {
            return true;
        } else if ("BIKE".equalsIgnoreCase(vehicleType) && slotIndex >= 1 && slotIndex <= 2 && array[floorIndex][slotIndex] == null) {
            return true;
        } else if ("CAR".equalsIgnoreCase(vehicleType) && slotIndex >= 3 && array[floorIndex][slotIndex] == null) {
            return true;
        }
        return false;
    }

    private boolean isVehicleParked(int floorIndex, int slotIndex, String vehicleType) {
        Vehicles[][] array = getVehiclesArray();
        if ("TRUCK".equalsIgnoreCase(vehicleType) && slotIndex == 0 && array[floorIndex][slotIndex] != null) {
            return true;
        } else if ("BIKE".equalsIgnoreCase(vehicleType) && slotIndex >= 1 && slotIndex <= 2 && array[floorIndex][slotIndex] != null) {
            return true;
        } else if ("CAR".equalsIgnoreCase(vehicleType) && slotIndex >= 3 && array[floorIndex][slotIndex] != null) {
            return true;
        }
        return false;
    }

    private String generateTicketId(int floorIndex, int slotIndex){
        return main.getParkingLotId() + "_" + (floorIndex + 1) + "_" + (slotIndex + 1);
    }

    public Vehicles[][] getVehiclesArray(){
        return vehiclesArray;
    }
}
