/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.entities;

import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

/**
 *
 * @author alfredo
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="estado")
    private String estado;
    
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="persona_id")
    private Long persona_id;
    
    @OneToMany
    private Persona persona;

    public String getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getPersona_id() {
        return persona_id;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPersona_id(Long persona_id) {
        this.persona_id = persona_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tickets{" + "id=" + id + ", estado=" + estado + ", fecha=" + fecha + ", persona_id=" + persona_id + '}';
    }
    
}
