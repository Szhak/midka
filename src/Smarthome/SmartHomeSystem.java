package Smarthome;

import java.util.ArrayList;
import java.util.List;

class SmartHomeSystem {
    private final List<SmartRoom> rooms;
    private final List<ScheduledOperationDecorator> schedules = new ArrayList<>();

    public SmartHomeSystem(List<SmartRoom> rooms) {
        this.rooms = rooms;
        startScheduler();
    }

    public SmartRoom getRoom(String roomName) {
        return rooms.stream().filter(r -> r.getName().equalsIgnoreCase(roomName)).findFirst().orElse(null);
    }

    public void turnOnAll() {
        rooms.forEach(SmartRoom::turnOn);
    }

    public void turnOffAll() {
        rooms.forEach(SmartRoom::turnOff);
    }

    public void scheduleTask(ScheduledOperationDecorator task) {
        schedules.add(task);
    }

    private void startScheduler() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    schedules.forEach(ScheduledOperationDecorator::checkAndExecute);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
