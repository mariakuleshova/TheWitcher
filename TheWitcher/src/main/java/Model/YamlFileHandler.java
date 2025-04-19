/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author maria
 */
public class YamlFileHandler extends FileHandler {
    private final ObjectMapper mapper;

    public YamlFileHandler() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".yaml") || filename.toLowerCase().endsWith(".yml");
    }

    @Override
    public List<Monster> importFromFile(File file) {
    try {
        JsonNode root = mapper.readTree(file);
        JsonNode monstersNode = root.path("Monsters").path("Monster");
        return mapper.convertValue(monstersNode, new TypeReference<List<Monster>>() {});
    } catch (IOException e) {
         throw new RuntimeException("YAML import error: " + e.getMessage());
    }
}

    @Override
    public void exportToFile(List<Monster> monsters, File file) {
        try {
            mapper.writeValue(file, new MonsterCollection(monsters));
        } catch (IOException e) {
            throw new RuntimeException("YAML export error: " + e.getMessage());
        }
    }
}
