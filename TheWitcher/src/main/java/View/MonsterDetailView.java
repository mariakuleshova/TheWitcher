/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.*;
import javax.swing.*;
import Controller.MonsterController;
import Model.Immunities;
import Model.Ingredient;
import Model.Monster;
import java.util.List;

/**
 *
 * @author maria
 */
public class MonsterDetailView extends JDialog {
    private final Monster monster;
    private final MonsterController controller;
    private final String source;
    private JTextField nameField;
    private JTextArea typeArea;
    private JTextField habitatField;
    private JSpinner dangerLevelSpinner;
    private JLabel sourceLabel;
    private JTextField firstMentionField;
    private JTextField invulnerabilityField;
    private JTextField vulnerabilityField;
    private JSpinner heightSpinner;
    private JSpinner weightSpinner;
    private JTextArea immunitiesArea;
    private JTextField activityField;
    private JTextArea recipeArea;
    private JSpinner idSpinner;
    
    public MonsterDetailView(Monster monster, MonsterController controller, String source) {
        this.monster = monster;
        this.controller = controller;
        this.source = source;
        
        setTitle("Monster Details: " + monster.getName());
        setSize(700, 800); 
        setModal(true);
        
        createComponents();
    }
    
    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        // Basic Info Panel
        JPanel basicInfoPanel = createBasicInfoPanel();
        // Physical Attributes Panel
        JPanel physicalPanel = createPhysicalPanel();
        // Combat Attributes Panel
        JPanel combatPanel = createCombatPanel();
        // Recipe Panel
        JPanel recipePanel = createRecipePanel();
        
        formPanel.add(basicInfoPanel);
        formPanel.add(physicalPanel);
        formPanel.add(combatPanel);
        formPanel.add(recipePanel);
        
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // Action listeners
        saveButton.addActionListener(e -> saveChanges());
        cancelButton.addActionListener(e -> dispose());
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    
    private JPanel createBasicInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 0: Name and ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = new JTextField(monster.getName());
        nameField.setEditable(false);
        panel.add(nameField, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 3; gbc.weightx = 0.5;
        idSpinner = new JSpinner(new SpinnerNumberModel(monster.getId(), 1, 1000, 1));
        panel.add(idSpinner, gbc);
        
        // Row 1: Type
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Type:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1.0;
        typeArea = new JTextArea(monster.getType(), 3, 20);
        typeArea.setLineWrap(true);
        typeArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(typeArea), gbc);
        
        // Row 2: Habitat and Activity
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Habitat:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        habitatField = new JTextField(monster.getHabitat());
        panel.add(habitatField, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Activity:"), gbc);
        
        gbc.gridx = 3; gbc.weightx = 1.0;
        activityField = new JTextField(monster.getActivity());
        panel.add(activityField, gbc);
        
        // Row 3: First Mention and Source
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.0;
        panel.add(new JLabel("First Mention:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        firstMentionField = new JTextField(monster.getFirstMention());
        panel.add(firstMentionField, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Source:"), gbc);
        
        gbc.gridx = 3; gbc.weightx = 1.0;
        sourceLabel = new JLabel(monster.getSource());
        panel.add(sourceLabel, gbc);
        
        return panel;
    }
    
    private JPanel createPhysicalPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Physical Attributes"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 0: Height and Weight
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Height (m):"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        heightSpinner = new JSpinner(new SpinnerNumberModel(monster.getHeightInMetres(), 0.0, 100.0, 0.1));
        panel.add(heightSpinner, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Weight (kg):"), gbc);
        
        gbc.gridx = 3; gbc.weightx = 1.0;
        weightSpinner = new JSpinner(new SpinnerNumberModel(monster.getWeightInKilos(), 0.0, 10000.0, 1.0));
        panel.add(weightSpinner, gbc);
        
        return panel;
    }
    
    private JPanel createCombatPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Combat Attributes"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 0: Danger Level
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Danger Level:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        dangerLevelSpinner = new JSpinner(new SpinnerNumberModel(monster.getDangerLevel(), 1, 10, 1));
        panel.add(dangerLevelSpinner, gbc);
        
        // Row 1: Invulnerability and Vulnerability
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        panel.add(new JLabel("Invulnerability:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1.0;
        invulnerabilityField = new JTextField(monster.getInvulnerability());
        panel.add(invulnerabilityField, gbc);
        
        gbc.gridx = 2; gbc.weightx = 0.0;
        panel.add(new JLabel("Vulnerability:"), gbc);
        
        gbc.gridx = 3; gbc.weightx = 1.0;
        vulnerabilityField = new JTextField(monster.getVulnerability());
        panel.add(vulnerabilityField, gbc);
        
        // Row 2: Immunities
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.weightx = 0.0;
        panel.add(new JLabel("Immunities:"), gbc);
        
        gbc.gridy = 3; gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        immunitiesArea = new JTextArea(String.join(", ", monster.getImmunitiesList()));
        immunitiesArea.setLineWrap(true);
        immunitiesArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(immunitiesArea), gbc);
        
        return panel;
    }
    
    private JPanel createRecipePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Recipe"));
        
        StringBuilder recipeText = new StringBuilder();
        if (monster.getRecipe() != null) {
            recipeText.append("Name: ").append(monster.getRecipe().getName()).append("\n");
            recipeText.append("Time: ").append(monster.getRecipe().getTimeInMinutes()).append(" minutes\n");
            recipeText.append("Efficiency: ").append(monster.getRecipe().getEfficiency()).append("\n\n");
            recipeText.append("Ingredients:\n");
            
            for (Ingredient ingredient : monster.getRecipe().getIngredients().getIngredients()) {
                recipeText.append("- ").append(ingredient.getName())
                         .append(" (").append(ingredient.getQuantity()).append(")\n");
            }
        }
        
        recipeArea = new JTextArea(recipeText.toString());
        recipeArea.setEditable(false);
        recipeArea.setLineWrap(true);
        recipeArea.setWrapStyleWord(true);
        
        panel.add(new JScrollPane(recipeArea), BorderLayout.CENTER);
        return panel;
    }
    
    private void saveChanges() {
        try {
            Monster updatedMonster = new Monster();
            updatedMonster.setName(monster.getName()); // Name is not editable 
            updatedMonster.setType(typeArea.getText());
            updatedMonster.setHabitat(habitatField.getText());
            updatedMonster.setActivity(activityField.getText());
            updatedMonster.setFirstMention(firstMentionField.getText());
            updatedMonster.setSource(monster.getSource()); // Source is not editable 
            updatedMonster.setId((Integer) idSpinner.getValue());
            updatedMonster.setHeightInMetres((Double) heightSpinner.getValue());
            updatedMonster.setWeightInKilos((Double) weightSpinner.getValue());
            updatedMonster.setDangerLevel((Integer) dangerLevelSpinner.getValue());
            updatedMonster.setInvulnerability(invulnerabilityField.getText());
            updatedMonster.setVulnerability(vulnerabilityField.getText());
            Immunities immunities = new Immunities();
            immunities.setImmunities(List.of(immunitiesArea.getText().split(",\\s*")));
            updatedMonster.setImmunities(immunities);
            updatedMonster.setRecipe(monster.getRecipe()); // Recipe is not editable 
            
            controller.updateMonster(source, updatedMonster);
            
            JOptionPane.showMessageDialog(this, 
                "Monster details updated successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saving changes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}