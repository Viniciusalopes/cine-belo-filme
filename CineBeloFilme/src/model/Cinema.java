/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vovostudio
 */
public class Cinema {

    private int id;
    private String Nome;
    private List<Sala> salas;
    private int entrada_id;
    private double preco_entrada;
    private int ocupacao_inicial;
    private List<Entrada> entradas;
    private List<Filme> filmes;
    private List<Usuario> usuarios;

    public Cinema(int id, String Nome, List<Sala> salas,
            int entrada_id, double preco_entrada, int ocupacao_inicial, List<Filme> filmes,
            List<Usuario> usuarios) {
        this.id = id;
        this.Nome = Nome;
        this.salas = salas;
        this.entrada_id = entrada_id;
        this.preco_entrada = preco_entrada;
        this.ocupacao_inicial = ocupacao_inicial;
        this.entradas = new ArrayList();
        this.filmes = filmes;
        this.usuarios = usuarios;

    }

    // Get
    public int getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public int getEntrada_id() {
        return entrada_id;
    }

    public double getPreco_entrada() {
        return preco_entrada;
    }

    public int getOcupacao_inicial() {
        return ocupacao_inicial;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    
    // Set
    private void incrementaEntrada_id() {
        entrada_id += 1;
    }

    public void incluirEntrada(Entrada entrada) {
        entradas.add(entrada);
        incrementaEntrada_id();
    }

    public void excluirEntrada(int entrada_id) {
        for (Entrada e : entradas) {
            if (e.getId() == entrada_id) {
                entradas.remove(e);
                break;
            }
        }
    }
}
