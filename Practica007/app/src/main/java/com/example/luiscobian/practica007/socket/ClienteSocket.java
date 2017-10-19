package com.example.luiscobian.practica007.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by luiscobian on 10/18/17.
 */

public class ClienteSocket {

    private static final String host = "10.42.0.228";
    private static final int port = 6007;

    DataInputStream entrada;
    DataOutputStream salida;

    public ClienteSocket() {
        try{
            Socket socket = new Socket(host,port);
            salida = new DataOutputStream(socket.getOutputStream());
            entrada = new DataInputStream(socket.getInputStream());
        }catch (Exception e){
            System.out.println("Ocurrio un error al abrir el socket");
            e.printStackTrace();
        }
    }

    public String leerDatos() {
        try{
            if(salida==null || entrada==null)
                return "NO HAY DATOS";
            else {
                salida.writeUTF("recibir$");
                String cad = entrada.readUTF();
                return cad;
            }
        }catch(Exception e){
            System.out.println("Error al leer");
            e.printStackTrace();
        }
        return "";
    }


}
