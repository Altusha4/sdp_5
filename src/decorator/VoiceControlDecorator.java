package decorator;
import device.Device;

public class VoiceControlDecorator extends DeviceDecorator {
    public VoiceControlDecorator(Device device) {
        super(device);
    }
    @Override
    public String name() {
        return device.name() + " (Voice)";
    }
    @Override
    public void operate(String command) {
        device.operate(map(command));
    }
    private String map(String s) {
        s = s.toLowerCase().trim();
        s = s.replaceFirst("^[,.:;\\s]+", "").replace(" the ", " ").trim();

        if (s.contains("turn on") || s.equals("on"))  return "on";
        if (s.contains("turn off") || s.equals("off")) return "off";
        if (s.startsWith("play"))   return "play";
        if (s.startsWith("pause"))  return "pause";
        if (s.startsWith("next"))   return "next";
        if (s.startsWith("set"))    return "set";
        if (s.startsWith("source")) return "source";
        if (s.startsWith("volume")) return "volume";
        return s;
    }
}
