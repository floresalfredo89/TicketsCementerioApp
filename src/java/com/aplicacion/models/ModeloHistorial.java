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
public class ModeloHistorial {
    private DataSource origenDatos;
    
    public ModeloHistorial(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }
    
    public List<String[]> getHIstoriales() throws Exception {
        List<String[]> historiales = new ArrayList();
        
        Connection conexion = origenDatos.getConnection();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM historial");
        while(rs.next()){
            String historial_id = rs.getString("id");
            String fecha = rs.getString("fecha");
            String nota = rs.getString("nota");
            String ticket_id = rs.getString("ticket_id");
            String[] historial = {historial_id,fecha,nota,ticket_id};
            System.out.println(historial[1]);
            historiales.add(historial);
        }
        conexion.close();
        
        return historiales;
    }
    
    public List<String[]> getHIstorialesByTicket(String ticket_id) {
        List<String[]> historiales = new ArrayList();
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM historial WHERE ticket_id="+ticket_id);
            while(rs.next()){
                String historial_id = rs.getString("id");
                String fecha = rs.getString("fecha");
                String nota = rs.getString("nota");
                String ticket_id_fk = rs.getString("ticket_id");
                String[] historial = {historial_id,fecha,nota,ticket_id_fk};
                System.out.println(historial[1]);
                historiales.add(historial);
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloHistorial.class.getName()).log(Level.SEVERE, null, e);
        }
        return historiales;
    }
    
    public void saveHistorial(String fecha, String nota, String ticket_id) {
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO historial VALUES (null,'"+fecha+"','"+nota+"',null,"+ticket_id+")";
            st.executeUpdate(sql);
            System.out.println(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public String[] getHistorial(String id){
        String[] historial = new String[4];
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM historial WHERE id="+id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                historial[0] = rs.getString("id");
                historial[1] = rs.getString("fecha");
                historial[2] = rs.getString("nota");
                historial[3] = rs.getString("ticket_id");
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloTickets.class.getName()).log(Level.SEVERE, null, e);
        }
        return historial;
    }
    
    public void updateHistorial(String id,String fecha,String nota){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "UPDATE historial SET fecha='"+fecha+"',nota='"+nota+"' WHERE id="+id;
            System.out.println(sql);
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public void deleteHistorial(String id){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "DELETE FROM historial WHERE id="+id;
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
}
