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

        if (command.equals("play")) {
            isPlaying = true;
            System.out.println("Now Playing: " + track + " via " + source);
        }
        else if (command.equals("pause")) {
            isPlaying = false;
            System.out.println("Music paused");
        }
        else if (command.equals("next")) {
            System.out.print("Enter next track name: ");
            track =  sc.nextLine();
            System.out.println("Now Playing: " + track);
        }
        else if (command.equals("sources")) {
            System.out.print("Enter source (usb / bluetooth / fm): ");
            source = sc.nextLine();
            System.out.println("Source set to: " + source);
        }
        else if (command.equals("volume")) {
            System.out.print("Enter volume (0-100): ");
            volume = sc.nextInt();
            System.out.println("Volume set to: " + volume);
        }
        else if (command.equals("show")) {
            System.out.println("Source: " + source + ", Track: " + track + ", Volume: " + volume + ", State: " +(isPlaying ? "Playing" : "Paused"));
        }
        else {
            System.out.println("Unknown command for MusicSystem");
        }
   }
}
