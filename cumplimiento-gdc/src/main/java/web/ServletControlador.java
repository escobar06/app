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
                case "evaluacion":
                    this.listarEvaluacion(request, response);
                    break;
                case "grafica":
                    this.graficaCliente(request, response);
                    break;
                case "reporte":
                    this.reporteCliente(request, response);
                    break;
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
        List<Control> controles = controlService.listar();
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
        int listaImpacto = Integer.parseInt(request.getParameter("listaImpacto"));
        System.out.println("listaImpacto = " + listaImpacto);
        int listaSolucion = Integer.parseInt(request.getParameter("listaSolucion"));
        System.out.println("listaSolucion = " + listaSolucion);
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
        int nivel = 0;
        if (valorPeriodo <= 6) {
            valorProyecto = 1;
            nivel = 1;
        } else if (valorPeriodo > 6) {
            valorProyecto = 2;
            nivel = 2;
        } else if (valorPeriodo > 13) {
            valorProyecto = 3;
            nivel = 3;
        } else if (valorPeriodo > 21) {
            valorProyecto = 4;
            nivel = 4;
        } else if (valorPeriodo > 25) {
            valorProyecto = 5;
            nivel = 5;
        }

        Evalua evalua = new Evalua(hallazgo, recomendacion, valorUsuario, valorFecha, listaControl, valorProyecto, idperiodo, listaImpacto, listaSolucion);
        //insert
        int resultado = cnn.insert(evalua);
        //update
        Evalua eva = new Evalua(listaControl, nivel);
        int resultado2 = cnn.update(eva);
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
        /*response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "filename=sumarnum.xls");
        PrintWriter out = response.getWriter();
        try {
            out.println("Numero1:\t1");
            out.println("Numero2:\t2");
        } finally {
            out.close();
        }*/
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspLogin = "login.jsp";
        response.sendRedirect(jspLogin);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        this.login(request, response);
    }
}
