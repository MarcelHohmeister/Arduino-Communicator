package net.hohmeima.arduinocommunicator.app.controller.modules;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import net.hohmeima.arduinocommunicator.app.controller.DialogueControllerSubclass;

import java.io.IOException;

public class ModuleRuntimeBuilder {

    public static myModule<?> build(ModuleData data) {

        try {
            FXMLLoader loader;
            String type = data.getModuleType();

            /* ---------------------------------------
             * FXML-Auswahl nach Modultyp
             * --------------------------------------- */
            switch (type) {

                case "RGB" -> loader =
                        new FXMLLoader(DialogueControllerSubclass.class
                                .getResource("/rgbModule.fxml"));

                case "TEXT" -> loader =
                        new FXMLLoader(DialogueControllerSubclass.class
                                .getResource("/textModule.fxml"));

                case "SWITCH" -> loader =
                        new FXMLLoader(DialogueControllerSubclass.class
                                .getResource("/switchButtonModule.fxml"));

                default -> {
                    System.err.println("[WARN] Unbekannter Modultyp: " + type);
                    return null;
                }
            }

            /* ---------------------------------------
             * UI + Controller laden
             * --------------------------------------- */
            VBox ui = loader.load();
            Object controller = loader.getController();

            /* ---------------------------------------
             * Gemeinsame Styles
             * --------------------------------------- */
            String bg1 = (String) data.getSettings().get("bg1");
            String bg2 = (String) data.getSettings().get("bg2");
            String text = (String) data.getSettings().get("text");

            if (bg1 != null && bg2 != null) {
                ui.setStyle(
                        "-fx-background-color: linear-gradient(to bottom right, "
                                + bg1 + ", " + bg2 + ");"
                );
            }

            Color textColor = text != null ? Color.web(text) : Color.WHITE;

            /* ---------------------------------------
             * Controller-spezifische Initialisierung
             * --------------------------------------- */

            if (controller instanceof rgbModuleController rgbCtrl) {
                rgbCtrl.setModuleName(data.getModuleName());
                rgbCtrl.setTextColor(textColor);

            } else if (controller instanceof textModuleController textCtrl) {
                textCtrl.setModuleName(data.getModuleName());
                textCtrl.setTextColor(textColor);

            } else if (controller instanceof switchButtonModuleController switchCtrl) {
                switchCtrl.setModuleName(data.getModuleName());
                switchCtrl.setTextColor(textColor);
            }

            /* ---------------------------------------
             * Runtime-Modul erzeugen
             * --------------------------------------- */
            myModule<Object> module =
                    new myModule<>(data, controller, ui);

            ui.setUserData(module);

            return module;

        } catch (IOException e) {
            System.err.println("[ERROR] Modul konnte nicht gebaut werden: "
                    + data.getModuleName());
            e.printStackTrace();
            return null;
        }
    }
}