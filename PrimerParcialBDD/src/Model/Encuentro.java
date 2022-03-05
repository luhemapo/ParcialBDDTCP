package Model;

public class Encuentro {
	public int valorApostado;
	public int idUsuario;
	
	public Encuentro(int valorApostado, int idUsuario) {
	
		this.valorApostado = valorApostado;
		this.idUsuario = idUsuario;
	}

	public int getValorApostado() {
		return valorApostado;
	}

	public void setValorApostado(int valorApostado) {
		this.valorApostado = valorApostado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
