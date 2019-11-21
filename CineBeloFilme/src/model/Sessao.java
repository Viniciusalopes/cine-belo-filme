/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gustavo
 */

    import java.util.List;

public class Sessao {
 // Atributos   
    private int id;
    private String periodo;
    private Filme filme;
    private Poltrona[][] poltronas;
    
    //construtor

    public Sessao(int id, String periodo, Filme filme, Poltrona[][] poltronas) {
        this.id = id;
        this.periodo = periodo;
        this.filme = filme;
        this.poltronas = poltronas;
    }
    // metodos 

    public int getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Filme getFilme() {
        return filme;
    }

    public Poltrona[][] getPoltronas() {
        return poltronas;
    }
    
}
