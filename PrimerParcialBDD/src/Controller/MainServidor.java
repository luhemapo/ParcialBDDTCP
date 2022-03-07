package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Cliente;
import Model.Clientes;
import Model.Encuentro;
import Model.Encuentros;
 
public class MainServidor {
	
	Clientes clientes;
	Cliente cliente;
	Encuentro encuentro;
	Encuentros encuentros;
	
	public ArrayList <Cliente> auxclientes;
	public ArrayList <Encuentro> auxEncuentro;
	public String mensaje = "";
	String tempCliente;
	String tempEqLocal;
	String tempEqVisitante;
	String tempFecha;
	String tempDeporte;
	String tempConsecutivo;
	String tempValorApostado;
	String tempNombre;
	int saldoaux;
	public int tempTxtConsecutivo;
	
	public MainServidor() {
		
		clientes = new Clientes();
		encuentros = new Encuentros();
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        tempNombre = "";
        saldoaux = 0;
        tempCliente = "";
    	tempEqLocal = "";
    	tempEqVisitante = "";
    	tempFecha = "";
    	tempDeporte = "";
        tempTxtConsecutivo=0;
        tempValorApostado= "";
        consecutivoTxt();
        tempCliente = "";
        tempConsecutivo = "";
        
        //puerto de nuestro servidor
        final int PUERTO = 5000;
 
        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado\n");
 
            //Siempre estara escuchando peticiones
            while (true) {
 
                //Espero a que un cliente se conecte
                sc = servidor.accept();
 
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
 
                //Recibo el mensaje del cliente
                mensaje = in.readUTF();
                
                if(validarID(clientes.getCliente())== true) {
        			System.out.println("Id de cliente valido\n");
        			
        			//Envio un mensaje al cliente
        			out.writeUTF("Bienvenido! "+clientes.getCliente().get(Integer.parseInt(mensaje)).getNombres()+
        					"\n\n¿A quien deseas apostar?\n");
        			tempNombre = clientes.getCliente().get(Integer.parseInt(mensaje)).getNombres();
        			
        		}else {
        			//Envio un mensaje al cliente
        			out.writeUTF("Id no valido");
        			sc.close();
                    System.out.println("Cliente desconectado por ID invalido\n");
                }
                
                //Recibo el mensaje del cliente
                mensaje = in.readUTF();
                System.out.println("consecutivo ingresado: "+mensaje);
                if(validarConsecutivo(encuentros.getEncuentro())== true) {
                	System.out.println("Consecutivo valido");
                	tempConsecutivo = mensaje;
                	tempEqLocal = encuentros.getEncuentro().get(Integer.parseInt(mensaje)).getEquipoLocal();
                	tempEqVisitante = encuentros.getEncuentro().get(Integer.parseInt(mensaje)).getEquipoVisitante();
                	tempFecha = encuentros.getEncuentro().get(Integer.parseInt(mensaje)).getFecha();
                	tempDeporte = encuentros.getEncuentro().get(Integer.parseInt(mensaje)).getTipoDeporte();
                	//Envio un mensaje al cliente
                	out.writeUTF("¿Cuanto deseas apostar?");
                }
                else {
                	//Envio un mensaje al cliente
        			out.writeUTF("El consecutivo no es valido");
        			sc.close();
                    System.out.println("Cliente desconectado por consecutivo invalido");
                }
                
                //Recibo el mensaje del cliente
                mensaje = in.readUTF();
                System.out.println("Apuesta ingresada: "+mensaje);
                
                if(validarSaldo(clientes.getCliente().get(Integer.parseInt(tempCliente)).getSaldo())== true) {
                	System.out.println("Apuesta valida");
                	//Envio un mensaje al cliente
                	
                	tempValorApostado = mensaje;
                	String saldo = "";
                	saldo = Integer.toString(saldoaux - Integer.parseInt(tempValorApostado));
                	apuestasTxt();
                	encuentroTxt();
                	out.writeUTF("Apuesta realizada, su comprobante:\n\n"
                	+"Nombre del usuario: "+tempNombre+
                	"\nSaldo inicial: "+saldoaux+
                	"\nValor apostado: "+tempValorApostado+
                	"\nDatos del encuentro: "+tempEqLocal+" vs "+ tempEqVisitante+ " Fecha: "+tempFecha+
                	"\nNuevo saldo: "+saldo);
                	
                }else {
                	
                	//Envio un mensaje al cliente
                	out.writeUTF("Saldo insuficiente");
        			sc.close();
                    System.out.println("Cliente desconectado por saldo insuficiente");
                }

                sc.close();	
            }
 
        } catch (IOException ex) {
            Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    
	}

	public boolean validarID(ArrayList<Cliente> auxCliente) {
		boolean ans = false;
		for (int i = 0; i < auxCliente.size(); i++) {
			if (Integer.parseInt(mensaje) == auxCliente.get(i).getId()) {
				tempCliente = mensaje;
				ans = true;
			}
		}
		return ans;
		
	}
	
	public boolean validarConsecutivo(ArrayList <Encuentro> auxEncuentro) {
		boolean ans = false;
		for (int i = 0; i < auxEncuentro.size(); i++) {
			if (Integer.parseInt(mensaje) == auxEncuentro.get(i).getConsecutivo()) {
				ans = true;
			}
		}
		return ans;
	}
	
	public boolean validarSaldo(int saldo) {
		boolean ans = true;
		saldoaux = saldo;
		System.out.println("Saldo disponible: "+saldoaux);
			if (Integer.parseInt(mensaje) > saldoaux) {
				System.out.println("Saldo: "+saldo);
				ans = false;
			
		}
		return ans;
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
	public void consecutivoTxt() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("apuestas.txt"));
			String str;

			while ((str = in.readLine()) != null) {
				String[] arr = str.split(",");
				tempTxtConsecutivo = Integer.parseInt(arr[0]);
							}
			
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
	}
	
	public void apuestasTxt() {
	    try {
	    	
	        File apuestastxt = new File("apuestas.txt");
	        if (apuestastxt.createNewFile()) {
	        	
	          System.out.println("File created: " + apuestastxt.getName());
	          tempTxtConsecutivo = tempTxtConsecutivo+1;
	          String str = tempTxtConsecutivo+","+tempConsecutivo+"\n";
	          BufferedWriter writer = new BufferedWriter(new FileWriter("apuestas.txt", true));
	          writer.append(str);
	          
	          writer.close();
	        } else {
	          System.out.println("File already exists.");
	          tempTxtConsecutivo = tempTxtConsecutivo +1;
	          String str = tempTxtConsecutivo+","+tempConsecutivo+"\n";
	          BufferedWriter writer = new BufferedWriter(new FileWriter("apuestas.txt", true));
	          writer.append(str);
	          
	          writer.close();
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	public void encuentroTxt() {
	    try {
	    	
	        File encuentrotxt = new File("encuentro.txt");
	        if (encuentrotxt.createNewFile()) {
	        	
	          System.out.println("File created: " + encuentrotxt.getName());
	          
	          String str = tempValorApostado+","+tempCliente+"\n";
	          BufferedWriter writer = new BufferedWriter(new FileWriter("encuentro.txt", true));
	          writer.append(str);
	          
	          writer.close();
	        } else {
	          System.out.println("File already exists.");
	         
	          String str = tempValorApostado+","+tempCliente+"\n";
	          BufferedWriter writer = new BufferedWriter(new FileWriter("encuentro.txt", true));
	          writer.append(str);
	          
	          writer.close();
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
 
    public static void main(String[] args) {
    	MainServidor ms = new MainServidor();
    }
}