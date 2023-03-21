<%-- 
    Document   : EditarHistorial
    Created on : 6 mar 2023, 9:20:27
    Author     : alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Actualizar Datos del Historial</title>
    </head>
    <%
        String[] historial = (String[]) request.getAttribute("historial");
    %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="row text-center mt-4">
                    <h1>Editar Historial</h1>
                </div>
                <div class="row my-4">
                    <a href="/TicketsCementerioApp/Tickets?id=<%= historial[3] %>">Volver</a>
                </div>
                <div class="col-md-12">
                    
                    <div class="card shadow p-3">
                        <div class="card-body">
                            <h2>Realiza los cambios necesarios</h2>
                            <form action="/TicketsCementerioApp/Historiales" method="POST">
                                <input hidden type="hidden" name="method" value="put">
                                <input hidden type="hidden" name="historial_id" value="<%= historial[0]%>">
                                <input hidden type="hidden" name="ticket_id" value="<%= historial[3] %>">
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="fecha" class="form-label">Fecha de la Nota</label>
                                        <input type="date" class="form-control" name="fecha" id="fecha" value="<%= historial[1]%>">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="nota" class="form-label">Nota</label>
                                        <textarea id="nota" name="nota" rows="5" cols="10" class="form-control"><%= historial[2]%></textarea>
                                    </div>
                                </div>
                                <div>
                                    <button class="btn btn-success mt-4">Guardar Cambios</button>
                                </div>
                            </form>
                        </div>

                    </div>
                        
                    
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>
</html>
