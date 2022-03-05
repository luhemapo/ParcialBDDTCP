package Model;

public class Apuestas {
	public int consecutivo;
	public int idEncuentro;
	public int idCliente;
	
	public Apuestas(int consecutivo, int idEncuentro, int idCliente) {
		this.consecutivo = consecutivo;
		this.idEncuentro = idEncuentro;
		this.idCliente = idCliente;
		
	}

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public int getIdEncuentro() {
		return idEncuentro;
	}

	public void setIdEncuentro(int idEncuentro) {
		this.idEncuentro = idEncuentro;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
}
