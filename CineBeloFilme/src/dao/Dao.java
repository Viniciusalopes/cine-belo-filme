/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.Filme;
import model.Poltrona;
import model.Sala;
import model.Sessao;
import model.Usuario;

/**
 *
 * @author vovostudio
 */
public class Dao {

    // Atributos
    private Cinema cinema;
    private List<Sala> salas;
    private List<Filme> filmes;
    private List<Usuario> usuarios;

    // Parametros
    private int entrada_id;
    private int qtd_salas;
    private int qtd_sessoes_por_sala;
    private int qtd_fileiras;
    private int qtd_poltronas;
    private double preco_entrada;
    private int ocupacao_inicial;
    private String nome_cinema;
    private String[] nomes_salas;
    private String[] nomes_sessoes;

    /**
     * Inicializa dados do sistema
     */
    public Dao() {
        setValoresIniciais();
        setFilmes();
        setSalas();
        cinema = new Cinema(1, nome_cinema, salas, entrada_id, preco_entrada, ocupacao_inicial, filmes, usuarios);
    }

    // Get
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Inicializa valores
     */
    protected void setValoresIniciais() {

        salas = new ArrayList();
        filmes = new ArrayList();

        entrada_id = 1;
        qtd_salas = 3;
        qtd_sessoes_por_sala = 3;
        qtd_fileiras = 10;
        qtd_poltronas = 10;
        preco_entrada = 25.0;
        ocupacao_inicial = 20;

        nome_cinema = "Cine Belo Filme";

        nomes_salas = new String[3];
        nomes_salas[0] = "Vinicius";
        nomes_salas[1] = "Araujo";
        nomes_salas[2] = "Lopes";

        nomes_sessoes = new String[3];
        nomes_sessoes[0] = "Manhã";
        nomes_sessoes[1] = "Tarde";
        nomes_sessoes[2] = "Noite";

        usuarios = new ArrayList();
        usuarios.add(new Usuario());
        usuarios.get(0).setCpf("eitalala");
    }

    /**
     * Constrói lista de filmes
     */
    protected void setFilmes() {
        filmes = new ArrayList();
        filmes.add(new Filme(filmes.size() + 1, "Os Parças 2"));
        filmes.add(new Filme(filmes.size() + 1, "Doutor Sono"));
        filmes.add(new Filme(filmes.size() + 1, "Dora e a Cidade Perdida"));
        filmes.add(new Filme(filmes.size() + 1, "A Família Addams"));
        filmes.add(new Filme(filmes.size() + 1, "Invasão ao Serviço Secreto"));
        filmes.add(new Filme(filmes.size() + 1, "Coringa"));
        filmes.add(new Filme(filmes.size() + 1, "Malévola"));
        filmes.add(new Filme(filmes.size() + 1, "Mussum"));
        filmes.add(new Filme(filmes.size() + 1, "Maria do Caritó"));
    }

    /**
     * Constrói salas do cinema
     */
    protected void setSalas() {
        int indice_filme = 0;
        for (int i = 0; i < qtd_salas; i++) {
            salas.add(new Sala(i, nomes_salas[i], getSessoes(indice_filme)));
            indice_filme += salas.get(salas.size() - 1).getSessoes().size();
        }
    }

    /**
     * Constrói sessoes para as salas
     *
     * @param indice_filme Indice do próximo filme da lista
     * @return Lista de Sessões com poltronas vazias e filmes em cada sessão
     */
    protected List<Sessao> getSessoes(int indice_filme) {
        List<Sessao> sessoes = new ArrayList();
        for (int i = 0; i < qtd_sessoes_por_sala; i++) {
            sessoes.add(new Sessao(i, nomes_sessoes[i], getPoltronasVazias(), filmes.get(i + indice_filme)));
        }
        return sessoes;
    }

    /**
     * Constrói matriz de poltronas vazias
     *
     * @return Matriz de poltronas vazias
     */
    protected Poltrona[][] getPoltronasVazias() {
        Poltrona[][] poltronas = new Poltrona[qtd_fileiras][qtd_poltronas];
        char letra = 'A';
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                poltronas[i][j] = new Poltrona((i * poltronas.length) + (j), letra, j + 1);
            }
            letra++;
        }
        return poltronas;
    }

    
}
