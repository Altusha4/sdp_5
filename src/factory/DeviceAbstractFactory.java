package factory;
import device.Device;

public interface DeviceAbstractFactory {
    Device createLight();
    Device createThermostat();
    Device createCamera();
    Device createMusic();
}
