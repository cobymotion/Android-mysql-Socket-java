package mx.com.luiscobian.contactoproyecto.mysql;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connection with Mysql Server 
 * @author luiscobian
 */
public class Connection {
    
    private String user="root"; 
    private String pass=""; 
    private String databaseName="dbcontacto"; 
    private String host="localhost"; 
    
    private static Connection con; 
    private static java.sql.Connection conMysql; 
   
    public static Connection 
             newInstance(){
        if(con==null || conMysql==null)
            con = new Connection(); 
        return con; 
    }

    private Connection() {
        try {
            conMysql = DriverManager.getConnection("jdbc:mysql://"+host+"/"+databaseName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,pass);            
        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo conectar");
            System.out.println(ex.toString());
        }
        System.out.println("Se conecto correctamente");               
    }
    
    
    public boolean executeDML(String sql, Object ...params)
    {
        boolean ok=false; 
        if(conMysql!=null)
        {
            try { 
                PreparedStatement ps = conMysql.prepareStatement(sql);
                for(int i=0;i<params.length;i++)
                    ps.setObject(i+1, params[i]);
                ps.execute(); 
                ok=true;
            } catch (SQLException ex) {
                System.out.println("Error al guardar");
                System.out.println(ex.toString());
                throw new RuntimeException("Error al leer los datos");
            }
            
        }
        
        return ok;
            
    }
    
    
    public ResultSet executeQuery(String sql, Object ...params)
    {
        ResultSet rs = null; 
        if(conMysql!=null)
        {
            try { 
                PreparedStatement ps = conMysql.prepareStatement(sql);
                for(int i=0;i<params.length;i++)
                    ps.setObject(i+1, params[i]);
               rs = ps.executeQuery();
            } catch (SQLException ex) {
                System.out.println("Error al guardar");
                System.out.println(ex.toString());
            }
        }
        return rs; 
    }
    
             
             
    
}
