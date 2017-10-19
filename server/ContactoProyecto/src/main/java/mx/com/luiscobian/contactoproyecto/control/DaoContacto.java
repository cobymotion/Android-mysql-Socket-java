package mx.com.luiscobian.contactoproyecto.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.luiscobian.contactoproyecto.model.DtoContacto;
import mx.com.luiscobian.contactoproyecto.mysql.Connection;

/**
 * Data Access Object 
 * @author luiscobian
 */
public class DaoContacto {
    
    public boolean addContact(DtoContacto contact)
    {
        boolean ok ; 
        String sql="INSERT INTO contacto VALUES(?,?,?)"; 
        Object params[] = new Object[3]; 
        params[0] = contact.getId(); 
        params[1] = contact.getNombre(); 
        params[2] = contact.getEmail(); 
        Connection con = Connection.newInstance(); 
        ok = con.executeDML(sql, params);         
        return ok; 
        
    }
    
    public List<DtoContacto> getContacts(String filter)
    {
        List<DtoContacto> contacts = new ArrayList<>(); 
        String sql = "SELECT * FROM contacto WHERE nombre like '%"+filter+"%'"; 
        System.out.println(sql);
        Connection con = Connection.newInstance(); 
        ResultSet rs = con.executeQuery(sql); 
        if(rs==null)
            return null; 
        try { 
        while(rs.next())
        {
            DtoContacto dtoContact = new DtoContacto(); 
            dtoContact.setId(rs.getInt(1));
            dtoContact.setNombre(rs.getString(2));
            dtoContact.setEmail(rs.getString(3));
            contacts.add(dtoContact);
        }
        }catch(SQLException|RuntimeException ex)
        {
            System.out.println("Ocurrio al trer datos");
            System.out.println(ex.toString());
        }
        
        return contacts; 
    }
    
}
