/*
 * ---------------------------------------------------------------------------------------
 * Licença   : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *             <https://opensource.org/licenses/MIT>
 * ---------------------------------------------------------------------------------------
 * Criado em : novembro de 2019
 * ---------------------------------------------------------------------------------------
 * Projeto   : Projeto Integrador - Cine ABC
 * ---------------------------------------------------------------------------------------
 * Alunos    : Gustavo Henrique Ribeiro Martins
 *             Olair Soares de Almeida
 *             Vinicius Araujo Lopes
 * ---------------------------------------------------------------------------------------
 * Finalidade: Modelo de objeto da classe Cinema
 * ---------------------------------------------------------------------------------------
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int id;
    private String nome;
    private List<Sala> salas;
    private int entrada_id;
    private double preco_entrada;
    private int ocupacao_inicial;
    private List<Entrada> entradas;
    private List<Filme> filmes;
    private List<Usuario> usuarios;

    public Cinema(int id, String nome, List<Sala> salas,
            int entrada_id, double preco_entrada, int ocupacao_inicial, List<Filme> filmes,
            List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
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
        return nome;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Set
    private void incrementaEntrada_id() {
        entrada_id += 1;
    }

    /**
     * Inclui uma entrada na lista de entradas do cinema, e incrementa o contador de
     * entradas .
     *
     * @param entrada Objeto da classe Entrada
     */
    public void incluirEntrada(Entrada entrada) {
        entradas.add(entrada);
        incrementaEntrada_id();
    }

    /**
     * Exclui uma entrada da lista de entradas do cinema.
     *
     * @param entrada_id ID da entrada a ser excluída
     */
    public void excluirEntrada(int entrada_id) {
        for (Entrada e : entradas) {
            if (e.getId() == entrada_id) {
                entradas.remove(e);
                break;
            }
        }
    }
}
