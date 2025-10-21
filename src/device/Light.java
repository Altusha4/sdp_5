package device;
import app.ConsoleIO;

public class Light implements Device {
    int brightness = 50;
    String color = "white";
    boolean isOn = false;
    private static final String[] ALLOWED_COLORS = {"white","warm","blue","red","green"};

    @Override
    public String name() {
        return "Light";
    }
    private boolean isAllowedColor(String c) {
        for (String s : ALLOWED_COLORS) if (s.equalsIgnoreCase(c)) return true;
        return false;
    }
    @Override
    public void operate(String command) {
        String cmd = command.trim().toLowerCase();

        if (cmd.startsWith("brightness=")) {
            try {
                int b = Integer.parseInt(command.substring("brightness=".length()).trim());
                if (b < 0 || b > 100) {
                    ConsoleIO.println("Brightness must be 0..100.");
                } else {
                    brightness = b;
                    ConsoleIO.println("Light set to " + brightness + "% and color " + color);
                }
            } catch (Exception e) {
                ConsoleIO.println("Invalid number.");
            }
            return;
        }
        if (cmd.startsWith("color=")) {
            String c = command.substring("color=".length()).trim().toLowerCase();
            if (!isAllowedColor(c)) {
                ConsoleIO.println("No such color.");
            } else {
                color = c;
                ConsoleIO.println("Color set to: " + color);
            }
            return;
        }

        switch (cmd) {
            case "on":
                isOn = true;
                ConsoleIO.println("Light is ON");
                break;
            case "off":
                isOn = false;
                ConsoleIO.println("Light is OFF");
                break;
            case "set":
                ConsoleIO.println("Use: brightness=NN and color=white|warm|blue|red|green");
                break;
            case "show":
                ConsoleIO.println("Light is " + (isOn ? "ON" : "OFF")
                    + ", Brightness: " + brightness + "%, Color: " + color);
                break;
            case "help":
                ConsoleIO.println("""
                Commands for Light:
                  on           - turn on
                  off          - turn off
                  set          - setup brightness and color
                  show         - show status
                  eco:on/off   - on/off eco mode
                """);
                break;
            default:
                ConsoleIO.println("Unknown command for Light. Type 'help'");
        }
    }
}
