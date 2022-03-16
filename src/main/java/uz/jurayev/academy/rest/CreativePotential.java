package uz.jurayev.academy.rest;

import lombok.Data;

import java.util.List;

@Data
public class CreativePotential {

    private String type;
    private List<String> category;
}
