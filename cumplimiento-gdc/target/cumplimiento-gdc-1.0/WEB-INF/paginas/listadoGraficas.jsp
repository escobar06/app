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
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Controles implementados</h3>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                            <!-- fa-users = icono de usuario -->
                            <i class="fas fa-clipboard-list"></i> ${totalImplementa}
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Controles no implementados</h3>
                        <!-- display-4 = tamaño de titulo -->
                        <h4 class="display-4">
                            <!-- fa-users = icono de usuario -->
                            <i class="fas fa-clipboard-list"></i> ${totalNoImplementa}
                        </h4>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
