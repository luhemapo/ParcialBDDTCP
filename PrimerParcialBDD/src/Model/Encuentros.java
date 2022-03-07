package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Encuentros {
	
	public ArrayList<Encuentro> encuentro;
	
	public Encuentros() {
		encuentro = new ArrayList<Encuentro>();
		leerArchivo();
	}
	public void leerArchivo() {

		try {
			BufferedReader in = new BufferedReader(new FileReader("Encuentros.txt"));
			String str;

			while ((str = in.readLine()) != null) {
				String[] arr = str.split(",");
				Encuentro auxEncuentro = new Encuentro(Integer.parseInt(arr[0]), arr[1], arr[2],arr[3], arr[4]);
				encuentro.add(auxEncuentro);
							}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
	}
	public ArrayList<Encuentro> getEncuentro() {
		return encuentro;
	}
	public void setEncuentro(ArrayList<Encuentro> encuentro) {
		this.encuentro = encuentro;
	}
	
}
