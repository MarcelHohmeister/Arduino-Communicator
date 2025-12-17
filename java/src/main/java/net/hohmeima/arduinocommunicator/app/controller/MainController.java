package net.hohmeima.arduinocommunicator.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;
import net.hohmeima.arduinocommunicator.app.serialcommunication.ArduinoPortManager;
import com.fazecast.jSerialComm.SerialPort;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    @FXML
    private VBox projectPane;
    @FXML
    private VBox modulePane;

    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private Stage mainStage;

    // Neue FXML Elemente für den Statusbereich
    @FXML
    private ImageView connectionStatusIcon;
    @FXML
    private Label connectionStatusLabel;

    private static SerialPort connectedPort;

    //region Setup
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    public void initialize() {
        System.out.printf("");

        // 1️⃣ ModulePane im ModuleController setzen (damit Module geladen werden können)
        ModuleControllerSubclass.setModulePane(modulePane);

        // 2️⃣ Projekte aus JSON laden
        ProjectControllerSubclass.projectList = JsonStorage.loadProjects();

        // 3️⃣ Alle geladenen Projekte ins UI einfügen
        for (Project project : ProjectControllerSubclass.projectList) {
            // StackPane mit Button + Delete-Button erzeugen
            StackPane projectPaneItem = DialogueControllerSubclass.createProjectButtonWithDelete(project);

            // Optional: zusätzliche Styles auf den StackPane anwenden
            projectPaneItem.setStyle("-fx-padding: 5;");

            // Direkt in das Projektpane hinzufügen
            projectPane.getChildren().add(projectPaneItem);
        }

        // 4️⃣ Optional: Wenn du willst, dass Module eines Projekts beim Start angezeigt werden, z.B. erstes Projekt:
        if (!ProjectControllerSubclass.projectList.isEmpty()) {
            ModuleControllerSubclass.loadModulesForProject();
        }

        connectToArduino("COM9");

        // Startproportionen: 25% / 30% / 45%
        mainSplitPane.setDividerPositions(0.25, 0.55);
    }
    //endregion

    //region handle button Presses
    @FXML
    private void handleButtonPressed(javafx.event.ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        switch (sourceButton.getText()) {
            case "Projekt hinzufügen" -> DialogueControllerSubclass.showAddProjectDialog(mainStage, projectPane);
            case "Modul hinzufügen" -> ModuleControllerSubclass.addModuleWithDialogue(mainStage);
            case "Verbindung aufbauen" -> ConnectionDialogFactory.showConnectDialog(mainStage, this);
        }
    }
    //endregion

    public void connectToArduino(String portName) {
        connectedPort = ArduinoPortManager.connectTo(portName);

        if (connectedPort != null && connectedPort.isOpen()) {
            connectionStatusLabel.setText("Verbunden");
            connectionStatusIcon.setImage(new Image(getClass().getResourceAsStream("/icons/connectionIcon.png")));
        } else {
            connectionStatusLabel.setText("Nicht verbunden");
            connectionStatusIcon.setImage(new Image(getClass().getResourceAsStream("/icons/noConnectionIcon.png")));
        }
    }

    public static SerialPort getPort(){
        return connectedPort;
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        System.out.println("Speichere Daten...");
        Map<String, Object> testData = new HashMap<>();
        testData.put("projektName", "Mein Projekt");
        testData.put("moduleCount", 5);
        testData.put("istAktiv", true);
    }
    //endregion
}