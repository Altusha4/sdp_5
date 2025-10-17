package device;
import app.ConsoleIO;
import java.time.LocalTime;

public class SecurityCamera implements Device {
    boolean recording = false;
    boolean motionDetect = false;

    @Override
    public String name() {
        return "SecurityCamera";
    }
    @Override
    public void operate(String command) {
        switch (command) {
            case "record:on" -> {
                recording = true;
                System.out.println("Camera recording is ON");
            }
            case "record:off" -> {
                recording = false;
                System.out.println("Camera recording is OFF");
            }
            case "detect:on" -> {
                motionDetect = true;
                System.out.println("Motion detection is ON");
            }
            case "detect:off" -> {
                motionDetect = false;
                System.out.println("Motion detection is OFF");
            }
            case "simulate" -> {
                if (motionDetect) {
                    System.out.println("Motion detected at " + LocalTime.now() +
                            (recording ? " → Recording..." : " → Standby"));
                } else {
                    System.out.println("Motion detection is OFF. No action.");
                }
            }
            case "set" -> {
                String md = ConsoleIO.ask("Turn motion detection on/off (on/off/back): ").toLowerCase();
                if (md.equals("back")) {
                    System.out.println("Canceled.");
                    return;
                }
                if (md.equals("on") || md.equals("off")) {
                    motionDetect = md.equals("on");
                    System.out.println("Motion detection is " + (motionDetect ? "ON" : "OFF"));
                } else {
                    System.out.println("Invalid option.");
                    return;
                }

                String rec = ConsoleIO.ask("Turn recording on/off (on/off/back): ").toLowerCase();
                if (rec.equals("back")) {
                    System.out.println("Canceled.");
                    return;
                }
                if (rec.equals("on") || rec.equals("off")) {
                    recording = rec.equals("on");
                    System.out.println("Recording is " + (recording ? "ON" : "OFF"));
                } else {
                    System.out.println("Invalid option.");
                }
            }
            case "show" -> System.out.println("Camera status — Recording: " + (recording ? "ON" : "OFF")
                    + ", Motion detection: " + (motionDetect ? "ON" : "OFF"));
            case "help" -> System.out.println("""
                Commands for SecurityCamera:
                  record:on    - enable recording
                  record:off   - disable recording
                  detect:on    - enable motion detection
                  detect:off   - disable motion detection
                  simulate     - simulate motion event
                  set          - interactive toggle detection/recording
                  show         - show status
                  help         - show this help
                """);
            default -> System.out.println("Unknown command for SecurityCamera. Type 'help'");
        }
    }
}
