public class VoiceControlDecorator extends DevicceDecorator {
    public VoiceControlDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Voice Control Enabled]");
        device.operate(command);
    }
}
