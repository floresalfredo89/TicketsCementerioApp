/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author alfredo
 */
public class ModeloPersonas {
    
    private DataSource origenDatos;
    
    public ModeloPersonas(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }
    
    public List<String[]> getPersonas() throws Exception {
        List<String[]> personas = new ArrayList();
        
        Connection conexion = origenDatos.getConnection();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM persona");
        while(rs.next()){
            String persona_id = rs.getString("id");
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String[] persona = {persona_id,nombre,telefono,email};
            System.out.println(persona[1]);
            personas.add(persona);
        }
        conexion.close();
        
        return personas;
    }
    
    public void savePersona(String nombre, String telefono, String email) {
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO persona VALUES (null,'"+nombre+"',"+telefono+",'"+email+"')";
            st.executeUpdate(sql);
            System.out.println(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public String[] getPersona(String id){
        String[] persona = new String[4];
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM persona WHERE id="+id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                persona[0] = rs.getString("id");
                persona[1] = rs.getString("nombre");
                persona[2] = rs.getString("telefono");
                persona[3] = rs.getString("email");
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloTickets.class.getName()).log(Level.SEVERE, null, e);
        }
        return persona;
    }
    
    public void updatePersona(String id,String nombre,String telefono,String email){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "UPDATE persona SET nombre='"+nombre+"',telefono='"+telefono+"',email='"+email+"' WHERE id="+id;
            System.out.println(sql);
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public void deletePersona(String id){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "DELETE FROM persona WHERE id="+id;
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
}
