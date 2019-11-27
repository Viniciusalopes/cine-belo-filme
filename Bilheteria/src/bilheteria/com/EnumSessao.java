package bilheteria.com;

public enum EnumSessao {

	MANHA ("Sessão Matutino") ,
	
	TARDE("Sessão Vespertino"),
	
	NOITE("Sessão Noturana");
	public String descricao;

	EnumSessao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {

		return descricao;
	}
}
