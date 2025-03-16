package Smarthome;

import java.util.ArrayList;
import java.util.List;

class SmartRoom {
    private final String name;
    private final List<SmartDevice> devices = new ArrayList<>();

    public SmartRoom(String name) {
        this.name = name;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void turnOn() {
        devices.forEach(SmartDevice::turnOn);
    }

    public void turnOff() {
        devices.forEach(SmartDevice::turnOff);
    }

    public String getName() {
        return name;
    }

    public SmartDevice getDevice(String deviceName) {
        return devices.stream().filter(d -> d.name().equalsIgnoreCase(deviceName)).findFirst().orElse(null);
    }
}
