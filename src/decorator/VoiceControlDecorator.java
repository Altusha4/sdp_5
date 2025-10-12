package decorator;

import device.Device;

public class VoiceControlDecorator extends DeviceDecorator {
    public VoiceControlDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Voice Control Enabled]");
        device.operate(command);
    }
}
