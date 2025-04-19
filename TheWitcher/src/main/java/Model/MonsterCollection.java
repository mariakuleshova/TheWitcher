/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 *
 * @author maria
 */
@JacksonXmlRootElement(localName = "Monsters")
public class MonsterCollection {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Monster")
    private List<Monster> monsters;

    // Конструктор по умолчанию
    public MonsterCollection() {
    }
    
    public MonsterCollection(List<Monster> monsters) {
        this.monsters = monsters;
    }
    
    public List<Monster> getMonster() {
        return monsters;
    }

    public void setMonster(List<Monster> monsters) {
        this.monsters = monsters;
    }
}
