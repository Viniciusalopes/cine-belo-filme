/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
<<<<<<< HEAD
 * @author vovostudio
 */
public class Entrada {

    private int id;
    private int filme_id;
    private int sala_id;
=======
 * @author Gustavo
 */
public class Entrada {
    
    
    //Atributos
    
    private int id;
    private int filme_id;
>>>>>>> 05b2ab742c4b37fa9610aaf60a7fa43cd95a820b
    private int sessao_id;
    private int poltrona_id;
    private boolean meia;
    private double preco;
<<<<<<< HEAD
    private String cpf;

    public Entrada(int id, int filme_id, int sala_id, int sessao_id, int poltrona_id, boolean meia, double preco, String cpf) {
        this.id = id;
        this.filme_id = filme_id;
        this.sala_id = sala_id;
=======
    
    
    // Construtor

    public Entrada(int id, int filme_id, int sessao_id, int poltrona_id, boolean meia, double preco) {
        this.id = id;
        this.filme_id = filme_id;
>>>>>>> 05b2ab742c4b37fa9610aaf60a7fa43cd95a820b
        this.sessao_id = sessao_id;
        this.poltrona_id = poltrona_id;
        this.meia = meia;
        this.preco = preco;
<<<<<<< HEAD
        this.cpf = cpf;
=======
    
    //Metodos Publicos
    
>>>>>>> 05b2ab742c4b37fa9610aaf60a7fa43cd95a820b
    }

    public int getId() {
        return id;
    }

    public int getFilme_id() {
        return filme_id;
    }

<<<<<<< HEAD
    public int getSala_id() {
        return sala_id;
    }

=======
>>>>>>> 05b2ab742c4b37fa9610aaf60a7fa43cd95a820b
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
<<<<<<< HEAD

    public String getCpf() {
        return cpf;
    }
=======
    
    
    
    
    
    
    
>>>>>>> 05b2ab742c4b37fa9610aaf60a7fa43cd95a820b
}
