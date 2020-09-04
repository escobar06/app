<%@page import="java.util.List"%>
<%@page import="datos.ConexionDaoImpl"%>
<meta http-equiv="Cache-Control" content="no-store" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- modal fade = modal basico, agregarClienteModal = data-target definido en botonesNavegacion-->
<% 
    ConexionDaoImpl cnn = new ConexionDaoImpl();
        List eval = cnn.listarControl();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("evaluacion", eval);
%>
<div class="modal fade" id="agregarEvaluacionModal">
    <!-- modal-lg = modal large -->
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Cabecera del modal -->
            <!-- bg-info = color celeste -->
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Registrar Evaluación</h5>
                <!-- close = clase que aplica estilo de cierre, data-dismiss = se cerrara el modal -->
                <button class="close" data-dismiss="modal">
                    <!-- &times; = icono x -->
                    <!-- span = agrupa elementos en linea -->
                    <span>&times;</span>
                </button>
            </div>

            <!-- was-validated = valida que se ingresan datos en los items required -->
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <!-- for = especifica el id del elemento for al que hace referencia -->
                        <label for="control">Control</label>
                        <select name="listaControl" class="form-control" required>
                            <c:forEach var="evaluacion" items="${evaluacion}" varStatus="status">
                                <option value="${evaluacion.idcontrol}"> ${evaluacion.control}</option>
                            </c:forEach>
                        </select>
                        <!-- form-control = estilo de controles de forma textual -->
                    </div>
                    <div class="form-group">
                        <label for="impacto">Impacto</label>
                        <select name="listaImpacto" class="form-control" required>
                            <option value=1>Bajo</option>
                            <option value=2>Medio</option>
                            <option value=3>Alto</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="solucion">Solución</label>
                        <select name="listaSolucion" class="form-control" required>
                            <option value=1>Bajo</option>
                            <option value=2>Medio</option>
                            <option value=3>Alto</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="hallazgo">Hallazgo</label>
                        <input type="text" class="form-control" name="hallazgo"/>
                    </div>
                    <div class="form-group">
                        <label for="recomendacion">Recomendación</label>
                        <input type="text" class="form-control" name="recomendacion"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
