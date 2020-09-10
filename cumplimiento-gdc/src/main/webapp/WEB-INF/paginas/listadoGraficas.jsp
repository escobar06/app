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
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=graf1" style="color:white;">
                            <h3>Implementación de controles</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-tasks"></i>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=graf2" style="color:white;">
                            <h3>Controles por CMMI</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-level-up"></i>
                        </h4>
                    </div>
                </div>
            </div>            

            <!-- Tarjetas para los totales -->
            <div class="col-md-3">
                <!-- bg-success = background verde -->
                <div class="card text-center bg-dark text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=graf3" style="color:white;">
                            <h3>Cumplimiento de normativa</h3></a>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                        <!-- fa-users = icono de usuario -->
                            <i class="fa fa-legal"></i>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=graf4" style="color:white;">
                            <h3>Evolución de cumplimiento</h3></a>
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
