package device;
import java.time.LocalTime;
import java.util.Scanner;

public class SecurityCamera implements Device {
    boolean recording = false;
    boolean motionDetect = false;

    @Override
    public String name() {
        return "SecurityCamera";
    }

    @Override
    public void operate(String command) {
        Scanner sc = new Scanner(System.in);

        switch (command) {
            case "record:on":
                recording = true;
                System.out.println("Camera recording is ON");
                break;
            case "record:off":
                recording = false;
                System.out.println("Camera recording is OFF");
                break;
            case "detect:on":
                motionDetect = true;
                System.out.println("Motion detection is ON");
                break;
            case "detect:off":
                motionDetect = false;
                System.out.println("Motion detection is OFF");
                break;
            case "simulate":
                if (motionDetect) {
                    System.out.println("Motion detected at " + LocalTime.now() +
                            (recording ? " → Recording..." : " → Standby"));
                } else {
                    System.out.println("Motion detection is OFF. No action.");
                }
                break;
            case "set":
                System.out.print("Turn motion detection on/off (on/off/back): ");
                String md = sc.nextLine().trim().toLowerCase();
                if (md.equals("back")) {
                    System.out.println("Canceled.");
                    break;
                }
                if (md.equals("on") || md.equals("off")) {
                    motionDetect = md.equals("on");
                    System.out.println("Motion detection is " + (motionDetect ? "ON" : "OFF"));
                } else {
                    System.out.println("Invalid option.");
                    break;
                }
                System.out.print("Turn recording on/off (on/off/back): ");
                String rec = sc.nextLine().trim().toLowerCase();
                if (rec.equals("back")) {
                    System.out.println("Canceled.");
                    break;
                }
                if (rec.equals("on") || rec.equals("off")) {
                    recording = rec.equals("on");
                    System.out.println("Recording is " + (recording ? "ON" : "OFF"));
                } else {
                    System.out.println("Invalid option.");
                }
                break;
            case "show":
                System.out.println("Camera status — Recording: " + (recording ? "ON" : "OFF")
                        + ", Motion detection: " + (motionDetect ? "ON" : "OFF"));
                break;
            default:
                System.out.println("Unknown command for SecurityCamera");
        }
    }
}
