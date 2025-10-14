package decorator;
import device.Device;

public class VoiceControlDecorator implements Device {
    private final Device target;

    public VoiceControlDecorator(Device target) {
        if (target == null) throw new IllegalArgumentException("target is null");
        this.target = target;
    }

    @Override
    public String name() {
        return target.name() + " (Voice)";
    }

    @Override
    public void operate(String command) {
        System.out.println("Voice command: " + command);
        target.operate(map(command));
    }

    private String map(String s) {
        s = s.toLowerCase();
        if (s.contains("turn on"))  return "on";
        if (s.contains("turn off")) return "off";
        if (s.startsWith("play"))   return "play";
        if (s.startsWith("pause"))  return "pause";
        if (s.startsWith("next"))   return "next";
        if (s.startsWith("set"))    return "set";
        if (s.startsWith("source")) return "source";
        if (s.startsWith("volume")) return "volume";
        return s;
    }
}
