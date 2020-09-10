package web;

import datos.*;
import java.util.*;
import java.io.*;
import dominio.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import servicio.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    ConexionDaoImpl cnn = new ConexionDaoImpl();
    @Inject
    ControlService controlService;
    EvaluacionService evaluacionService;//Instancia de interface EJB local

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String lista = request.getParameter("lista");
        if (accion != null) {
            switch (accion) {
                //case "editar":
                //    this.editarEvaluacion(request, response);
                //    break;
                //case "eliminar":
                //    this.eliminarEvaluacion(request, response);
                //    break;
                case "graf1":
                    this.grafica1(request, response);
                    break;
                case "graf2":
                    this.grafica2(request, response);
                    break;
                case "graf3":
                    this.grafica3(request, response);
                    break;
                case "graf4":
                    this.grafica4(request, response);
                    break;
                case "evaluacion":
                    this.listarEvaluacion(request, response);
                    break;
                case "grafica":
                    this.graficaCliente(request, response);
                    break;
                case "reporte":
                    this.reporteCliente(request, response);
                    break;
                case "reporte1":
                    this.reporte1(request, response);
                    break;
                case "reporte2":
                    this.reporte2(request, response);
                    break;
                case "reporte3":
                    this.reporte3(request, response);
                    break;
                case "reporte4":
                    this.reporte4(request, response);
                    break;
                case "reporte5":
                    this.reporte5(request, response);
                    break;
                case "reporte6":
                    this.reporte6(request, response);
                    break;
                case "listar":
                    switch (lista) {
                        case "control":
                            this.listarControl(request, response);
                            break;
                        default:
                            this.listarControl(request, response);
                    }
                    break;
                case "logout":
                    this.logout(request, response);
                    break;
                default:
                    this.login(request, response);
            }
        } else {
            this.login(request, response);
        }
    }

    private void listarControl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Control> controles = cnn.listarControlPrin();//controlService.listar();
        List<Control> controlesImpl = controlService.contarImplementa();
        List<Control> controlesNoImpl = controlService.contarNoImplementa();
        System.out.println("controles = " + controles);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("controles", controles);
        //sesion.setAttribute("totalControles", controles.size());//cantidad de registros en la lista
        sesion.setAttribute("totalImplementa", controlesImpl.size());
        sesion.setAttribute("totalNoImplementa", controlesNoImpl.size());
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);//no redirecciona correctamente a la pagina de clientes
        response.sendRedirect("controles.jsp");
    }

    /*private void agregarEvaluacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List eval = cnn.listarEvaluacion();
        System.out.println(eval);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("evaluacion", eval);
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);//no redirecciona correctamente a la pagina de clientes
        response.sendRedirect("agregarEvaluacion.jsp");
    }*/
    private void listarEvaluacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List ev = cnn.listarEvaluacion();
        System.out.println(ev);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("evalua", ev);
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);//no redirecciona correctamente a la pagina de clientes
        response.sendRedirect("evaluacion.jsp");
    }

    /*private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        cliente = evaluacionService.encontrarCliente(cliente);
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        evaluacionService.eliminarCliente(cliente);
        this.listarControl(request, response);
    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String lista = request.getParameter("lista");
        if (accion != null) {
            switch (accion) {
                case "evaluacion":
                    this.listarEvaluacion(request, response);
                    break;
                case "insertar": {
                    try {
                        this.insertarEvaluacion(request, response);
                    } catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                    } catch (ParseException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                //case "modificar":
                //    this.modificarCliente(request, response);
                //    break;
                case "listar":
                    switch (lista) {
                        case "control":
                            this.listarControl(request, response);
                            break;
                        /*case "agregarEvaluacion":
                            this.agregarEvaluacion(request, response);
                            break;*/
                        default:
                            this.listarControl(request, response);
                    }
                    break;
                default:
                    this.login(request, response);
            }
        } else {
            this.login(request, response);
        }
    }

    private void insertarEvaluacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        int listaControl = Integer.parseInt(request.getParameter("listaControl"));
        System.out.println("listaControl = " + listaControl);
        int listaNivel = Integer.parseInt(request.getParameter("listaNivel"));
        System.out.println("listaNivel = " + listaNivel);
        //int listaImpacto = Integer.parseInt(request.getParameter("listaImpacto"));
        //System.out.println("listaImpacto = " + listaImpacto);
        //int listaSolucion = Integer.parseInt(request.getParameter("listaSolucion"));
        //System.out.println("listaSolucion = " + listaSolucion);
        String hallazgo = request.getParameter("hallazgo");
        System.out.println("hallazgo = " + hallazgo);
        String recomendacion = request.getParameter("recomendacion");
        System.out.println("recomendacion = " + recomendacion);
        String valorUsuario = request.getUserPrincipal().toString();
        System.out.println("valorUsuario = " + valorUsuario);

        String lastCrawlDate = "2020-09-05";
        Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastCrawlDate);
        java.sql.Date valorFecha = new java.sql.Date(utilDate.getTime());

        List ev = cnn.listarControlEv(listaControl);
        int valorPeriodo = ev.size();
        int idperiodo = ++valorPeriodo;
        int valorProyecto = 0;
        int listaImpacto = 0;
        int listaSolucion = 0;
        int implementa = 0;
        int bandera = 0;
        List cbandera = cnn.listarControl();
        int cband = cbandera.size();
        if (listaNivel == 6) {
            valorProyecto = 1;
            listaImpacto = 3;
            listaSolucion = 1;
            implementa = 0;
        } else if (listaNivel == 1) {
            valorProyecto = 2;
            listaImpacto = 3;
            listaSolucion = 1;
            implementa = 1;
        } else if (listaNivel == 2) {
            valorProyecto = 3;
            listaImpacto = 3;
            listaSolucion = 1;
            implementa = 1;
        } else if (listaNivel == 3) {
            valorProyecto = 4;
            listaImpacto = 2;
            listaSolucion = 2;
            implementa = 1;
        } else if (listaNivel == 4) {
            valorProyecto = 5;
            listaImpacto = 1;
            listaSolucion = 3;
            implementa = 1;
        } else if (listaNivel == 5) {
            valorProyecto = 5;
            listaImpacto = 1;
            listaSolucion = 3;
            implementa = 1;
        }

        Evalua evalua = new Evalua(hallazgo, recomendacion, valorUsuario, valorFecha, listaControl, valorProyecto, idperiodo, listaImpacto, listaSolucion, listaNivel);
        //insert
        int resultado = cnn.insert(evalua);
        //update
        Evalua eva = new Evalua(listaControl, listaNivel, implementa, bandera);
        int resultado3 = cnn.update(eva);
        if (cband == 1){
            int resultado2 = cnn.updateband(1);
        }
        this.listarControl(request, response);
    }

    private void modificarControl(Control control) {
        controlService.actualizar(control);
    }

    private void graficaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspGrafica = "generagrafica.jsp";
        request.getRequestDispatcher(jspGrafica).forward(request, response);
    }

    private void reporteCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspReporte = "generareporte.jsp";
        request.getRequestDispatcher(jspReporte).forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspLogin = "login.jsp";
        response.sendRedirect(jspLogin);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        this.login(request, response);
    }

    private void reporte1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=ley_reglamento.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte1();
            out.println("IdReglamento\tReglamento\tIdCapitulo\tCapitulo\tIdArticulo\tArticulo");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdreglamento() + "\t" + rep1.getReglamento() + "\t" + rep1.getIdcapitulo() + "\t" + rep1.getCapitulo() + "\t" + rep1.getIdarticulo() + "\t" + rep1.getArticulo());
            }
        } finally {
            out.close();
        }
    }

    private void reporte2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=proyecto_implementacion.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte2();
            out.println("IdProyecto\tProyecto\tPresupuesto\tTiempo");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdproyecto() + "\t" + rep1.getProyecto() + "\t" + rep1.getPresupuesto() + "\t" + rep1.getTiempo());
            }
        } finally {
            out.close();
        }
    }

    private void reporte3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=area_control.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte3();
            out.println("IdArea\tArea\tIdControl\tControl");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdarea() + "\t" + rep1.getArea() + "\t" + rep1.getIdcontrol() + "\t" + rep1.getControl());
            }
        } finally {
            out.close();
        }
    }

    private void reporte4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=ley_control.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte4();
            out.println("IdCapitulo\tCapitulo\tIdArticulo\tArticulo\tIdControl\tControl");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdreglamento() + "\t" + rep1.getReglamento() + "\t" + rep1.getIdcapitulo() + "\t" + rep1.getCapitulo() + "\t" + rep1.getIdarticulo() + "\t" + rep1.getArticulo());
            }
        } finally {
            out.close();
        }
    }

    private void reporte5(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=control_cmmi.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte5();
            out.println("IdControl\tControl\tIdNivel\tNivel\tImplementado");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdcontrol() + "\t" + rep1.getControl() + "\t" + rep1.getNivel() + "\t" + rep1.getLevel() + "\t" + rep1.getImpl());
            }
        } finally {
            out.close();
        }
    }

    private void reporte6(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=evaluacion_trim.xls");
        PrintWriter out = response.getWriter();
        try {
            List<Pivot> r = cnn.listarReporte6();
            out.println("IdControl\tControl\tPeriodo\tIdNivel\tNivel\tHallazgo\tRecomendacion\tUsuarioEvaluacion\tFechaEvaluacion");
            for (Pivot rep1 : r) {
                out.println(rep1.getIdcontrol() + "\t" + rep1.getControl() + "\t" + rep1.getPeriodo() + "\t" + rep1.getNivel() + "\t" + rep1.getLevel() + "\t" + rep1.getHallazgo() + "\t" + rep1.getRecomendacion() + "\t" + rep1.getUsuario() + "\t" + rep1.getFecha());
            }
        } finally {
            out.close();
        }
    }

    private void grafica1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //List graf1 = cnn.listarGrafica1();
        //HttpSession sesion = request.getSession();
        //sesion.setAttribute("graf1", graf1);
        response.sendRedirect("grafica1.jsp");
    }

    private void grafica2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("grafica2.jsp");
    }

    private void grafica3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("grafica3.jsp");
    }

    private void grafica4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("grafica4.jsp");
    }
}
