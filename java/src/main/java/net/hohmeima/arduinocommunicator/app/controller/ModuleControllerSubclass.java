package net.hohmeima.arduinocommunicator.app.controller;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleData;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleRuntimeBuilder;
import net.hohmeima.arduinocommunicator.app.controller.modules.myModule;

public class ModuleControllerSubclass {
    public static Project currentProject = null;

    // Container für alle Module im UI
    private static VBox modulePane;
    public static VBox moduleListContainer; // wird als VBox in modulePane hinzugefügt

    /**
     * Setzt das Modul-Pane aus FXML. Muss beim Start in MainController.initialize() aufgerufen werden.
     */
    public static void setModulePane(VBox pane) {
        modulePane = pane;

        // Container initial erstellen, falls noch nicht vorhanden
        if (moduleListContainer == null) {
            moduleListContainer = new VBox(5);
            modulePane.getChildren().add(moduleListContainer);
        }
    }

    /**
     * Fügt ein neues Modul über Dialog hinzu
     */
    public static void addModuleWithDialogue(Stage mainStage) {
        if (currentProject == null) return;

        System.out.println("Modul hinzufügen");
        DialogueControllerSubclass.showAddModuleDialog(mainStage, moduleListContainer);
    }

    /**
     * Lädt die Module des aktuellen Projekts in den UI-Container
     */
    public static void loadModulesForProject() {
        if (currentProject == null || moduleListContainer == null) return;

        System.out.println("Module für Projekt " + currentProject.getName() + " laden");

        moduleListContainer.getChildren().clear();

        for (ModuleData data : currentProject.getModules()) {
            System.out.println("Rebuild UI für Modul: " + data.getModuleName());

            myModule<?> runtimeModule = ModuleRuntimeBuilder.build(data);
            Node ui = runtimeModule.getUI();

            moduleListContainer.getChildren().add(ui);
        }

        System.out.println("Module geladen");
    }
}