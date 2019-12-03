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
 * Finalidade: Modelo de objeto da classe Usuario
 * ---------------------------------------------------------------------------------------
 */
package model;

public class Usuario {

    // Atributos
    private String tipo;
    private String cpf;

    // Construtor
    public Usuario() {
        this.tipo = "admin";
        this.cpf = "";
    }

    // Get
    public String getTipo() {
        return tipo;
    }

    public String getCpf() {
        return cpf;
    }

    // Set
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
