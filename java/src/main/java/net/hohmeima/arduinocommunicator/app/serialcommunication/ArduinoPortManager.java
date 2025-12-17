package net.hohmeima.arduinocommunicator.app.serialcommunication;

import com.fazecast.jSerialComm.SerialPort;

public class ArduinoPortManager {

    public static SerialPort connectTo(String portName) {
        SerialPort port = SerialPort.getCommPort(portName);
        port.setBaudRate(9600);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);

        if (!port.openPort()) {
            System.out.println("Konnte Port nicht öffnen!");
            return null;
        }

        System.out.println("Port geöffnet: " + portName);

        try {
            // Arduino braucht Zeit zum Reset
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Eingangsbuffer leeren
        byte[] dump = new byte[1024];
        while (port.bytesAvailable() > 0) {
            port.readBytes(dump, dump.length);
        }

        // Handshake senden
        String handshake = "HELLO\n";
        boolean handshakeSuccess = false;

        for (int i = 0; i < 5; i++) {
            port.writeBytes(handshake.getBytes(), handshake.length());
            System.out.println("Java → Arduino: " + handshake.trim());

            // Antwort lesen
            byte[] buffer = new byte[64];
            int bytes = port.readBytes(buffer, buffer.length);
            if (bytes > 0) {
                String received = new String(buffer, 0, bytes).trim();
                System.out.println("Arduino → Java: " + received);
                if (received.contains("ARDUINO_READY")) {
                    handshakeSuccess = true;
                    break;
                }
            }

            try {
                Thread.sleep(200); // kurz warten und nochmal senden
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (handshakeSuccess) {
            System.out.println("Arduino bereit!");
            return port;
        } else {
            System.out.println("Keine passende Antwort vom Arduino.");
            port.closePort();
            return null;
        }
    }
}