/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejopersonas;
import datos.PersonaJDBC;
import domain.Persona;
import java.util.List;
/**
 *
 * @author user
 */
public class ManejoPersonas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PersonaJDBC personasJDBC = new PersonaJDBC();
        //prueba del metodo insert
        personasJDBC.insert("Lina", "Juares");
        
        //prueba del metodo update
        personasJDBC.update(2, "Maria", "benidto");
        
        //prueba del metodo delete
        personasJDBC.delete(1);
        
        //prueba del metodo select
        //uso del objeto persona para encapsular la inforamcion
        //de un registro de base de datos
        
        List<Persona> personas = personasJDBC.select();
        for (Persona persona: personas){
            System.out.println(persona);
            System.out.println("");
        }
    }
    
}
