package net.hohmeima.arduinocommunicator.app.savesystem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.hohmeima.arduinocommunicator.app.Project;
import net.hohmeima.arduinocommunicator.app.controller.ProjectControllerSubclass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonStorage {

    private static final String FILE_PATH = "projects.json";

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /* --------------------------------------------------
     * SAVE
     * -------------------------------------------------- */
    public static void saveProjects() {
        try {
            Map<String, Object> root = new HashMap<>();
            root.put("projects", ProjectControllerSubclass.projectList);
            mapper.writeValue(new File(FILE_PATH), root);
            System.out.println("[INFO] Projekte erfolgreich gespeichert");
        } catch (IOException e) {
            System.err.println("[ERROR] Fehler beim Speichern der Projekte");
            e.printStackTrace();
        }
    }

    /* --------------------------------------------------
     * LOAD
     * -------------------------------------------------- */
    public static List<Project> loadProjects() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("[INFO] Keine JSON-Datei gefunden – starte leer");
            return new ArrayList<>();
        }

        try {
            JsonNode root = mapper.readTree(file);
            if (!root.has("projects")) {
                System.err.println("[WARN] JSON enthält keinen 'projects'-Block");
                return new ArrayList<>();
            }

            List<Project> projects = mapper.readerForListOf(Project.class)
                    .readValue(root.get("projects"));

            System.out.println("[INFO] " + projects.size() + " Projekt(e) geladen");
            return projects;

        } catch (IOException e) {
            System.err.println("[ERROR] Fehler beim Laden der Projekte");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* --------------------------------------------------
     * UTILS
     * -------------------------------------------------- */
    public static boolean hasSaveFile() {
        return new File(FILE_PATH).exists();
    }

    public static void deleteSaveFile() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.delete()) {
            System.out.println("[INFO] JSON-Datei gelöscht");
        }
    }
}