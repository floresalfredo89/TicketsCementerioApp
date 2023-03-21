/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aplicacion.controllers;

import com.aplicacion.models.ModeloUsuarios;
import com.aplicacion.models.Usuario;
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
@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class UsuariosController extends HttpServlet {
    
    // Se usaba para llamar el resource desde context.xml
    //@Resource(name="jdbc/tickets")
    private DataSource DBPool;
    
    private ModeloUsuarios mUsuarios;
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        try {
            DBPool = ConexionBaseDatos.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            mUsuarios = new ModeloUsuarios(DBPool);
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
        
        if(request.getParameter("id") != null){
            if(request.getParameter("editar") != null){
                editarUsuario(request,response);
            } else {
                verUsuario(request,response);
            }
        } else {
            List<Usuario> usuarios = new ArrayList();
        
            try {
                usuarios = mUsuarios.getUsuarios();
            } catch (Exception ex) {
                Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(usuarios.isEmpty()){
                Usuario tempUsuario = new Usuario("Bot","1234","probar que no funciono la consulta a BD");
                usuarios.add(tempUsuario);
            }

            request.setAttribute("lista_usuarios", usuarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Usuarios/Usuarios.jsp");
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
        
        
        String nombre = (String) request.getParameter("nombre");
        String telefono = (String) request.getParameter("telefono");
        String cargo = (String) request.getParameter("cargo");
        Usuario usuario = null;
        String id = "";
        
        switch(request.getParameter("method")){
            case "post":
                usuario = new Usuario(nombre,telefono,cargo);
                mUsuarios.saveUsuario(usuario);
                response.sendRedirect(request.getContextPath() + "/Usuarios");
                break;
            case "put":
                updateUsuario(request,response);
                break;
            case "delete":
                deleteUsuario(request,response);
                break;
            default:
                System.out.println("No hizo nada se fue a default");
                response.sendRedirect(request.getContextPath() + "/Usuarios");
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
    
    protected void verUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String id = request.getParameter("id");
        String[] usuario = mUsuarios.getUsuario(id);
        request.setAttribute("usuario",usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Usuarios/Usuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String id = request.getParameter("id");
        String[] usuario = mUsuarios.getUsuario(id);
        request.setAttribute("usuario",usuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Usuarios/EditarUsuario.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void updateUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String id = request.getParameter("usuario_id");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String cargo = request.getParameter("cargo");
        mUsuarios.update(id,nombre,telefono,cargo);
        response.sendRedirect(request.getContextPath() + "/Usuarios");
    }
    
    protected void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String id = request.getParameter("usuario_id");
        //usuario = mUsuarios.getUsuarioObj(id);
        mUsuarios.delete(id);
        response.sendRedirect(request.getContextPath() + "/Usuarios");
    }
    
}
