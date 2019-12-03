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
 * Finalidade: Modelo de objeto da classe Poltrona
 * ---------------------------------------------------------------------------------------
 */
package model;

public class Poltrona {

    // Atributos
    private int id;
    private char fileira;
    private int numero;

    // Construtor
    public Poltrona(int id, char fileira, int numero) {
        this.id = id;
        this.fileira = fileira;
        this.numero = numero;

    }

    // Get
    public int getId() {
        return id;
    }

    public char getFileira() {
        return fileira;
    }

    public int getNumero() {
        return numero;
    }
}
