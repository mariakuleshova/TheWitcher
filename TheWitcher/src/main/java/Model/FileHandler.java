/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.util.List;

/**
 *
 * @author maria
 */
public abstract class FileHandler implements Handler{
    protected FileHandler nextHandler;

    @Override
    public void setNextHandler(FileHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
//    @Override
//    public abstract boolean canHandle(String filename);
//    
//    @Override
//    public abstract List<Monster> importFromFile(File file);
//    
//    @Override
//    public abstract void exportToFile(List<Monster> monsters, File file);
    
    @Override
    public List<Monster> processImport(File file) {
        if (canHandle(file.getName())) {
            return importFromFile(file);
        } else if (nextHandler != null) {
            return nextHandler.processImport(file);
        }
        throw new UnsupportedOperationException("No handler available for: " + file.getName());
    }
    
    @Override
    public void processExport(List<Monster> monsters, File file) {
        if (canHandle(file.getName())) {
            exportToFile(monsters, file);
        } else if (nextHandler != null) {
            nextHandler.processExport(monsters, file);
        } else {
            throw new UnsupportedOperationException("No handler available for: " + file.getName());
        }
    }
} 