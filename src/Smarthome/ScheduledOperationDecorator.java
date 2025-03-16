package Smarthome;

import java.time.LocalTime;

class ScheduledOperationDecorator implements SmartDevice {
    private final SmartDevice device;
    private final LocalTime scheduledTime;
    private final String action;
    private boolean executed = false;

    public ScheduledOperationDecorator(SmartDevice device, String time, String action) {
        this.device = device;
        this.scheduledTime = LocalTime.parse(time);
        this.action = action;
    }

    public void checkAndExecute() {
        if (!executed && LocalTime.now().isAfter(scheduledTime)) {
            if (action.equals("on")) {
                device.turnOn();
            } else {
                device.turnOff();
            }
            executed = true;
            System.out.println("Scheduled operation executed for " + device.name());
        }
    }

    public void turnOn() {
        device.turnOn();
    }

    public void turnOff() {
        device.turnOff();
    }

    public String name() {
        return device.name();
    }
}
