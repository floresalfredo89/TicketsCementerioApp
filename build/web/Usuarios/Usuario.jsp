<%-- 
    Document   : Usuario
    Created on : 28 feb. 2023, 18:50:24
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.aplicacion.models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Usuario</title>
        
        <%
            String[] usuario = (String[]) request.getAttribute("usuario");
        %>
    </head>
    <body>
        <div class="container">
            <div class="row mt-5">
                <h1 class="text-center">Usuario</h1>
            </div>
            <div class="row mt-5">
                <div class="col-md-6">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Detalle del Usuario</p>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Nombre</td>
                                        <td><%= usuario[1] %></td>
                                    </tr>
                                    <tr>
                                        <td>Telefono</td>
                                        <td><%= usuario[2] %></td>
                                    </tr>
                                    <tr>
                                        <td>Cargo</td>
                                        <td><%= usuario[3] %></td>
                                    </tr>
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
