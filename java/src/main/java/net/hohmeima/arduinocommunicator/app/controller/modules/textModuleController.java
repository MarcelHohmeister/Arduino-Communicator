package net.hohmeima.arduinocommunicator.app.controller.modules;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.DialogueControllerSubclass;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;
import net.hohmeima.arduinocommunicator.app.serialcommunication.ArduinoDataSender;

public class textModuleController {

    @FXML private Label moduleTitle;
    @FXML private Label moduleType;

    @FXML private TextField textField;  // Textfeld für Eingaben
    @FXML private TextField datatypeField; //Textfeld für Datentyp

    private String moduleName;
    private myModule<textModuleController> controllerModule;

    // ====================== Delete ======================
    @FXML
    private void handleDelete() {
        System.out.println("Delete Button");

        if(controllerModule == null) {
            System.out.println("Controller-Modul ist NULL");
            return;
        }

        System.out.println("Löschung des Text-Moduls: " + moduleName);

        // 1. Das Project aus dem aktiven Button holen
        Project project = (Project) DialogueControllerSubclass.getActiveProjectButton().getUserData();
        if (project != null) {
            project.getModules().remove(controllerModule);
            System.out.println("Modul aus Liste entfernt");
            System.out.println();
        } else {
            System.out.println("Project is null");
        }

        // 2. Das Modul-UI aus dem Fenster entfernen
        VBox modulePane = (VBox) controllerModule.getUI().getParent();
        modulePane.getChildren().remove(controllerModule.getUI());

        JsonStorage.saveProjects();
    }

    // ====================== Senden ======================
    @FXML
    private void handleSend() {
        String textInput = textField.getText();
        String datatype = datatypeField.getText();

        ArduinoDataSender.sendText(datatype, textInput);
    }

    // ====================== Einstellungen ======================
    @FXML
    private void handleSettings() {
        System.out.println("Einstellungen für das Text-Modul öffnen: " + moduleName);
        // Hier kannst du weitere Logik für das Modul einbauen
    }

    // ====================== Modulname setzen ======================
    public void setModuleName(String name) {
        this.moduleName = name;
        moduleTitle.setText(name);
    }

    public String getModuleName() {
        return moduleName;
    }

    // ====================== Controller-Modul setzen ======================
    public void setModule(myModule<textModuleController> m) {
        this.controllerModule = m;
    }

    public myModule<textModuleController> getModule() {
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