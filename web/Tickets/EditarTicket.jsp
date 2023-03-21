<%-- 
    Document   : EditarTicket
    Created on : 1 mar. 2023, 09:17:51
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Editar Ticket</title>
        <%
            String[] ticket = (String[]) request.getAttribute("ticket");
        %>
    </head>
    <body>
        <div class="container">
            <div class="row text-center">
                <h1>Editar Ticket</h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card shadow p-4">
                        <form action="/TicketsCementerioApp/Tickets" method="POST">
                            <input hidden type="hidden" name="method" value="put">
                            <input hidden type="hidden" name="ticket_id" value="<%=ticket[0]%>">
                            <div class="form-group">
                                <label for="estado" class="form-label">Estado del Ticket</label>
                                <input type="text" name="estado" id="estado" class="form-control" value="<%=ticket[1]%>">
                            </div>
                            <div class="form-group">
                                <label for="fecha" class="form-label">Fecha de apertura</label>
                                <input type="date" name="fecha" id="fecha" class="form-control" value="<%=ticket[2]%>">
                            </div>
                            <div class="form-group">
                                <label for="persona" class="form-label">Persona que lo inicia</label>
                                <input type="number" name="persona_id" id="persona_id" class="form-control" value="<%=ticket[3]%>">
                            </div>
                            <div class="form-group mt-3">
                                <input type="submit" id="boton" class="btn btn-success" value="Guardar Cambios">
                            </div>
                        </form>
                    </div>
                </div>
                
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>
</html>
