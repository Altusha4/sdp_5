public class HomeAutomationFacade {
    Device light, music, thermostat, camera;

    public HomeAutomationFacade(Device light, Device music, Device thermostat, Device camera) {
        this.light = light;
        this.music = music;
        this.thermostat = thermostat;
        this.camera = camera;
    }
}
