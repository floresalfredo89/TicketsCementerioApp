/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.models;

/**
 *
 * @author Alfredo
 */
public class Usuario {
    private String id,nombre,cargo,telefono;
    
    public Usuario(String id, String nombre, String telefono, String cargo){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cargo = cargo;
    }
    
    public Usuario(String nombre, String telefono, String cargo){
        this.nombre = nombre;
        this.telefono = telefono;
        this.cargo = cargo;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getCargo(){
        return this.cargo;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
}
