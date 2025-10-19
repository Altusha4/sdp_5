package app;

import facade.HomeAutomationFacade;
import factory.SmartDeviceFactory;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade home = new HomeAutomationFacade();
        home.setLight(SmartDeviceFactory.create("light"));
        home.setThermostat(SmartDeviceFactory.create("thermostat"));
        home.setCamera(SmartDeviceFactory.create("camera"));
        home.setMusic(SmartDeviceFactory.create("music"));

        String homeName = ConsoleIO.ask("Give your smart home a name: ").trim();
        System.out.println("Nice! Your home is now called: " + homeName);

        while (true) {
            System.out.println("\n--- SMART HOME ---");
            System.out.println("1) Light  2) Thermostat  3) Camera  4) Music");
            System.out.println("5) Movie Night  6) Shutdown All  7) Exit  8) Voice Control Mode");

            int choice = ConsoleIO.askInt("Choose: ");
            switch (choice) {
                case 1 -> {
                    if (home.getLight() == null) { System.out.println("Light not connected."); break; }
                    while (true) {
                        String cmd = ConsoleIO.ask("\n[Light] (on/off/set/show/help/back): ")
                                .trim().toLowerCase();

                        if (cmd.startsWith(homeName.toLowerCase())) {
                            cmd = cmd.substring(homeName.length())
                                    .replaceFirst("^[,\\s]+", "").trim();
                            if (cmd.contains("turn on"))  cmd = "on";
                            if (cmd.contains("turn off")) cmd = "off";
                            System.out.println("Voice command: " + cmd);
                        }

                        if (cmd.equals("back"))
                            break;

                        if (cmd.equals("set")) {
                            String bStr = ConsoleIO.ask("Enter brightness 0..100 (or 'back'): ").trim().toLowerCase();
                            if (bStr.equals("back")) { System.out.println("Canceled.");
                                continue;
                            }
                            try {
                                int b = Integer.parseInt(bStr);
                                home.getLight().operate("brightness=" + b);
                            } catch (NumberFormatException e) {
                                System.out.println("Enter a number 0..100.");
                                continue;
                            }

                            String c = ConsoleIO.ask("Enter color (white, warm, blue, red, green) or 'back': ")
                                    .trim().toLowerCase();
                            if (c.equals("back")) { System.out.println("Canceled.");
                                continue;
                            }
                            home.getLight().operate("color=" + c);
                            continue;
                        }
                        home.getLight().operate(cmd);
                    }
                }
                case 2 -> {
                    if (home.getThermostat() == null) {
                        System.out.println("Thermostat not connected.");
                        break;
                    }
                    while (true) {
                        String cmd = ConsoleIO.ask("\n[Thermostat] (on/off/set/show/help/back): ")
                                .toLowerCase(Locale.ROOT);
                        if (cmd.equals("back")) break;
                        home.getThermostat().operate(cmd);
                    }
                }
                case 3 -> {
                    if (home.getCamera() == null) { System.out.println("Camera not connected."); break; }
                    while (true) {
                        System.out.println("\n[Camera cmds] ron | roff | don | doff | sim | show | ? | back");
                        String cmd = ConsoleIO.ask("Camera> ").toLowerCase(Locale.ROOT);
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
                        String cmd = ConsoleIO.ask("\n[Music] (play/pause/next/src/vol/show/help/back): ")
                                .trim().toLowerCase();

                        if (cmd.startsWith(homeName.toLowerCase())) {
                            cmd = cmd.substring(homeName.length())
                                    .replaceFirst("^[,\\s]+", "")
                                    .trim();
                            if (cmd.contains("turn on") || cmd.contains("play"))  cmd = "play";
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
                case 5 -> home.movieNight();
                case 6 -> home.shutdownAll();
                case 7 -> {
                    System.out.println("Bye!");
                    ConsoleIO.close();
                    return;
                }
                case 8 -> {
                    System.out.println("\n--- Voice Mode Active ---");
                    System.out.println("Say: \"" + homeName + ", turn off light\"  | type 'exit' to leave.");

                    while (true) {
                        String voice = ConsoleIO.ask("> ").trim().toLowerCase();
                        if (voice.equals("exit"))
                            break;

                        String name = homeName.toLowerCase();
                        if (!voice.startsWith(name)) {
                            System.out.println("Please address me by name: " + homeName);
                            continue;
                        }

                        String cmd = voice.substring(name.length()).replaceFirst("^[,\\s]+", "").trim();

                        boolean toLight = cmd.contains("light");
                        boolean toMusic = cmd.contains("music");
                        boolean toCam   = cmd.contains("camera");
                        boolean toTherm = cmd.contains("thermostat");

                        String action = cmd;
                        if (action.contains("turn on"))       action = "on";
                        else if (action.contains("turn off")) action = "off";
                        else if (action.startsWith("play"))   action = "play";
                        else if (action.startsWith("pause"))  action = "pause";
                        else if (action.startsWith("next"))   action = "next";
                        else if (action.contains("source"))   action = "source";
                        else if (action.matches("volume\\s+\\d+"))
                            action = "volume=" + action.replaceAll("\\D+", "");
                        else if (action.matches("brightness\\s+\\d+"))
                            action = "brightness=" + action.replaceAll("\\D+", "");
                        else if (action.startsWith("set"))    action = "set";
                        else if (action.contains("eco on"))   action = "eco:on";
                        else if (action.contains("eco off"))  action = "eco:off";

                        System.out.println("Voice command: " + action);

                        if (toLight && home.getLight()!=null)             home.getLight().operate(action);
                        else if (toMusic && home.getMusic()!=null)        home.getMusic().operate(action);
                        else if (toCam && home.getCamera()!=null)         home.getCamera().operate(action);
                        else if (toTherm && home.getThermostat()!=null)   home.getThermostat().operate(action);
                        else System.out.println("I didn't understand the command.");
                    }
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}