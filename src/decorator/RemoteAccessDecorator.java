package decorator;

import device.Device;

public class RemoteAccessDecorator extends DeviceDecorator {
    public RemoteAccessDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Remote Access Active]");
        device.operate(command);
    }
}
