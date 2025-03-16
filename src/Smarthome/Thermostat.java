package Smarthome;

record Thermostat(String name) implements SmartDevice {
    public void turnOn() {
        System.out.println(name + " is ON");
    }

    public void turnOff() {
        System.out.println(name + " is OFF");
    }
}
