<%-- 
    Document   : Ticket
    Created on : 1 mar. 2023, 09:50:09
    Author     : Alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.aplicacion.models.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <title>Ticket</title>
        <%
            String[] ticket = (String[]) request.getAttribute("ticket");
            String[] persona = (String[]) request.getAttribute("persona");
            List<String[]> historiales = (List<String[]>) request.getAttribute("historiales");
        %>
    </head>
    <body>
        <div class="container">
            <div class="row mt-5">
                <h1 class="text-center">Ticket No.<%= ticket[0] %></h1>
            </div>
            <div class="row mt-4">
                <a href="/TicketsCementerioApp/Tickets">Volver al Listado de Tickets</a>
            </div>
            <div class="row mt-5">
                <div class="col-md-6">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Detalles del Ticket</p>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>No. de Ticket</td>
                                        <td><%= ticket[0] %></td>
                                    </tr>
                                    <tr>
                                        <td>Estado</td>
                                        <td><%= ticket[1] %></td>
                                    </tr>
                                    <tr>
                                        <td>Fecha</td>
                                        <td><%= ticket[2] %></td>
                                    </tr>
                                </tbody>
                            </table>
                            <form action="/TicketsCementerioApp/Tickets" method="post">
                                <input type="hidden" id="method" name="method" value="put">
                                <input type="hidden" id="ticket_id" name="ticket_id" value="<%= ticket[0] %>">
                                <label for="estado" class="form-label">Selecciona para cambiar el estado del ticket</label>
                                <select name="estado" id="estado" class="form-select mb-3">
                                    <option value=''> -- Selecciona --</option>
                                    <option value='En curso'> En curso </option>
                                    <option value='Cerrado'> Cerrado </option>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Actualizar Estado">
                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Datos del Usuario</p>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Nombre del interesado</td>
                                        <td><%= persona[1] %></td>
                                    </tr>
                                    <tr>
                                        <td>Telefono</td>
                                        <td><%= persona[2] %></td>
                                    </tr>
                                    <tr>
                                        <td>Correo electrónico</td>
                                        <td><%= persona[3] %></td>
                                    </tr>
                                </tbody>
                            </table>
                            <a href="/TicketsCementerioApp/Personas?id=<%= persona[0] %>&editar=true&ticket_id=<%= ticket[0] %>" class='btn btn-warning'>Editar Datos</a>
                        </div>

                    </div>
                </div>
            </div>
            <div class="row my-4">
                <div class="col-md-12">
                    <div class="card shadow">
                        <div class="card-header">
                            <p class="fs-4 fw-bold">Historial</p>
                            <div class="mb-3">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#miModal">
                                    Crear Nueva Entrada
                                </button>
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                <th>Fecha</th>
                                <th>Nota</th>
                                <th>Opciones</th>
                                </thead>
                                <tbody>
                                    <% for(String[] historial : historiales){ %>
                                    <tr>
                                        <td><%= historial[1] %></td>
                                        <td><%= historial[2] %></td>
                                        <td class="d-inline-flex">
                                            <a href="/TicketsCementerioApp/Historiales?historial_id=<%= historial[0] %>&editar=true" class="btn btn-warning me-2">Editar</a>
                                            <button type="submit" class="btn btn-danger" value="<%= historial[0] %>">Eliminar</button>                                            
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

        <!-- Modal Nuevo Historial -->
        <div class="modal fade" id="miModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="miModalLabel">Crea una nueva entrada en el historial</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="/TicketsCementerioApp/Historiales" method="POST">
                            <input hidden type="hidden" name="method" value="post">
                            <input type="hidden" name="ticket_id" value="<%= ticket[0] %>">
                            <div class="mb-3">
                                <label for="fecha" class="form-label">Fecha</label>
                                <input type="date" name="fecha" id="fecha" class="form-control">
                            </div>
                            <div class="mb-3">
                                <label for="nota" class="form-label">Nota</label>
                                <textarea id="nota" name="nota" class="form-control"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Guardar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <script>
            $(document).ready( function () {
                $(".btn-danger").click(function(event){
                    var historial_id = $(this).val();
                    event.preventDefault();
                    Swal.fire({
                        title: '¿Desear eliminar esta nota del historial?',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Si, Eliminar',
                        cancelButtonText: 'Cancelar'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            $.ajax({
                                type: "POST",
                                url: "/TicketsCementerioApp/Historiales",
                                data: {
                                    method: 'delete',
                                    historial_id: historial_id
                                },
                                success: function () {
                                    window.location.reload();
                                },
                                error: function(data){
                                    alert("Error al eliminar");
                                }
                            });
                            
                        }
                    })
                });
            });
        </script>
    </body>
</html>
