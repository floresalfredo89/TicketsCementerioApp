/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aplicacion.service;

import com.aplicacion.entities.Persona;
import java.util.List;

/**
 *
 * @author alfredo
 */
public interface PersonaDAO {
    
    public List<Persona> findAllPersonas();
    
    public Persona findPersonaById(Persona persona);
    
    public Persona findPersonaByNombre(Persona persona);
    
    public void insertPersona(Persona persona);
    
}
