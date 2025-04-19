/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;

/**
 *
 * @author maria
 */
public class Immunities {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Immunity")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> immunities;

    public List<String> getImmunities() {
        return immunities;
    }

    public void setImmunities(List<String> immunities) {
        this.immunities = immunities;
    }
}