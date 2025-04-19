/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.MonsterController;
import Model.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

/**
 *
 * @author maria
 */
public class MainView extends JFrame {
    private final MonsterController controller;
    private final JTree monsterTree;
    private final DefaultMutableTreeNode rootNode;
    private final JButton importButton;
    private final JButton exportButton;
    
    public MainView (MonsterController controller) {
        this.controller = controller;
        
        //JFrame
        setTitle("Monster Bestiary Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Tree 
        rootNode = new DefaultMutableTreeNode("Monster Sources");
        monsterTree = new JTree(rootNode);
        JScrollPane treeScrollPane = new JScrollPane(monsterTree);
        
        //Buttons
        JPanel buttonPanel = new JPanel();
        importButton = new JButton("Import File");
        exportButton = new JButton("Export File");
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);
        
        add(treeScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Listeners
        setupListeners();
    }
    
    private void setupListeners() {
        importButton.addActionListener(e -> importFile());
        
        exportButton.addActionListener(e -> controller.exportFiles());
        
        monsterTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) {
                    TreePath path = monsterTree.getPathForLocation(evt.getX(), evt.getY());
                    if (path != null) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.isLeaf() && !node.toString().equals("Monster Sources")) {
                            String monsterName = node.toString();
                            String source = node.getParent().toString();
                            showMonsterDetails(source, monsterName);
                        }
                    }
                }
            }
        });
    }
    
    private void importFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON/XML/YAML", "json", "xml", "yml"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            controller.importFile(selectedFile);
            updateTreeView();
        }
    }
    
    
    private void showMonsterDetails(String source, String monsterName) {
        Monster monster = controller.getMonsterByName(source, monsterName);
        if (monster != null) {
            new MonsterDetailView(monster, controller, source).setVisible(true);
        }
    }
    
    public void updateTreeView() {
        rootNode.removeAllChildren();
        
        List<String> sources = controller.getSources();
        for (String source : sources) {
            DefaultMutableTreeNode sourceNode = new DefaultMutableTreeNode(source);
            rootNode.add(sourceNode);
            
            List<Monster> monsters = controller.getMonstersBySource(source);
            for (Monster monster : monsters) {
                DefaultMutableTreeNode monsterNode = new DefaultMutableTreeNode(monster.getName());
                sourceNode.add(monsterNode);
            }
        }
        
        ((DefaultTreeModel) monsterTree.getModel()).reload();
    }
} 