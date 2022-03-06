package Model;

public class Cliente {
	public int id;
	public String nombres;
	
	public int saldo;
	
	public Cliente(int id, String nombres, int saldo) {
		
		this.id = id;
		this.nombres = nombres;
		this.saldo=saldo;
	}
	
	public String[] getCliente() {
		String [] auxCliente = {getId()+"", getNombres(), getSaldo()+""};
		return auxCliente;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
}