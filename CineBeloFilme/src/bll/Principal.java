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
 * Finalidade: Cine Belo Filme - Classe Principal.
 * ---------------------------------------------------------------------------------------
 */
package bll;

import app.JFrameAcesso;
import app.JFrameCinema;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author vovostudio
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frameAcesso();
    }

    /**
     * Exibe o jFrame de seleção do tipo de acesso ao sistema.
     */
    public static void frameAcesso() {
        JFrameAcesso acesso = new JFrameAcesso(null);
        acesso.setLocationRelativeTo(null);
        acesso.setVisible(true);
    }
}
