<%-- 
    Document   : EditarUsuario
    Created on : 27 feb. 2023, 09:32:41
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Inicio</title>
        <%
            String[] usuario = (String[]) request.getAttribute("usuario");
        %>
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="row text-center">
                    <h1>Editar Usuario</h1>
                </div>
                <div class="col-md-12">
                    
                    <div class="card shadow p-3">
                        <div class="card-body">
                            <h2>Realiza los cambios necesarios</h2>
                            <form action="/TicketsCementerioApp/Usuarios" method="POST">
                                <input hidden type="hidden" name="method" value="put">
                                <input hidden type="hidden" name="usuario_id" value="<%= usuario[0]%>">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="nombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" name="nombre" id="name" value="<%= usuario[1]%>">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="telefono" class="form-label">Número de Teléfono</label>
                                        <input type="number" class="form-control" name="telefono" id="telefono" value="<%= usuario[2]%>">
                                    </div>
                                </div>

                                <div>
                                    <label for="cargo" class="form-label">Cargo que ostenta</label>
                                    <input type="text" class="form-control" name="cargo" id="cargo" value="<%= usuario[3]%>">
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
