
package mx.com.luiscobian.contactoproyecto.ServerApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 * Configuration File to socket connect 
 * @author luiscobian
 */
public class ServerProcess {

    DefaultListModel lista = new DefaultListModel();
    
    public void startCommunicationServer(){
         System.out.println("Opening communications"); 

        try {
            
            ServerSocket sConexion
                    = new ServerSocket(6007);
            while(true){
                System.out.println("Waiting for client ");
            Socket sCliente = sConexion.accept();
                System.out.println("Client Connect....");
            DataInputStream dis = new 
                      DataInputStream(sCliente
                              .getInputStream());
                DataOutputStream dos = new 
                       DataOutputStream(sCliente
                       .getOutputStream()); 
               Thread hilo = new Thread
                         (new ClientProcessServer(dis,dos,lista)); 
               hilo.start();
            }
           
        } catch (IOException e) {
            System.out.println("<Error> Open communications Failed!!! ");
            System.out.println(e.toString());
        }
    }
    
    
}
