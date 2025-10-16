package decorator;
import device.Device;

public class RemoteAccessDecorator extends DeviceDecorator {

    public RemoteAccessDecorator(Device device) {
        super(device);
    }

    @Override
    public String name() {
        return device.name() + " (Remote)";
    }

    @Override
    public void operate(String command) {
        System.out.println("Sending remotely: " + command);
        device.operate(command);
    }
}
