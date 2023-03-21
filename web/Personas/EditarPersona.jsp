<%-- 
    Document   : EditarPersona
    Created on : 6 mar 2023, 8:22:54
    Author     : alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Actualizar Datos del Usuario</title>
    </head>
    <%
        String[] persona = (String[]) request.getAttribute("persona");
        String ticket_id = (String) request.getAttribute("ticket_id");
    %>
    <body>
        <div class="container">
            <div class="row mt-4">
                <div class="row text-center">
                    <h1>Editar Usuario</h1>
                </div>
                <div class="row my-4">
                    <a href="/TicketsCementerioApp/Tickets?id=<%= ticket_id %>">Volver</a>
                </div>
                <div class="col-md-12">
                    <div class="card shadow p-3">
                        <div class="card-body">
                            <h2>Realiza los cambios necesarios</h2>
                            <form action="/TicketsCementerioApp/Personas" method="POST">
                                <input hidden type="hidden" name="method" value="put">
                                <input hidden type="hidden" name="persona_id" value="<%= persona[0]%>">
                                <input hidden type="hidden" name="ticket_id" value="<%= ticket_id%>">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="nombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" name="nombre" id="name" value="<%= persona[1]%>">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="telefono" class="form-label">Número de Teléfono</label>
                                        <input type="text" class="form-control" name="telefono" id="telefono" value="<%= persona[2]%>">
                                    </div>
                                </div>
                                <div>
                                    <label for="email" class="form-label">Correo electrónico</label>
                                    <input type="text" class="form-control" name="email" id="email" value="<%= persona[3]%>">
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
