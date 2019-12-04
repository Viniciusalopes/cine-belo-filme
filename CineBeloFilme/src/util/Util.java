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
 * Finalidade: Utilidades e utilitários
 * ---------------------------------------------------------------------------------------
 */
package util;

import java.util.Random;

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
