package decorator;
import device.Device;

public class RemoteAccessDecorator implements Device {
    private final Device target;
    public RemoteAccessDecorator(Device target) {
        if (target == null) throw new IllegalArgumentException("target is null");
        this.target = target;
    }
    @Override public String name(){ return target.name() + " (Remote)"; }
    @Override public void operate(String command){
        System.out.println("Sending remotely: " + command);
        target.operate(command);
    }
}
