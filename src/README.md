# Smart Home System
Overview:
This Java console project demonstrates 3 design patterns:
1) Factory - creates devices dynamically
2) Decorator - adds extra features (Eco mode, Voice Control, Remote access)
3) Facade - manages all devices via ine simplified interface
Packages:
app/ -> Console interaction (Main, ConsoleIO)
device/ -> Base interface and devices
decorator/ -> VoiceControl, EnergySaving, RemoteAccess
facade/ -> HomeAutomationFacade
factory/ -> DeviceFactory, SmartFactory

# Main Features
1) Light
Commands: on/off/set/show/help
Extra Feature: Eco mode limits brightness <= 70%
2) MusicSystem
Commands: play/pause/next/src/vol/show/help
Extra Feature: Eco mode limits volume <= 70%
3) Thermostat
Commands: on/off/set/show/help
Extra Feature: Mode: heating/colling/auto
4) SecurityCamera
Commands: record:on/off,detect:on/off,simulate
Remote access decorator