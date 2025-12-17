package net.hohmeima.arduinocommunicator.app.controller.modules;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.DialogueControllerSubclass;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;
import net.hohmeima.arduinocommunicator.app.serialcommunication.ArduinoDataSender;

public class switchButtonModuleController {

    @FXML private Label moduleTitle;
    @FXML private Label moduleType;
    @FXML private CheckBox switchCheckBox;
    @FXML private Button momentaryButton;
    @FXML private TextField datatypeField;

    private String moduleName;
    private myModule<switchButtonModuleController> controllerModule;

    boolean currentBoolState = false;

    // ====================== Delete ======================
    @FXML
    private void handleDelete() {
        System.out.println("Delete Button");

        if (controllerModule == null){
            System.out.println("Controller is Null");
            return;
        }

        Project project = (Project) DialogueControllerSubclass.getActiveProjectButton().getUserData();
        if(project != null) project.getModules().remove(controllerModule);

        VBox modulePane = (VBox) controllerModule.getUI().getParent();
        modulePane.getChildren().remove(controllerModule.getUI());

        JsonStorage.saveProjects();
    }

    // ====================== Switch ======================
    @FXML
    private void handleSwitchToggle() {
        currentBoolState = !currentBoolState;
        ArduinoDataSender.sendSwitch(getDatatype(), currentBoolState);
    }

    // ====================== Momentary Button ======================
    @FXML
    private void handleButtonPress() {
        currentBoolState = !currentBoolState;
        ArduinoDataSender.sendSwitch(getDatatype(),currentBoolState);
    }

    @FXML
    private void handleButtonRelease() {
        currentBoolState = !currentBoolState;
        ArduinoDataSender.sendSwitch(getDatatype(), currentBoolState);
    }

    // ====================== Modulname setzen ======================
    public void setModuleName(String name) {
        this.moduleName = name;
        moduleTitle.setText(name);
    }

    public String getModuleName() { return moduleName; }

    // ====================== Datentyp auslesen ======================
    private String getDatatype() {
        String datatype = datatypeField.getText().trim();
        if (datatype.isEmpty()) {
            datatype = moduleName; // Fallback oder Fehlerhandling
        }
        return datatype;
    }

    // ====================== Controller-Modul ======================
    public void setModule(myModule<switchButtonModuleController> m) {
        this.controllerModule = m;
    }

    public myModule<switchButtonModuleController> getModule() {
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