package net.hohmeima.arduinocommunicator.app.controller.modules;

import javafx.scene.Node;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleData;

public class myModule<T> {
    private final ModuleData data;
    private final T controller;
    private final Node ui;

    public myModule(ModuleData data, T controller, Node ui) {
        this.data = data;
        this.controller = controller;
        this.ui = ui;
    }

    public ModuleData getData() { return data; }
    public T getController() { return controller; }
    public Node getUI() { return ui; }
}