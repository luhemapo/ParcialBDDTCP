package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Clientes {

	public ArrayList<Cliente> cliente;

	public Clientes() {

		cliente = new ArrayList<Cliente>();
		leerArchivo();
	}

	public void leerArchivo() {

		try {
			BufferedReader in = new BufferedReader(new FileReader("MaeClientes.txt"));
			String str;

			while ((str = in.readLine()) != null) {
				String[] arr = str.split(",");
				Cliente auxCliente = new Cliente(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
				cliente.add(auxCliente);
							}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
	}

	public ArrayList<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(ArrayList<Cliente> cliente) {
		this.cliente = cliente;
	}
}
