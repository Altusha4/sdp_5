package facade;
import device.Device;

public class HomeAutomationFacade {
    private Device light;
    private Device thermostat;
    private Device camera;
    private Device music;

    public void setLight(Device d) {
        this.light = d;
    }
    public void setThermostat(Device d) {
        this.thermostat = d;
    }
    public void setCamera(Device d) {
        this.camera = d;
    }
    public void setMusic(Device d) {
        this.music = d;
    }

    public Device getLight() {
        return light;
    }
    public Device getThermostat() {
        return thermostat;
    }
    public Device getCamera() {
        return camera;
    }
    public Device getMusic() {
        return music;
    }

    public void movieNight() {
        if (light == null || thermostat == null || camera == null || music == null) {
            System.out.println("Error: some devices are not connected to the system.");
            return;
        }
        System.out.println("Movie Night mode is activated!");
        light.operate("off");
        thermostat.operate("set");
        camera.operate("record:on");
        music.operate("play");
    }

    public void shutdownAll() {
        System.out.println("Shutting down all devices...");
        if (light != null)      light.operate("off");
        if (thermostat != null) thermostat.operate("show");
        if (camera != null)     camera.operate("record:off");
        if (music != null)      music.operate("pause");
    }
}
