/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author maria
 */
public class Ingredient {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Quantity")
    private int quantity;
    
    // Конструктор по умолчанию
    public Ingredient() {
    }
    
    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }  
}
