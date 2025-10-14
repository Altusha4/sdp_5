package facade;
import device.*;

public class HomeAutomationFacade {
    Light light;
    Thermostat thermostat;
    SecurityCamera camera;
    MusicSystem music;

    public HomeAutomationFacade() {
        light = new Light();
        thermostat = new Thermostat();
        camera = new SecurityCamera();
        music = new MusicSystem();
    }
    public void movieNight() {
        System.out.println("Movie Night mode is activated!");
        light.operate("on");
        light.operate("set");
        thermostat.operate("set");
        camera.operate("record:on");
        music.operate("play");
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
    public Light getLight() {
        return light;
    }
    public Thermostat getThermostat() {
        return thermostat;
    }
    public SecurityCamera getCamera() {
        return camera;
    }
    public MusicSystem getMusic() {
        return music;
    }
}
