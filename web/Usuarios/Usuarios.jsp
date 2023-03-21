<%-- 
    Document   : Usuarios
    Created on : 28 feb. 2023, 17:25:46
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.aplicacion.models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Usuarios</title>
        
        <%
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("lista_usuarios");
        %>
    </head>
    <body>
        <div class="container">
            <div class="row mt-5">
                <h1 class="text-center">Listado de Usuarios</h1>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <button class="btn btn-success" onclick="window.location.href='Usuarios/NuevoUsuario.jsp'">Agregar Nuevo Usuario</button>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col-md-12">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Usuarios Disponibles</p>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Telefono</th>
                                        <th>Cargo</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(Usuario usu : usuarios){ %>
                                    <tr>
                                        <td><%= usu.getNombre() %></td>
                                        <td><%= usu.getTelefono() %></td>
                                        <td><%= usu.getCargo() %></td>
                                        <td class="d-inline-flex">
                                            <a href="/TicketsCementerioApp/Usuarios?id=<%= usu.getId() %>" class="btn btn-secondary me-2">Ver</a> 
                                            <a href="/TicketsCementerioApp/Usuarios?editar=true&id=<%= usu.getId() %>" class="btn btn-warning me-2">Editar</a> 
                                            <form action="/TicketsCementerioApp/Usuarios" method="post">
                                                <input hidden type="hidden" name="method" value="delete">
                                                <input hidden type="hidden" name="usuario_id" value="<%= usu.getId() %>">
                                                <input type="submit" class="btn btn-danger" name="method" value="Eliminar">
                                            </form>
                                        </td>
                                    </tr>
                                <% } %>
                                </tbody>
                            </table>
                            
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>
</html>
