public class RemoteAccessDecorator extends DevicceDecorator {
    public RemoteAccessDecorator(Device d) {
        super(d);
    }
    @Override
    public void operate(String command) {
        System.out.println("[Remote Access Active]");
        device.operate(command);
    }
}
