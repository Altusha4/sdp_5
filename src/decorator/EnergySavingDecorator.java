package decorator;

import device.Device;

public class EnergySavingDecorator implements Device {
    private final Device target;
    public EnergySavingDecorator(Device target) {
        if (target == null) throw new IllegalArgumentException("target is null");
        this.target = target;
    }
    @Override public String name(){ return target.name() + " (Eco)"; }
    @Override public void operate(String command){
        if (command.contains("brightness=100")) command = command.replace("100","70");
        target.operate(command);
    }
}
