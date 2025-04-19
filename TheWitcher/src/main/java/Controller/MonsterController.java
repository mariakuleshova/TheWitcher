/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;
import View.*;
import javax.swing.*;
import java.io.File;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author maria
 */
public class MonsterController {
    private final MonsterManager model;
    private MainView view;
    
    public MonsterController(MonsterManager model) {
        this.model = model;
    }
    
    public void setView(MainView view) {
        this.view = view;
    }
        
    public void importFile(File file) {
        try {
            model.importFromFile(file);
            SwingUtilities.invokeLater(() -> view.updateTreeView());
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(view, 
                        "Error importing file: " + e.getMessage(),
                        "Import Error", 
                        JOptionPane.ERROR_MESSAGE);
            });
        }
    }
    
    public List<String> getSources() {
        return model.getSources();
    }
    
    public List<Monster> getMonstersBySource(String source) {
        return model.getMonsters(source);
    }
    
    public Monster getMonsterByName(String source, String name) {
        return model.getMonsterByName(source, name);
    }
    
    public void updateMonster(String source, Monster updatedMonster) {
    model.updateMonster(source, updatedMonster);
}
   
    public void exportFiles() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Export Monster Data");
    
    // Настройка фильтров для форматов
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("YAML Files", "yml", "yaml"));
    fileChooser.setAcceptAllFileFilterUsed(false);

    if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String extension = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
        file = ensureFileExtension(file, extension);
        
        try {
            model.exportToFile(file);
            JOptionPane.showMessageDialog(view, 
                "File exported successfully!", 
                "Export Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Export error: " + e.getMessage(), 
                "Export Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    }

    private File ensureFileExtension(File file, String extension) {
        if (!file.getName().toLowerCase().endsWith("." + extension)) {
            return new File(file.getAbsolutePath() + "." + extension);
        }
        return file;
    }
}