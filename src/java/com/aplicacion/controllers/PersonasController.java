/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aplicacion.controllers;

import com.aplicacion.models.ModeloPersonas;
import com.aplicacion.utils.ConexionBaseDatos;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author alfredo
 */
@WebServlet(name = "Personas", urlPatterns = {"/Personas"})
public class PersonasController extends HttpServlet {
    
    
    private DataSource DBPool;
    
    private ModeloPersonas mPersonas;
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        try {
            DBPool = ConexionBaseDatos.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            mPersonas = new ModeloPersonas(DBPool);
        } catch(Exception e){
            throw new ServletException(e);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String persona_id = request.getParameter("id");
        if(persona_id != null){
            if(request.getParameter("editar") != null){
                editarPersona(request,response,persona_id);
            } else {
                verPersona(request,response,persona_id);
            }
            
        } else {
            List<String[]> personas = new ArrayList();
        
            try {
                personas = mPersonas.getPersonas();
            } catch (Exception ex) {
                Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("personas", personas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Personas/Personas.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        
        switch(request.getParameter("method")){
            case "post":
                mPersonas.savePersona(nombre,telefono,email);
                response.sendRedirect("/TicketsCementerioApp/NuevoTicket");  
                break;
            case "put":
                updatePersona(request,response);
                break;
            case "delete":
                deletePersona(request,response);
                break;
            default:
                System.out.println("No hizo nada se fue a default");
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void verPersona(HttpServletRequest request, HttpServletResponse response, String persona_id)
            throws ServletException, IOException{
        String[] persona = mPersonas.getPersona(persona_id);
        request.setAttribute("persona", persona);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Personas/Persona.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void editarPersona(HttpServletRequest request, HttpServletResponse response, String persona_id)
            throws ServletException, IOException{
        String[] persona = mPersonas.getPersona(persona_id);
        System.out.println(persona[1]);
        String ticket_id = request.getParameter("ticket_id");
        System.out.println(ticket_id);
        request.setAttribute("ticket_id",ticket_id);
        request.setAttribute("persona", persona);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Personas/EditarPersona.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void updatePersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String persona_id = request.getParameter("persona_id");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String ticket_id = request.getParameter("ticket_id");
        
        mPersonas.updatePersona(persona_id,nombre,telefono,email);
        response.sendRedirect("/TicketsCementerioApp/Tickets?id="+ticket_id);
    }
    
    protected void deletePersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String id = request.getParameter("id");
        mPersonas.deletePersona(id);
        doGet(request,response);
    }
    
}
