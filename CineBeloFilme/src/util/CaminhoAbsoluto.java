/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
