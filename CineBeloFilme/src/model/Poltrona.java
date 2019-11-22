package model;

public class Poltrona {

	// Atributos

	private int id;
	private char fileira;
	private int numero;
	private int entrada_id;

	// Contrutor
	public Poltrona(int id, char fileira, int numero) {
		this.id = id;
		this.fileira = fileira;
		this.numero = numero;
	}

	// Métodos públicos
	public int getId() {
		return id;
	}

	public char getFileira() {
		return fileira;
	}

	public int getNumero() {
		return numero;
	}

	public int getEntrada_id() {
		return entrada_id;
	}
    
	public void setEntrada_id(int entrada_id){
		this.entrada_id = entrada_id;
	}
}
