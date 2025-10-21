package factory;
import device.*;

public class BasicDeviceFactory implements DeviceAbstractFactory {
    @Override
    public Device createLight() {
        return new Light();
    }
    @Override
    public Device createThermostat() {
        return new Thermostat();
    }
    @Override
    public Device createCamera() {
        return new SecurityCamera();
    }
    @Override
    public Device createMusic() {
        return new MusicSystem();
    }
}
