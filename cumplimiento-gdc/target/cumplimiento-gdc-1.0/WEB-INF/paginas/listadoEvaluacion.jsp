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
            <div class="col-md-12">
                <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <!-- card-header = cabecera de una tarjeta
                         tarjeta es un contenedor de contenido flexible y extensible-->
                    <!-- table = clase, table-striped = agrega rayas de cebra a cualquier fila en la tabla -->
                    <table class="table table-striped">
                        <!-- thead-dark = agrega color gris claro u oscuro a las cabeceras de la tabla -->
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Proyecto</th>
                                <th>Control</th>
                                <th>Período</th>
                                <th>Impacto</th>
                                <th>Solución</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="evaluacion" items="${evalua}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${evaluacion.proyecto}</td>
                                    <td>${evaluacion.control}</td>
                                    <td>${evaluacion.periodo}</td>
                                    <td>${evaluacion.impacto}</td>
                                    <td>${evaluacion.solucion}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary"/>
                                        <i class="fas fa-file-pdf"></i> Acta
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Agregar cliente MODAL, 
     al inicio / ya que debemos regresar a la raiz, porque estamos en un folder privado -->
<!-- jsp:include page="/WEB-INF/paginas/cliente/agregarCliente.jsp"/ -->
