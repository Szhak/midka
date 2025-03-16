package Smarthome;
import java.util.*;

public class SmartHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartRoom livingRoom = new SmartRoom("Living Room");
        livingRoom.addDevice(new Light("Light"));
        livingRoom.addDevice(new Thermostat("Thermostat"));

        SmartRoom kitchen = new SmartRoom("Kitchen");
        kitchen.addDevice(new Light("Light"));

        SmartHomeSystem smartHome = new SmartHomeSystem(Arrays.asList(livingRoom, kitchen));

        while (true) {
            System.out.println("Enter command: all-on, all-off, (room)-(device)-(on/off), schedule-(room)-(device)-(HH:mm)-(on/off), weather-(city), exit");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Exiting...");
                break;
            }
            if (command.startsWith("schedule-")) {
                String[] parts = command.split("-");
                if (parts.length == 5) {
                    SmartRoom room = smartHome.getRoom(parts[1]);
                    if (room != null) {
                        SmartDevice device = room.getDevice(parts[2]);
                        if (device != null) {
                            smartHome.scheduleTask(new ScheduledOperationDecorator(device, parts[3], parts[4]));
                            System.out.println("Scheduled " + parts[2] + " to turn " + parts[4] + " at " + parts[3]);
                        }
                    }
                }
                continue;
            }

            if (command.startsWith("weather-")) {
                System.out.println("Current temperature: " + WeatherService.getTemperature(command.substring(8)) + "°C");
                continue;
            }

            String[] parts = command.split("-");
            if (parts.length == 3) {
                SmartRoom room = smartHome.getRoom(parts[0]);
                if (room != null) {
                    SmartDevice device = room.getDevice(parts[1]);
                    if (device != null) {
                        if (parts[2].equals("on")) device.turnOn();
                        else device.turnOff();
                    }
                }
            }
            if (command.equals("all-on")) {
                smartHome.turnOnAll();
            } else if (command.equals("all-off")) {
                smartHome.turnOffAll();
            } else if (command.contains("-")) {
                String[] parts2 = command.split("-");
                if (parts2.length == 3) {
                    String roomName = parts2[0];
                    String deviceName = parts2[1];
                    String action = parts2[2];

                    SmartRoom room = smartHome.getRoom(roomName);
                    if (room != null) {
                        SmartDevice device = room.getDevice(deviceName);
                        if (device != null) {
                            if (action.equals("on")) {
                                device.turnOn();
                            } else if (action.equals("off")) {
                                device.turnOff();
                            } else {
                                System.out.println("Invalid action. Use 'on' or 'off'.");
                            }
                        } else {
                            System.out.println("Device not found in " + roomName);
                        }
                    } else {
                        System.out.println("Room not found: " + roomName);
                    }
                }
            }
        }
    }
}
