<%-- 
    Document   : NuevoTicket
    Created on : 1 mar. 2023, 08:54:20
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.aplicacion.models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <title>Nuevo Ticket</title>
        <%
            List<String[]> personas = (List<String[]>) request.getAttribute("personas");
        %>
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row text-center mt-4">
                <h1>Crear Nuevo Ticket</h1>
            </div>
            <div class="row my-4">
                <a href="/TicketsCementerioApp/Tickets">Volver al Listado de Tickets</a>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card shadow p-4">
                        <form action="/TicketsCementerioApp/Tickets" method="POST">
                            <input hidden type="hidden" name="method" value="post">
                            <div class="mb-3">
                                <label for="persona" class="form-label">Crear nuevo registro para usuario</label>
                                <select class="form-select" name="persona_id">
                                    <option value=""> -- Selecciona -- </option>
                                    <% for(String[] persona : personas){ %>
                                    <option value="<%= persona[0] %>"><%= persona[1] %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#miModal">
                                    Crear Nueva Persona
                                </button>
                            </div>
                            <div class="mb-3">
                                <label for="fecha" class="form-label">Fecha de apertura</label>
                                <input type="date" name="fecha" id="fecha" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="nota" class="form-label">Describe la situacion o consulta realizadas</label>
                                <textarea name="nota" id="nota" class="form-control"></textarea>
                            </div>
                            <div class="mb-3">
                                <input type="submit" id="boton" class="btn btn-success" value="Guardar">
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            <!-- Modal -->
            <div class="modal fade" id="miModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Crea un Nuevo Registro de Usuario</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/TicketsCementerioApp/Personas" method="POST">
                                <input hidden type="hidden" name="method" value="post">
                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre</label>
                                    <input type="text" name="nombre" id="nombre" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label for="telefono" class="form-label">Teléfono</label>
                                    <input type="phone" name="telefono" id="telefono" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Correo Electrónico</label>
                                    <input type="email" name="email" id="email" class="form-control">
                                </div>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
                $('.form-select').select2();
            });
        </script>
    </body>
</html>
