<meta http-equiv="Cache-Control" content="no-store" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
    .my-custom-scrollbar {
        position: relative;
        height: 435px;
        overflow: auto;
    }
    .table-wrapper-scroll-y {
        display: block;
    }
</style>
<%-- definicion de la localidad para los formatos --%>
<fmt:setLocale value="es_GT"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <!-- card-header = cabecera de una tarjeta
                         tarjeta es un contenedor de contenido flexible y extensible-->
                    <!-- table = clase, table-striped = agrega rayas de cebra a cualquier fila en la tabla -->
                    <table class="table table-striped">
                        <!-- thead-dark = agrega color gris claro u oscuro a las cabeceras de la tabla -->
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Control</th>
                                <th>CMMI</th>
                                <th>Implementado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de lista de clientes, atributo clientes del alcance request creado en servlet -->
                            <c:forEach var="control" items="${controles}" varStatus="status">
                                <tr>
                                    <!-- status.count = valor autoincrementable -->
                                    <td>${status.count}</td>
                                    <td>${control.descripcion}</td>
                                    <td>${control.nivel.getDescripcion()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${control.implementa == 1}">
                                                <i class="fas fa-check" style="font-size:30px;color:green"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-close" style="font-size:30px;color:red"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <!-- formatNumber = formato de numero, currency = moneda -->
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

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

<!-- Agregar cliente MODAL, 
     al inicio / ya que debemos regresar a la raiz, porque estamos en un folder privado -->
<jsp:include page="/WEB-INF/paginas/agregarEvaluacion.jsp"/>
