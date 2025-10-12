public class Thermostat implements Device{
    @Override
    public void operate(String command) {
        System.out.println("Thermostat: " + command);
    }
}
