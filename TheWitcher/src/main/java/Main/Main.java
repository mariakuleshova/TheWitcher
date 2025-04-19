/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.*;
import Model.MonsterManager;
import View.MainView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author maria
 */
public class Main {
    public static void main(String[] args) {
        // Look and feel 
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        MonsterManager model = new MonsterManager();
        MonsterController controller = new MonsterController(model);
        
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView(controller);
            controller.setView(view);
            
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        });
    }
}