abstract class DevicceDecorator implements Device {
    Device device;
    public DevicceDecorator(Device device) {
        this.device = device;
    }
    @Override
    public void operate(String command) {
        device.operate(command);
    }
}
