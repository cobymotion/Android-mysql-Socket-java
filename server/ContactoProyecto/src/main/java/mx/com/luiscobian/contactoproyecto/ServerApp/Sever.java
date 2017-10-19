package mx.com.luiscobian.contactoproyecto.ServerApp;

/**
 * Main of the application 
 * @author luiscobian 
 */
public class Sever {
    
    public static void main(String[] args) {
        System.out.println("Loading server .... ");
        
        ServerProcess process = new ServerProcess(); 
        process.startCommunicationServer();
        
    }
    
}
