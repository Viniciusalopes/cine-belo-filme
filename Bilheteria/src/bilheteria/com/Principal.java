package bilheteria.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JMenuBar barra = new JMenuBar();
	JMenu menuSalas = new JMenu("Salas");
	JMenu menuFilmes = new JMenu("Filmes");
	JMenu menuImprimir = new JMenu("Imprimir");
	JMenu menuSair = new JMenu("Sair");

	JMenuItem menuItemSala_1 = new JMenuItem("Sala 1");
	JMenuItem menuItemSala_2 = new JMenuItem("Sala 2");
	JMenuItem menuItemSala_3 = new JMenuItem("Sala 3");
	
	JMenuItem menuItemRelatorio = new JMenuItem("Relatório");
	JMenuItem menuItemIngresso = new JMenuItem("Ingresso");
	

	JMenuItem menuSairExit = new JMenuItem("Exit");

	JMenuItem listaFilmes = new JMenuItem("Listas Filmes");

	public Principal() {

		super("CINE_FATESG");
		Font font = new Font("serif", Font.BOLD, 38);
		Font fontn = new Font("serif", Font.BOLD, 30);

		JLabel label = new JLabel("TELA");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setToolTipText("Frente - Tela do Cinema");
		label.setFont(font);
		label.setForeground(Color.blue);

		setJMenuBar(barra);
		barra.add(menuSalas);
		barra.add(menuFilmes);
		barra.add(menuImprimir);
		barra.add(menuSair);

		menuSalas.add(menuItemSala_1);
		menuSalas.add(menuItemSala_2);
		menuSalas.add(menuItemSala_3);

		menuFilmes.add(listaFilmes);
		
		menuImprimir.add(menuItemRelatorio);
		menuImprimir.add(menuItemIngresso);

		menuSair.add(menuSairExit);

		Container c = getContentPane();
		c.setLayout(new BorderLayout(5, 5));

		Container fileiraE = new JPanel();
		Container cadeiras = new JPanel();
		Container fileiraR = new JPanel();

		fileiraE.setLayout(new GridLayout(10, 1, 5, 5));
		cadeiras.setLayout(new GridLayout(10, 10, 5, 5));
		cadeiras.setFont(fontn);
		fileiraR.setLayout(new GridLayout(10, 1, 5, 5));

		for (int i = 0; i < 10; i++) {
			cadeiras.add(new JButton("1")).setBackground(Color.green);
			cadeiras.add(new JButton("2")).setBackground(Color.green);
			cadeiras.add(new JButton("3")).setBackground(Color.green);
			cadeiras.add(new JButton("4")).setBackground(Color.green);
			cadeiras.add(new JButton("5")).setBackground(Color.green);
			cadeiras.add(new JButton("6")).setBackground(Color.green);
			cadeiras.add(new JButton("7")).setBackground(Color.green);
			cadeiras.add(new JButton("8")).setBackground(Color.green);
			cadeiras.add(new JButton("9")).setBackground(Color.green);
			cadeiras.add(new JButton("10")).setBackground(Color.green);
			
		}

		fileiraE.add(new JButton("A")).setBackground(Color.orange);
		fileiraE.add(new JButton("B")).setBackground(Color.orange);
		fileiraE.add(new JButton("C")).setBackground(Color.orange);
		fileiraE.add(new JButton("D")).setBackground(Color.orange);
		fileiraE.add(new JButton("E")).setBackground(Color.orange);
		fileiraE.add(new JButton("F")).setBackground(Color.orange);
		fileiraE.add(new JButton("G")).setBackground(Color.orange);
		fileiraE.add(new JButton("H")).setBackground(Color.orange);
		fileiraE.add(new JButton("I")).setBackground(Color.orange);
		fileiraE.add(new JButton("J")).setBackground(Color.orange);

		fileiraR.add(new JButton("A")).setBackground(Color.orange);
		fileiraR.add(new JButton("B")).setBackground(Color.orange);
		fileiraR.add(new JButton("C")).setBackground(Color.orange);
		fileiraR.add(new JButton("D")).setBackground(Color.orange);
		fileiraR.add(new JButton("E")).setBackground(Color.orange);
		fileiraR.add(new JButton("F")).setBackground(Color.orange);
		fileiraR.add(new JButton("G")).setBackground(Color.orange);
		fileiraR.add(new JButton("H")).setBackground(Color.orange);
		fileiraR.add(new JButton("I")).setBackground(Color.orange);
		fileiraR.add(new JButton("J")).setBackground(Color.orange);

		c.add(BorderLayout.NORTH, label);
		c.add(BorderLayout.EAST, fileiraE);
		c.add(BorderLayout.CENTER, cadeiras);
		c.add(BorderLayout.WEST, fileiraR);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
		setLocation(100, 100);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Principal();

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
