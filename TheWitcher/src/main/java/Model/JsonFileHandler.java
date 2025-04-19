/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

/**
 *
 * @author maria
 */
public class JsonFileHandler extends FileHandler {
    private final ObjectMapper mapper;

    public JsonFileHandler() {
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".json");
    }

    @Override
    public List<Monster> importFromFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(file);
            JsonNode monstersNode = root.path("Monsters").path("Monster");
            return mapper.convertValue(monstersNode, new TypeReference<List<Monster>>() {});
        } catch (IOException e) {
            throw new RuntimeException("JSON import error: " + e.getMessage());
        }
    }
    
    @Override
    public void exportToFile(List<Monster> monsters, File file) {
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, new MonsterCollection(monsters));
        } catch (IOException e) {
            throw new RuntimeException("JSON export error: " + e.getMessage());
        }
    }
} 