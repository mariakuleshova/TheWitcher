/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author maria
 */
public class XmlFileHandler extends FileHandler {
    private final XmlMapper mapper;

    public XmlFileHandler() {
        this.mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".xml");
    }

    @Override
    public List<Monster> importFromFile(File file) {
        try {
            MonsterCollection collection = mapper.readValue(file, MonsterCollection.class);
            return collection.getMonster();
        } catch (IOException e) {
            throw new RuntimeException("XML import error: " + e.getMessage());
        }
    }

    @Override
    public void exportToFile(List<Monster> monsters, File file) {
        try {
            MonsterCollection collection = new MonsterCollection(monsters);
            mapper.writeValue(file, collection);
        } catch (IOException e) {
            throw new RuntimeException("XML export error: " + e.getMessage());
        }
    }
}
