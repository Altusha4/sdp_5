package app;
import facade.HomeAutomationFacade;
import factory.*;
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
                case 1 -> controlLight(home, homeName);
                case 2 -> controlThermostat(home);
                case 3 -> controlCamera(home);
                case 4 -> controlMusic(home, homeName);
                case 5 -> home.movieNight();
                case 6 -> home.shutdownAll();
                case 7 -> {
                    System.out.println("Bye!");
                    ConsoleIO.close();
                    return;
                }
                case 8 -> voiceControlMode(home, homeName);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private static void controlLight(HomeAutomationFacade home, String homeName) {
        if (home.getLight() == null) {
            System.out.println("Light not connected.");
            return;
        }
        while (true) {
            String cmd = ConsoleIO.ask("\n[Light] (on/off/set/show/help/back): ").trim().toLowerCase();

            if (cmd.startsWith(homeName.toLowerCase())) {
                cmd = cmd.substring(homeName.length()).trim();
                if (cmd.contains("turn on")) cmd = "on";
                if (cmd.contains("turn off")) cmd = "off";
                System.out.println("Voice command: " + cmd);
            }

            if (cmd.equals("back")) break;

            if (cmd.equals("set")) {
                handleLightSettings(home);
                continue;
            }
            home.getLight().operate(cmd);
        }
    }

    private static void handleLightSettings(HomeAutomationFacade home) {
        String bStr = ConsoleIO.ask("Enter brightness 0..100 (or 'back'): ").trim().toLowerCase();
        if (bStr.equals("back")) {
            System.out.println("Canceled.");
            return;
        }
        try {
            int b = Integer.parseInt(bStr);
            home.getLight().operate("brightness=" + b);
        } catch (NumberFormatException e) {
            System.out.println("Enter a number 0..100.");
            return;
        }

        String c = ConsoleIO.ask("Enter color (white, warm, blue, red, green) or 'back': ").trim().toLowerCase();
        if (c.equals("back")) {
            System.out.println("Canceled.");
            return;
        }
        home.getLight().operate("color=" + c);
    }

    private static void controlThermostat(HomeAutomationFacade home) {
        if (home.getThermostat() == null) {
            System.out.println("Thermostat not connected.");
            return;
        }
        while (true) {
            String cmd = ConsoleIO.ask("\n[Thermostat] (on/off/set/show/help/back): ").toLowerCase(Locale.ROOT);
            if (cmd.equals("back")) break;
            home.getThermostat().operate(cmd);
        }
    }

    private static void controlCamera(HomeAutomationFacade home) {
        if (home.getCamera() == null) {
            System.out.println("Camera not connected.");
            return;
        }
        while (true) {
            System.out.println("\n[Camera cmds] ron | roff | don | doff | sim | show | ? | back");
            String cmd = ConsoleIO.ask("Camera> ").toLowerCase(Locale.ROOT);
            if (cmd.equals("back")) break;
            if (cmd.equals("?")) {
                home.getCamera().operate("help");
                continue;
            }
            cmd = switch (cmd) {
                case "ron" -> "record:on";
                case "roff" -> "record:off";
                case "don" -> "detect:on";
                case "doff" -> "detect:off";
                case "sim" -> "simulate";
                default -> cmd;
            };
            home.getCamera().operate(cmd);
        }
    }

    private static void controlMusic(HomeAutomationFacade home, String homeName) {
        if (home.getMusic() == null) {
            System.out.println("Music not connected.");
            return;
        }
        while (true) {
            String cmd = ConsoleIO.ask("\n[Music] (play/pause/next/src/vol/show/help/back): ").trim().toLowerCase();

            if (cmd.startsWith(homeName.toLowerCase())) {
                cmd = cmd.substring(homeName.length()).trim();
                if (cmd.contains("turn on") || cmd.contains("play")) cmd = "play";
                if (cmd.contains("turn off") || cmd.contains("pause")) cmd = "pause";
                System.out.println("Voice command: " + cmd);
            }

            if (cmd.equals("back")) break;
            if (cmd.equals("?")) cmd = "help";
            if (cmd.equals("src")) cmd = "source";

            if (cmd.equals("vol") || cmd.equals("volume")) {
                int v = ConsoleIO.askInt("Enter volume (0–100): ");
                home.getMusic().operate("volume=" + v);
                continue;
            }
            home.getMusic().operate(cmd);
        }
    }

    private static void voiceControlMode(HomeAutomationFacade home, String homeName) {
        System.out.println("\n--- Voice Mode Active ---");
        System.out.println("Say: \"" + homeName + ", turn off light\"  | type 'exit' to leave.");

        while (true) {
            String voice = ConsoleIO.ask("> ").trim().toLowerCase();
            if (voice.equals("exit")) break;

            if (!voice.startsWith(homeName.toLowerCase())) {
                System.out.println("Please address me by name: " + homeName);
                continue;
            }

            String cmd = voice.substring(homeName.length()).trim();

            boolean toLight = cmd.contains("light");
            boolean toMusic = cmd.contains("music");
            boolean toCam = cmd.contains("camera");
            boolean toTherm = cmd.contains("thermostat");

            String action = parseVoiceCommand(cmd);

            System.out.println("Voice command: " + action);

            if (toLight && home.getLight() != null) home.getLight().operate(action);
            else if (toMusic && home.getMusic() != null) home.getMusic().operate(action);
            else if (toCam && home.getCamera() != null) home.getCamera().operate(action);
            else if (toTherm && home.getThermostat() != null) home.getThermostat().operate(action);
            else System.out.println("I didn't understand the command.");
        }
    }

    private static String parseVoiceCommand(String cmd) {
        if (cmd.contains("turn on")) return "on";
        if (cmd.contains("turn off")) return "off";
        if (cmd.startsWith("play")) return "play";
        if (cmd.startsWith("pause")) return "pause";
        if (cmd.startsWith("next")) return "next";
        if (cmd.contains("source")) return "source";
        if (cmd.matches("volume\\s+\\d+")) return "volume=" + cmd.replaceAll("\\D+", "");
        if (cmd.matches("brightness\\s+\\d+")) return "brightness=" + cmd.replaceAll("\\D+", "");
        if (cmd.startsWith("set")) return "set";
        if (cmd.contains("eco on")) return "eco:on";
        if (cmd.contains("eco off")) return "eco:off";
        return cmd;
    }
}
