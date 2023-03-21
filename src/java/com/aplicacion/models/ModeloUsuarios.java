/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ModeloUsuarios {
    
    
    private DataSource origenDatos;
    
    public ModeloUsuarios(DataSource origenDatos){
        this.origenDatos = origenDatos;
    }
    
    public List<Usuario> getUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList();
        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;
                
        conexion = origenDatos.getConnection();
        st = conexion.createStatement();
        rs = st.executeQuery("SELECT * FROM usuario");
        while(rs.next()){
            String usuario_id = rs.getString("id");
            String nombre = rs.getString("nombre");
            String cargo = rs.getString("cargo");
            String telefono = rs.getString("telefono");
            usuarios.add(new Usuario(usuario_id,nombre,telefono,cargo));
        }
        conexion.close();
        return usuarios;
    }
    
    public void saveUsuario(Usuario usuario) {
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "INSERT INTO usuario VALUES (null,'"+usuario.getNombre()+"',"+usuario.getTelefono()+",'"+usuario.getCargo()+"')";
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public String[] getUsuario(String id){
        String[] usuario = new String[4];
                
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM usuario WHERE id="+id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                usuario[0] = rs.getString("id");
                usuario[1] = rs.getString("nombre");
                usuario[2] = rs.getString("telefono");
                usuario[3] = rs.getString("cargo");
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public Usuario getUsuarioObj(String id){
        Usuario usuario = null;
                
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM usuario WHERE id="+id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if(rs.first()){
                String usuario_id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String cargo = rs.getString("cargo");
                String telefono = rs.getString("telefono");
                usuario = new Usuario(usuario_id,telefono,nombre,cargo);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public void update(String id, String nombre, String telefono, String cargo){
        try {
            Connection conexion = origenDatos.getConnection();
            Statement st = conexion.createStatement();
            String sql = "UPDATE usuario SET nombre='"+nombre+"',telefono='"+telefono+"',cargo='"+cargo+"' WHERE id="+id;
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
            String sql = "DELETE FROM usuario WHERE id="+id;
            st.executeUpdate(sql);
            conexion.close();
        } catch (Exception ex){
            Logger.getLogger(ModeloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
}
