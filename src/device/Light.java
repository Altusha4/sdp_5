package device;
import app.ConsoleIO;

public class Light implements Device {
    int brightness = 50;
    String color = "white";
    boolean isOn = false;

    private static final String[] ALLOWED_COLORS = {"white", "warm", "blue", "red", "green"};
    @Override
    public String name() {
        return "Light";
    }
    private boolean isAllowedColor(String c) {
        for (String s : ALLOWED_COLORS) {
            if (s.equalsIgnoreCase(c)) return true;
        }
        return false;
    }
    @Override
    public void operate(String command) {
        if (command.startsWith("brightness=")) {
            String val = command.substring("brightness=".length()).trim();
            try {
                int b = Integer.parseInt(val);
                if (b < 0 || b > 100) {
                    System.out.println("Brightness must be between 0 and 100.");
                } else {
                    brightness = b;
                    System.out.println("Light set to " + brightness + "% and color " + color);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
            return;
        }
        switch (command) {
            case "on" -> {
                isOn = true;
                System.out.println("Light is ON");
            }
            case "off" -> {
                isOn = false;
                System.out.println("Light is OFF");
            }
            case "set" -> {
                while (true) {
                    String in = ConsoleIO.ask("Enter brightness 0..100 (or 'back'): ");
                    if (in.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    try {
                        int b = Integer.parseInt(in);
                        if (b < 0 || b > 100) {
                            System.out.println("Brightness must be between 0 and 100.");
                            continue;
                        }
                        brightness = b;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a number 0..100.");
                    }
                }
                while (true) {
                    String c = ConsoleIO.ask("Enter color (white, warm, blue, red, green) or 'back': ");
                    if (c.equalsIgnoreCase("back")) {
                        System.out.println("Canceled.");
                        return;
                    }
                    if (!isAllowedColor(c)) {
                        System.out.println("No such color. Try again.");
                        continue;
                    }
                    color = c.toLowerCase();
                    break;
                }
                System.out.println("Light set to " + brightness + "% and color " + color);
            }
            case "show" -> System.out.println("Light is " + (isOn ? "ON" : "OFF")
                    + ", Brightness: " + brightness + "%, Color: " + color);
            case "help" -> System.out.println("""
                Commands for Light:
                  on           - turn on
                  off          - turn off
                  set          - set brightness and color
                  show         - show status
                  help         - show this help
                """);
            default -> System.out.println("Unknown command for Light. Type 'help'");
        }
    }
}
