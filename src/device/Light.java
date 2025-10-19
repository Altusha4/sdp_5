package device;
import app.ConsoleIO;

public class Light implements Device {
    int brightness = 50;
    String color = "white";
    boolean isOn = false;
    boolean eco = false;

    private static final String[] ALLOWED_COLORS = {"white", "warm", "blue", "red", "green"};
    @Override
    public String name() {
        return "Light" + (eco ? " (Eco)" : "");
    }
    private boolean isAllowedColor(String c) {
        for (String s : ALLOWED_COLORS) if (s.equalsIgnoreCase(c)) return true;
        return false;
    }
    @Override
    public void operate(String command) {
        if (command.equalsIgnoreCase("eco:on")) {
            eco = true;
            ConsoleIO.println("Eco mode ON");
            return;
        }
        if (command.equalsIgnoreCase("eco:off")) {
            eco = false;
            ConsoleIO.println("Eco mode OFF");
            return; }
        if (command.equalsIgnoreCase("eco:show")) {
            ConsoleIO.println("Eco mode " + (eco?"ON":"OFF"));
            return;
        }
        if (command.startsWith("brightness=")) {
            String val = command.substring("brightness=".length()).trim();
            try {
                int b = Integer.parseInt(val);
                if (b < 0 || b > 100) {
                    ConsoleIO.println("Brightness must be between 0 and 100.");
                } else {
                    if (eco && b > 70) {
                        b = 70;
                        ConsoleIO.println("[Eco] Brightness limited to 70%");
                    }
                    brightness = b;
                    ConsoleIO.println("Light set to " + brightness + "% and color " + color);
                }
            } catch (NumberFormatException e) {
                ConsoleIO.println("Invalid number.");
            }
            return;
        }
        switch (command) {
            case "on" -> {
                isOn = true;
                ConsoleIO.println("Light is ON");
            }
            case "off" -> {
                isOn = false;
                ConsoleIO.println("Light is OFF");
            }
            case "set" -> {
                String ecoAns = ConsoleIO.ask("Enable Eco mode? (yes/no/back): ").trim().toLowerCase();
                if (ecoAns.equals("back")) {
                    ConsoleIO.println("Canceled.");
                    return;
                }
                if (ecoAns.equals("yes")) {
                    eco = true;
                    ConsoleIO.println("Eco mode ON");
                }
                else if (ecoAns.equals("no")) {
                    eco = false;
                    ConsoleIO.println("Eco mode OFF");
                }
                else {
                    ConsoleIO.println("Invalid option. Keeping previous: " + (eco?"ON":"OFF"));
                }

                while (true) {
                    String in = ConsoleIO.ask("Enter brightness 0..100 (or 'back'): ");
                    if (in.equalsIgnoreCase("back")) {
                        ConsoleIO.println("Canceled.");
                        return;
                    }
                    try {
                        int b = Integer.parseInt(in);
                        if (b < 0 || b > 100) {
                            ConsoleIO.println("Brightness must be between 0 and 100.");
                            continue;
                        }
                        if (eco && b > 70) {
                            b = 70; ConsoleIO.println("[Eco] Brightness limited to 70%");
                        }
                        brightness = b;
                        break;
                    } catch (NumberFormatException e) {
                        ConsoleIO.println("Enter a number 0..100.");
                    }
                }
                while (true) {
                    String c = ConsoleIO.ask("Enter color (white, warm, blue, red, green) or 'back': ");
                    if (c.equalsIgnoreCase("back")) {
                        ConsoleIO.println("Canceled.");
                        return;
                    }
                    if (!isAllowedColor(c)) {
                        ConsoleIO.println("No such color. Try again.");
                        continue;
                    }
                    color = c.toLowerCase();
                    break;
                }
                ConsoleIO.println("Light set to " + brightness + "% and color " + color);
            }
            case "show" -> ConsoleIO.println("Light is " + (isOn ? "ON" : "OFF")
                    + ", Brightness: " + brightness + "%, Color: " + color
                    + ", Eco: " + (eco ? "ON" : "OFF"));
            case "help" -> ConsoleIO.println("""
                Commands for Light:
                  on/off        - power
                  set           - interactive setup with Eco question
                  brightness=NN - direct brightness (0..100; capped at 70 in Eco)
                  eco:on|off    - toggle Eco mode
                  eco:show      - show Eco state
                  show          - status
                  help          - this help
                """);
            default -> ConsoleIO.println("Unknown command for Light. Type 'help'");
        }
    }
}
