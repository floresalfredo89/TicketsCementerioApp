/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aplicacion.controllers;

import com.aplicacion.models.ModeloPersonas;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author alfredo
 */
@WebServlet(name = "NuevoTicket", urlPatterns = {"/NuevoTicket"})
public class NuevoTicketController extends HttpServlet {
    
    @Resource(name="jdbc/tickets")
    private DataSource DBPool;
    
    private ModeloPersonas mPersonas;
    
    @Override
    public void init() throws ServletException{
        super.init();
        
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
        List<String[]> personas = new ArrayList();
        
        try {
            personas = mPersonas.getPersonas();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < personas.size(); i++){
            String[] persona = personas.get(i);
            System.out.println("persona contiene: "+persona[1]);
        }

        request.setAttribute("personas", personas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Tickets/NuevoTicket.jsp");
        dispatcher.forward(request, response);
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
        // Nada
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

}
