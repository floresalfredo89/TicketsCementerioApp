/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aplicacion.controllers;

import com.aplicacion.models.ModeloHistorial;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author alfredo
 */
@WebServlet(name = "Historiales", urlPatterns = {"/Historiales"})
public class HistorialesController extends HttpServlet {
    
    private DataSource DBPool;
    
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
        
        String editar = request.getParameter("editar");
        String ticket_id = request.getParameter("ticket_id");
        
        if(editar != null){
            editarHistorial(request,response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Tickets?id="+ticket_id);
            rd.forward(request, response);
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
        
        String fecha = request.getParameter("fecha");
        String nota = request.getParameter("nota");
        String ticket_id = request.getParameter("ticket_id");
        
        switch(request.getParameter("method")){
            case "post":
                mHistorial.saveHistorial(fecha,nota,ticket_id);
                
                response.sendRedirect("/TicketsCementerioApp/Tickets?id="+ticket_id);
                break;
            case "put":
                updateHistorial(request,response);
                break;
            case "delete":
                deleteHistorial(request,response);
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

    protected void updateHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String ticket_id = request.getParameter("ticket_id");
        String fecha = request.getParameter("fecha");
        String nota = request.getParameter("nota");
        String historial_id = request.getParameter("historial_id");
        
        mHistorial.updateHistorial(historial_id,fecha,nota);
        response.sendRedirect("/TicketsCementerioApp/Tickets?id="+ticket_id);
    }
    
    protected void deleteHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String historial_id = request.getParameter("historial_id");
        String ticket_id = request.getParameter("ticket_id");
        
        mHistorial.deleteHistorial(historial_id);
        response.sendRedirect("/TicketsCementerioApp/Tickets?id="+ticket_id);  
    }
    
    protected void editarHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String historial_id = request.getParameter("historial_id");
        String[] historial = mHistorial.getHistorial(historial_id);
        request.setAttribute("historial", historial);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Historiales/EditarHistorial.jsp");
        dispatcher.forward(request, response);
    }
    
}
