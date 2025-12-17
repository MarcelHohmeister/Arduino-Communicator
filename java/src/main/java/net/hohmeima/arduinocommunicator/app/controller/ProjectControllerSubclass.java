package net.hohmeima.arduinocommunicator.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;

import java.util.List;
import java.util.ArrayList;

public class ProjectControllerSubclass {
    public static List<Project> projectList = new ArrayList<>();

    @FXML
    public static void showProjectModules(javafx.event.ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        ModuleControllerSubclass.currentProject = (Project) sourceButton.getUserData();
        System.out.println("Gedrückter Button: " + ModuleControllerSubclass.currentProject.getName());

        // Optional: Module für dieses Projekt in der GUI laden
        ModuleControllerSubclass.loadModulesForProject();
    }

    public static void deleteProject(Project project, Button projectButton) {
        if (project == null || projectButton == null) return;

        // Projekt aus der Liste entfernen
        projectList.remove(project);

        // Projekt aus der GUI entfernen
        VBox parentPane = (VBox) projectButton.getParent().getParent(); // StackPane -> VBox
        parentPane.getChildren().remove(projectButton.getParent());

        // Aktiven Button zurücksetzen, falls gelöscht
        if (DialogueControllerSubclass.getActiveProjectButton() == projectButton) {
            DialogueControllerSubclass.setActiveProjectButton(null);
        }

        // Module des gelöschten Projekts entfernen
        ModuleControllerSubclass.moduleListContainer.getChildren().clear();
        ModuleControllerSubclass.currentProject = null;

        System.out.println("Projekt gelöscht: " + project.getName());

        // Optional: Änderungen speichern
        JsonStorage.saveProjects();
    }
}