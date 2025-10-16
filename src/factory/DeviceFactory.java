package factory;
import device.*;

public class DeviceFactory {
    public static Device createDevice(String type) {
        if (type == null) {
            System.out.println("Error: device type is null.");
            return null;
        }
        switch (type.toLowerCase()) {
            case "light":
                return new Light();
            case "thermostat":
                return new Thermostat();
            case "camera":
                return new SecurityCamera();
            case "music":
                return new MusicSystem();
            default:
                System.out.println("Unknown device type: " + type);
                return null;
        }
    }
}
