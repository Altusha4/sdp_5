package facade;

import device.Device;

public class HomeAutomationFacade {
    private final Device light, music, thermostat, camera;

    public HomeAutomationFacade(Device light, Device music, Device thermostat, Device camera) {
        this.light = light;
        this.music = music;
        this.thermostat = thermostat;
        this.camera = camera;
    }
    public void startPartyMode() {
        System.out.println("\n--- Party Mode ---");
        light.operate("Turn ON with dim effect");
        music.operate("Play music loudly");
    }
    public void activateNightMode() {
        System.out.println("\n--- Night Mode ---");
        light.operate("Turn OFF");
        thermostat.operate("Set to ECO mode");
        camera.operate("Enable Security");
        music.operate("Stop music");
    }
    public void leaveHome () {
        System.out.println("\n--- Leave Home Mode ---");
        light.operate("Turn OFF");
        music.operate("Stop music");
        thermostat.operate("Eco temperature");
        camera.operate("Security ON");
    }
}
