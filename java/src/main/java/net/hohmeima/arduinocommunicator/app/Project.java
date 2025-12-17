package net.hohmeima.arduinocommunicator.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;
import net.hohmeima.arduinocommunicator.app.controller.modules.ModuleData;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;

    // Farben als HEX für Speicherung
    private String bgColor1;
    private String bgColor2;
    private String textColor;
    private String activeBorderColor;

    private List<ModuleData> modules = new ArrayList<>();

    // 🔴 Wichtig für Jackson
    public Project() {}

    public Project(String name, Color bg1, Color bg2, Color text, Color border) {
        this.name = name;
        this.bgColor1 = toHex(bg1);
        this.bgColor2 = toHex(bg2);
        this.textColor = toHex(text);
        this.activeBorderColor = toHex(border);
    }

    /* ---------- Getter / Setter für JSON ---------- */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBgColor1() { return bgColor1; }
    public void setBgColor1(String bgColor1) { this.bgColor1 = bgColor1; }

    public String getBgColor2() { return bgColor2; }
    public void setBgColor2(String bgColor2) { this.bgColor2 = bgColor2; }

    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }

    public String getActiveBorderColor() { return activeBorderColor; }
    public void setActiveBorderColor(String activeBorderColor) { this.activeBorderColor = activeBorderColor; }

    public List<ModuleData> getModules() { return modules; }
    public void setModules(List<ModuleData> modules) { this.modules = modules; }

    /* ---------- UI-Helper (nicht serialisiert) ---------- */
    @JsonIgnore
    public Color getBgColor1Fx() { return Color.web(bgColor1); }
    @JsonIgnore
    public Color getBgColor2Fx() { return Color.web(bgColor2); }
    @JsonIgnore
    public Color getTextColorFx() { return Color.web(textColor); }
    @JsonIgnore
    public Color getActiveBorderColorFx() { return Color.web(activeBorderColor); }

    @JsonIgnore
    public String getGradientStyle() {
        return String.format("-fx-background-color: linear-gradient(to bottom right, %s, %s);", bgColor1, bgColor2);
    }

    /* ---------- Helper ---------- */
    private static String toHex(Color c) {
        return String.format("#%02X%02X%02X",
                (int)(c.getRed()*255),
                (int)(c.getGreen()*255),
                (int)(c.getBlue()*255));
    }
}