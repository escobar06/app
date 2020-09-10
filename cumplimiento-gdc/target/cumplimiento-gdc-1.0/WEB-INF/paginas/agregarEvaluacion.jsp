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
<script>
    /*function ocultar() {
     var selectBox = document.getElementById("contrl");
     var selectedValue = selectBox.options[selectBox.selectedIndex].value;
     alert(selectedValue);
     //if (nivel === "") {
     //    n = nivel;
     //} else {
     //    n = parseInt(nivel,10);
     //}
     if (n === 6) {
     document.getElementById('a').style.display = 'none';
     document.getElementById('b').style.display = 'block';
     document.getElementById('c').style.display = 'none';
     document.getElementById('d').style.display = 'none';
     document.getElementById('e').style.display = 'none';
     document.getElementById('f').style.display = 'none';
     } else if (n === 1) {
     document.getElementById('a').style.display = 'block';
     document.getElementById('b').style.display = 'none';
     document.getElementById('c').style.display = 'block';
     document.getElementById('d').style.display = 'none';
     document.getElementById('e').style.display = 'none';
     document.getElementById('f').style.display = 'none';
     } else if (n === 2) {
     document.getElementById('a').style.display = 'none';
     document.getElementById('b').style.display = 'block';
     document.getElementById('c').style.display = 'none';
     document.getElementById('d').style.display = 'block';
     document.getElementById('e').style.display = 'none';
     document.getElementById('f').style.display = 'none';
     } else if (n === 3) {
     document.getElementById('a').style.display = 'none';
     document.getElementById('b').style.display = 'none';
     document.getElementById('c').style.display = 'block';
     document.getElementById('d').style.display = 'none';
     document.getElementById('e').style.display = 'block';
     document.getElementById('f').style.display = 'none';
     } else if (n === 4) {
     document.getElementById('a').style.display = 'none';
     document.getElementById('b').style.display = 'none';
     document.getElementById('c').style.display = 'none';
     document.getElementById('d').style.display = 'block';
     document.getElementById('e').style.display = 'none';
     document.getElementById('f').style.display = 'block';
     } else {
     document.getElementById('a').style.display = 'none';
     document.getElementById('b').style.display = 'none';
     document.getElementById('c').style.display = 'none';
     document.getElementById('d').style.display = 'none';
     document.getElementById('e').style.display = 'block';
     document.getElementById('f').style.display = 'none';
     }
     }*/
</script>
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

            <!--was-validated = valida que se ingresan datos en los items required -->
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <!-- for = especifica el id del elemento for al que hace referencia -->
                        <label for="control">Control</label>
                        <select id="contrl" name="listaControl" class="form-control" required>
                            <c:forEach var="evaluacion" items="${evaluacion}" varStatus="status">
                                <option value="${evaluacion.idcontrol}">${"Artículo "}${evaluacion.idarticulo}${" - "}${evaluacion.control}</option>
                            </c:forEach>
                        </select>
                        <!-- form-control = estilo de controles de forma textual -->
                    </div>
                    <div class="form-group">
                        <label for="nivel">Nivel</label>
                        <select name="listaNivel" class="form-control" required>
                            <option value=6 id="a">0 - Incompleto</option>
                            <option value=1 id="b">1 - Inicial</option>
                            <option value=2 id="c">2 - Administrado</option>
                            <option value=3 id="d">3 - Definido</option>
                            <option value=4 id="e">4 - Administrado cuantitativamente</option>
                            <option value=5 id="f">5 - Optimizado</option>
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
                    <button id="btmodal" class="btn btn-primary" type="submit">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
