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
        switch (command.trim().toLowerCase()) {
            case "record:on":
                recording = true;
                ConsoleIO.println("Camera recording is ON");
                break;
            case "record:off":
                recording = false;
                ConsoleIO.println("Camera recording is OFF");
                break;
            case "detect:on":
                motionDetect = true;
                ConsoleIO.println("Motion detection is ON");
                break;
            case "detect:off":
                motionDetect = false;
                ConsoleIO.println("Motion detection is OFF");
                break;
            case "simulate":
                simulateMotion();
                break;
            case "set":
                configureSettings();
                break;
            case "show":
                showStatus();
                break;
            case "help":
                showHelp();
                break;
            default:
                ConsoleIO.println("Unknown command for SecurityCamera. Type 'help'");
        }
    }
    private void simulateMotion() {
        if (motionDetect) {
            ConsoleIO.println("Motion detected at " + LocalTime.now() +
                    (recording ? " → Recording..." : " → Standby"));
        } else {
            ConsoleIO.println("Motion detection is OFF. No action.");
        }
    }
    private void configureSettings() {
        String md = ConsoleIO.ask("Turn motion detection on/off (on/off/back): ").toLowerCase();
        if (md.equals("back")) {
            ConsoleIO.println("Canceled.");
            return;
        }
        motionDetect = md.equals("on");
        ConsoleIO.println("Motion detection is " + (motionDetect ? "ON" : "OFF"));

        String rec = ConsoleIO.ask("Turn recording on/off (on/off/back): ").toLowerCase();
        if (rec.equals("back")) {
            ConsoleIO.println("Canceled.");
            return;
        }
        recording = rec.equals("on");
        ConsoleIO.println("Recording is " + (recording ? "ON" : "OFF"));
    }
    private void showStatus() {
        ConsoleIO.println("Camera status — Recording: " + (recording ? "ON" : "OFF") +
                ", Motion detection: " + (motionDetect ? "ON" : "OFF"));
    }
    private void showHelp() {
        ConsoleIO.println("""
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
    }
}
