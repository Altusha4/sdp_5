package decorator;

import device.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    public EnergySavingDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Energy Saving Mode]");
        device.operate(command);
    }
}
