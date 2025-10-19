package decorator;
import device.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    private boolean eco = false;

    public EnergySavingDecorator(Device device) { super(device); }

    @Override
    public String name() {
        return device.name() + (eco ? " (Eco)" : "");
    }
    @Override public void operate(String command) {
        String cmd = command.trim().toLowerCase();
        switch (cmd) {
            case "eco:on"  -> {
                eco = true;
                System.out.println("Eco mode ON");
                return;
            }
            case "eco:off" -> {
                eco = false;
                System.out.println("Eco mode OFF");
                return;
            }
            case "eco:show"-> {
                System.out.println("Eco mode " + (eco?"ON":"OFF"));
                return;
            }
        }
        String c = command;
        if (eco) {
            c = cap("brightness=", c, 70);
            c = cap("volume=",    c, 70);
        }
        device.operate(c);
    }
    private String cap(String key, String cmd, int cap) {
        String low = cmd.toLowerCase();
        if (!low.startsWith(key)) return cmd;
        try {
            int v  = Integer.parseInt(cmd.substring(key.length()).trim());
            int nv = Math.min(v, cap);
            if (nv != v) System.out.println("[Eco] " + key.replace("="," ") + "limited to " + nv + "%");
            return key + nv;
        } catch (NumberFormatException e) {
            return cmd;
        }
    }
}
