<%-- 
    Document   : Tickets
    Created on : 1 mar. 2023, 08:45:21
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.aplicacion.models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        
        <link href="https://cdn.datatables.net/1.13.3/css/dataTables.bootstrap5.min.css" rel="stylesheet"/>
        <title>Lista de Tickets</title>
        
        <%
            List<String[]> tickets = (List<String[]>) request.getAttribute("tickets");
        %>
    </head>
    <body>
        <div class="container">
            <div class="row mt-5">
                <h1 class="text-center">Lista de Tickets</h1>
            </div>
            <div class="row">
                <div class="col-md-12 mt-3">
                    <a href="/TicketsCementerioApp/NuevoTicket" class="btn btn-success">Crear Nuevo Ticket</a>
                </div>
            </div>
            <div class="row my-5">
                <div class="col-md-12">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Listado de tickets</p>
                        </div>
                        <div class="card-body">
                            <table class="table" id="miTabla">
                                <thead>
                                    <tr>
                                        <th>Fecha</th>
                                        <th>Nombre del Usuario</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(String[] ticket : tickets){ %>
                                    <tr>
                                        <td><%= ticket[2] %></td>
                                        <td><%= ticket[5] %></td>
                                        <td><%= ticket[1] %></td>
                                        <td>
                                            <a href="/TicketsCementerioApp/Tickets?id=<%= ticket[0] %>" class="btn btn-secondary me-2">Ver</a>
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
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/v/dt/dt-1.13.3/datatables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.3/js/dataTables.bootstrap5.min.js"></script>
        <script>
            $('document').ready( function (){
                let table = new DataTable('#miTabla',{
                    language: {
                        url: '//cdn.datatables.net/plug-ins/1.13.3/i18n/es-ES.json',
                    }
                })
            });
                    
        </script>
    </body>
</html>
