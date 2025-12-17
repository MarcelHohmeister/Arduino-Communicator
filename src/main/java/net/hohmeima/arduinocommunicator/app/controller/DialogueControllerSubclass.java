package net.hohmeima.arduinocommunicator.app.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleData;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleRuntimeBuilder;
import net.hohmeima.arduinocommunicator.app.controller.modules.myModule;
import net.hohmeima.arduinocommunicator.app.savesystem.JsonStorage;

public class DialogueControllerSubclass {

    private static Button activeProjectButton = null;

    /* =========================================================
     * ADD PROJECT DIALOG
     * ========================================================= */
    public static void showAddProjectDialog(Stage ownerStage, VBox projectPane) {

        Stage dialog = new Stage();
        dialog.initOwner(ownerStage);
        dialog.setTitle("Neues Projekt");

        VBox root = new VBox(25);
        root.setPadding(new Insets(25));
        root.getStyleClass().add("dialog-root");

        Label title = new Label("Projekt erstellen");
        title.getStyleClass().add("dialog-title");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.getStyleClass().add("dialog-section");

        TextField nameField = new TextField();
        nameField.getStyleClass().add("dialog-textfield");

        ColorPicker bg1 = new ColorPicker(Color.web("#00979D"));        //Arduino Blau, Base Color der App
        ColorPicker bg2 = new ColorPicker(Color.web("#00C1C7"));                      //zweite passende Farbe
        ColorPicker text = new ColorPicker(Color.WHITE);
        ColorPicker border = new ColorPicker(Color.DARKBLUE);

        bg1.getStyleClass().add("dialog-colorpicker");
        bg2.getStyleClass().add("dialog-colorpicker");
        text.getStyleClass().add("dialog-colorpicker");
        border.getStyleClass().add("dialog-colorpicker");

        HBox gradientBox = new HBox(10, bg1, bg2);

        Label nameLabel = new Label("Projektname");
        Label bgLabel = new Label("Hintergrund (Gradient)");
        Label textLabel = new Label("Textfarbe");
        Label borderLabel = new Label("Aktiver Rand");

        nameLabel.getStyleClass().add("dialog-label");
        bgLabel.getStyleClass().add("dialog-label");
        textLabel.getStyleClass().add("dialog-label");
        borderLabel.getStyleClass().add("dialog-label");

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(bgLabel, 0, 1);
        form.add(gradientBox, 1, 1);

        form.add(textLabel, 0, 2);
        form.add(text, 1, 2);

        form.add(borderLabel, 0, 3);
        form.add(border, 1, 3);

        Button ok = new Button("Projekt anlegen");
        ok.getStyleClass().add("dialog-button");

        HBox buttonBox = new HBox(ok);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        ok.setOnAction(e -> {
            String projectName = nameField.getText().trim();
            if (projectName.isEmpty()) return;

            Project project = new Project(
                    projectName,
                    bg1.getValue(),
                    bg2.getValue(),
                    text.getValue(),
                    border.getValue()
            );

            ProjectControllerSubclass.projectList.add(project);
            JsonStorage.saveProjects();

            StackPane projectPaneItem = createProjectButtonWithDelete(project);
            projectPane.getChildren().add(projectPaneItem);

            dialog.close();
        });

        root.getChildren().addAll(title, form, buttonBox);

        Scene scene = new Scene(new ScrollPane(root), 505, 330);
        scene.getStylesheets().add(
                DialogueControllerSubclass.class
                        .getResource("/dialog.css")
                        .toExternalForm()
        );

        dialog.setScene(scene);
        dialog.show();
    }

