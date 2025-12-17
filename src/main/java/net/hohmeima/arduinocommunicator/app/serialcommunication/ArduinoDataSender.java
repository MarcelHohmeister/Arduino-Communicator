package net.hohmeima.arduinocommunicator.app.serialcommunication;

import com.fazecast.jSerialComm.SerialPort;
import net.hohmeima.arduinocommunicator.app.controller.MainController;

import java.io.IOException;

public class ArduinoDataSender {

    private static SerialPort port = MainController.getPort();

    // Allgemeine Methode für alle Module
    public static void sendData(String datatype, String payload) {
        String data = datatype + ":" + payload + "\n";
        System.out.println("Gesendet: " + data);
        sendToArduino(data);
    }

    // Hilfsmethoden für die Module
    public static void sendRGB(String datatype, int r, int g, int b) {
        System.out.println("Sende RGB-Daten: r = " + r + " | g = " + g + " | b = " +b);
        sendData(datatype, r + "," + g + "," + b);
    }

    public static void sendText(String datatype, String text) {
        System.out.println("Sende Text: '" + text + "'");
        sendData(datatype, text);
    }

    public static void sendSwitch(String datatype, boolean state) {
        System.out.println("Sende Button Status: " + state);
        sendData(datatype, state ? "1" : "0");
    }

    // private Serial-Methode
    private static void sendToArduino(String data) {
        if (port != null && port.isOpen()) {
            try {
                port.getOutputStream().write(data.getBytes());
                port.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}