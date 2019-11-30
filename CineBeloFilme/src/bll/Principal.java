/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public static void frameAcesso() {
        JFrameAcesso acesso = new JFrameAcesso(null);
        acesso.setLocationRelativeTo(null);
        acesso.setVisible(true);
    }
}
