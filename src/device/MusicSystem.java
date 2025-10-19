package device;
import app.ConsoleIO;

public class MusicSystem implements Device {
    String source = "bluetooth";
    String track = "Shape of You";
    int volume = 40;
    boolean isPlaying = false;

    @Override
    public String name() {
        return "MusicSystem";
    }
    @Override
    public void operate(String command) {
        String cmd = command.trim().toLowerCase();
        if (cmd.startsWith("volume=")) {
            try {
                int v = Integer.parseInt(command.substring("volume=".length()).trim());
                if (v < 0 || v > 100) {
                    ConsoleIO.println("Volume out of range 0–100.");
                } else {
                    volume = v;
                    ConsoleIO.println("Volume set to: " + volume);
                }
            } catch (Exception e) {
                ConsoleIO.println("Invalid number.");
            }
            return;
        }
        switch (cmd) {
            case "play" -> {
                isPlaying = true;
                ConsoleIO.println("Now playing: " + track + " via " + source);
            }
            case "pause" -> {
                isPlaying = false;
                ConsoleIO.println("Music paused");
            }
            case "next" -> {
                track = ConsoleIO.ask("Enter next track name: ");
                ConsoleIO.println("Now playing: " + track);
            }
            case "src", "source" -> {
                String newSource = ConsoleIO.ask("Enter source (usb / bluetooth / fm): ").toLowerCase();
                if (!newSource.equals("usb") && !newSource.equals("bluetooth") && !newSource.equals("fm")) {
                    ConsoleIO.println("Unknown source. Keeping previous: " + source);
                } else {
                    source = newSource;
                    ConsoleIO.println("Source set to: " + source);
                }
            }
            case "vol", "volume" -> {
                int v = ConsoleIO.askInt("Enter volume (0–100): ");
                if (v < 0 || v > 100) {
                    ConsoleIO.println("Volume out of range 0–100.");
                } else {
                    volume = v;
                    ConsoleIO.println("Volume set to: " + volume);
                }
            }
            case "show" -> ConsoleIO.println(
                    "Source: " + source +
                            " | Track: " + track +
                            " | Volume: " + volume +
                            " | State: " + (isPlaying ? "Playing" : "Paused"));
            case "help","?" -> ConsoleIO.println("""
                Commands for Music System:
                  play          - start playing current track
                  pause         - pause music
                  next          - enter next track
                  src/source    - set source (usb/bluetooth/fm)
                  vol/volume    - set volume 0–100 
                  volume=NN     - direct set volume 
                  eco:on|off    - toggle Eco mode
                  eco:show      - show Eco state
                  show          - show status
                  help/?        - this help
                """);
            default -> ConsoleIO.println("Unknown command. Type 'help' or '?'.");
        }
    }
}
