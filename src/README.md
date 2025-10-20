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

# How the project was built
1) Define core abstraction
Create minimal Device interface to unify all devices: name() and operate(String command) 
or similar methods
2) Implement concrete devices
Light, Thermostat, SecurityCamera, MusicSystem. Each class holds its own state and implements
command handling
3) Add a simple CLI
Implement Main with a loop over Scanner input. Show a main menu and sub-menus
per device (on/off/show/help/back)
4) Introduce Facade 
Add HomeAutomationFacade to combine multi-step flows suc as a Movie Night or Shutdown All.
The CLI calls the facade instead of scripting device steps directly
5) Apply Decorator to extend behavior
Create wrappers like EnergySavingDecorator, RemoteAccessDecorator, VoiceControlDecorator that
add cross-cutting features without modifying original device classes
6) Add Factory to centralize creation
Introduce a SmartDeviceFactory that builds devices in a consistent way. Provide concrete factories 
for different configurations (e.g., default, energy‑saving, remote‑enabled). Wire the CLI and the 
facade to request devices from the factory
7) Wire everything in Main
Construct a concrete factory, create devices through it, optionally wrap them with decorators, 
and pass them to the facade and menus. Keep dependencies one‑directional (UI → Facade/Factory → Devices)
8) Manual test & refine
Verify device commands, scene flows, and error handling. Adjust help texts and menu navigation

# What the app does
* Devices: Light, Thermostat, SecurityCamera, MusicSystem
* Scenes (via Facade): Movie Night, Shutdown All
* Decorators: energy saving, remote access, basic voice/alias control
* Factory: consistent creation of devices and preconfigured variants
* Terminal UI: navigable menu for all operations

# Design patterns
## Facade
Intent: Provide a simple API for complex subsystems
Here: HomeAutomationFacade exposes high‑level methods:
* startMovieNight() might: dim Light, set Thermostat to comfy temperature, 
arm SecurityCamera detection, start MusicSystem or media
* shutdownAll() turns devices off safely in the right order
Why it helps: The CLI calls one method instead of coordinating many device calls. 
It hides sequencing and error handling.
Typical responsibilities in this project:
* Keep references to all devices.
* Offer named scenarios as single methods.
* Contain no UI logic and no low‑level device details.

## Decorator
Intent: Add responsibilities to objects dynamically without altering their classes.
Here: Decorators wrap devices implementing the same interface and extend behavior.
Examples used:
* EnergySavingDecorator(Light)
* May clamp max brightness, auto‑reduce levels, or block wasteful states.
* RemoteAccessDecorator(Device)
* Simulates authorization/logging before forwarding commands.
* VoiceControlDecorator(Device)
* Provides alias mapping or voice‑style triggers before normalization.
Why it helps: New cross‑cutting features compose at runtime per device. You avoid inheritance explosion and keep base devices simple.
Key mechanics in code:
* Constructor takes a Device to wrap.
* Override operate(cmd) → do extra work → delegate to wrapped device.
* State added by the decorator stays local to the wrapper.

## Factory
Intent: Encapsulate object creation to keep clients independent from concrete classes and to produce families of related objects
Concrete factories (examples):
* DefaultDeviceFactory — returns plain devices.
* EnergySavingDeviceFactory — returns devices already wrapped with EnergySavingDecorator where applicable (e.g., Light).
* RemoteEnabledDeviceFactory — returns devices wrapped with RemoteAccessDecorator to simulate remote features.
Why it helps:
* Centralizes construction logic in one place.
* Lets you switch product families without touching the CLI/facade.
* Keeps Main thin and testable: pass a different factory to change behavior.
Usage in this project:
* Main selects a concrete factory based on a flag or mode.
* The factory builds devices. Decorators may be applied inside the factory, so callers receive fully configured instances.
* The facade receives factory‑created devices and orchestrates them.