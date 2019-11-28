/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.Dao;
import java.util.List;
import model.Cinema;
import model.Entrada;
import model.Filme;
import model.Poltrona;
import model.Sala;
import model.Sessao;
import model.Usuario;
import static util.Util.sorteia;

/**
 *
 * @author vovostudio
 */
public class Bll {

    private Dao dao;
    private Cinema cinema;

    public Bll() {
        dao = new Dao();
        cinema = dao.getCinema();
        ocupacao_inicial(cinema.getOcupacao_inicial());
    }

    public List<Sala> getSalas() {
        return cinema.getSalas();
    }

    public List<Filme> getFilmes() {
        return cinema.getFilmes();
    }

    public Filme getFilme(int filme_id) {
        for (Filme f : getFilmes()) {
            if (f.getId() == filme_id) {
                return f;
            }
        }
        return null;
    }

    public Sessao getSessaoDoFilme(int filme_id) {
        for (Sala sl : cinema.getSalas()) {
            for (Sessao ss : sl.getSessoes()) {
                if (ss.getFilme().getId() == filme_id) {
                    return ss;
                }
            }
        }
        return null;
    }

    public Sala getSalaDaSessao(int sessao_id, int filme_id) {
        for (Sala sl : cinema.getSalas()) {
            for (Sessao ss : sl.getSessoes()) {
                if (ss.getId() == sessao_id
                        && ss.getFilme().getId() == filme_id) {
                    return sl;
                }
            }
        }
        return null;
    }

    public Poltrona[][] getPoltronas(int filme_id) {
        for (Sala sl : cinema.getSalas()) {
            for (Sessao ss : sl.getSessoes()) {
                if (ss.getFilme().getId() == filme_id) {
                    return ss.getPoltronas();
                }
            }
        }
        return null;
    }

    public String getLabelPoltrona(int fileira, int poltrona) {
        char letra = 'A';
        letra += fileira;
        return String.format("%s%d", letra, poltrona + 1);
    }

    public String getLabelPoltrona_id(int poltrona_id, int filme_id) {
        Poltrona[][] poltronas = getPoltronas(filme_id);
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                if (poltronas[i][j].getId() == poltrona_id) {
                    return String.format("%s%d", poltronas[i][j].getFileira(), poltronas[i][j].getNumero());
                }
            }
        }
        return null;
    }

    public List<Entrada> getEntradas() {
        return cinema.getEntradas();
    }

    public String[][] getRelatorio() {
        String[][] ret = new String[9][9];
        List<Entrada> entradas = getEntradas();
        List<Filme> filmes = getFilmes();
        double[][] totais = new double[9][6];

        Entrada entrada;
        Sessao sessao;
        int filme_id;

        for (int i = 0; i < filmes.size(); i++) {
            filme_id = filmes.get(i).getId();
            sessao = getSessaoDoFilme(filme_id);
            ret[i][0] = String.format("%d", filme_id);
            ret[i][1] = filmes.get(i).getTitulo();
            ret[i][2] = sessao.getPeriodo();
            for (int j = 0; j < entradas.size(); j++) {
                entrada = entradas.get(j);
                if (entrada.getFilme_id() == filme_id) {
                    if (entrada.isMeia()) {
                        totais[i][0]++;
                        totais[i][1] += entrada.getPreco();
                    } else {
                        totais[i][2]++;
                        totais[i][3] += entrada.getPreco();
                    }
                    totais[i][4]++;
                    totais[i][5] += entrada.getPreco();
                }
            }
        }

        for (int i = 0; i < totais.length; i++) {
            for (int j = 0; j < totais[i].length; j++) {
                ret[i][j + 3] = String.format("%.2f", totais[i][j]);
            }
        }
        return ret;
    }

    public double getPreco_entrada() {
        return cinema.getPreco_entrada();
    }

    public void incluirEntrada(int filme_id, int sala_id, int sessao_id, int poltrona_id, boolean meia, double preco, String cpf) {
        cinema.incluirEntrada(
                new Entrada(cinema.getEntrada_id(), filme_id, sala_id, sessao_id, poltrona_id, meia, preco, cpf)
        );
    }

    public void excluirEntrada(int entrada_id) {
        cinema.excluirEntrada(entrada_id);
    }

    private void ocupacao_inicial(int quantidade) {
        int[] sorteados;
        boolean meia;

        for (Sala sl : cinema.getSalas()) {
            for (Sessao ss : sl.getSessoes()) {
                sorteados = sorteiaDiferentes(quantidade, 0, 99);
                for (int i = 0; i < quantidade; i++) {
                    meia = (sorteia(0, 1) == 0) ? false : true;
                    incluirEntrada(
                            ss.getFilme().getId(),
                            sl.getId(),
                            ss.getId(),
                            ss.getPoltronas()[sorteados[i] / 10][sorteados[i] % 10].getId(),
                            meia,
                            (meia) ? cinema.getPreco_entrada() / 2 : cinema.getPreco_entrada(),
                            "eitalala"
                    );
                }
            }
        }
    }

    private int[] sorteiaDiferentes(int quantidade, int de, int ate) {
        int[] ret = new int[quantidade];
        boolean existe;
        for (int i = 0; i < quantidade; i++) {
            do {
                existe = false;
                ret[i] = sorteia(de, ate);
                for (int j = 0; j < i; j++) {
                    if (ret[j] == ret[i]) {
                        existe = true;
                        break;
                    }
                }
            } while (existe);
        }
        return ret;
    }

    public boolean usuarioValido(String tipo, String cpfSenha) {
        for (Usuario u : cinema.getUsuarios()) {
            if (u.getTipo().equals(tipo) && u.getCpf().equals(cpfSenha)) {
                return true;
            }
        }
        return false;
    }

}
