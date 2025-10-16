package app;

import facade.HomeAutomationFacade;
import factory.SmartDeviceFactory;

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade home = new HomeAutomationFacade();
        home.setLight(SmartDeviceFactory.create("light"));
        home.setThermostat(SmartDeviceFactory.create("thermostat"));
        home.setCamera(SmartDeviceFactory.create("camera"));
        home.setMusic(SmartDeviceFactory.create("music"));

        while (true) {
            System.out.println("\n--- SMART HOME MENU ---");
            System.out.println("1. Light");
            System.out.println("2. Thermostat");
            System.out.println("3. Security Camera");
            System.out.println("4. Music System");
            System.out.println("5. Movie Night");
            System.out.println("6. Shutdown All");
            System.out.println("7. Exit");

            int choice = ConsoleIO.askInt("Choose option: ");

            switch (choice) {
                case 1 -> {
                    if (home.getLight() == null) { System.out.println("Light not connected."); break; }
                    String cmd = ConsoleIO.ask("Light cmd (on/off/set/show): ");
                    home.getLight().operate(cmd);
                }
                case 2 -> {
                    if (home.getThermostat() == null) { System.out.println("Thermostat not connected."); break; }
                    String cmd = ConsoleIO.ask("Thermo cmd (set/show): ");
                    home.getThermostat().operate(cmd);
                }
                case 3 -> {
                    if (home.getCamera() == null) { System.out.println("Camera not connected."); break; }
                    String cmd = ConsoleIO.ask("Camera cmd (record:on/record:off/detect:on/detect:off/simulate/show/set): ");
                    home.getCamera().operate(cmd);
                }
                case 4 -> {
                    if (home.getMusic() == null) { System.out.println("Music not connected."); break; }
                    String cmd = ConsoleIO.ask("Music cmd (play/pause/next/source/volume/show): ");
                    home.getMusic().operate(cmd);
                }
                case 5 -> home.movieNight();
                case 6 -> home.shutdownAll();
                case 7 -> { System.out.println("Bye"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}