package decorator;
import device.Device;

abstract class DeviceDecorator implements Device {
    protected final Device device;
    public DeviceDecorator(Device device) {
        this.device = device;
    }
    @Override
    public String name() {
        return device.name();
    }
    @Override
    public void operate(String command) {
        device.operate(command);
    }
}
