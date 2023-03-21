/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aplicacion.controllers;

import com.aplicacion.models.ModeloHistorial;
import com.aplicacion.models.ModeloPersonas;
import com.aplicacion.models.ModeloTickets;
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
 * @author Alfredo
 */
@WebServlet(name = "Tickets", urlPatterns = {"/Tickets"})
public class TicketsController extends HttpServlet {
    
    private DataSource DBPool;
    
    private ModeloTickets mTickets;
    private ModeloHistorial mHistorial;
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        try {
            DBPool = ConexionBaseDatos.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            mTickets = new ModeloTickets(DBPool);
            mHistorial = new ModeloHistorial(DBPool);
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
        
        String ticket_id = request.getParameter("id");
        if(ticket_id != null){
            verTicket(request,response,ticket_id);
        } else {
            List<String[]> tickets = new ArrayList();
        
            try {
                tickets = mTickets.getTickets();
            } catch (Exception ex) {
                Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            

            request.setAttribute("tickets", tickets);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Tickets/Tickets.jsp");
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
        
        String estado = "Abierto";
        String fecha = request.getParameter("fecha");
        String persona_id = request.getParameter("persona_id");
        String nota = request.getParameter("nota");
        
        switch(request.getParameter("method")){
            case "post":
                String ticket_id = mTickets.saveTicket(estado,fecha,persona_id);
                mHistorial.saveHistorial(fecha,nota,ticket_id);
                response.sendRedirect("/TicketsCementerioApp/Tickets");
                break;
            case "put":
                updateTicket(request,response);
                break;
            case "delete":
                deleteTicket(request,response);
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

    
    protected void updateTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String ticket_id = request.getParameter("ticket_id");
        String estado = request.getParameter("estado");
        
        mTickets.updateTicket(ticket_id,estado);
        response.sendRedirect("/TicketsCementerioApp/Tickets?id="+ticket_id);
    }
    
    protected void deleteTicket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String id = request.getParameter("ticket_id");
        mTickets.delete(id);
        doGet(request,response);
    }
    
    protected void verTicket(HttpServletRequest request, HttpServletResponse response, String ticket_id)
            throws ServletException, IOException{
        String[] ticket = mTickets.getTicket(ticket_id);
        request.setAttribute("ticket", ticket);
        
        ModeloPersonas mPersona = new ModeloPersonas(DBPool);
        String[] persona = mPersona.getPersona(ticket[3]);
        request.setAttribute("persona", persona);
        
        ModeloHistorial mHistorial = new ModeloHistorial(DBPool);
        List<String[]> historiales = mHistorial.getHIstorialesByTicket(ticket_id);
        request.setAttribute("historiales",historiales);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Tickets/Ticket.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void editarTicket(HttpServletRequest request, HttpServletResponse response, String ticket_id)
            throws ServletException, IOException{
        String[] ticket = mTickets.getTicket(ticket_id);
        System.out.println(ticket[1]);
        request.setAttribute("ticket", ticket);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Tickets/EditarTicket.jsp");
        dispatcher.forward(request, response);
    }
}
