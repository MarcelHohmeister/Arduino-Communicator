module net.hohmeima.arduinocommunicator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.graphics;
    requires com.fazecast.jSerialComm;
    requires com.fasterxml.jackson.databind;

    // FXML Controller
    opens net.hohmeima.arduinocommunicator.app.controller to javafx.fxml;

    // Module-Controller (z.B. rgbModulController) für FXML öffnen
    opens net.hohmeima.arduinocommunicator.app.controller.modules to javafx.fxml;

    // Hauptpaket für Launcher zugänglich machen
    opens net.hohmeima.arduinocommunicator.app to javafx.fxml, javafx.graphics, com.fasterxml.jackson.databind;

    // Export für Jackson, damit Reflection auf Getter funktioniert
    exports net.hohmeima.arduinocommunicator.app to com.fasterxml.jackson.databind;

    // ModuleData für Jackson zugänglich machen
    exports net.hohmeima.arduinocommunicator.app.controller.modules to com.fasterxml.jackson.databind;
}