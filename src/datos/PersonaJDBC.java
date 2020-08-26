/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import domain.Persona;
import java.sql.*;
import java.util.*;
/**
 *
 * @author user
 */
public class PersonaJDBC {
    private final String SQL_INSERT = "INSERT INTO persona(nombre, apellido) VALUES(?,?)";
    
    private final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?  WHERE idpersona=?";
    
    private final String SQL_DELETE = "DELETE FROM persona WHERE idpersona = ?";
    
    private final String SQL_SELECT = "SELECT idpersona, nombre, apellido FROM persona ORDER BY idpersona";

public int insert(String nombre, String apellido){
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    int rows = 0; //registros afectados
    try{
        conn = Conexion.getConnecion();
        stmt = conn.prepareStatement(SQL_INSERT);
        int index = 1; //contador de columnas
        stmt.setString(index++, nombre); //param 1 => ?
        stmt.setString(index++, apellido); //param 2 => ?
        System.out.println("Ejecutando query:" + SQL_INSERT);
        rows = stmt.executeUpdate(); //no. registros afectados
        System.out.println("Registros afectados:" + rows);
        
    } catch (SQLException e){
        e.printStackTrace();
    } finally {
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return rows;
}

public int update(int idpersona, String nombre, String apellido){
    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;
    
    try {
        conn = Conexion.getConnecion();
        System.out.println("Ejecutando query:" + SQL_UPDATE);
        stmt = conn.prepareStatement(SQL_UPDATE);
        int index = 1;
        stmt.setString(index++, nombre);
        stmt.setString(index++, apellido);
        stmt.setInt(index, idpersona);
        rows = stmt.executeUpdate();
        System.out.println("Registros actualizados:" + rows);
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return rows;
}

public int delete(int idpersona){
    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;
    try{
        conn = Conexion.getConnecion();
        System.out.println("Ejecutando query:" + SQL_DELETE);
        stmt = conn.prepareStatement(SQL_DELETE);
        stmt.setInt(1, idpersona);
        rows = stmt.executeUpdate();
        System.out.println("Registros eliminados:" + rows);
    } catch (SQLException e){
        e.printStackTrace();
    } finally{
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return rows;
}

public List<Persona> select(){
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Persona persona = null;
    List<Persona> personas = new ArrayList<Persona>();
    
    try{
        conn = Conexion.getConnecion();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        while (rs.next()){
            int idpersona = rs.getInt(1);
            String nombre = rs.getString(2);
            String apellido = rs.getString(3);
            persona = new Persona();
            persona.setId_persona(idpersona);
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            personas.add(persona);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    return personas;
}
}
