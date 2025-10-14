package decorator;
import device.Device;

public class VoiceControlDecorator extends DeviceDecorator {
    private Device device;

    public VoiceControlDecorator(Device d) {
        super(d);
    }

    @Override
    public String name() {
        return device.name() + " (Voice Control)";
    }

    @Override
    public void operate(String command) {
        System.out.println("Voice command: " + command);
        String simpleCommand = convert(command);
        device.operate(simpleCommand);
    }

    private String convert(String speech) {
        speech = speech.toLowerCase();
        if (speech.contains("turn on")) return "on";
        if (speech.contains("turn off")) return "off";
        if (speech.contains("set")) return "set";
        return speech;
    }
}
