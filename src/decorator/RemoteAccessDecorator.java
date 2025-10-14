package decorator;
import device.Device;

public class RemoteAccessDecorator extends DeviceDecorator {
    private Device device;

    public RemoteAccessDecorator(Device d) {
        super(d);
    }

    @Override
    public String name() {
        return device.name() + " (Remote)";
    }

    @Override
    public void operate(String command) {
        System.out.println("Sending command remotely: " + command);
        device.operate(command);
    }
}
