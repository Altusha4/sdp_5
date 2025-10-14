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

        if (command.equals("record:on")) {
            recording = true;
            System.out.println("Camera recording is ON");
        }
        else if (command.equals("record:off")) {
            recording = false;
            System.out.println("Camera recording is OFF");
        }
        else if (command.equals("detect:on")) {
            motionDetect = true;
            System.out.println("Motion detecting is ON");
        }
        else if (command.equals("detect:off")) {
            motionDetect = false;
            System.out.println("Motion detecting is OFF");
        }
        else if (command.equals("simulate")) {
            if(motionDetect) {
                System.out.println("Motion detecting at" + LocalTime.now() + (recording ? " -> Recording..." : " -> Stanby"));
            } else {
                System.out.println("Motion delection is OFF. No action");
            }
        }
        else {
            System.out.println("Unknown command for Camera");
        }
    }
}
