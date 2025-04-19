/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;

/**
 *
 * @author maria
 */
public class Ingredients {
//    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Ingredient")
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() { 
        return ingredients; 
    }
    
    public void setIngredients(List<Ingredient> ingredients) { 
        this.ingredients = ingredients; 
    }
}
