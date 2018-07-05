<%-- 
    Document   : registro
    Created on : Jul 4, 2018, 06:10:02 PM
    Author     : jpalillero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-notify/0.2.0/css/bootstrap-notify.min.css" type="text/css">
        
        <link rel="stylesheet" href="<c:url value='resources/css/theme.css'/>" />
        
    </head>

    <body>
        <div class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading text-center">
                                <h3>
                                    Registro de empleado
                                </h3>
                            </div>
                            <div class="panel-body">
                                <form id="registroForm">
                                    <div class="form-group">
                                        <label>Numero de empleado</label>
                                        <input type="text" id="numEmpleado" class="form-control" placeholder="Ingrese su número de empleado">
                                        <small class="form-text text-muted"></small>
                                    </div>
                                    <button type="button" id="sendEnter" class="btn btn-primary">Entrada</button>
                                    <button type="button" id="sendExit" class="btn btn-danger">Salida</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card text-white p-5 bg-primary" >
                            <div class="card-body">
                                <h1 class="mb-4">Ingresar</h1>
                                <form id="loginForm">
                                    <div class="form-group">
                                        <label>Número de empleado</label>
                                        <input type="text" id="numEmpleadoL" class="form-control" placeholder="Ingrese su número de empleado"> 
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" id="password" class="form-control" placeholder="Ingrese su password"> 
                                    </div>
                                    <button type="button" id="sendLogin" class="btn btn-secondary">Login</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="http://bootstrap-notify.remabledesigns.com/js/bootstrap-notify.min.js"></script>
        <script src="<c:url value='resources/js/funciones.js'/>"></script>
        <script>
            $(document).ready(function(){
               funciones.registro.init(); 
            });
        </script>
    </body>

</html>
