package factory;

import device.*;
import decorator.*;

public class SmartDeviceFactory {
    public static Device create(String type) {
        switch (type.toLowerCase()) {
            case "light"      -> { return new VoiceControlDecorator(new EnergySavingDecorator(new Light())); }
            case "thermostat" -> { return new Thermostat(); }
            case "camera"     -> { return new RemoteAccessDecorator(new SecurityCamera()); }
            case "music"      -> { return new VoiceControlDecorator(new MusicSystem()); }
            default -> { System.out.println("Unknown device type: " + type); return null; }
        }
    }
}
