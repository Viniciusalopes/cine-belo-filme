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
 * Finalidade: Modelo de objeto da classe Sessao
 * ---------------------------------------------------------------------------------------
 */
package model;

public class Sessao {

    // Atributos
    private int id;
    private String periodo;
    private Poltrona[][] poltronas;
    private Filme filme;

    // Construtor
    public Sessao(int id, String periodo, Poltrona[][] poltronas, Filme filme) {
        this.id = id;
        this.periodo = periodo;
        this.poltronas = poltronas;
        this.filme = filme;
    }

    // Get
    public int getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Poltrona[][] getPoltronas() {
        return poltronas;
    }

    public Filme getFilme() {
        return filme;
    }
}
