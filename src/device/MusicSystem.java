package device;
import app.ConsoleIO;

public class MusicSystem implements Device {
    String source = "bluetooth";
    String track = "Shape of You";
    int volume = 40;
    boolean isPlaying = false;
    boolean eco = false;

    @Override
    public String name() {
        return "MusicSystem" + (eco ? " (Eco)" : "");
    }

    @Override
    public void operate(String command) {
        String cmd = command.trim().toLowerCase();
        if (cmd.equals("eco:on"))  {
            eco = true;
            ConsoleIO.println("Eco mode ON");
            return;
        }
        if (cmd.equals("eco:off")) {
            eco = false;
            ConsoleIO.println("Eco mode OFF");
            return;
        }
        if (cmd.equals("eco:show")) {
            ConsoleIO.println("Eco is " + (eco ? "ON" : "OFF"));
            return;
        }
        if (cmd.startsWith("volume=")) {
            setVolumeSafe(parseInt(command.substring(7).trim()));
            ConsoleIO.println("Volume set to: " + volume);
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
                setVolumeSafe(v);
                ConsoleIO.println("Volume set to: " + volume);
            }
            case "show" -> ConsoleIO.println(
                    "Source: " + source +
                            " | Track: " + track +
                            " | Volume: " + volume +
                            " | State: " + (isPlaying ? "Playing" : "Paused") +
                            " | Eco: " + (eco ? "ON" : "OFF"));
            case "help", "?" -> ConsoleIO.println("""
                Commands for Music System:
                  play          - start playing current track
                  pause         - pause music
                  next          - enter next track
                  src/source    - set source (usb/bluetooth/fm)
                  vol/volume    - set volume 0–100 (capped 70 in Eco)
                  volume=NN     - direct set volume (works with Voice)
                  eco:on|off    - toggle Eco mode
                  eco:show      - show Eco state
                  show          - show status
                  help/?        - this help
                """);
            default -> ConsoleIO.println("Unknown command. Type 'help' or '?' for options.");
        }
    }
    private void setVolumeSafe(int v) {
        if (v < 0 || v > 100) { ConsoleIO.println("Volume out of range 0–100."); return; }
        if (eco && v > 70) { ConsoleIO.println("[Eco] Volume limited to 70%"); v = 70; }
        volume = v;
    }
    private int parseInt(String s) { try { return Integer.parseInt(s); } catch (Exception e) { return -1; } }
}
