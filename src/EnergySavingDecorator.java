public class EnergySavingDecorator extends DevicceDecorator {
    public EnergySavingDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Energy Saving Mode]");
        c
    }
}
