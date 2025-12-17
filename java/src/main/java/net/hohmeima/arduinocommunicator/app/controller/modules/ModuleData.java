package net.hohmeima.arduinocommunicator.app.controller.modules;

import java.util.HashMap;
import java.util.Map;

public class ModuleData {

    private String moduleType;
    private String moduleName;

    // Frei erweiterbare Parameter, jetzt sicher für Jackson
    private Map<String, String> settings = new HashMap<>();

    // 🔴 Wichtig für Jackson
    public ModuleData() {}

    public ModuleData(String moduleType, String moduleName) {
        this.moduleType = moduleType;
        this.moduleName = moduleName;
    }

    // Getter/Setter für Jackson
    public String getModuleType() { return moduleType; }
    public void setModuleType(String moduleType) { this.moduleType = moduleType; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    public Map<String, String> getSettings() { return settings; }
    public void setSettings(Map<String, String> settings) { this.settings = settings; }
}