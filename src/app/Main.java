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
            System.out.println("\n--- SMART HOME ---");
            System.out.println("1) Light  2) Thermostat  3) Camera  4) Music");
            System.out.println("5) Movie Night  6) Shutdown All  7) Exit");

            int choice = ConsoleIO.askInt("Choose: ");
            switch (choice) {
                case 1 -> {
                    if (home.getLight() == null) { System.out.println("Light not connected."); break; }
                    while (true) {
                        String cmd = ConsoleIO.ask("\n[Light] (on/off/set/show/help/back): ").toLowerCase();
                        if (cmd.equals("back")) break;
                        home.getLight().operate(cmd);
                    }
                }
                case 2 -> {
                    if (home.getThermostat() == null) { System.out.println("Thermostat not connected."); break; }
                    while (true) {
                        String cmd = ConsoleIO.ask("\n[Thermostat] (on/off/set/show/help/back): ").toLowerCase();
                        if (cmd.equals("back")) break;
                        home.getThermostat().operate(cmd);
                    }
                }
                case 3 -> {
                    if (home.getCamera() == null) { System.out.println("Camera not connected."); break; }
                    while (true) {
                        System.out.println("\n[Camera cmds] ron | roff | don | doff | sim | show | ? | back");
                        String cmd = ConsoleIO.ask("Camera> ").toLowerCase();
                        if (cmd.equals("back")) break;
                        if (cmd.equals("?")) { home.getCamera().operate("help"); continue; }
                        switch (cmd) {
                            case "ron"  -> cmd = "record:on";
                            case "roff" -> cmd = "record:off";
                            case "don"  -> cmd = "detect:on";
                            case "doff" -> cmd = "detect:off";
                            case "sim"  -> cmd = "simulate";
                        }
                        home.getCamera().operate(cmd);
                    }
                }
                case 4 -> {
                    if (home.getMusic() == null) { System.out.println("Music not connected."); break; }
                    while (true) {
                        System.out.println("\n[Music cmds] play | pause | next | src | vol | show | ? | back");
                        String cmd = ConsoleIO.ask("Music> ").toLowerCase();
                        if (cmd.equals("back")) break;
                        if (cmd.equals("?")) { home.getMusic().operate("help"); continue; }
                        if (cmd.equals("src")) cmd = "source";
                        if (cmd.equals("vol")) cmd = "volume";
                        home.getMusic().operate(cmd);
                    }
                }
                case 5 -> home.movieNight();
                case 6 -> home.shutdownAll();
                case 7 -> { System.out.println("Bye"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}