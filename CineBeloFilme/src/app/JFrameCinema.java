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
 * Finalidade: Tela principal com seleção de filmes, poltronas e relatório.
 * ---------------------------------------------------------------------------------------
 */
package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import bll.Bll;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrada;
import model.Filme;
import model.Poltrona;
import model.Sala;
import model.Sessao;
import model.Usuario;
import util.CaminhoAbsoluto;

public class JFrameCinema extends javax.swing.JFrame {

    // Variáveis
    private JToggleButton botao;
    private Bll bll;
    private List<Entrada> entradas;
    private Usuario usuario;

    private int filme_id, poltrona_id, entrada_id, sala_id, sessao_id;
    private boolean livre, meia;
    private String cpf;

    private String txtDisponivel = "A poltrona [ %s ] está DISPONÍVEL!";
    private String txtOcupada = "A poltrona [ %s ] já foi VENDIDA!";

    // DD = Disponível, II = Inteira, OO = Ocupada, MM = Meia entrada, SS = Sua poltrona
    private String txtAjudaDD;
    private String txtAjudaII;
    private String txtAjudaOO;
    private String txtAjudaMM;
    private String txtAjudaSS;

    private String localFonteUbuntu;
    private Font ubuntuMonoBold;

    /**
     * Creates new form JFrameCinema
     */
    public JFrameCinema(Usuario usuario, Bll bll) {
        initComponents();
        setValoresIniciais(usuario, bll);
        setAmbiente();
        setBotoesSalas();
    }

    // VOVOLINUX -------------------------------------------------------------------------
    /**
     * Oculta painel de lugares e de detalhes do lugar, obtém textos de ajuda da bll.
     *
     * @param usuario Tipo do usuário: "cliente" ou "admin"
     * @param bll Objeto da classe Bll com regras de negócio e dados do cinema
     */
    private void setValoresIniciais(Usuario usuario, Bll bll) {
        this.bll = bll;
        this.usuario = usuario;
        jPanelLugares.setVisible(false);

        jPanelDetalhes.setVisible(false);

        this.txtAjudaDD = bll.getTxtAjuda("disponivel");
        this.txtAjudaII = bll.getTxtAjuda("inteira");
        this.txtAjudaOO = bll.getTxtAjuda("ocupada");
        this.txtAjudaMM = bll.getTxtAjuda("meia");
        this.txtAjudaSS = bll.getTxtAjuda("sua");

        this.jLabelLegendaDD.setText(txtAjudaDD);
        this.jLabelLegendaII.setText(txtAjudaII);
        this.jLabelLegendaMM.setText(txtAjudaMM);

        this.jButtonII.setBackground(Color.red);
        this.jButtonMM.setBackground(Color.orange);

        setFonteUbuntu();
    }

