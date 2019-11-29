/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

/**
 *
 * @author vovostudio
 */
public class Util {

    /**
     * Gera um número aleatório
     *
     * @param limiteInferior Limite inferior para o número aleatório
     * @param limiteSuperior Limite superior para o número aleatório
     * @return Inteiro randômico
     */
    public static int sorteia(int limiteInferior, int limiteSuperior) {
        Random rd = new Random();
        return rd.nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
    }
}
