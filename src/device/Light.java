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
    @Override
    public void operate(String command) {
        String cmd = command.trim().toLowerCase();

        if (cmd.startsWith("brightness=")) {
            handleBrightnes(cmd);
            return;
        }
        if (cmd.startsWith("color=")) {
            handleColor(cmd);
            return;
        }

        switch (cmd) {
            case "on":
                isOn = true;
                ConsoleIO.println("Light is ON");
            case "off":
                isOn = false;
                ConsoleIO.println("Light is OFF");
                break;
            case "set":
                ConsoleIO.println("Use: brightness=NN and color=white|warm|blue|red|green");
                break;
            case "show":
                showStatus();
            case "help":
                showHelp();
            default:
                ConsoleIO.println("Unknown command for Light. Type 'help'");
        }
    }
    private void handleBrightnes(String cmd) {
        try {
            int b = Integer.parseInt(cmd.substring("brightness=".length()).trim());
            if (b < 0 || b > 100) {
                ConsoleIO.println("Brightness must be 0..100");
            } else {
                brightness = b;
                ConsoleIO.println("Light set to " + brightness + "% and color " + color);
            }
        } catch (Exception e) {
            ConsoleIO.println("Invalid number");
        }
    }
    private void handleColor(String cmd) {
        String c = cmd.substring("color=".length()).trim().toLowerCase();
        if (!isAllowedColor(c)) {
            ConsoleIO.println("No such color.");
        } else {
            color = c;
            ConsoleIO.println("Color set to: " + color);
        }
    }
    private boolean isAllowedColor(String c) {
        for (String s : ALLOWED_COLORS) {
            if (s.equalsIgnoreCase(c)) return true;
        }
        return false;
    }
    private void showStatus() {
        ConsoleIO.println("Light is " + (isOn ? "ON" : "OFF")
                + ", Brightness: " + brightness + "%, Color: " + color);
    }
    private void showHelp() {
        ConsoleIO.println("""
                Commands for Light:
                  on           - turn on
                  off          - turn off
                  set          - setup brightness and color
                  show         - show status
                  eco:on/off   - on/off eco mode
                """);
    }
}
