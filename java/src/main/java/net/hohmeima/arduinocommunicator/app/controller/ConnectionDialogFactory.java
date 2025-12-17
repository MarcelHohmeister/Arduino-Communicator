package net.hohmeima.arduinocommunicator.app.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class ConnectionDialogFactory {
    /**
     * Zeigt einen Dialog zum Verbinden mit einem Arduino an.
     */
    public static void showConnectDialog(Stage ownerStage, MainController mainController) {

        Stage dialog = new Stage();
        dialog.initOwner(ownerStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Arduino verbinden");

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-padding: 20;");

        Label infoLabel = new Label("Gib den COM-Port deines Arduinos ein:");
        TextField portInput = new TextField("COM9");
        portInput.setMaxWidth(120);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(false);

        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            String portName = portInput.getText().trim();
            if (!portName.isEmpty()) {
                mainController.connectToArduino(portName);
            }
            dialog.close();
        });

        Button cancel = new Button("Abbrechen");
        cancel.setOnAction(e -> dialog.close());

        content.getChildren().addAll(infoLabel, portInput, errorLabel, ok, cancel);

        dialog.setScene(new Scene(content));
        dialog.setMinWidth(300);
        dialog.setMinHeight(180);
        dialog.show();
    }
}