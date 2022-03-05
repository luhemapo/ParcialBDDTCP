package Model;

public class Maeclientes {
	public int id;
	public String nombre;
	public String apellido;
	public int saldo;
	
	public Maeclientes(int id, String nombre, String apellido, int saldo) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.saldo=saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}
