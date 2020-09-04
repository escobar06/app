<meta http-equiv="Cache-Control" content="no-store" />
<!-- Seccion - botones de navegacion -->
<!-- actions = elemento que realizara una accion, py-4 = padding top/bottom de tamaño 1.5, mb-4 = margen inferior del padding de 1.5, bg-light = color transparente -->
<section id="actions" class="py-2 mb-2 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <!-- btn-ligth = boton color transparente -->
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=listar&lista=control" class="btn btn-ligth btn-block">
                    <!-- fa-arrow-left = icono <- -->
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <div class="col-md-3">
                <!-- btn-success = boton color verde -->
                <button type="submit" class="btn btn-success btn-block">
                    <!-- fa-check = icono check -->
                    <i class="fas fa-check"></i> Guardar Evaluacion
                </button>
            </div>
        </div>
    </div>
</section>
