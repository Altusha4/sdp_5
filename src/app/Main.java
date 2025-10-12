package app;
import device.*;
import decorator.*;
import facade.*;

public class Main {
    public static void main(String[] args) {
        Device light = new Light();
        Device music = new MusicSystem();
        Device thermostat = new Thermostat();
        Device camera = new SecurityCamera();

        Device smartLight = new EnergySavingDecorator(new VoiceControlDecorator(light));
        Device smartMusic = new RemoteAccessDecorator(music);
        Device smartThermo = new VoiceControlDecorator(thermostat);
        Device smartCamera = new RemoteAccessDecorator(camera);

        HomeAutomationFacade home = new HomeAutomationFacade(smartLight, smartMusic, smartThermo, smartCamera);

        home.startPartyMode();
        home.activateNightMode();
        home.leaveHome();
    }
}