package Controller;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class MainCliente {
	
	public MainCliente() {
		
		Scanner scan = new Scanner(System.in);
        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;
 
        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);
 
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
 
            //Envio un mensaje al servidor
            System.out.println("Ingrese su ID: ");
            out.writeUTF(scan.nextLine());
 
            //Recibo el mensaje del servidor
            String mensaje = in.readUTF();
        	System.out.println(mensaje);
        	
        	showEncuentros();
            System.out.println("\nIngrese el numero del consecutivo por el que quiere apostar:  ");
            //Envio un mensaje al servidor
            out.writeUTF(scan.nextLine());
            
            //Recibo el mensaje del servidor
            mensaje = in.readUTF();
            System.out.println(mensaje);
            
            //Envio un mensaje al servidor
            out.writeUTF(scan.nextLine());
            
            //Recibo el mensaje del servidor
            mensaje = in.readUTF();
            System.out.println(mensaje);
            
            
            
            sc.close();
 
        } catch (IOException ex) {
            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
	}
	
	public void showEncuentros() {
		System.out.println("Consecutivo / Equipo local / Equipo visitante / Fecha / Tipo de deporte");

		try {
			BufferedReader in = new BufferedReader(new FileReader("Encuentros.txt"));
			String str;

			while ((str = in.readLine()) != null) {
				String[] arr = str.split(",");
				System.out.println(Arrays.toString(arr));
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
	}
 
    public static void main(String[] args) {
    	MainCliente mc = new MainCliente();
    }
}
