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
 * Finalidade: Obter caminho absoluto atual da aplicação
 * ---------------------------------------------------------------------------------------
 */
package util;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author vovostudio
 */
public class CaminhoAbsoluto {

    /**
     * Caminho absoluto atual para *nix
     *
     * @return String com a saída do comando pwd
     * @throws IOException
     */
    public static String getLocalAbsoluto() throws IOException {
        // Fonte: https://www.vivaolinux.com.br/topico/Java/Runtime.getRuntime().exec()

        Process processo = Runtime.getRuntime().exec("pwd");
        InputStream in = processo.getInputStream();
        int c;
        String saida = "";

        while ((c = in.read()) != -1) {
            saida += ((char) c);
        }

        return saida;
    }
}
