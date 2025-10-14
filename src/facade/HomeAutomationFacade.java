package facade;

import device.Device;

public class HomeAutomationFacade {
    private Device light, thermostat, camera, music;

    public void setLight(Device d){
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

    public Device getLight(){ return light; }
    public Device getThermostat(){ return thermostat; }
    public Device getCamera(){ return camera; }
    public Device getMusic(){ return music; }

    public void movieNight(){
        System.out.println("Movie Night mode is activated!");
        light.operate("on");
        thermostat.operate("set");
        camera.operate("record:on");
        music.operate("play");
    }
}
