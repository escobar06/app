<meta http-equiv="Cache-Control" content="no-store" />
<!-- Seccion - botones de navegacion -->
<!-- actions = elemento que realizara una accion, py-4 = padding top/bottom de tamaño 1.5, mb-4 = margen inferior del padding de 1.5, bg-light = color transparente -->
<section id="actions" class="py-3 mb-3 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <!-- btn = clase boton, btn-primary = color celeste, btn-block = se expande todo el ancho disponible -->
                <!-- data-toggle="modal" = cuadro de dialogo o ventana emergente que se muestra en la parte superior de la pagina 
                     data-target = modal que se mostrara href="# -->
                <a href="#" class="btn btn-primary"
                   data-toggle="modal" data-target="#agregarEvaluacionModal">
                    <!-- i = para agregar iconos, fa-plus = icono de + -->
                    <i class="fas fa-plus"></i> Evaluación
                </a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=evaluacion" class="btn btn-primary">
                    <i class="fa fa-line-chart"></i> Evaluación
                </a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=grafica" class="btn btn-primary">
                    <i class="fa fa-line-chart"></i> Gráficas
                </a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte" class="btn btn-primary">
                    <i class="fas fa-file"></i> Reportes
                </a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=logout" class="btn btn-primary">
                    <i class="fa fa-sign-out"></i> Logout
                </a>
            </div>
        </div>
    </div>
</section>        
