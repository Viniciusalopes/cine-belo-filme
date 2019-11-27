package bilheteria.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Filmes extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	String nome;
	double duracao;
	
	JButton filme1 = new JButton() ;
	JButton filme2 = new JButton() ;
	JButton filme3 = new JButton() ;
		
	public Filmes() {
		
		super("FILMES");
		
		setLayout(null);
		filme1.setBounds(10, 10, 178, 265);
		filme1.setIcon(new ImageIcon("/media/eduardo/Eduardo/Bilheteria/src/bilheteria/com/images/a-grande-mentira.jpg"));
		
		filme2.setBounds(200, 10, 178, 265);
		filme2.setIcon(new ImageIcon("/media/eduardo/Eduardo/Bilheteria/src/bilheteria/com/images/a-vida-invisivel.jpg"));
		
		filme3.setBounds(390, 10, 178, 265);
		filme3.setIcon(new ImageIcon("/media/eduardo/Eduardo/Bilheteria/src/bilheteria/com/images/de-volta-para-o-futuro.jpg"));
		
		add(filme1);
		add(filme2);
		add(filme3);
		
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
				
		
		
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	// Excluir depois esse metodo main
	public static void main(String[] args) {
		new Filmes();
	}

}
