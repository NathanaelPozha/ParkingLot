package ParkingLot.src.com.systemlauncher.main;

import java.util.*;

import ParkingLot.src.com.systemlauncher.main.parkinglot.ParkingLot;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.Vehicles;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Bike;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Car;
import ParkingLot.src.com.systemlauncher.main.parkinglot.vehicles.vehiclestype.Truck;

public class Main {
    
Scanner scan = new Scanner(System.in);
ParkingLot launch = new ParkingLot();


private String createCommand;
private String parkingLotId;
private int numberOfFloors;
private int numberOfSlots;
private boolean isfinish = false;

    public void startSystem() {

        System.out.println("");
        System.out.println("Parking System by Nathanael Pozha");

        System.out.println("Please input the desired value for the parking lot you intend to create");
        createCommand = scan.next();
        parkingLotId = scan.next();
        numberOfFloors = scan.nextInt();
        numberOfSlots = scan.nextInt();
        scan.nextLine();

        while (!"create_parking_lot".equalsIgnoreCase(createCommand)){
            System.out.println("Error: Invalid command");
            System.out.println("Please enter a valid command (create_parking_lot)");
            
            createCommand = scan.next();
            parkingLotId = scan.next();
            numberOfFloors = scan.nextInt();
            numberOfSlots = scan.nextInt();
            scan.nextLine();
        }

        launch.setParkingLot(this);
        launch.vehiclesArray();

        System.out.println("Created parking lot with " + numberOfFloors + " floors and " + numberOfSlots + " slots per floor");

        while (!isfinish) {
            System.out.println("");
            System.out.println("Plese input your next command:");
            String inputLine = scan.nextLine();
            String[] commands = inputLine.split(" ");

            if (commands.length > 0){
                String mainCommand = commands[0];

                if("display".equalsIgnoreCase(mainCommand) && commands.length >= 2){
                    String displayType = commands[1];
                    String vehicleType = commands[2];

                    if ("free_slots".equalsIgnoreCase(displayType)){
                        displayFreeSlots(vehicleType);

                    } else if ("free_count".equalsIgnoreCase(displayType)){
                        displayFreeCount(vehicleType);

                    } else if ("occupied_slots".equalsIgnoreCase(displayType)){
                        displayOccupiedSlots(vehicleType);
                    }

                } else if("park_vehicle".equalsIgnoreCase(mainCommand) && commands.length >= 4){
                    String vehicleType = commands[1];
                    String registrationNumber = commands[2];
                    String color = commands[3];
                    
                    parkVehicle(vehicleType, registrationNumber, color);

                } else if("unpark_vehicle".equalsIgnoreCase(mainCommand) && commands.length >= 2){
                    String ticketId = commands[1];

                    unparkVehicle(ticketId);
                    
                } else if("Exit".equalsIgnoreCase(mainCommand)){
                    isfinish = true;
                
                } else {
                    System.out.println("Error: Invalid command");
                    System.out.println("Please enter a valid command (display, park_vehicle, unpark_vehicle)");
                }
            }
        }
    }

    private void parkVehicle(String vehicleType, String registrationNumber, String color){
        Vehicles[][] array = launch.getVehiclesArray();
        boolean parked = false;

        for (int i = 0; i < numberOfFloors; i++){
            for (int j = 0; j < numberOfSlots && !parked; j++){
                if(isVehicleTypeAllowed(i, j, vehicleType)){
                    if("TRUCK".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Truck(registrationNumber, color);
                    
                    } else if("BIKE".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Bike(registrationNumber, color);
                    
                    } else if("CAR".equalsIgnoreCase(vehicleType)){
                        array[i][j] = new Car(registrationNumber, color);
                    }

                String ticketId = parkingLotId + "_" + (i + 1) + "_" + (j + 1);
                System.out.println("Parked vehicle. Ticket ID:" + ticketId);
                parked = true;
                }
            }
        }

        if (!parked){
            System.out.println("Parking Lot Full");
        }
    }

    private void unparkVehicle(String ticketId){
        Vehicles[][] array = launch.getVehiclesArray();
        boolean unparked = false;

        for (int i = 0; i < numberOfFloors; i++){
            for (int j = 0; j < numberOfSlots && !unparked; j++){
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
            System.out.println("Parking Lot Full");
        }
    }

    private void displayFreeSlots(String vehicleType) {
        
        for (int i = 0; i < numberOfFloors; i++) {
            System.out.print("Free slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            
            boolean isFirstSlot = true;
        
            for (int j = 0; j < numberOfSlots; j++) {
                if (isVehicleTypeAllowed(i, j, vehicleType)) {
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

    private void displayFreeCount(String vehicleType) {

        for (int i = 0; i < numberOfFloors; i++) {
            int freeSlotCount = 0;

            for (int j = 0; j < numberOfSlots; j++) {
                if (isVehicleTypeAllowed(i, j, vehicleType)) {
                    freeSlotCount++;
                }
            }

            System.out.println("No. of free slots for " + vehicleType + " on Floor " + (i + 1) + ": " + freeSlotCount);
        }
    }

    private void displayOccupiedSlots(String vehicleType) {
        
        for (int i = 0; i < numberOfFloors; i++) {
            System.out.print("Occupied slots for " + vehicleType + " on Floor " + (i + 1) + ": ");
            
            boolean isFirstSlot = true;
        
            for (int j = 0; j < numberOfSlots; j++) {
                if (!isVehicleTypeAllowed(i, j, vehicleType)) {
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
    
    private boolean isVehicleTypeAllowed(int floorIndex, int slotIndex, String vehicleType) {
        Vehicles[][] array = launch.getVehiclesArray();
        if ("TRUCK".equalsIgnoreCase(vehicleType) && slotIndex == 0 && array[floorIndex][slotIndex] == null) {
            return true;
        } else if ("BIKE".equalsIgnoreCase(vehicleType) && slotIndex >= 1 && slotIndex <= 2 && array[floorIndex][slotIndex] == null) {
            return true;
        } else if ("CAR".equalsIgnoreCase(vehicleType) && slotIndex >= 3 && array[floorIndex][slotIndex] == null) {
            return true;
        }
        return false;
    }

    private String generateTicketId(int floorIndex, int slotIndex){
        return parkingLotId + "_" + (floorIndex + 1) + "_" + (slotIndex + 1);
    }
    
    public int getNumberOfSlots(){
        return numberOfSlots;
    }

    public int getNumberOfFloors(){
        return numberOfFloors;
    }
}
