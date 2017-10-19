
package mx.com.luiscobian.contactoproyecto.ServerApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import mx.com.luiscobian.contactoproyecto.control.DecodingDaoSocket;
import mx.com.luiscobian.contactoproyecto.model.DtoContacto;

/**
 * Manager of Client, This allow the operations to communications with Client 
 * @author luiscobian
 */

public class ClientProcessServer implements 
        Runnable,ListDataListener{
    
    DataInputStream datosEntrada ; 
    DataOutputStream datosSalida;
    DefaultListModel lista;

    public ClientProcessServer(DataInputStream datosEntrada,
                       DataOutputStream datosSalida, 
                       DefaultListModel lista) {
        this.datosEntrada = datosEntrada;
        this.datosSalida = datosSalida; 
        this.lista = lista; 
        this.lista.addListDataListener(this);
    }

    @Override
    public void run() {
       String cad = ""; 
       while(!cad.equals("salir")){
           try {
               cad = datosEntrada.readUTF();
               synchronized(lista)
               {
                   StringTokenizer tokenizer = new StringTokenizer(cad, "$"); 
                   String operacion = tokenizer.nextToken(); 
                   switch(operacion)
                   {
                       case "agregar": 
                            DecodingDaoSocket dds = new DecodingDaoSocket(); 
                            boolean ok = dds.addContactDecode(cad);
                            if(ok)
                                lista.addElement("Ok");
                            else 
                                lista.addElement("Fail");
                            break; 
                       case "recibir":
                           DecodingDaoSocket ddsq = new DecodingDaoSocket(); 
                           List<DtoContacto> contacts = ddsq.getContacsDecode(cad); 
                           int cont=0; 
                           cad = ""; 
                           for(DtoContacto contact: contacts)
                           {
                               cont++; 
                               cad = cad.concat("R" + cont + "$" + contact.getId() +
                                       "$" + contact.getNombre() + "$"+contact.getEmail()+"#");                                
                           }
                           System.out.println(cad);
                           lista.addElement(cad);                            
                   }
                   
               }
           } catch (IOException ex) {
           }
       }
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
       
       String cad = (String) lista.getElementAt
                (e.getIndex0()); 
        try {
            datosSalida.writeUTF(cad);
        } catch (Exception ee) {
            System.out.println("Send Message Failed");
            System.out.println(ee.toString());
        }
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
       
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
       
    }
}