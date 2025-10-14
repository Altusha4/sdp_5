package decorator;
import device.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    private Device device;

    public EnergySavingDecorator(Device d) {
        super(d);
    }

    @Override
    public String name() {
        return device.name() + " (Energy Saving)";
    }
    @Override
    public void operate(String command) {
        System.out.println("Energy-saving mode active");
        if (command.contains("brightness=100")) {
            command = command.replace("100", "70");
        }
        device.operate(command);
    }
}
