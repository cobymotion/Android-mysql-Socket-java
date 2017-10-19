/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.luiscobian.contactoproyecto.control;

import java.util.List;
import mx.com.luiscobian.contactoproyecto.model.DtoContacto;

/**
 *
 * @author luiscobian
 */
public class DecodingDaoSocket {
    
    
     public boolean addContactDecode(String string)
     {
         boolean ok = false; 
         String tokens[] = string.split("\\$"); 
         if(tokens.length>=4)
         {
             DtoContacto contact = new DtoContacto(Integer.parseInt(tokens[1]), tokens[2],tokens[3]);
             DaoContacto dao = new DaoContacto(); 
             ok=dao.addContact(contact);
         }
         return ok; 
     }
     
     public List<DtoContacto> getContacsDecode(String string)
     {
         List<DtoContacto> contacts = null; 
         String tokens[] = string.split("\\$"); 
         DaoContacto dao = new DaoContacto(); 
         if(tokens.length>=2)
         {
             contacts = dao.getContacts(tokens[1]); 
         } else {
             contacts = dao.getContacts("");
         }
         return contacts; 
     }
    
}
