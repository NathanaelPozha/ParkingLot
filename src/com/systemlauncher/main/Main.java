package ParkingLot.src.com.systemlauncher.main;

import java.util.*;

import ParkingLot.src.com.systemlauncher.main.parkinglot.ParkingLot;

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
                        launch.displayFreeSlots(vehicleType);

                    } else if ("free_count".equalsIgnoreCase(displayType)){
                        launch.displayFreeCount(vehicleType);

                    } else if ("occupied_slots".equalsIgnoreCase(displayType)){
                        launch.displayOccupiedSlots(vehicleType);
                    }

                } else if("park_vehicle".equalsIgnoreCase(mainCommand) && commands.length >= 4){
                    String vehicleType = commands[1];
                    String registrationNumber = commands[2];
                    String color = commands[3];
                    
                    launch.parkVehicle(vehicleType, registrationNumber, color);

                } else if("unpark_vehicle".equalsIgnoreCase(mainCommand) && commands.length >= 2){
                    String ticketId = commands[1];

                    launch.unparkVehicle(ticketId);
                    
                } else if("Exit".equalsIgnoreCase(mainCommand)){
                    isfinish = true;
                
                } else {
                    System.out.println("Error: Invalid command");
                    System.out.println("Please enter a valid command (display, park_vehicle, unpark_vehicle)");
                }
            }
        }
    }
    
    public int getNumberOfSlots(){
        return numberOfSlots;
    }

    public int getNumberOfFloors(){
        return numberOfFloors;
    }

    public String getParkingLotId(){
        return parkingLotId;
    }
}
