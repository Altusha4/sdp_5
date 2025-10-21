package factory;
import device.*;
import decorator.*;

public class SmartDeviceFactory implements DeviceAbstractFactory {
    public Device createLight() {
        return new VoiceControlDecorator(new EnergySavingDecorator(new Light()));
    }
    public Device createThermostat() {
        return new VoiceControlDecorator(new Thermostat());
    }
    public Device createCamera() {
        return new VoiceControlDecorator(new RemoteAccessDecorator(new SecurityCamera()));
    }
    public Device createMusic() {
        return new VoiceControlDecorator(new EnergySavingDecorator(new MusicSystem()));
    }
}