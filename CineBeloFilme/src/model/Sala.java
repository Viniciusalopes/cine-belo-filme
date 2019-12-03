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
 * Finalidade: Modelo de objeto da classe Sala
 * ---------------------------------------------------------------------------------------
 */
package model;

import java.util.List;

public class Sala {

    // Atributos
    private int id;
    private String nome;
    private List<Sessao> sessoes;

    // Construtor
    public Sala(int id, String nome, List<Sessao> sessoes) {
        this.id = id;
        this.nome = nome;
        this.sessoes = sessoes;
    }

    // Métodos
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }
}
