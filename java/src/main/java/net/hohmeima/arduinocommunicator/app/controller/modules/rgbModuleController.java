package net.hohmeima.arduinocommunicator.app.controller.modules;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.DialogueControllerSubclass;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;
import net.hohmeima.arduinocommunicator.app.serialcommunication.ArduinoDataSender;

public class rgbModuleController {

    @FXML private Label moduleTitle;
    @FXML private Label moduleType;

    @FXML private ColorPicker colorPicker;
    @FXML private TextField datatypeField;

    private String moduleName;
    private myModule<rgbModuleController> controllerModule;

    // ====================== Delete ======================
    @FXML
    private void handleDelete() {
        System.out.println("Delete Button");
        if (controllerModule == null){
            System.out.println("Controller is Null");
            return;
        }

        Project project = (Project) DialogueControllerSubclass.getActiveProjectButton().getUserData();
        if (project != null) project.getModules().remove(controllerModule);

        VBox modulePane = (VBox) controllerModule.getUI().getParent();
        modulePane.getChildren().remove(controllerModule.getUI());

        JsonStorage.saveProjects();
    }

    // ====================== Senden ======================
    @FXML
    private void handleSend() {
        if (colorPicker == null) return;

        String datatype = datatypeField.getText();

        Color color = colorPicker.getValue();
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);

        System.out.println("Senden der RGB Daten: " + moduleName + " -> " + r + "," + g + "," + b);

        // Hier wird an Arduino gesendet
        ArduinoDataSender.sendRGB(datatype,r, g, b);
    }

    // ====================== Settings ======================
    @FXML
    private void handleSettings() {
        // Weitere Logik für Einstellungen einfügen
    }

    // ====================== Modulname ======================
    public void setModuleName(String name) {
        this.moduleName = name;
        moduleTitle.setText(name);
    }

    public String getModuleName() {
        return moduleName;
    }

    // ====================== Controller-Modul ======================
    public void setModule(myModule<rgbModuleController> m) {
        this.controllerModule = m;
    }

    public myModule<rgbModuleController> getModule() {
        return controllerModule;
    }

    // ====================== Textfarben ======================
    public void setTextColor(Color color) {
        String colorStr = String.format("#%02X%02X%02X",
                (int)(color.getRed()*255),
                (int)(color.getGreen()*255),
                (int)(color.getBlue()*255));
        moduleTitle.setStyle("-fx-text-fill: " + colorStr + ";");
        moduleType.setStyle("-fx-text-fill: " + colorStr + ";");
    }
}