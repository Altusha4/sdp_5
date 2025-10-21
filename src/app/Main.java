package app;
import facade.HomeAutomationFacade;
import factory.*;
import device.Device;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade home = new HomeAutomationFacade();
        DeviceAbstractFactory factory = new SmartDeviceFactory();
        home.setLight(factory.createLight());
        home.setThermostat(factory.createThermostat());
        home.setCamera(factory.createCamera());
        home.setMusic(factory.createMusic());

        String homeName = ConsoleIO.ask("Give your smart home a name: ").trim();
        System.out.println("Nice! Your home is now called: " + homeName);

        while (true) {
            System.out.println("\n--- SMART HOME ---");
            System.out.println("1) Light  2) Thermostat  3) Camera  4) Music");
            System.out.println("5) Movie Night  6) Shutdown All  7) Exit  8) Voice Control Mode");

            int choice = ConsoleIO.askInt("Choose: ");
            switch (choice) {
                case 1 -> {
                    if (!checkDeviceConnected(home.getLight(), "Light")) break;
                    processDeviceCommands(home.getLight(), "Light");
                }
                case 2 -> {
                    if (!checkDeviceConnected(home.getThermostat(), "Thermostat")) break;
                    processDeviceCommands(home.getThermostat(), "Thermostat");
                }
                case 3 -> {
                    if (!checkDeviceConnected(home.getCamera(), "Camera")) break;
                    processDeviceCommands(home.getCamera(), "Camera");
                }
                case 4 -> {
                    if (!checkDeviceConnected(home.getMusic(), "Music")) break;
                    processDeviceCommands(home.getMusic(), "Music");
                }
                case 5 -> home.movieNight();
                case 6 -> home.shutdownAll();
                case 7 -> {
                    System.out.println("Bye!");
                    ConsoleIO.close();
                    return;
                }
                case 8 -> processVoiceControl(homeName, home);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private static boolean checkDeviceConnected(Device device, String deviceName) {
        if (device == null) {
            System.out.println(deviceName + " not connected.");
            return false;
        }
        return true;
    }
    private static void processDeviceCommands(Device device, String deviceName) {
        while (true) {
            String cmd = ConsoleIO.ask("\n[" + deviceName + "] (on/off/set/show/help/back): ").trim().toLowerCase();
            if (cmd.equals("back")) break;
            device.operate(cmd);
        }
    }
    private static void processVoiceControl(String homeName, HomeAutomationFacade home) {
        System.out.println("\n--- Voice Mode Active ---");
        System.out.println("Say: \"" + homeName + ", turn off light\"  | type 'exit' to leave.");

        while (true) {
            String voice = ConsoleIO.ask("> ").trim().toLowerCase();
            if (voice.equals("exit")) break;

            if (!voice.startsWith(homeName.toLowerCase())) {
                System.out.println("Please address me by name: " + homeName);
                continue;
            }

            String action = voice.substring(homeName.length()).replaceFirst("^[,\\s]+", "").trim();
            processVoiceCommand(action, home);
        }
    }
    private static void processVoiceCommand(String action, HomeAutomationFacade home) {
        String cmd = action;
        if (cmd.contains("turn on")) cmd = "on";
        else if (cmd.contains("turn off")) cmd = "off";
        else if (cmd.startsWith("play")) cmd = "play";
        else if (cmd.startsWith("pause")) cmd = "pause";
        else if (cmd.startsWith("next")) cmd = "next";
        else if (cmd.contains("source")) cmd = "source";
        else if (cmd.matches("volume\\s+\\d+"))
            cmd = "volume=" + cmd.replaceAll("\\D+", "");
        else if (cmd.matches("brightness\\s+\\d+"))
            cmd = "brightness=" + cmd.replaceAll("\\D+", "");
        else if (cmd.startsWith("set")) cmd = "set";
        else if (cmd.contains("eco on")) cmd = "eco:on";
        else if (cmd.contains("eco off")) cmd = "eco:off";

        System.out.println("Voice command: " + cmd);

        if (cmd.contains("light") && home.getLight() != null) home.getLight().operate(cmd);
        else if (cmd.contains("music") && home.getMusic() != null) home.getMusic().operate(cmd);
        else if (cmd.contains("camera") && home.getCamera() != null) home.getCamera().operate(cmd);
        else if (cmd.contains("thermostat") && home.getThermostat() != null) home.getThermostat().operate(cmd);
        else System.out.println("I didn't understand the command.");
    }
}
