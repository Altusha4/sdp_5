public class Light implements Device {
    @Override
    public void operate(String command) {
        System.out.println("Light: " + command);
    }
}
