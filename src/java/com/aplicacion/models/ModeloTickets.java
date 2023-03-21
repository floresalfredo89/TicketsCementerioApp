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
 * @author Alfredo
 */
public class ModeloTickets {
    private DataSource origenDatos;
    
    public ModeloTickets(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }
    
    public List<String[]> getTickets(){
        List<String[]> tickets = new ArrayList();
         
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ticket");
            while(rs.next()){
                String ticket_id = rs.getString("id");
                String estado = rs.getString("estado");
                String fecha = rs.getString("fecha");
                String persona_id = rs.getString("persona_id");
                ModeloPersonas mPersona = new ModeloPersonas(origenDatos);
                String[] persona = mPersona.getPersona(persona_id);
                String[] ticket = {ticket_id,estado,fecha,persona_id,persona[0],persona[1],persona[2],persona[3]};
                tickets.add(ticket);
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloTickets.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return tickets;
    }
    
    public String saveTicket(String estado, String fecha, String persona_id){
        String ticket_id = null;
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO ticket VALUES (null,'"+estado+"','"+fecha+"',"+persona_id+")";
            System.out.println(sql);
            st.executeUpdate(sql);
            conexion.close();
            ticket_id = getUltimoTicket();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
        return ticket_id;
    }
    
    public String[] getTicket(String id){
        String[] ticket = new String[4];
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM ticket WHERE id="+id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ticket[0] = rs.getString("id");
                ticket[1] = rs.getString("estado");
                ticket[2] = rs.getString("fecha");
                ticket[3] = rs.getString("persona_id");
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloTickets.class.getName()).log(Level.SEVERE, null, e);
        }
        return ticket;
    }
    
    public void update(String ticket_id,String estado,String fecha,String persona_id){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "UPDATE ticket SET estado='"+estado+"',fecha='"+fecha+"',persona_id="+persona_id+" WHERE id="+ticket_id;
            System.out.println(sql);
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public void updateTicket(String ticket_id,String estado){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "UPDATE ticket SET estado='"+estado+"' WHERE id="+ticket_id;
            System.out.println(sql);
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public void delete(String id){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "DELETE FROM ticket WHERE id="+id;
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    private String getUltimoTicket(){
        String ticket_id = null;
        try{
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ticket ORDER BY id DESC LIMIT 1");
            if(rs.next()){
                ticket_id = rs.getString("id");
            }
            conexion.close();
        } catch (Exception e) {
            Logger.getLogger(ModeloTickets.class.getName()).log(Level.SEVERE, null, e);
        }
        return ticket_id;
    }
}
