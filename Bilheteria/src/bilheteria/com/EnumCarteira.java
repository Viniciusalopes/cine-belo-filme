package bilheteria.com;

public enum EnumCarteira  {

	MEIA ("Usuário paga meia entrada") ,
	INTEIRA("Usuário paga inteira");
	
	public String descricao;

	EnumCarteira(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {

		return descricao;
	}
	
	
}
