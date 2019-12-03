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
 * Finalidade: Modelo de objeto da classe Filme
 * ---------------------------------------------------------------------------------------
 */
package model;

public class Filme {

    // Atributos
    private int id;
    private String titulo;

    // Construtor
    public Filme(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    // Get
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
