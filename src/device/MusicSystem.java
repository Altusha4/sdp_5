package device;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

        switch (command.toLowerCase()) {
            case "play" -> {
                isPlaying = true;
                System.out.println("Now playing: " + track + " via " + source);
            }
            case "pause" -> {
                isPlaying = false;
                System.out.println("Music paused");
            }
            case "next" -> {
                System.out.print("Enter next track name: ");
                track = sc.nextLine();
                System.out.println("Now playing: " + track);
            }
            case "src", "source" -> {
                System.out.print("Enter source (usb / bluetooth / fm): ");
                source = sc.nextLine().toLowerCase();
                if (!source.equals("usb") && !source.equals("bluetooth") && !source.equals("fm")) {
                    System.out.println("Unknown source. Keeping previous: " + this.source);
                } else {
                    System.out.println("Source set to: " + source);
                }
            }
            case "vol", "volume" -> {
                System.out.print("Enter volume (0–100): ");
                try {
                    int v = Integer.parseInt(sc.nextLine());
                    if (v < 0 || v > 100) {
                        System.out.println("Volume out of range. Keeping previous: " + volume);
                    } else {
                        volume = v;
                        System.out.println("Volume set to: " + volume);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Try again.");
                }
            }
            case "show" -> System.out.println("Source: " + source +
                    " | Track: " + track +
                    " | Volume: " + volume +
                    " | State: " + (isPlaying ? "Playing" : "Paused"));
            case "help", "?" -> System.out.println("""
                Commands for Music System:
                  play   - start playing current track
                  pause  - pause music
                  next   - enter next track
                  src    - set source (usb/bluetooth/fm)
                  vol    - set volume 0–100
                  show   - show status
                  help/? - show this help
                """);
            default -> System.out.println("Unknown command. Type 'help' or '?' for available options.");
        }
    }
}
