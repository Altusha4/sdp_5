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

        switch (command) {
            case "play":
                isPlaying = true;
                System.out.println("Now Playing: " + track + " via " + source);
                break;
            case "pause":
                isPlaying = false;
                System.out.println("Music paused");
                break;
            case "next":
                System.out.print("Enter next track name (or 'back'): ");
                String next = sc.nextLine().trim();
                if (next.equalsIgnoreCase("back")) {
                    System.out.println("Cancelled");
                    break;
                }
                track = next;
                System.out.println("Now Playing: " + track);
                break;

            case "source":
                System.out.print("Enter source name (usb / blueetooth / fm) or 'back': ");
                String s = sc.nextLine().trim().toLowerCase();
                if (s.equalsIgnoreCase("back")) {
                    System.out.println("Cancelled");
                    break;
                }
                if (s.equals("usb") || s.equals("blueetooth") || s.equals("fm")) {
                    source = s;
                    System.out.println("Source set to: " + source);
                } else {
                    System.out.println("Invalid source");
                }
                break;

            case "volume":
                while (true) {
                    System.out.print("Enter volume (0-100) or 'back': ");
                    String input = sc.nextLine().trim();
                    if (input.equalsIgnoreCase("back")) {
                        System.out.println("Cancelled");
                        break;
                    }
                    try {
                        int v = Integer.parseInt(input);
                        if (v < 0 || v > 100) {
                            System.out.println("Volume must be between 0 and 100");
                            continue;
                        }
                        volume = v;
                        System.out.println("Volume set to: " + volume);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a number 0-100");
                    }
                }
                break;

            case "show":
                System.out.println("Source: " + source
                        + ", Track: " + track
                        + ", Volume: " + volume
                        + ", State: " + (isPlaying ? "Playing" : "Paused"));
                break;

            default:
                System.out.println("Unknown command for MusicSystem");
        }
    }
}
