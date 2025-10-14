package app;

import device.*;
import decorator.*;
import facade.*;
import factory.DeviceFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Light light = (Light) DeviceFactory.createDevice("light");
        Thermostat thermostat = (Thermostat) DeviceFactory.createDevice("thermostat");
        SecurityCamera camera = (SecurityCamera) DeviceFactory.createDevice("securityCamera");
        MusicSystem music = (MusicSystem) DeviceFactory.createDevice("music");

        HomeAutomationFacade home = new HomeAutomationFacade();
        home.setLight((Light) DeviceFactory.createDevice("light"));
        home.setThermostat((Thermostat) DeviceFactory.createDevice("thermostat"));
        home.setCamera((SecurityCamera) DeviceFactory.createDevice("camera"));
        home.setMusic((MusicSystem) DeviceFactory.createDevice("music"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SMART HOME MENU ---");
            System.out.println("1. Light");
            System.out.println("2. Thermostat");
            System.out.println("3. Security Camera");
            System.out.println("4. Music System");
            System.out.println("5. Movie Night Mode");
            System.out.println("6. Party Mode");
            System.out.println("7. Night Mode");
            System.out.println("8. Leave Home");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Light Control ---");
                    System.out.print("Enter command (on / off / set / show): ");
                    String cmd = sc.nextLine();
                    home.getLight().operate(cmd);
                }
                case 2 -> {
                    System.out.println("\n--- Thermostat Control ---");
                    System.out.print("Enter command (set / show): ");
                    String cmd = sc.nextLine();
                    home.getThermostat().operate(cmd);
                }
                case 3 -> {
                    System.out.println("\n--- Security Camera Control ---");
                    System.out.print("Enter command (record:on / record:off / detect:on / detect:off / simulate): ");
                    String cmd = sc.nextLine();
                    home.getCamera().operate(cmd);
                }
                case 4 -> {
                    System.out.println("\n--- Music System Control ---");
                    System.out.print("Enter command (play / pause / next / source / volume / show): ");
                    String cmd = sc.nextLine();
                    home.getMusic().operate(cmd);
                }
                case 5 -> home.movieNight();
                case 6 -> home.startPartyMode();
                case 7 -> home.activateNightMode();
                case 8 -> home.leaveHome();
                case 9 -> {
                    System.out.println("Exiting Smart Home... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}