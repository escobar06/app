<meta http-equiv="Cache-Control" content="no-store" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- definicion de la localidad para los formatos --%>
<fmt:setLocale value="es_GT"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <!-- Tarjetas para los totales -->
            <div class="col-md-3">
                <!-- bg-success = background verde -->
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte1" style="color:white;">
                            <h3>Reglamentos y Leyes</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-legal"></i>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte2" style="color:white;">
                            <h3>Proyectos de implementación</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-tasks"></i>
                        </h4>
                    </div>
                </div>
            </div>            
            <!-- Tarjetas para los totales -->
            <div class="col-md-3">
                <!-- bg-success = background verde -->
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte3" style="color:white;">
                            <h3>Controles por área</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fas fa-network-wired"></i>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte4" style="color:white;">
                            <h3>Leyes y controles</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-legal"></i>
                        </h4>
                    </div>
                </div>
            </div> 
            <!-- Tarjetas para los totales -->
            <div class="col-md-3">
                <!-- bg-success = background verde -->
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte5" style="color:white;">
                            <h3>CMMI de controles</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-level-up"></i>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=reporte6" style="color:white;">
                            <h3>Evaluaciones trimestrales</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-check"></i>
                        </h4>
                    </div>
                </div>
            </div>            

        </div>
    </div>
</section>
