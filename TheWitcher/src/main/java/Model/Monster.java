/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class Monster {
    @JsonProperty("Name")
    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JsonProperty("Type")
    @JacksonXmlProperty(localName = "Type")
    private String type;

    @JsonProperty("Danger")
    @JacksonXmlProperty(localName = "Danger")
    private int dangerLevel;

    @JsonProperty("Habitat")
    @JacksonXmlProperty(localName = "Habitat")
    private String habitat;

    @JsonProperty("FirstMention")
    @JacksonXmlProperty(localName = "FirstMention")
    private String firstMention;

    @JsonProperty("Invulnerability")
    @JacksonXmlProperty(localName = "Invulnerability")
    private String invulnerability;
    
    @JsonProperty("Vulnerability")
    @JacksonXmlProperty(localName = "Vulnerability")
    private String vulnerability;

    @JsonProperty("HeightInMetres")
    @JacksonXmlProperty(localName = "HeightInMetres")
    private double heightInMetres;

    @JsonProperty("WeightInKilos")
    @JacksonXmlProperty(localName = "WeightInKilos")
    private double weightInKilos;

    @JsonProperty("Immunities")
    @JacksonXmlProperty(localName = "Immunities")
    private Immunities immunities;

    @JsonProperty("Activity")
    @JacksonXmlProperty(localName = "Activity")
    private String activity;

    @JsonProperty("Recipe")
    @JacksonXmlProperty(localName = "Recipe")
    private Recipe recipe;

    @JsonProperty("id")
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;
    
    private String source;

    // Конструктор по умолчанию
    public Monster() {
    }

    // Конструктор с параметрами 
    public Monster(String name, String type, int dangerLevel, String habitat, String firstMention, String invulnerability, String vulnerability, double heightInMetres, double weightInKilos, Immunities immunities, String activity, Recipe recipe, int id) {
        this.name = name;
        this.type = type;
        this.dangerLevel = dangerLevel;
        this.habitat = habitat;
        this.firstMention = firstMention;
        this.invulnerability = invulnerability;
        this.vulnerability = vulnerability;
        this.heightInMetres = heightInMetres;
        this.weightInKilos = weightInKilos;
        this.immunities = immunities;
        this.activity = activity;
        this.recipe = recipe;
        this.id = id;
    }
    
    // Геттеры и сеттеры для всех полей
    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }

    public String getType() { 
        return type; 
    }
    
    public void setType(String type) { 
        this.type = type; 
    }

    public String getHabitat() { 
        return habitat; 
    }
    
    public void setHabitat(String habitat) { 
        this.habitat = habitat; 
    }

    public int getDangerLevel() { 
        return dangerLevel; 
    }
    
    public void setDangerLevel(int dangerLevel) { 
        this.dangerLevel = dangerLevel; 
    }

    public String getSource() { 
        return source; 
    }
    
    public void setSource(String source) { 
        this.source = source; 
    }

    public String getFirstMention() {
        return firstMention;
    }

    public void setFirstMention(String firstMention) {
        this.firstMention = firstMention;
    }

    public String getInvulnerability() {
        return invulnerability;
    }

    public void setInvulnerability(String invulnerability) {
        this.invulnerability = invulnerability;
    }

    public String getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }
    
    public double getHeightInMetres() {
        return heightInMetres;
    }

    public void setHeightInMetres(double heightInMetres) {
        this.heightInMetres = heightInMetres;
    }

    public double getWeightInKilos() {
        return weightInKilos;
    }

    public void setWeightInKilos(double weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    public List<String> getImmunitiesList() {
        return immunities != null ? immunities.getImmunities() : new ArrayList<>();
    }

    public void setImmunities(Immunities immunities) {
        this.immunities = immunities;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
} 