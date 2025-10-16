package app;

import facade.HomeAutomationFacade;
import factory.SmartDeviceFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade home = new HomeAutomationFacade();
        home.setLight(SmartDeviceFactory.create("light"));
        home.setThermostat(SmartDeviceFactory.create("thermostat"));
        home.setCamera(SmartDeviceFactory.create("camera"));
        home.setMusic(SmartDeviceFactory.create("music"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- SMART HOME MENU ---");
            System.out.println("1. Light");
            System.out.println("2. Thermostat");
            System.out.println("3. Security Camera");
            System.out.println("4. Music System");
            System.out.println("5. Movie Night");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> { System.out.print("Light cmd (on/off/set/show): "); home.getLight().operate(sc.nextLine()); }
                case 2 -> { System.out.print("Thermo cmd (set/show): "); home.getThermostat().operate(sc.nextLine()); }
                case 3 -> { System.out.print("Camera cmd (record:on/record:off/detect:on/detect:off/simulate): "); home.getCamera().operate(sc.nextLine()); }
                case 4 -> { System.out.print("Music cmd (play/pause/next/source/volume/show): "); home.getMusic().operate(sc.nextLine()); }
                case 5 -> home.movieNight();
                case 6 -> { System.out.println("Bye"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}