    /**
     * Registra a fonte Ubunto Mono Bold, utilizada nos botões de poltronas.
     */
    private void setFonteUbuntu() {
        try {
            this.localFonteUbuntu = CaminhoAbsoluto.getLocalAbsoluto().split("\n")[0] + "/src/app/UbuntuMono-B.ttf";
            this.ubuntuMonoBold = Font.createFont(Font.TRUETYPE_FONT, new File(this.localFonteUbuntu)).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(this.ubuntuMonoBold);
        } catch (IOException e) {
            Logger.getLogger(JFrameCinema.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Oculta a tab de relatórios para usuário do tipo "cliente" e muda o texto do botão
     * de Vender para Comprar.
     */
    private void setAmbiente() {
        if (this.usuario.getTipo().equals("cliente")) {
            this.jTabbedPane.removeTabAt(1);
            this.jButtonVender.setText("Comprar");
        }
    }

    // Get e Set
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Atualiza o painel de salas com os nomes das salas, das sessões e dos filmes;
     *
     * Inclui evento nos botões por id do filme.
     */
    private void setBotoesSalas() {
        List<Sala> salas = bll.getSalas();

        // Salas e Sessões
        Sala sala = salas.get(0);
        this.jLabelSala0.setText("Sala " + sala.getNome());
        this.jLabelSessao00.setText(sala.getSessoes().get(0).getPeriodo());
        this.jLabelSessao01.setText(sala.getSessoes().get(1).getPeriodo());
        this.jLabelSessao02.setText(sala.getSessoes().get(2).getPeriodo());

        sala = salas.get(1);
        this.jLabelSala1.setText("Sala " + sala.getNome());
        this.jLabelSessao10.setText(sala.getSessoes().get(0).getPeriodo());
        this.jLabelSessao11.setText(sala.getSessoes().get(1).getPeriodo());
        this.jLabelSessao12.setText(sala.getSessoes().get(2).getPeriodo());

        sala = salas.get(2);
        this.jLabelSala2.setText("Sala " + sala.getNome());
        this.jLabelSessao20.setText(sala.getSessoes().get(0).getPeriodo());
        this.jLabelSessao21.setText(sala.getSessoes().get(1).getPeriodo());
        this.jLabelSessao22.setText(sala.getSessoes().get(2).getPeriodo());

        // Filmes
        List<Filme> filmes = bll.getFilmes();
        this.jToggleButtonFilme00.setText(filmes.get(0).getTitulo());
        this.jToggleButtonFilme00.addActionListener(getActionListenerButtonFilme(filmes.get(0).getId()));
        this.jToggleButtonFilme01.setText(filmes.get(1).getTitulo());
        this.jToggleButtonFilme01.addActionListener(getActionListenerButtonFilme(filmes.get(1).getId()));
        this.jToggleButtonFilme02.setText(filmes.get(2).getTitulo());
        this.jToggleButtonFilme02.addActionListener(getActionListenerButtonFilme(filmes.get(2).getId()));
        this.jToggleButtonFilme10.setText(filmes.get(3).getTitulo());
        this.jToggleButtonFilme10.addActionListener(getActionListenerButtonFilme(filmes.get(3).getId()));
        this.jToggleButtonFilme11.setText(filmes.get(4).getTitulo());
        this.jToggleButtonFilme11.addActionListener(getActionListenerButtonFilme(filmes.get(4).getId()));
        this.jToggleButtonFilme12.setText(filmes.get(5).getTitulo());
        this.jToggleButtonFilme12.addActionListener(getActionListenerButtonFilme(filmes.get(5).getId()));
        this.jToggleButtonFilme20.setText(filmes.get(6).getTitulo());
        this.jToggleButtonFilme20.addActionListener(getActionListenerButtonFilme(filmes.get(6).getId()));
        this.jToggleButtonFilme21.setText(filmes.get(7).getTitulo());
        this.jToggleButtonFilme21.addActionListener(getActionListenerButtonFilme(filmes.get(7).getId()));
        this.jToggleButtonFilme22.setText(filmes.get(8).getTitulo());
        this.jToggleButtonFilme22.addActionListener(getActionListenerButtonFilme(filmes.get(8).getId()));
    }

    /**
     * Atualiza os botões das poltronas da sessão do filme e ocupa as poltronas vendidas.
     *
     * @param filme_id Filme selecionado.
     */
    private void setBotoesPoltronas(int filme_id) {
        // Variáves
        this.filme_id = filme_id;
        Poltrona[][] poltronas = bll.getPoltronas(filme_id);
        this.entradas = bll.getEntradas();
        int poltrona_id;

        // Atualiza o texto de ajuda para usuário-cliente
        if (this.usuario.getTipo().equals("cliente")) {
            this.jLabelLegendaII.setText(this.txtAjudaOO);
            this.jLabelLegendaMM.setText(this.txtAjudaSS);
        }

        // Libera todas as poltronas da sessão
        for (int i = 0; i < poltronas.length; i++) {
            for (int j = 0; j < poltronas[i].length; j++) {
                poltrona_id = poltronas[i][j].getId();
                botao = getBotao(poltrona_id);
                botao.setFont(this.ubuntuMonoBold);
                botao.setBackground(null);
                botao.setText(bll.getLabelPoltrona(i, j));
                for (ActionListener al : botao.getActionListeners()) {
                    botao.removeActionListener(al);
                }
                botao.addActionListener(getActionListenerButtonPoltrona(poltrona_id, filme_id));
                buttonGroupLugares.clearSelection();
                liberaLugar(poltrona_id, botao);
            }
        }

        // Ocupa as poltronas vendidas
        for (Entrada e : entradas) {
            if (e.getFilme_id() == filme_id) {
                poltrona_id = e.getPoltrona_id();
                botao.setFont(this.ubuntuMonoBold);
                botao = getBotao(poltrona_id);
                if (this.usuario.getTipo().equals("cliente")) {
                    if (e.getCpf().equals(usuario.getCpf())) {
                        botao.setBackground(Color.orange);
                    } else {
                        botao.setBackground(Color.red);
                    }
                } else {
                    if (e.isMeia()) {
                        botao.setBackground(Color.orange);
                    } else {
                        botao.setBackground(Color.red);
                    }
                    ocupaLugar(poltrona_id, botao);
                }
            }
        }
    }

    private void liberaLugar(int poltrona_id, JToggleButton botao) {
        atualizaLugar(poltrona_id, botao);
    }

    private void ocupaLugar(int poltrona_id, JToggleButton botao) {
        atualizaLugar(poltrona_id, botao);
    }

    /**
     * Atualiza os detalhes da poltrona selecionada, no filme selecionado.
     *
     * @param poltrona_id ID da poltrona selecionada
     * @param filme_id ID do filme Selecionado
     */
    private void setDetalhes(int poltrona_id, int filme_id) {
        // Variáveis
        this.poltrona_id = poltrona_id;
        this.filme_id = filme_id;
        this.sessao_id = bll.getSessaoDoFilme(filme_id).getId();
        this.sala_id = bll.getSalaDaSessao(sessao_id, filme_id).getId();
        this.meia = false;
        this.livre = true;
        boolean visivel = true;

        String label = bll.getLabelPoltrona_id(poltrona_id, filme_id);

        // Busca a poltrona selecionada na lista de entradas
        for (Entrada e : entradas) {
            if (e.getPoltrona_id() == poltrona_id
                    && e.getFilme_id() == filme_id) {
                this.livre = false;
                this.entrada_id = e.getId();
                this.meia = e.isMeia();
                this.cpf = e.getCpf();
                break;
            }
        }

        // Botões e texto de ajuda
        if (livre) {
            this.jLabelStatus.setForeground(Color.blue);
            this.jLabelStatus.setText(String.format(this.txtDisponivel, label));

            this.jRadioButtonInteira.setSelected(true);
            this.jRadioButtonInteira.setEnabled(true);
            this.jRadioButtonMeia.setEnabled(true);

            this.jRadioButtonInteira.setVisible(visivel);
            this.jRadioButtonMeia.setVisible(visivel);

            this.jButtonVender.setVisible(true);
            this.jButtonDevolver.setVisible(false);
            this.jButtonVerIngresso.setVisible(false);
        } else {
            if (this.usuario.getTipo().equals("cliente")) {
                this.txtOcupada = "A poltrona [ %s ] já está OCUPADA!";

                visivel = (this.usuario.getCpf().equals(this.cpf)) ? true : false;
                if (visivel) {
                    this.txtOcupada = "Você já comprou a poltrona [ %s ].";
                }
            }
            this.jLabelStatus.setForeground(Color.red);
            this.jLabelStatus.setText(String.format(this.txtOcupada, label));

            if (this.meia) {
                this.jRadioButtonMeia.setSelected(true);
            } else {
                this.jRadioButtonInteira.setSelected(true);
            }

            this.jRadioButtonInteira.setVisible(visivel);
            this.jRadioButtonMeia.setVisible(visivel);

            this.jRadioButtonInteira.setEnabled(false);
            this.jRadioButtonMeia.setEnabled(false);

            this.jLabelPreco.setVisible(visivel);

            this.jButtonVender.setVisible(false);
            this.jButtonDevolver.setVisible(visivel);
            this.jButtonVerIngresso.setVisible(visivel);

        }
    }

    /**
     * Atualiza os detalhes da poltrona selecionada, no filme selecionado.
     *
     * @param poltrona_id ID da poltrona selecionada
     * @param filme_id ID do filme Selecionado
     */
    private void exibeDetalhesPoltrona(int poltrona_id, int filme_id) {
        if (!jPanelDetalhes.isVisible()) {
            jPanelDetalhes.setVisible(true);
        }
        setDetalhes(poltrona_id, filme_id);
    }

    /**
     * Exibe as poltronas do filme selecionado.
     *
     * @param filme_id ID do filme selecionado
     */
    private void exibePoltronas(int filme_id) {
        if (!jPanelLugares.isVisible()) {
            jPanelLugares.setVisible(true);
        }
        setBotoesPoltronas(filme_id);
        this.jPanelDetalhes.setVisible(false);
    }

    /**
     * Preenche a jTable com a matriz dos dados do relatório.
     */
    private void preencheTabela() {
        String[][] relatorio = bll.getRelatorio();
        if (relatorio != null) {
            for (int i = 0; i < relatorio.length - 2; i++) {
                for (int j = 0; j < relatorio[i].length; j++) {
                    this.jTableRelatorio.setValueAt(relatorio[i][j], i, j);
                }
            }
        }
        setLabelsTotal(relatorio);
    }

    /**
     * Atualiza os valores de totais do relatório.
     *
     * @param relatorio
     */
    private void setLabelsTotal(String[][] relatorio) {
        this.jLabelTotalMeia.setText(String.format(
                "R$ %.2f (%s unidades)",
                Double.parseDouble(relatorio[relatorio.length - 2][4].replace(",", ".")),
                relatorio[relatorio.length - 1][3]));

        this.jLabelTotalInteira.setText(String.format(
                "R$ %.2f (%s unidades)",
                Double.parseDouble(relatorio[relatorio.length - 2][6].replace(",", ".")),
                relatorio[relatorio.length - 1][5]));

        this.jLabelTotalGeral.setText(String.format(
                "R$ %.2f (%s unidades)",
                Double.parseDouble(relatorio[relatorio.length - 2][8].replace(",", ".")),
                relatorio[relatorio.length - 1][7]));

        /*
        relatorio[10][0] = Integer.toString(idMaiorA);
        relatorio[10][1] = String.format("%.2f", maiorA);
        relatorio[10][2] = Integer.toString(idMenorA);
        relatorio[10][3] = String.format("%.2f", menorA);
        relatorio[10][4] = Integer.toString(idMaiorB);
        relatorio[10][5] = String.format("%.0f", maiorB);
        relatorio[10][6] = Integer.toString(idMenorB);
        relatorio[10][7] = String.format("%.0f", menorB);
         */
        this.jLabelMaiorArrecadacao.setText(
                getTxtLabelMaiorMenor(
                        relatorio[relatorio.length - 1][0], relatorio[relatorio.length - 1][1], "R$ %s"));

        this.jLabelMenorArrecadacao.setText(
                getTxtLabelMaiorMenor(
                        relatorio[relatorio.length - 1][2], relatorio[relatorio.length - 1][3], "R$ %s"));

        this.jLabelMaiorBilheteria.setText(
                getTxtLabelMaiorMenor(
                        relatorio[relatorio.length - 1][4], relatorio[relatorio.length - 1][5], "%s unidades"));

        this.jLabelMenorBilheteria.setText(
                getTxtLabelMaiorMenor(
                        relatorio[relatorio.length - 1][6], relatorio[relatorio.length - 1][7], "%s unidades"));
    }

    /**
     * Formata o texto de total com o nome do filme e o formato (unidades ou reais).
     *
     * @param id ID do filme
     * @param valor Valor a ser formatado
     * @param formato Reais: "R$ %s", Unidades: "%s unidades"
     * @return Nome do filme com o valor formatado
     */
    private String getTxtLabelMaiorMenor(String id, String valor, String formato) {
        int filme_id = Integer.parseInt(id);
        String titulo = bll.getFilme(filme_id).getTitulo();
        return String.format("%04d - %s, com " + formato, filme_id, titulo, valor);
    }

    /**
     * Corrige alinhamento das colunas da tabela de relatório.
     */
    private void formataTabela() {

        /**
         * Alnhamento das células do jTable
         *
         * FONTE: https://www.guj.com.br//t/alinhamento-de-colunas-no-jtable/225200/2
         */
//        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();

//        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setOpaque(false);

        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        direita.setOpaque(false);

//        this.jTableRelatorio.getColumnModel().getColumn(0).setCellRenderer(esquerda);
        this.jTableRelatorio.getColumnModel().getColumn(2).setCellRenderer(centralizado);

        for (int i = 3; i < this.jTableRelatorio.getColumnCount(); i++) {
            this.jTableRelatorio.getColumnModel().getColumn(i).setCellRenderer(direita);
        }
    }

    /**
     * Calcula o preço e atualiza o jLabel com o valor.
     */
    private void atualizaLabelPreco() {
        this.meia = this.jRadioButtonMeia.isSelected();
        this.jLabelPreco.setVisible(true);
        this.jLabelPreco.setText(
                String.format(
                        "Preço: R$ %.2f",
                        (this.meia)
                                ? bll.getPreco_entrada() / 2
                                : (bll.getPreco_entrada())
                )
        );
    }

    /**
     * Realiza a venda do ingresso, solicita o cpf do cliente e oferece a impressão do
     * ingresso.
     */
    private void venderIngresso() {
        String cpf = "";
        String operacao = (this.usuario.getTipo().equals("cliente")) ? "compra" : "venda";
        boolean invalido = true;

        if (JOptionPane.showConfirmDialog(null, "Confirma a " + operacao + "?", "Confirmação:", 0) == 0) {
            do {
                cpf = JOptionPane.showInputDialog(null, "Número do CPF:");
                if (cpf == null) { // Cancelou
                    invalido = false;
                } else {
                    invalido = !bll.cpfValido(cpf);
                    if (invalido) {
                        JOptionPane.showMessageDialog(null, "Opa!\nCPF inválido!");
                    }
                }
            } while (invalido);

            if (cpf != null) {
                bll.incluirEntrada(this.filme_id, this.sala_id, this.sessao_id, this.poltrona_id,
                        this.meia, ((this.meia) ? bll.getPreco_entrada() / 2 : bll.getPreco_entrada()), cpf);
                setBotoesPoltronas(this.filme_id);
                setDetalhes(this.poltrona_id, this.filme_id);

                if (JOptionPane.showConfirmDialog(null, "Imprimir ingresso?", "Pergunta:", 0) == 0) {
                    verIngresso();
                }
            }
        }
    }

    /**
     * Impressão do ingresso na tela.
     */
    private void verIngresso() {
        Entrada e = bll.getEntrada(this.entrada_id);
        Filme f = bll.getFilme(e.getFilme_id());
        Sessao ss = bll.getSessaoDoFilme(f.getId());
        Sala sl = bll.getSalaDaSessao(ss.getId(), f.getId());

        String texto = String.format("INGRESSO Nº %06d\n\n", e.getId());
        texto += String.format("CPF do CLIENTE:\n%s\n\n", e.getCpf());
        texto += String.format("FILME: %s\n\n", f.getTitulo());
        texto += String.format("SESSÃO: %s\n\n", ss.getPeriodo());
        texto += String.format("SALA: %s\n\n", sl.getNome());
        texto += String.format("POLTRONA: %s\n\n", bll.getLabelPoltrona_id(e.getPoltrona_id(), f.getId()));
        texto += String.format("TIPO DE ENTRADA:\n%s\n\n", (e.isMeia()) ? "Meia-Entrada" : "Inteira");
        texto += String.format("PREÇO: R$ %.2f", e.getPreco());

        JOptionPane.showMessageDialog(null, texto);
    }

    /**
     * Realiza a devolução do ingresso e disponibiliza novamente a poltrona.
     */
    private void devolverIngresso() {
        if (JOptionPane.showConfirmDialog(null, "Confirma a devolução?", "Confirmação:", 0) == 0) {
            bll.excluirEntrada(this.entrada_id);
            setBotoesPoltronas(this.filme_id);
            setDetalhes(this.poltrona_id, this.filme_id);
        }
    }

    /**
     * Obtém o botão no estado atual.
     *
     * @param poltrona_id ID da poltrona correspondente ao botão
     * @return Objeto JToggleButton no estado atual.
     */
    private JToggleButton getBotao(int poltrona_id) {

        switch (poltrona_id) {
            case 0:
                return this.jToggleButton0;

            case 1:
                return this.jToggleButton1;

            case 2:
                return this.jToggleButton2;

            case 3:
                return this.jToggleButton3;

            case 4:
                return this.jToggleButton4;

            case 5:
                return this.jToggleButton5;

            case 6:
                return this.jToggleButton6;

            case 7:
                return this.jToggleButton7;

            case 8:
                return this.jToggleButton8;

            case 9:
                return this.jToggleButton9;

            case 10:
                return this.jToggleButton10;

            case 11:
                return this.jToggleButton11;

            case 12:
                return this.jToggleButton12;

            case 13:
                return this.jToggleButton13;

            case 14:
                return this.jToggleButton14;

            case 15:
                return this.jToggleButton15;

            case 16:
                return this.jToggleButton16;

            case 17:
                return this.jToggleButton17;

            case 18:
                return this.jToggleButton18;

            case 19:
                return this.jToggleButton19;

            case 20:
                return this.jToggleButton20;

            case 21:
                return this.jToggleButton21;

            case 22:
                return this.jToggleButton22;

            case 23:
                return this.jToggleButton23;

            case 24:
                return this.jToggleButton24;

            case 25:
                return this.jToggleButton25;

            case 26:
                return this.jToggleButton26;

            case 27:
                return this.jToggleButton27;

            case 28:
                return this.jToggleButton28;

            case 29:
                return this.jToggleButton29;

            case 30:
                return this.jToggleButton30;

            case 31:
                return this.jToggleButton31;

            case 32:
                return this.jToggleButton32;

            case 33:
                return this.jToggleButton33;

            case 34:
                return this.jToggleButton34;

            case 35:
                return this.jToggleButton35;

            case 36:
                return this.jToggleButton36;

            case 37:
                return this.jToggleButton37;

            case 38:
                return this.jToggleButton38;

            case 39:
                return this.jToggleButton39;

            case 40:
                return this.jToggleButton40;

            case 41:
                return this.jToggleButton41;

            case 42:
                return this.jToggleButton42;

            case 43:
                return this.jToggleButton43;

            case 44:
                return this.jToggleButton44;

            case 45:
                return this.jToggleButton45;

            case 46:
                return this.jToggleButton46;

            case 47:
                return this.jToggleButton47;

            case 48:
                return this.jToggleButton48;

            case 49:
                return this.jToggleButton49;

            case 50:
                return this.jToggleButton50;

            case 51:
                return this.jToggleButton51;

            case 52:
                return this.jToggleButton52;

            case 53:
                return this.jToggleButton53;

            case 54:
                return this.jToggleButton54;

            case 55:
                return this.jToggleButton55;

            case 56:
                return this.jToggleButton56;

            case 57:
                return this.jToggleButton57;

            case 58:
                return this.jToggleButton58;

            case 59:
                return this.jToggleButton59;

            case 60:
                return this.jToggleButton60;

            case 61:
                return this.jToggleButton61;

            case 62:
                return this.jToggleButton62;

            case 63:
                return this.jToggleButton63;

            case 64:
                return this.jToggleButton64;

            case 65:
                return this.jToggleButton65;

            case 66:
                return this.jToggleButton66;

            case 67:
                return this.jToggleButton67;

            case 68:
                return this.jToggleButton68;

            case 69:
                return this.jToggleButton69;

            case 70:
                return this.jToggleButton70;

            case 71:
                return this.jToggleButton71;

            case 72:
                return this.jToggleButton72;

            case 73:
                return this.jToggleButton73;

            case 74:
                return this.jToggleButton74;

            case 75:
                return this.jToggleButton75;

            case 76:
                return this.jToggleButton76;

            case 77:
                return this.jToggleButton77;

            case 78:
                return this.jToggleButton78;

            case 79:
                return this.jToggleButton79;

            case 80:
                return this.jToggleButton80;

            case 81:
                return this.jToggleButton81;

            case 82:
                return this.jToggleButton82;

            case 83:
                return this.jToggleButton83;

            case 84:
                return this.jToggleButton84;

            case 85:
                return this.jToggleButton85;

            case 86:
                return this.jToggleButton86;

            case 87:
                return this.jToggleButton87;

            case 88:
                return this.jToggleButton88;

            case 89:
                return this.jToggleButton89;

            case 90:
                return this.jToggleButton90;

            case 91:
                return this.jToggleButton91;

            case 92:
                return this.jToggleButton92;

            case 93:
                return this.jToggleButton93;

            case 94:
                return this.jToggleButton94;

            case 95:
                return this.jToggleButton95;

            case 96:
                return this.jToggleButton96;

            case 97:
                return this.jToggleButton97;

            case 98:
                return this.jToggleButton98;

            case 99:
                return this.jToggleButton99;

            default:
                throw new AssertionError();
        }
    }

    /**
     * Atualiza o botão com novas informações sobre a ocupação da poltrona correspondente.
     *
     * @param poltrona_id ID da poltrona correspondente ao botão
     * @param botao Novo botão
     */
    private void atualizaLugar(int poltrona_id, JToggleButton botao) {

        switch (poltrona_id) {
            case 0:
                this.jToggleButton0 = botao;
                break;
            case 1:
                this.jToggleButton1 = botao;
                break;
            case 2:
                this.jToggleButton2 = botao;
                break;
            case 3:
                this.jToggleButton3 = botao;
                break;
            case 4:
                this.jToggleButton4 = botao;
                break;
            case 5:
                this.jToggleButton5 = botao;
                break;
            case 6:
                this.jToggleButton6 = botao;
                break;
            case 7:
                this.jToggleButton7 = botao;
                break;
            case 8:
                this.jToggleButton8 = botao;
                break;
            case 9:
                this.jToggleButton9 = botao;
                break;
            case 10:
                this.jToggleButton10 = botao;
                break;
            case 11:
                this.jToggleButton11 = botao;
                break;
            case 12:
                this.jToggleButton12 = botao;
                break;
            case 13:
                this.jToggleButton13 = botao;
                break;
            case 14:
                this.jToggleButton14 = botao;
                break;
            case 15:
                this.jToggleButton15 = botao;
                break;
            case 16:
                this.jToggleButton16 = botao;
                break;
            case 17:
                this.jToggleButton17 = botao;
                break;
            case 18:
                this.jToggleButton18 = botao;
                break;
            case 19:
                this.jToggleButton19 = botao;
                break;
            case 20:
                this.jToggleButton20 = botao;
                break;
            case 21:
                this.jToggleButton21 = botao;
                break;
            case 22:
                this.jToggleButton22 = botao;
                break;
            case 23:
                this.jToggleButton23 = botao;
                break;
            case 24:
                this.jToggleButton24 = botao;
                break;
            case 25:
                this.jToggleButton25 = botao;
                break;
            case 26:
                this.jToggleButton26 = botao;
                break;
            case 27:
                this.jToggleButton27 = botao;
                break;
            case 28:
                this.jToggleButton28 = botao;
                break;
            case 29:
                this.jToggleButton29 = botao;
                break;
            case 30:
                this.jToggleButton30 = botao;
                break;
            case 31:
                this.jToggleButton31 = botao;
                break;
            case 32:
                this.jToggleButton32 = botao;
                break;
            case 33:
                this.jToggleButton33 = botao;
                break;
            case 34:
                this.jToggleButton34 = botao;
                break;
            case 35:
                this.jToggleButton35 = botao;
                break;
            case 36:
                this.jToggleButton36 = botao;
                break;
            case 37:
                this.jToggleButton37 = botao;
                break;
            case 38:
                this.jToggleButton38 = botao;
                break;
            case 39:
                this.jToggleButton39 = botao;
                break;
            case 40:
                this.jToggleButton40 = botao;
                break;
            case 41:
                this.jToggleButton41 = botao;
                break;
            case 42:
                this.jToggleButton42 = botao;
                break;
            case 43:
                this.jToggleButton43 = botao;
                break;
            case 44:
                this.jToggleButton44 = botao;
                break;
            case 45:
                this.jToggleButton45 = botao;
                break;
            case 46:
                this.jToggleButton46 = botao;
                break;
            case 47:
                this.jToggleButton47 = botao;
                break;
            case 48:
                this.jToggleButton48 = botao;
                break;
            case 49:
                this.jToggleButton49 = botao;
                break;
            case 50:
                this.jToggleButton50 = botao;
                break;
            case 51:
                this.jToggleButton51 = botao;
                break;
            case 52:
                this.jToggleButton52 = botao;
                break;
            case 53:
                this.jToggleButton53 = botao;
                break;
            case 54:
                this.jToggleButton54 = botao;
                break;
            case 55:
                this.jToggleButton55 = botao;
                break;
            case 56:
                this.jToggleButton56 = botao;
                break;
            case 57:
                this.jToggleButton57 = botao;
                break;
            case 58:
                this.jToggleButton58 = botao;
                break;
            case 59:
                this.jToggleButton59 = botao;
                break;
            case 60:
                this.jToggleButton60 = botao;
                break;
            case 61:
                this.jToggleButton61 = botao;
                break;
            case 62:
                this.jToggleButton62 = botao;
                break;
            case 63:
                this.jToggleButton63 = botao;
                break;
            case 64:
                this.jToggleButton64 = botao;
                break;
            case 65:
                this.jToggleButton65 = botao;
                break;
            case 66:
                this.jToggleButton66 = botao;
                break;
            case 67:
                this.jToggleButton67 = botao;
                break;
            case 68:
                this.jToggleButton68 = botao;
                break;
            case 69:
                this.jToggleButton69 = botao;
                break;
            case 70:
                this.jToggleButton70 = botao;
                break;
            case 71:
                this.jToggleButton71 = botao;
                break;
            case 72:
                this.jToggleButton72 = botao;
                break;
            case 73:
                this.jToggleButton73 = botao;
                break;
            case 74:
                this.jToggleButton74 = botao;
                break;
            case 75:
                this.jToggleButton75 = botao;
                break;
            case 76:
                this.jToggleButton76 = botao;
                break;
            case 77:
                this.jToggleButton77 = botao;
                break;
            case 78:
                this.jToggleButton78 = botao;
                break;
            case 79:
                this.jToggleButton79 = botao;
                break;
            case 80:
                this.jToggleButton80 = botao;
                break;
            case 81:
                this.jToggleButton81 = botao;
                break;
            case 82:
                this.jToggleButton82 = botao;
                break;
            case 83:
                this.jToggleButton83 = botao;
                break;
            case 84:
                this.jToggleButton84 = botao;
                break;
            case 85:
                this.jToggleButton85 = botao;
                break;
            case 86:
                this.jToggleButton86 = botao;
                break;
            case 87:
                this.jToggleButton87 = botao;
                break;
            case 88:
                this.jToggleButton88 = botao;
                break;
            case 89:
                this.jToggleButton89 = botao;
                break;
            case 90:
                this.jToggleButton90 = botao;
                break;
            case 91:
                this.jToggleButton91 = botao;
                break;
            case 92:
                this.jToggleButton92 = botao;
                break;
            case 93:
                this.jToggleButton93 = botao;
                break;
            case 94:
                this.jToggleButton94 = botao;
                break;
            case 95:
                this.jToggleButton95 = botao;
                break;
            case 96:
                this.jToggleButton96 = botao;
                break;
            case 97:
                this.jToggleButton97 = botao;
                break;
            case 98:
                this.jToggleButton98 = botao;
                break;
            case 99:
                this.jToggleButton99 = botao;
                break;
            default:
                throw new AssertionError();
        }
    }

    // Eventos ---------------------------------------------------------------------------
    /**
     * Exibe os detalhes da poltrona selecionada.
     *
     * @param poltrona_id ID da poltrona selecionada
     * @param filme_id ID do filme selecionado
     */
    private void jToggleButtonActionPerformedButtonPoltrona(int poltrona_id, int filme_id) {
        exibeDetalhesPoltrona(poltrona_id, filme_id);
    }

    /**
     * Retorna um ActionListener para os botões de poltronas.
     *
     * @param poltrona_id ID da poltrona correspondente ao botão
     * @param filme_id ID do filme selecionado
     * @return Listener com id da poltrona e do filme
     */
    private ActionListener getActionListenerButtonPoltrona(int poltrona_id, int filme_id) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButtonActionPerformedButtonPoltrona(poltrona_id, filme_id);
            }
        };
    }

