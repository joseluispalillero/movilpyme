/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
funciones = {
    registro: {
        init: function () {
            $("#sendEnter").on("click", function () {
                $("#sendEnter").attr("disabled", true);    
                console.log("envio de entrada");
                $.ajax({
                    type: "POST",
                    url: "empleado/registro",
                    dataType: "json",
                    data:{numeroEmpleado: $("#registroForm #numEmpleado").val()},
                    cache: false,
                    async: true,
                    success: function (resp) {
                        if (resp.message){
                            $.notify({
                                message: resp.message
                            }, {
                                type: 'danger'
                            });
                        }else{
                            $.notify({
                                message: resp.response
                            }, {
                                type: 'success'
                            });     
                        }
                       $("#sendEnter").attr("disabled", false);
                       $("#registroForm #numEmpleado").val("");
                    },
                    error: function () {

                    }
                });
            });
            
            $("#sendExit").on("click", function () {
                $("#sendExit").attr("disabled", true);    
                console.log("envio de entrada");
                $.ajax({
                    type: "POST",
                    url: "empleado/registroSalida",
                    dataType: "json",
                    data:{numeroEmpleado: $("#registroForm #numEmpleado").val()},
                    cache: false,
                    async: true,
                    success: function (resp) {
                        if (resp.message){
                            $.notify({
                                message: resp.message
                            }, {
                                type: 'danger'
                            });
                        }else{
                            $.notify({
                                message: resp.response
                            }, {
                                type: 'success'
                            });     
                        }
                       $("#sendExit").attr("disabled", false);
                       $("#registroForm #numEmpleado").val("");
                    },
                    error: function () {

                    }
                });
            });
            
            $("#sendLogin").on("click", function () {
                $("#sendLogin").attr("disabled", true);    
                console.log("envio de entrada");
                $.ajax({
                    type: "POST",
                    url: "empleado/login",
                    dataType: "json",
                    data:{
                        numeroEmpleado: $("#loginForm #numEmpleadoL").val(),
                        password: $("#loginForm #password").val()
                    },
                    cache: false,
                    async: true,
                    success: function (resp) {
                        if (resp.message){
                            $.notify({
                                message: resp.message
                            }, {
                                type: 'danger'
                            });
                        }else{
                           window.location = "listado"; 
                        }
                       $("#sendLogin").attr("disabled", false);
                       
                    },
                    error: function () {

                    }
                });
            });
        }
    },
    listado: {
        init: function(){
            $("#listadoTable").DataTable( {
                ajax: "registro/listado",
                columns:[
                    {"data" : "fecha"},
                    {"data" : "nombre"},
                    {"data" : "horaEntrada"},
                    {"data" : "horaSalida"},
                    {"data" : "puesto"},
                    {"data" : "sueldo"},
                    {"data" : "retardo"},
                ],
                language: {
                    url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                },
                dom: 'Bfrtip',
                buttons: [
                    {
                        extend: 'excelHtml5',
                        title: 'Listado'
                    },
                    {
                        extend: 'pdfHtml5',
                        title: 'Listado'
                    }
                ]
            } );
        }
    }
};