    /* =========================================================
     * ADD MODULE DIALOG
     * ========================================================= */
    public static void showAddModuleDialog(Stage ownerStage, VBox modulePane) {
        if (activeProjectButton == null) return;

        Stage dialog = new Stage();
        dialog.initOwner(ownerStage);
        dialog.setTitle("Neues Modul");

        VBox root = new VBox(25);
        root.setPadding(new Insets(25));
        root.getStyleClass().add("dialog-root");

        Label title = new Label("Modul hinzufügen");
        title.getStyleClass().add("dialog-title");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.getStyleClass().add("dialog-section");

        TextField nameField = new TextField();
        nameField.getStyleClass().add("dialog-textfield");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("RGB", "TEXT", "SWITCH");
        typeBox.setValue("RGB");
        typeBox.getStyleClass().add("dialog-combobox");

        ColorPicker bg1 = new ColorPicker(Color.BLACK);
        ColorPicker bg2 = new ColorPicker(Color.RED);
        ColorPicker text = new ColorPicker(Color.WHITE);

        bg1.getStyleClass().add("dialog-colorpicker");
        bg2.getStyleClass().add("dialog-colorpicker");
        text.getStyleClass().add("dialog-colorpicker");

        HBox gradientBox = new HBox(10, bg1, bg2);

        Label nameLabel = new Label("Modulname");
        Label typeLabel = new Label("Modultyp");
        Label bgLabel = new Label("Hintergrund (Gradient)");
        Label textLabel = new Label("Textfarbe");

        nameLabel.getStyleClass().add("dialog-label");
        typeLabel.getStyleClass().add("dialog-label");
        bgLabel.getStyleClass().add("dialog-label");
        textLabel.getStyleClass().add("dialog-label");

        form.add(nameLabel, 0, 0);
        form.add(nameField, 1, 0);

        form.add(typeLabel, 0, 1);
        form.add(typeBox, 1, 1);

        form.add(bgLabel, 0, 2);
        form.add(gradientBox, 1, 2);

        form.add(textLabel, 0, 3);
        form.add(text, 1, 3);

        Button ok = new Button("Modul hinzufügen");
        ok.getStyleClass().add("dialog-button");

        HBox buttonBox = new HBox(ok);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        ok.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) return;

            Project project = (Project) activeProjectButton.getUserData();

            ModuleData data = new ModuleData(typeBox.getValue(), name);
            data.getSettings().put("bg1", toHex(bg1.getValue()));
            data.getSettings().put("bg2", toHex(bg2.getValue()));
            data.getSettings().put("text", toHex(text.getValue()));

            project.getModules().add(data);
            JsonStorage.saveProjects();

            myModule<?> runtimeModule = ModuleRuntimeBuilder.build(data);
            Node ui = runtimeModule.getUI();
            modulePane.getChildren().add(ui);

            dialog.close();
        });

        root.getChildren().addAll(title, form, buttonBox);

        Scene scene = new Scene(new ScrollPane(root), 505.3, 330.3);
        scene.getStylesheets().add(
                DialogueControllerSubclass.class
                        .getResource("/dialog.css")
                        .toExternalForm()
        );

        dialog.setScene(scene);
        dialog.show();
    }

    /* =========================================================
     * HELPERS
     * ========================================================= */

    public static StackPane createProjectButtonWithDelete(Project project) {
        // Haupt-Button
        Button btn = new Button(project.getName());
        btn.setUserData(project);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("projectEntryButtonStyle");
        btn.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, "
                        + project.getBgColor1() + ", " + project.getBgColor2() + ");"
                        + "-fx-text-fill: " + project.getTextColor() + ";"
                        + "-fx-border-color: transparent;"
                        + "-fx-border-width: 2px;"
        );

        btn.setOnAction(e -> {
            if (activeProjectButton != null) {
                Project old = (Project) activeProjectButton.getUserData();
                activeProjectButton.setStyle(
                        "-fx-background-color: linear-gradient(to bottom right, "
                                + old.getBgColor1() + ", " + old.getBgColor2() + ");"
                                + "-fx-text-fill: " + old.getTextColor() + ";"
                                + "-fx-border-color: transparent;"
                                + "-fx-border-width: 2px;"
                );
            }
            activeProjectButton = btn;
            btn.setStyle(
                    "-fx-background-color: linear-gradient(to bottom right, "
                            + project.getBgColor1() + ", " + project.getBgColor2() + ");"
                            + "-fx-text-fill: " + project.getTextColor() + ";"
                            + "-fx-border-color: " + project.getActiveBorderColor() + ";"
                            + "-fx-border-width: 2px;"
            );
            ProjectControllerSubclass.showProjectModules(new javafx.event.ActionEvent(btn, null));
        });

        // Delete-Button
        Button deleteBtn = new Button("X");
        deleteBtn.getStyleClass().add("deleteButton");
        deleteBtn.setOnAction(e -> ProjectControllerSubclass.deleteProject(project, btn));

        // StackPane zum Überlagern
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5));
        pane.getChildren().addAll(btn, deleteBtn);
        StackPane.setAlignment(deleteBtn, Pos.TOP_RIGHT); // Delete oben rechts

        return pane;
    }

    public static StackPane wrapProjectButton(Button projectButton) {
        StackPane pane = new StackPane(projectButton);
        pane.setPadding(new Insets(5));
        return pane;
    }

    private static String toHex(Color c) {
        return String.format(
                "#%02X%02X%02X",
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255)
        );
    }

    public static Button getActiveProjectButton() {
        return activeProjectButton;
    }

    public static void setActiveProjectButton(Button button) {
        activeProjectButton = button;
    }
}