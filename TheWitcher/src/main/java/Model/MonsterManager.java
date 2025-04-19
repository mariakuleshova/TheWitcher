/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maria
 */
public class MonsterManager {
    private final Map<String, List<Monster>> monsterCollections;
    private final FileHandler fileHandlerChain;
    
    public MonsterManager() {
        monsterCollections = new HashMap<>();
        //Chain of responsibility for file handlers
        fileHandlerChain = new JsonFileHandler();
        FileHandler xmlHandler = new XmlFileHandler();
        FileHandler yamlHandler = new YamlFileHandler();
        
        fileHandlerChain.setNextHandler(xmlHandler);
        xmlHandler.setNextHandler(yamlHandler);
    }
            
    public void importFromFile(File file) {
        List<Monster> monsters = fileHandlerChain.processImport(file);
        monsters.forEach(m -> m.setSource(file.getName())); // Установка источника
        monsterCollections.put(file.getName(), monsters);
    }
    
    public List<Monster> getMonsters(String source) {
        return monsterCollections.getOrDefault(source, new ArrayList<>());
    }
    
    public List<String> getSources() {
        return new ArrayList<>(monsterCollections.keySet());
    }
        
    public Monster getMonsterByName(String source, String name) {
        List<Monster> monsters = getMonsters(source);
        if (monsters == null) return null;
        for (Monster monster : monsters) {
            if (monster.getName() != null && monster.getName().equals(name)) {
                return monster;
            }
        }
        return null;
    }
    
    public void updateMonster(String source, Monster updatedMonster) {
        List<Monster> monsters = getMonsters(source);
        
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getName().equals(updatedMonster.getName())) {
                monsters.set(i, updatedMonster);
                break;
            }
        }
    }
        
    public void exportToFile(File file) {
        List<Monster> allMonsters = new ArrayList<>();
        monsterCollections.values().forEach(allMonsters::addAll);
        fileHandlerChain.processExport(allMonsters, file);
    }
} 