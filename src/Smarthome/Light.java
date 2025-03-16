package Smarthome;

record Light(String name) implements SmartDevice {
    public void turnOn() {
        System.out.println(name + " light is ON");
    }

    public void turnOff() {
        System.out.println(name + " light is OFF");
    }
}
