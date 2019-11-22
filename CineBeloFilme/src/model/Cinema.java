/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Cinema {
    
    
    //Atributos
    
    private int id;
    private String nome;
    private List<Sala> salas;
    
    //construtor 

    public Cinema(int id, String nome, List<Sala> salas) {
        this.id = id;
        this.nome = nome;
        this.salas = salas;
    }

    // Metodos Públicos

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Sala> getSalas() {
        return salas;
    }
    
    
    
    
}
