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
 * Finalidade: Modelo de objeto da classe Entrada
 * ---------------------------------------------------------------------------------------
 */
package model;

public class Entrada {

    // Atributos
    private int id;
    private int filme_id;
    private int sala_id;
    private int sessao_id;
    private int poltrona_id;
    private boolean meia;
    private double preco;
    private String cpf;

    // Construtor
    public Entrada(int id, int filme_id, int sala_id, int sessao_id, int poltrona_id, boolean meia, double preco, String cpf) {
        this.id = id;
        this.filme_id = filme_id;
        this.sala_id = sala_id;
        this.sessao_id = sessao_id;
        this.poltrona_id = poltrona_id;
        this.meia = meia;
        this.preco = preco;
        this.cpf = cpf;
    }

    // Get
    public int getId() {
        return id;
    }

    public int getFilme_id() {
        return filme_id;
    }

    public int getSala_id() {
        return sala_id;
    }

    public int getSessao_id() {
        return sessao_id;
    }

    public int getPoltrona_id() {
        return poltrona_id;
    }

    public boolean isMeia() {
        return meia;
    }

    public double getPreco() {
        return preco;
    }

    public String getCpf() {
        return cpf;
    }
}