    /**
     * Exibe os botões das poltronas para o filme selecionado.
     *
     * @param filme_id ID do filme selecionado
     */
    private void jToogleButtonFilmeActionPerformedButtonFilme(int filme_id) {
        exibePoltronas(filme_id);
    }

    /**
     * Retorna um AcionListener para os botões de filmes
     *
     * @param filme_id ID do filme correspondente ao botão
     * @return Listener com o id do filme
     */
    private ActionListener getActionListenerButtonFilme(int filme_id) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToogleButtonFilmeActionPerformedButtonFilme(filme_id);
            }
        };
    }

    // Fim Eventos -----------------------------------------------------------------------
    //
    // VOVOLINUX -------------------------------------------------------------------------
    //
    //
    /**
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupFilmes = new javax.swing.ButtonGroup();
        buttonGroupLugares = new javax.swing.ButtonGroup();
        buttonGroupMeia = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelFilmes = new javax.swing.JPanel();
        jPanelSala0 = new javax.swing.JPanel();
        jLabelSala0 = new javax.swing.JLabel();
        jToggleButtonFilme00 = new javax.swing.JToggleButton();
        jToggleButtonFilme01 = new javax.swing.JToggleButton();
        jToggleButtonFilme02 = new javax.swing.JToggleButton();
        jLabelSessao00 = new javax.swing.JLabel();
        jLabelSessao01 = new javax.swing.JLabel();
        jLabelSessao02 = new javax.swing.JLabel();
        jPanelLugares = new javax.swing.JPanel();
        jLabelLugares = new javax.swing.JLabel();
        jToggleButton0 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jToggleButton12 = new javax.swing.JToggleButton();
        jToggleButton13 = new javax.swing.JToggleButton();
        jToggleButton14 = new javax.swing.JToggleButton();
        jToggleButton15 = new javax.swing.JToggleButton();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton19 = new javax.swing.JToggleButton();
        jToggleButton20 = new javax.swing.JToggleButton();
        jToggleButton21 = new javax.swing.JToggleButton();
        jToggleButton22 = new javax.swing.JToggleButton();
        jToggleButton23 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jToggleButton25 = new javax.swing.JToggleButton();
        jToggleButton26 = new javax.swing.JToggleButton();
        jToggleButton27 = new javax.swing.JToggleButton();
        jToggleButton28 = new javax.swing.JToggleButton();
        jToggleButton29 = new javax.swing.JToggleButton();
        jToggleButton30 = new javax.swing.JToggleButton();
        jToggleButton31 = new javax.swing.JToggleButton();
        jToggleButton32 = new javax.swing.JToggleButton();
        jToggleButton33 = new javax.swing.JToggleButton();
        jToggleButton34 = new javax.swing.JToggleButton();
        jToggleButton35 = new javax.swing.JToggleButton();
        jToggleButton36 = new javax.swing.JToggleButton();
        jToggleButton37 = new javax.swing.JToggleButton();
        jToggleButton38 = new javax.swing.JToggleButton();
        jToggleButton39 = new javax.swing.JToggleButton();
        jToggleButton40 = new javax.swing.JToggleButton();
        jToggleButton41 = new javax.swing.JToggleButton();
        jToggleButton42 = new javax.swing.JToggleButton();
        jToggleButton43 = new javax.swing.JToggleButton();
        jToggleButton44 = new javax.swing.JToggleButton();
        jToggleButton45 = new javax.swing.JToggleButton();
        jToggleButton46 = new javax.swing.JToggleButton();
        jToggleButton47 = new javax.swing.JToggleButton();
        jToggleButton48 = new javax.swing.JToggleButton();
        jToggleButton49 = new javax.swing.JToggleButton();
        jToggleButton50 = new javax.swing.JToggleButton();
        jToggleButton51 = new javax.swing.JToggleButton();
        jToggleButton52 = new javax.swing.JToggleButton();
        jToggleButton53 = new javax.swing.JToggleButton();
        jToggleButton54 = new javax.swing.JToggleButton();
        jToggleButton55 = new javax.swing.JToggleButton();
        jToggleButton56 = new javax.swing.JToggleButton();
        jToggleButton57 = new javax.swing.JToggleButton();
        jToggleButton58 = new javax.swing.JToggleButton();
        jToggleButton59 = new javax.swing.JToggleButton();
        jToggleButton61 = new javax.swing.JToggleButton();
        jToggleButton62 = new javax.swing.JToggleButton();
        jToggleButton63 = new javax.swing.JToggleButton();
        jToggleButton64 = new javax.swing.JToggleButton();
        jToggleButton65 = new javax.swing.JToggleButton();
        jToggleButton66 = new javax.swing.JToggleButton();
        jToggleButton67 = new javax.swing.JToggleButton();
        jToggleButton68 = new javax.swing.JToggleButton();
        jToggleButton69 = new javax.swing.JToggleButton();
        jToggleButton70 = new javax.swing.JToggleButton();
        jToggleButton71 = new javax.swing.JToggleButton();
        jToggleButton72 = new javax.swing.JToggleButton();
        jToggleButton73 = new javax.swing.JToggleButton();
        jToggleButton74 = new javax.swing.JToggleButton();
        jToggleButton75 = new javax.swing.JToggleButton();
        jToggleButton76 = new javax.swing.JToggleButton();
        jToggleButton77 = new javax.swing.JToggleButton();
        jToggleButton78 = new javax.swing.JToggleButton();
        jToggleButton79 = new javax.swing.JToggleButton();
        jToggleButton80 = new javax.swing.JToggleButton();
        jToggleButton81 = new javax.swing.JToggleButton();
        jToggleButton82 = new javax.swing.JToggleButton();
        jToggleButton83 = new javax.swing.JToggleButton();
        jToggleButton84 = new javax.swing.JToggleButton();
        jToggleButton85 = new javax.swing.JToggleButton();
        jToggleButton86 = new javax.swing.JToggleButton();
        jToggleButton87 = new javax.swing.JToggleButton();
        jToggleButton88 = new javax.swing.JToggleButton();
        jToggleButton89 = new javax.swing.JToggleButton();
        jToggleButton90 = new javax.swing.JToggleButton();
        jToggleButton91 = new javax.swing.JToggleButton();
        jToggleButton92 = new javax.swing.JToggleButton();
        jToggleButton93 = new javax.swing.JToggleButton();
        jToggleButton94 = new javax.swing.JToggleButton();
        jToggleButton95 = new javax.swing.JToggleButton();
        jToggleButton96 = new javax.swing.JToggleButton();
        jToggleButton97 = new javax.swing.JToggleButton();
        jToggleButton98 = new javax.swing.JToggleButton();
        jToggleButton99 = new javax.swing.JToggleButton();
        jToggleButton60 = new javax.swing.JToggleButton();
        jLabelLegenda = new javax.swing.JLabel();
        jButtonDD = new javax.swing.JButton();
        jButtonII = new javax.swing.JButton();
        jButtonMM = new javax.swing.JButton();
        jLabelLegendaDD = new javax.swing.JLabel();
        jLabelLegendaII = new javax.swing.JLabel();
        jLabelLegendaMM = new javax.swing.JLabel();
        jPanelDetalhes = new javax.swing.JPanel();
        jRadioButtonInteira = new javax.swing.JRadioButton();
        jRadioButtonMeia = new javax.swing.JRadioButton();
        jLabelPreco = new javax.swing.JLabel();
        jButtonVender = new javax.swing.JButton();
        jButtonVerIngresso = new javax.swing.JButton();
        jButtonDevolver = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();
        jPanelSala1 = new javax.swing.JPanel();
        jLabelSala1 = new javax.swing.JLabel();
        jToggleButtonFilme10 = new javax.swing.JToggleButton();
        jToggleButtonFilme11 = new javax.swing.JToggleButton();
        jToggleButtonFilme12 = new javax.swing.JToggleButton();
        jLabelSessao10 = new javax.swing.JLabel();
        jLabelSessao11 = new javax.swing.JLabel();
        jLabelSessao12 = new javax.swing.JLabel();
        jPanelSala2 = new javax.swing.JPanel();
        jLabelSala2 = new javax.swing.JLabel();
        jToggleButtonFilme20 = new javax.swing.JToggleButton();
        jToggleButtonFilme21 = new javax.swing.JToggleButton();
        jToggleButtonFilme22 = new javax.swing.JToggleButton();
        jLabelSessao20 = new javax.swing.JLabel();
        jLabelSessao21 = new javax.swing.JLabel();
        jLabelSessao22 = new javax.swing.JLabel();
        jPanelRelatorio = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRelatorio = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTotalInteira = new javax.swing.JLabel();
        jLabelTotalMeia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelTotalGeral = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelMaiorBilheteria = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelMenorBilheteria = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelMaiorArrecadacao = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelMenorArrecadacao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cine Belo Filme");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jPanelSala0.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSala0.setText("jLabelSala0");

        buttonGroupFilmes.add(jToggleButtonFilme00);
        jToggleButtonFilme00.setText("jToggleButtonFilme00");

        buttonGroupFilmes.add(jToggleButtonFilme01);
        jToggleButtonFilme01.setText("jToggleButtonFilme01");
        jToggleButtonFilme01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonFilme01ActionPerformed(evt);
            }
        });

        buttonGroupFilmes.add(jToggleButtonFilme02);
        jToggleButtonFilme02.setText("jToggleButtonFilme02");

        jLabelSessao00.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao00.setText("jLabelSessao00");
        jLabelSessao00.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao01.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao01.setText("jLabelSessao01");
        jLabelSessao01.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao02.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao02.setText("jLabelSessao02");
        jLabelSessao02.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanelSala0Layout = new javax.swing.GroupLayout(jPanelSala0);
        jPanelSala0.setLayout(jPanelSala0Layout);
        jPanelSala0Layout.setHorizontalGroup(
            jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala0Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSala0Layout.createSequentialGroup()
                        .addComponent(jLabelSala0)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSala0Layout.createSequentialGroup()
                        .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSala0Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabelSessao00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelSessao01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSessao02, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToggleButtonFilme02, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelSala0Layout.setVerticalGroup(
            jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSala0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSessao00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonFilme00))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme01)
                    .addComponent(jLabelSessao01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme02)
                    .addComponent(jLabelSessao02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelLugares.setText("Lugares da sala");

        buttonGroupLugares.add(jToggleButton0);
        jToggleButton0.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton0.setText("00");
        jToggleButton0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton0.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton0.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton0.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton1);
        jToggleButton1.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton1.setText("00");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton2.setText("00");
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton2.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton2.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton3);
        jToggleButton3.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton3.setText("00");
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton3.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton3.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton3.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton4);
        jToggleButton4.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton4.setText("00");
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton4.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton4.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton4.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton5);
        jToggleButton5.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton5.setText("00");
        jToggleButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton5.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton5.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton5.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton6);
        jToggleButton6.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton6.setText("00");
        jToggleButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton6.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton6.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton6.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton7);
        jToggleButton7.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton7.setText("00");
        jToggleButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton7.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton7.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton7.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton8);
        jToggleButton8.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton8.setText("00");
        jToggleButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton8.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton8.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton8.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton9);
        jToggleButton9.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton9.setText("00");
        jToggleButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton9.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton9.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton9.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton10);
        jToggleButton10.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton10.setText("00");
        jToggleButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton10.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton10.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton10.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton11);
        jToggleButton11.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton11.setText("00");
        jToggleButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton11.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton11.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton11.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton12);
        jToggleButton12.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton12.setText("00");
        jToggleButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton12.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton12.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton12.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton13);
        jToggleButton13.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton13.setText("00");
        jToggleButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton13.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton13.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton13.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton14);
        jToggleButton14.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton14.setText("00");
        jToggleButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton14.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton14.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton14.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton15);
        jToggleButton15.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton15.setText("00");
        jToggleButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton15.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton15.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton15.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton16);
        jToggleButton16.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton16.setText("00");
        jToggleButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton16.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton16.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton16.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton17);
        jToggleButton17.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton17.setText("00");
        jToggleButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton17.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton17.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton17.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton18);
        jToggleButton18.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton18.setText("00");
        jToggleButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton18.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton18.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton18.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton19);
        jToggleButton19.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton19.setText("00");
        jToggleButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton19.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton19.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton19.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton20);
        jToggleButton20.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton20.setText("00");
        jToggleButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton20.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton20.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton20.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton21);
        jToggleButton21.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton21.setText("00");
        jToggleButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton21.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton21.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton21.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton22);
        jToggleButton22.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton22.setText("00");
        jToggleButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton22.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton22.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton22.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton23);
        jToggleButton23.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton23.setText("00");
        jToggleButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton23.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton23.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton23.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton24);
        jToggleButton24.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton24.setText("00");
        jToggleButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton24.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton24.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton24.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton25);
        jToggleButton25.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton25.setText("00");
        jToggleButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton25.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton25.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton25.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton26);
        jToggleButton26.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton26.setText("00");
        jToggleButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton26.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton26.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton26.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton27);
        jToggleButton27.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton27.setText("00");
        jToggleButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton27.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton27.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton27.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton28);
        jToggleButton28.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton28.setText("00");
        jToggleButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton28.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton28.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton28.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton29);
        jToggleButton29.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton29.setText("00");
        jToggleButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton29.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton29.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton29.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton30);
        jToggleButton30.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton30.setText("00");
        jToggleButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton30.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton30.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton30.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton31);
        jToggleButton31.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton31.setText("00");
        jToggleButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton31.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton31.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton31.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton32);
        jToggleButton32.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton32.setText("00");
        jToggleButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton32.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton32.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton32.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton33);
        jToggleButton33.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton33.setText("00");
        jToggleButton33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton33.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton33.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton33.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton34);
        jToggleButton34.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton34.setText("00");
        jToggleButton34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton34.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton34.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton34.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton35);
        jToggleButton35.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton35.setText("00");
        jToggleButton35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton35.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton35.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton35.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton36);
        jToggleButton36.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton36.setText("00");
        jToggleButton36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton36.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton36.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton36.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton37);
        jToggleButton37.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton37.setText("00");
        jToggleButton37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton37.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton37.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton37.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton38);
        jToggleButton38.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton38.setText("00");
        jToggleButton38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton38.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton38.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton38.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton39);
        jToggleButton39.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton39.setText("00");
        jToggleButton39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton39.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton39.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton39.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton40);
        jToggleButton40.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton40.setText("00");
        jToggleButton40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton40.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton40.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton40.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton41);
        jToggleButton41.setFont(new java.awt.Font("Ubuntu Mono", 1, 16)); // NOI18N
        jToggleButton41.setText("00");
        jToggleButton41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton41.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton41.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton41.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton42);
        jToggleButton42.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton42.setText("00");
        jToggleButton42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton42.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton42.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton42.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton43);
        jToggleButton43.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton43.setText("00");
        jToggleButton43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton43.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton43.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton43.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton44);
        jToggleButton44.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton44.setText("00");
        jToggleButton44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton44.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton44.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton44.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton45);
        jToggleButton45.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton45.setText("00");
        jToggleButton45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton45.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton45.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton45.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton46);
        jToggleButton46.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton46.setText("00");
        jToggleButton46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton46.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton46.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton46.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton47);
        jToggleButton47.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton47.setText("00");
        jToggleButton47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton47.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton47.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton47.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton48);
        jToggleButton48.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton48.setText("00");
        jToggleButton48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton48.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton48.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton48.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton49);
        jToggleButton49.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton49.setText("00");
        jToggleButton49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton49.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton49.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton49.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton50);
        jToggleButton50.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton50.setText("00");
        jToggleButton50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton50.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton50.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton50.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton51);
        jToggleButton51.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton51.setText("00");
        jToggleButton51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton51.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton51.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton51.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton52);
        jToggleButton52.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton52.setText("00");
        jToggleButton52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton52.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton52.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton52.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton53);
        jToggleButton53.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton53.setText("00");
        jToggleButton53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton53.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton53.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton53.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton54);
        jToggleButton54.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton54.setText("00");
        jToggleButton54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton54.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton54.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton54.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton55);
        jToggleButton55.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton55.setText("00");
        jToggleButton55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton55.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton55.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton55.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton56);
        jToggleButton56.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton56.setText("00");
        jToggleButton56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton56.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton56.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton56.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton57);
        jToggleButton57.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton57.setText("00");
        jToggleButton57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton57.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton57.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton57.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton58);
        jToggleButton58.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton58.setText("00");
        jToggleButton58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton58.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton58.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton58.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton59);
        jToggleButton59.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton59.setText("00");
        jToggleButton59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton59.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton59.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton59.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton61);
        jToggleButton61.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton61.setText("00");
        jToggleButton61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton61.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton61.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton61.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton62);
        jToggleButton62.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton62.setText("00");
        jToggleButton62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton62.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton62.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton62.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton63);
        jToggleButton63.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton63.setText("00");
        jToggleButton63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton63.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton63.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton63.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton64);
        jToggleButton64.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton64.setText("00");
        jToggleButton64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton64.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton64.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton64.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton65);
        jToggleButton65.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton65.setText("00");
        jToggleButton65.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton65.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton65.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton65.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton66);
        jToggleButton66.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton66.setText("00");
        jToggleButton66.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton66.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton66.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton66.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton67);
        jToggleButton67.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton67.setText("00");
        jToggleButton67.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton67.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton67.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton67.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton68);
        jToggleButton68.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton68.setText("00");
        jToggleButton68.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton68.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton68.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton68.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton69);
        jToggleButton69.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton69.setText("00");
        jToggleButton69.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton69.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton69.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton69.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton70);
        jToggleButton70.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton70.setText("00");
        jToggleButton70.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton70.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton70.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton70.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton71);
        jToggleButton71.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton71.setText("00");
        jToggleButton71.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton71.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton71.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton71.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton72);
        jToggleButton72.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton72.setText("00");
        jToggleButton72.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton72.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton72.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton72.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton73);
        jToggleButton73.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton73.setText("00");
        jToggleButton73.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton73.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton73.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton73.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton74);
        jToggleButton74.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton74.setText("00");
        jToggleButton74.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton74.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton74.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton74.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton75);
        jToggleButton75.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton75.setText("00");
        jToggleButton75.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton75.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton75.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton75.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton76);
        jToggleButton76.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton76.setText("00");
        jToggleButton76.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton76.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton76.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton76.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton77);
        jToggleButton77.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton77.setText("00");
        jToggleButton77.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton77.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton77.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton77.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton78);
        jToggleButton78.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton78.setText("00");
        jToggleButton78.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton78.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton78.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton78.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton79);
        jToggleButton79.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton79.setText("00");
        jToggleButton79.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton79.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton79.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton79.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton80);
        jToggleButton80.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton80.setText("00");
        jToggleButton80.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton80.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton80.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton80.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton81);
        jToggleButton81.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton81.setText("00");
        jToggleButton81.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton81.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton81.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton81.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton82);
        jToggleButton82.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton82.setText("00");
        jToggleButton82.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton82.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton82.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton82.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton83);
        jToggleButton83.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton83.setText("00");
        jToggleButton83.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton83.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton83.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton83.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton84);
        jToggleButton84.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton84.setText("00");
        jToggleButton84.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton84.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton84.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton84.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton85);
        jToggleButton85.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton85.setText("00");
        jToggleButton85.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton85.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton85.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton85.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton86);
        jToggleButton86.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton86.setText("00");
        jToggleButton86.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton86.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton86.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton86.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton87);
        jToggleButton87.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton87.setText("00");
        jToggleButton87.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton87.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton87.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton87.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton88);
        jToggleButton88.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton88.setText("00");
        jToggleButton88.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton88.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton88.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton88.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton89);
        jToggleButton89.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton89.setText("00");
        jToggleButton89.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton89.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton89.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton89.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton90);
        jToggleButton90.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton90.setText("00");
        jToggleButton90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton90.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton90.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton90.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton91);
        jToggleButton91.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton91.setText("00");
        jToggleButton91.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton91.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton91.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton91.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton92);
        jToggleButton92.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton92.setText("00");
        jToggleButton92.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton92.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton92.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton92.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton93);
        jToggleButton93.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton93.setText("00");
        jToggleButton93.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton93.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton93.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton93.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton94);
        jToggleButton94.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton94.setText("00");
        jToggleButton94.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton94.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton94.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton94.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton95);
        jToggleButton95.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton95.setText("00");
        jToggleButton95.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton95.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton95.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton95.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton96);
        jToggleButton96.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton96.setText("00");
        jToggleButton96.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton96.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton96.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton96.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton97);
        jToggleButton97.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton97.setText("00");
        jToggleButton97.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton97.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton97.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton97.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton98);
        jToggleButton98.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton98.setText("00");
        jToggleButton98.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton98.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton98.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton98.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton99);
        jToggleButton99.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton99.setText("00");
        jToggleButton99.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton99.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton99.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton99.setPreferredSize(new java.awt.Dimension(60, 35));

        buttonGroupLugares.add(jToggleButton60);
        jToggleButton60.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jToggleButton60.setText("00");
        jToggleButton60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton60.setMaximumSize(new java.awt.Dimension(60, 35));
        jToggleButton60.setMinimumSize(new java.awt.Dimension(60, 35));
        jToggleButton60.setPreferredSize(new java.awt.Dimension(60, 35));

        jLabelLegenda.setText("LEGENDA");
        jLabelLegenda.setToolTipText("");

        jButtonDD.setFont(new java.awt.Font("Ubuntu Mono", 1, 16)); // NOI18N
        jButtonDD.setText(" ");
        jButtonDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDDActionPerformed(evt);
            }
        });

        jButtonII.setFont(new java.awt.Font("Ubuntu Mono", 1, 16)); // NOI18N
        jButtonII.setText(" ");
        jButtonII.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIIActionPerformed(evt);
            }
        });

        jButtonMM.setFont(new java.awt.Font("Ubuntu Mono", 1, 16)); // NOI18N
        jButtonMM.setText(" ");
        jButtonMM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMMActionPerformed(evt);
            }
        });

        jLabelLegendaDD.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelLegendaDD.setText("jLabelLegendaDD");
        jLabelLegendaDD.setToolTipText("");

        jLabelLegendaII.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelLegendaII.setText("jLabelLegendaII");
        jLabelLegendaII.setToolTipText("");

        jLabelLegendaMM.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelLegendaMM.setText("jLabelLegendaMM");
        jLabelLegendaMM.setToolTipText("");

        buttonGroupMeia.add(jRadioButtonInteira);
        jRadioButtonInteira.setText("Inteira");
        jRadioButtonInteira.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonInteiraStateChanged(evt);
            }
        });
        jRadioButtonInteira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInteiraActionPerformed(evt);
            }
        });

        buttonGroupMeia.add(jRadioButtonMeia);
        jRadioButtonMeia.setText("Meia Entrada");
        jRadioButtonMeia.setActionCommand("");
        jRadioButtonMeia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonMeiaStateChanged(evt);
            }
        });

        jLabelPreco.setText("jLabelPreco");

        jButtonVender.setText("Vender");
        jButtonVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVenderActionPerformed(evt);
            }
        });

        jButtonVerIngresso.setText("Ingresso");
        jButtonVerIngresso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerIngressoActionPerformed(evt);
            }
        });

        jButtonDevolver.setText("Devolver");
        jButtonDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverActionPerformed(evt);
            }
        });

        jLabelStatus.setText("jLabelStatus");

        javax.swing.GroupLayout jPanelDetalhesLayout = new javax.swing.GroupLayout(jPanelDetalhes);
        jPanelDetalhes.setLayout(jPanelDetalhesLayout);
        jPanelDetalhesLayout.setHorizontalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addComponent(jRadioButtonInteira)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonMeia))
                    .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                        .addComponent(jButtonVender, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVerIngresso, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDevolver, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addComponent(jLabelPreco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelStatus))
                .addContainerGap())
        );
        jPanelDetalhesLayout.setVerticalGroup(
            jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetalhesLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonInteira)
                    .addComponent(jRadioButtonMeia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPreco)
                .addGap(12, 12, 12)
                .addGroup(jPanelDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVender)
                    .addComponent(jButtonVerIngresso)
                    .addComponent(jButtonDevolver))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanelLugaresLayout = new javax.swing.GroupLayout(jPanelLugares);
        jPanelLugares.setLayout(jPanelLugaresLayout);
        jPanelLugaresLayout.setHorizontalGroup(
            jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLugaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLugaresLayout.createSequentialGroup()
                        .addComponent(jLabelLugares)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton90, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton91, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton92, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton93, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton94, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton95, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton96, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton97, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton98, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton99, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton80, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton81, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton82, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton83, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton84, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton85, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton86, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton87, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton88, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton89, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton75, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton77, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton78, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                                    .addComponent(jToggleButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelLugaresLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLugaresLayout.createSequentialGroup()
                                        .addComponent(jButtonII, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelLegendaII))
                                    .addGroup(jPanelLugaresLayout.createSequentialGroup()
                                        .addComponent(jButtonDD, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelLegendaDD))
                                    .addGroup(jPanelLugaresLayout.createSequentialGroup()
                                        .addComponent(jButtonMM, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelLegendaMM))
                                    .addComponent(jLabelLegenda))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLugaresLayout.setVerticalGroup(
            jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLugaresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLugares)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton75, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton76, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton77, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton79, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton78, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton80, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton81, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton82, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton83, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton84, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton85, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton86, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton87, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton89, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton88, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton90, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton91, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton92, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton93, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton94, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton95, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton96, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton97, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton99, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton98, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLugaresLayout.createSequentialGroup()
                        .addComponent(jLabelLegenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDD)
                            .addComponent(jLabelLegendaDD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonII)
                            .addComponent(jLabelLegendaII))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLugaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonMM)
                            .addComponent(jLabelLegendaMM)))
                    .addComponent(jPanelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSala1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSala1.setText("jLabelSala1");

        buttonGroupFilmes.add(jToggleButtonFilme10);
        jToggleButtonFilme10.setText("jToggleButtonFilme10");

        buttonGroupFilmes.add(jToggleButtonFilme11);
        jToggleButtonFilme11.setText("jToggleButtonFilme11");

        buttonGroupFilmes.add(jToggleButtonFilme12);
        jToggleButtonFilme12.setText("jToggleButtonFilme12");

        jLabelSessao10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao10.setText("jLabelSessao10");
        jLabelSessao10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao11.setText("jLabelSessao11");
        jLabelSessao11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao12.setText("jLabelSessao12");
        jLabelSessao12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanelSala1Layout = new javax.swing.GroupLayout(jPanelSala1);
        jPanelSala1.setLayout(jPanelSala1Layout);
        jPanelSala1Layout.setHorizontalGroup(
            jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSala1Layout.createSequentialGroup()
                        .addComponent(jLabelSala1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSala1Layout.createSequentialGroup()
                        .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSala1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabelSessao10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelSessao11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSessao12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToggleButtonFilme12, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelSala1Layout.setVerticalGroup(
            jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSala1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSessao10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonFilme10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme11)
                    .addComponent(jLabelSessao11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme12)
                    .addComponent(jLabelSessao12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanelSala2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSala2.setText("jLabelSala2");

        buttonGroupFilmes.add(jToggleButtonFilme20);
        jToggleButtonFilme20.setText("jToggleButtonFilme20");

        buttonGroupFilmes.add(jToggleButtonFilme21);
        jToggleButtonFilme21.setText("jToggleButtonFilme21");

        buttonGroupFilmes.add(jToggleButtonFilme22);
        jToggleButtonFilme22.setText("jToggleButtonFilme22");

        jLabelSessao20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao20.setText("jLabelSessao20");
        jLabelSessao20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao21.setText("jLabelSessao21");
        jLabelSessao21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabelSessao22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSessao22.setText("jLabelSessao22");
        jLabelSessao22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanelSala2Layout = new javax.swing.GroupLayout(jPanelSala2);
        jPanelSala2.setLayout(jPanelSala2Layout);
        jPanelSala2Layout.setHorizontalGroup(
            jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSala2Layout.createSequentialGroup()
                        .addComponent(jLabelSala2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSala2Layout.createSequentialGroup()
                        .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSala2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabelSessao20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelSessao21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSessao22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToggleButtonFilme22, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButtonFilme20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(17, Short.MAX_VALUE))))
        );
        jPanelSala2Layout.setVerticalGroup(
            jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSala2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSala2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSessao20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonFilme20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme21)
                    .addComponent(jLabelSessao21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSala2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonFilme22)
                    .addComponent(jLabelSessao22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFilmesLayout = new javax.swing.GroupLayout(jPanelFilmes);
        jPanelFilmes.setLayout(jPanelFilmesLayout);
        jPanelFilmesLayout.setHorizontalGroup(
            jPanelFilmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilmesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelSala1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSala2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSala0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelFilmesLayout.setVerticalGroup(
            jPanelFilmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFilmesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFilmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelLugares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFilmesLayout.createSequentialGroup()
                        .addComponent(jPanelSala0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelSala1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelSala2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jTabbedPane.addTab("Filmes", jPanelFilmes);

        jTableRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Filme", "Sessão", "Qtd.Meia", "R$ Meia", "Qtd.Inteira", "R$ Inteira", "Qtd.Total", "R$ Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRelatorio.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableRelatorio);
        if (jTableRelatorio.getColumnModel().getColumnCount() > 0) {
            jTableRelatorio.getColumnModel().getColumn(0).setPreferredWidth(28);
            jTableRelatorio.getColumnModel().getColumn(1).setMinWidth(180);
            jTableRelatorio.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTableRelatorio.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Meia Entrada:");

        jLabel2.setText("TOTAIS");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Entrada Inteira:");

        jLabelTotalInteira.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelTotalInteira.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalInteira.setText("jLabelTotalInteira");

        jLabelTotalMeia.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelTotalMeia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalMeia.setText("jLabelTotalMeia");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Total Geral:");

        jLabelTotalGeral.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelTotalGeral.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTotalGeral.setText("jLabelTotalGeral");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Maior Bilheteria:");

        jLabelMaiorBilheteria.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelMaiorBilheteria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMaiorBilheteria.setText("jLabelMaiorBilheteria");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Menor Bilheteria:");

        jLabelMenorBilheteria.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelMenorBilheteria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMenorBilheteria.setText("jLabelMenorBilheteria");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Maior Arrecadação:");

        jLabelMaiorArrecadacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelMaiorArrecadacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMaiorArrecadacao.setText("jLabelMaiorArrecadacao");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Menor Arrecadação:");

        jLabelMenorArrecadacao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelMenorArrecadacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMenorArrecadacao.setText("jLabelMenorArrecadacao");

        javax.swing.GroupLayout jPanelRelatorioLayout = new javax.swing.GroupLayout(jPanelRelatorio);
        jPanelRelatorio.setLayout(jPanelRelatorioLayout);
        jPanelRelatorioLayout.setHorizontalGroup(
            jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                    .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTotalInteira)
                            .addComponent(jLabelTotalMeia)
                            .addComponent(jLabelTotalGeral))
                        .addGap(198, 198, 198)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMaiorArrecadacao)
                            .addComponent(jLabelMenorArrecadacao)
                            .addComponent(jLabelMaiorBilheteria)
                            .addComponent(jLabelMenorBilheteria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel2)
                    .addContainerGap(1093, Short.MAX_VALUE)))
        );
        jPanelRelatorioLayout.setVerticalGroup(
            jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelTotalMeia))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelTotalInteira))
                        .addGap(47, 47, 47)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabelTotalGeral)))
                    .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelMaiorBilheteria))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelMenorBilheteria))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelMaiorArrecadacao, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabelMenorArrecadacao))))
                .addGap(259, 259, 259))
            .addGroup(jPanelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelRelatorioLayout.createSequentialGroup()
                    .addGap(208, 208, 208)
                    .addComponent(jLabel2)
                    .addContainerGap(374, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("Relatório", jPanelRelatorio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        if (this.jTabbedPane.getSelectedIndex() == 1) {
            preencheTabela();
            formataTabela();
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFrameAcesso acesso = new JFrameAcesso(this.bll);
        acesso.setLocationRelativeTo(null);
        acesso.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButtonDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverActionPerformed
        devolverIngresso();
    }//GEN-LAST:event_jButtonDevolverActionPerformed

    private void jButtonVerIngressoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerIngressoActionPerformed
        verIngresso();
    }//GEN-LAST:event_jButtonVerIngressoActionPerformed

    private void jButtonVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVenderActionPerformed
        venderIngresso();
    }//GEN-LAST:event_jButtonVenderActionPerformed

    private void jRadioButtonMeiaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonMeiaStateChanged
        atualizaLabelPreco();
    }//GEN-LAST:event_jRadioButtonMeiaStateChanged

    private void jRadioButtonInteiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInteiraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonInteiraActionPerformed

    private void jRadioButtonInteiraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonInteiraStateChanged
        atualizaLabelPreco();
    }//GEN-LAST:event_jRadioButtonInteiraStateChanged

    private void jButtonMMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMMActionPerformed
    }//GEN-LAST:event_jButtonMMActionPerformed

    private void jButtonIIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIIActionPerformed
    }//GEN-LAST:event_jButtonIIActionPerformed

    private void jButtonDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDDActionPerformed
    }//GEN-LAST:event_jButtonDDActionPerformed

    private void jToggleButtonFilme01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonFilme01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButtonFilme01ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameCinema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCinema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCinema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCinema.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new JFrameCinema(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupFilmes;
    private javax.swing.ButtonGroup buttonGroupLugares;
    private javax.swing.ButtonGroup buttonGroupMeia;
    private javax.swing.JButton jButtonDD;
    private javax.swing.JButton jButtonDevolver;
    private javax.swing.JButton jButtonII;
    private javax.swing.JButton jButtonMM;
    private javax.swing.JButton jButtonVender;
    private javax.swing.JButton jButtonVerIngresso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelLegenda;
    private javax.swing.JLabel jLabelLegendaDD;
    private javax.swing.JLabel jLabelLegendaII;
    private javax.swing.JLabel jLabelLegendaMM;
    private javax.swing.JLabel jLabelLugares;
    private javax.swing.JLabel jLabelMaiorArrecadacao;
    private javax.swing.JLabel jLabelMaiorBilheteria;
    private javax.swing.JLabel jLabelMenorArrecadacao;
    private javax.swing.JLabel jLabelMenorBilheteria;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelSala0;
    private javax.swing.JLabel jLabelSala1;
    private javax.swing.JLabel jLabelSala2;
    private javax.swing.JLabel jLabelSessao00;
    private javax.swing.JLabel jLabelSessao01;
    private javax.swing.JLabel jLabelSessao02;
    private javax.swing.JLabel jLabelSessao10;
    private javax.swing.JLabel jLabelSessao11;
    private javax.swing.JLabel jLabelSessao12;
    private javax.swing.JLabel jLabelSessao20;
    private javax.swing.JLabel jLabelSessao21;
    private javax.swing.JLabel jLabelSessao22;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTotalGeral;
    private javax.swing.JLabel jLabelTotalInteira;
    private javax.swing.JLabel jLabelTotalMeia;
    private javax.swing.JPanel jPanelDetalhes;
    private javax.swing.JPanel jPanelFilmes;
    private javax.swing.JPanel jPanelLugares;
    private javax.swing.JPanel jPanelRelatorio;
    private javax.swing.JPanel jPanelSala0;
    private javax.swing.JPanel jPanelSala1;
    private javax.swing.JPanel jPanelSala2;
    private javax.swing.JRadioButton jRadioButtonInteira;
    private javax.swing.JRadioButton jRadioButtonMeia;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableRelatorio;
    private javax.swing.JToggleButton jToggleButton0;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton13;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton15;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton20;
    private javax.swing.JToggleButton jToggleButton21;
    private javax.swing.JToggleButton jToggleButton22;
    private javax.swing.JToggleButton jToggleButton23;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton25;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private javax.swing.JToggleButton jToggleButton28;
    private javax.swing.JToggleButton jToggleButton29;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton30;
    private javax.swing.JToggleButton jToggleButton31;
    private javax.swing.JToggleButton jToggleButton32;
    private javax.swing.JToggleButton jToggleButton33;
    private javax.swing.JToggleButton jToggleButton34;
    private javax.swing.JToggleButton jToggleButton35;
    private javax.swing.JToggleButton jToggleButton36;
    private javax.swing.JToggleButton jToggleButton37;
    private javax.swing.JToggleButton jToggleButton38;
    private javax.swing.JToggleButton jToggleButton39;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton40;
    private javax.swing.JToggleButton jToggleButton41;
    private javax.swing.JToggleButton jToggleButton42;
    private javax.swing.JToggleButton jToggleButton43;
    private javax.swing.JToggleButton jToggleButton44;
    private javax.swing.JToggleButton jToggleButton45;
    private javax.swing.JToggleButton jToggleButton46;
    private javax.swing.JToggleButton jToggleButton47;
    private javax.swing.JToggleButton jToggleButton48;
    private javax.swing.JToggleButton jToggleButton49;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton50;
    private javax.swing.JToggleButton jToggleButton51;
    private javax.swing.JToggleButton jToggleButton52;
    private javax.swing.JToggleButton jToggleButton53;
    private javax.swing.JToggleButton jToggleButton54;
    private javax.swing.JToggleButton jToggleButton55;
    private javax.swing.JToggleButton jToggleButton56;
    private javax.swing.JToggleButton jToggleButton57;
    private javax.swing.JToggleButton jToggleButton58;
    private javax.swing.JToggleButton jToggleButton59;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton60;
    private javax.swing.JToggleButton jToggleButton61;
    private javax.swing.JToggleButton jToggleButton62;
    private javax.swing.JToggleButton jToggleButton63;
    private javax.swing.JToggleButton jToggleButton64;
    private javax.swing.JToggleButton jToggleButton65;
    private javax.swing.JToggleButton jToggleButton66;
    private javax.swing.JToggleButton jToggleButton67;
    private javax.swing.JToggleButton jToggleButton68;
    private javax.swing.JToggleButton jToggleButton69;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton70;
    private javax.swing.JToggleButton jToggleButton71;
    private javax.swing.JToggleButton jToggleButton72;
    private javax.swing.JToggleButton jToggleButton73;
    private javax.swing.JToggleButton jToggleButton74;
    private javax.swing.JToggleButton jToggleButton75;
    private javax.swing.JToggleButton jToggleButton76;
    private javax.swing.JToggleButton jToggleButton77;
    private javax.swing.JToggleButton jToggleButton78;
    private javax.swing.JToggleButton jToggleButton79;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton80;
    private javax.swing.JToggleButton jToggleButton81;
    private javax.swing.JToggleButton jToggleButton82;
    private javax.swing.JToggleButton jToggleButton83;
    private javax.swing.JToggleButton jToggleButton84;
    private javax.swing.JToggleButton jToggleButton85;
    private javax.swing.JToggleButton jToggleButton86;
    private javax.swing.JToggleButton jToggleButton87;
    private javax.swing.JToggleButton jToggleButton88;
    private javax.swing.JToggleButton jToggleButton89;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JToggleButton jToggleButton90;
    private javax.swing.JToggleButton jToggleButton91;
    private javax.swing.JToggleButton jToggleButton92;
    private javax.swing.JToggleButton jToggleButton93;
    private javax.swing.JToggleButton jToggleButton94;
    private javax.swing.JToggleButton jToggleButton95;
    private javax.swing.JToggleButton jToggleButton96;
    private javax.swing.JToggleButton jToggleButton97;
    private javax.swing.JToggleButton jToggleButton98;
    private javax.swing.JToggleButton jToggleButton99;
    private javax.swing.JToggleButton jToggleButtonFilme00;
    private javax.swing.JToggleButton jToggleButtonFilme01;
    private javax.swing.JToggleButton jToggleButtonFilme02;
    private javax.swing.JToggleButton jToggleButtonFilme10;
    private javax.swing.JToggleButton jToggleButtonFilme11;
    private javax.swing.JToggleButton jToggleButtonFilme12;
    private javax.swing.JToggleButton jToggleButtonFilme20;
    private javax.swing.JToggleButton jToggleButtonFilme21;
    private javax.swing.JToggleButton jToggleButtonFilme22;
    // End of variables declaration//GEN-END:variables
}
