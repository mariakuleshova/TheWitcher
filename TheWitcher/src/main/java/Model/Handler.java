/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.io.File;
import java.util.List;

/**
 *
 * @author maria
 */
public interface Handler {
    void setNextHandler(FileHandler nextHandler);
    boolean canHandle(String filename);
    List<Monster> processImport(File file);
    List<Monster> importFromFile(File file);
    void processExport(List<Monster> monsters, File file);
    void exportToFile(List<Monster> monsters, File file);
}