package datos;

import dominio.*;
import java.sql.*;
import java.util.*;

public class ConexionDaoImpl {

    private final String SQL_CONTROL = "select c.idarticulo,c.descripcion control,n.descripcion nivel,c.implementa from control c,nivel n where c.idnivel = n.idnivel and c.estado = ? order by c.idcontrol asc";
    private final String SQL_SELECT_EVALUACION = "SELECT e.idcontrol as idcontrol,pr.descripcion as proyecto,c.descripcion as control,concat(pe.descripcion,'-',a.descripcion) as periodo,i.descripcion as impacto,s.descripcion as solucion FROM evaluacion e,control c,proyecto pr,periodo pe,impacto i,solucion s,anio a WHERE e.idcontrol = c.idcontrol AND e.idproyecto = pr.idproyecto AND e.idperiodo = pe.idperiodo AND e.idimpacto = i.idimpacto AND e.idsolucion = s.idsolucion AND pe.idanio = a.idanio ORDER BY e.idevaluacion DESC";
    private final String SQL_SELECT_CONTROL = "SELECT idarticulo,idcontrol,descripcion control FROM control WHERE bandera = ? ORDER BY idarticulo ASC,idcontrol ASC";
    private final String SQL_SELECT_NIVEL = "SELECT idnivel FROM control WHERE idcontrol = ?";
    //private final String SQL_SELECT_ARTICULO = "SELECT idarticulo,descripcion articulo FROM articulo WHERE idcapitulo = ? ORDER BY idarticulo";
    //private final String SQL_SELECT_CAPITULO = "SELECT idcapitulo,descripcion capitulo FROM capitulo ORDER BY idcapitulo";
    private final String SQL_SELECT_CONTROL_EV = "SELECT idcontrol FROM evaluacion WHERE idcontrol = ?";
    
    private final String SQL_INSERT = "INSERT INTO evaluacion(idcontrol,idproyecto,idperiodo,idimpacto,idsolucion,hallazgo,recomendacion,usuario_evaluacion,fecha_evaluacion,nivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE control SET idnivel = ?,implementa = ?,bandera = ? WHERE idcontrol = ?";
    private final String SQL_UPDATE_BANDERA = "UPDATE control SET bandera = ?";
    //private final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    
    private final String GRAFICA1 = "SELECT a.implementa,b.noimplementa FROM (SELECT (count(implementa)*100)/(SELECT count(*) FROM control) implementa FROM control WHERE implementa = 1) a, (SELECT (count(implementa)*100)/(SELECT count(*) FROM control) noimplementa FROM control WHERE implementa = 0) b";
    private final String GRAFICA1_1 = "SELECT a.implementa FROM (SELECT (count(implementa)*100)/(SELECT count(*) FROM control) implementa FROM control WHERE implementa = 1) a";
    private final String GRAFICA1_2 = "SELECT b.noimplementa FROM (SELECT (count(implementa)*100)/(SELECT count(*) FROM control) noimplementa FROM control WHERE implementa = 0) b";
    private final String GRAFICA2 = "select (case c.idnivel when 6 then 0 else c.idnivel end) idnivel,count(*) contador from control c group by c.idnivel order by c.idnivel asc";
    private final String GRAFICA2_0 = "select count(*) contador from control c where idnivel = 6";
    private final String GRAFICA2_1 = "select count(*) contador from control c where idnivel = 1";
    private final String GRAFICA2_2 = "select count(*) contador from control c where idnivel = 2";
    private final String GRAFICA2_3 = "select count(*) contador from control c where idnivel = 3";
    private final String GRAFICA2_4 = "select count(*) contador from control c where idnivel = 4";
    private final String GRAFICA2_5 = "select count(*) contador from control c where idnivel = 5";
    private final String GRAFICA3_1 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 1) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 1 and c.idnivel > 2) b";
    private final String GRAFICA3_2 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 2) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 2 and c.idnivel > 2) b";
    private final String GRAFICA3_3 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 3) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 3 and c.idnivel > 2) b";
    private final String GRAFICA3_4 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 4) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 4 and c.idnivel > 2) b";
    private final String GRAFICA3_5 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 5) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 5 and c.idnivel > 2) b";
    private final String GRAFICA3_6 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 6) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 6 and c.idnivel > 2) b";
    private final String GRAFICA3_7 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 7) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 7 and c.idnivel > 2) b";
    private final String GRAFICA3_8 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 8) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 8 and c.idnivel > 2) b";
    private final String GRAFICA3_9 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 9) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 9 and c.idnivel > 2) b";
    private final String GRAFICA3_10 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 10) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 10 and c.idnivel > 2) b";
    private final String GRAFICA3_11 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 11) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 11 and c.idnivel > 2) b";
    private final String GRAFICA3_12 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 12) a, (select count(*) contador from control c,articulo a, capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo and ca.idcapitulo = 12 and c.idnivel > 2) b";
    private final String GRAFICA4_1 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 1) a, (select count(*) contador from evaluacion e where e.idperiodo = 1 and e.nivel > 2) b";
    private final String GRAFICA4_2 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 2) a, (select count(*) contador from evaluacion e where e.idperiodo = 2 and e.nivel > 2) b";
    private final String GRAFICA4_3 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 3) a, (select count(*) contador from evaluacion e where e.idperiodo = 3 and e.nivel > 2) b";
    private final String GRAFICA4_4 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 4) a, (select count(*) contador from evaluacion e where e.idperiodo = 4 and e.nivel > 2) b";
    private final String GRAFICA4_5 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 5) a, (select count(*) contador from evaluacion e where e.idperiodo = 5 and e.nivel > 2) b";
    private final String GRAFICA4_6 = "select IFNULL(((b.contador/a.contador)*100),0) porc from (select count(*) contador from evaluacion e where e.idperiodo = 6) a, (select count(*) contador from evaluacion e where e.idperiodo = 6 and e.nivel > 2) b";
            
    private final String REPORTE1 = "select r.idreglamento,r.descripcion reglamento,c.idcapitulo,c.descripcion capitulo,a.idarticulo,a.descripcion articulo from articulo a,capitulo c,reglamento r where a.idcapitulo = c.idcapitulo and c.idreglamento = r.idreglamento order by c.idcapitulo asc,a.idarticulo asc";
    private final String REPORTE2 = "select p.idproyecto,p.descripcion proyecto,p.presupuesto,p.tiempo from proyecto p order by p.idproyecto asc";
    private final String REPORTE3 = "select a.idarea,a.descripcion area,c.idcontrol,c.descripcion control from control c,area a where c.idarea = a.idarea order by a.idarea asc,c.idcontrol asc";
    private final String REPORTE4 = "select ca.idcapitulo,ca.descripcion capitulo,a.idarticulo,a.descripcion articulo,c.idcontrol,c.descripcion control from control c,articulo a,capitulo ca where c.idarticulo = a.idarticulo and a.idcapitulo = ca.idcapitulo";
    private final String REPORTE5 = "select c.idcontrol,c.descripcion control,(case c.idnivel when 6 then 0 else c.idnivel end) idnivel,n.descripcion nivel,(case c.implementa when 1 then'Si' else 'No' end) implementado from control c,nivel n where c.idnivel = n.idnivel order by c.idcontrol asc";
    private final String REPORTE6 = "select e.idcontrol,c.descripcion control,(case n.idnivel when 6 then 0 else n.idnivel end) idnivel,n.descripcion nivel,concat(pe.descripcion,'-',a.descripcion) periodo,e.hallazgo,e.recomendacion,e.usuario_evaluacion,date_format(e.fecha_evaluacion,'%Y-%m-%d') fecha_evaluacion from evaluacion e,control c,proyecto p,periodo pe,impacto i,solucion s,anio a,nivel n where e.idcontrol = c.idcontrol and e.idproyecto = p.idproyecto and e.idperiodo = pe.idperiodo and e.idimpacto = i.idimpacto and e.idsolucion = s.idsolucion and pe.idanio = a.idanio and e.nivel = n.idnivel order by e.idcontrol asc,e.idperiodo asc";

    public List listarEvaluacion() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List evaluacion = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_EVALUACION);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                String proyecto = rs.getString("proyecto");
                String periodo = rs.getString("periodo");
                String impacto = rs.getString("impacto");
                String solucion = rs.getString("solucion");
                evalua = new Pivot(idcontrol, control, proyecto, periodo, impacto, solucion);
                evaluacion.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return evaluacion;
    }

    public List listarControl() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_CONTROL);
            ps.setInt(1,1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idarticulo = rs.getInt("idarticulo");
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                evalua = new Pivot(idcontrol, control, idarticulo);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List listarControlPrin() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_CONTROL);
            ps.setString(1,"A");
            rs = ps.executeQuery();
            while (rs.next()) {
                int idarticulo = rs.getInt("idarticulo");
                String control = rs.getString("control");
                String nivel = rs.getString("nivel");
                int implementa = rs.getInt("implementa");
                evalua = new Pivot(idarticulo, control, implementa, nivel);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }
    
    public int listarNivel(int idcontrol) {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //List controles = new ArrayList();
        //Pivot evalua = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_NIVEL);
            ps.setInt(1,idcontrol);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("idnivel");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }

    /*public List listarArticulo(int idcapitulo) {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_ARTICULO);
            ps.setInt(1,idcapitulo);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idarticulo = rs.getInt("idarticulo");
                String articulo = rs.getString("articulo");
                evalua = new Pivot(idarticulo, articulo);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List listarCapitulo() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_CAPITULO);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcapitulo = rs.getInt("idcapitulo");
                String capitulo = rs.getString("capitulo");
                evalua = new Pivot(idcapitulo, capitulo);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }*/

    public List listarControlEv(int control) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_SELECT_CONTROL_EV);
            ps.setInt(1, control);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcontrol = rs.getInt("idcontrol");
                evalua = new Pivot(idcontrol);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }
    
    public int insert(Evalua ev) {
        Connection cnn = null;
        PreparedStatement ps = null;
        int resultado = 0;

            try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_INSERT);
            ps.setInt(1, ev.getIdcontrol());
            ps.setInt(2, ev.getIdproyecto());
            ps.setInt(3, ev.getIdperiodo());
            ps.setInt(4, ev.getIdimpacto());
            ps.setInt(5, ev.getIdsolucion());
            ps.setString(6, ev.getHallazgo());
            ps.setString(7, ev.getRecomendacion());
            ps.setString(8, ev.getUsuario_evaluacion());
            ps.setString(9, ev.getFecha_evaluacion().toString());
            ps.setInt(10, ev.getIdnivel());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.closeInsert(cnn, ps);
        }
        return resultado;
    }

    public int update(Evalua ev) {
        Connection cnn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        int resultado = 0;
        int resultado1 = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps1 = cnn.prepareStatement("SET SQL_SAFE_UPDATES = 1");
            resultado1 = ps1.executeUpdate();
            ps = cnn.prepareStatement(SQL_UPDATE);
            ps.setInt(1, ev.getIdnivel());
            ps.setInt(2, ev.getImplementa());
            ps.setInt(3, ev.getBandera());
            ps.setInt(4, ev.getIdcontrol());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.closeUpdate(cnn, ps, ps1);
        }
        return resultado;
    }

    public int updateband(int bandera) {
        Connection cnn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        int resultado = 0;
        int resultado1 = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps1 = cnn.prepareStatement("SET SQL_SAFE_UPDATES = 0");
            resultado1 = ps1.executeUpdate();
            ps = cnn.prepareStatement(SQL_UPDATE_BANDERA);
            ps.setInt(1, bandera);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.closeUpdate(cnn, ps, ps1);
        }
        return resultado;
    }

    public List<Pivot> listarReporte1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idreglam = rs.getInt("idreglamento");
                String reglam = rs.getString("reglamento");
                int idcap = rs.getInt("idcapitulo");
                String cap = rs.getString("capitulo");
                int idart = rs.getInt("idarticulo");
                String art = rs.getString("articulo");
                evalua = new Pivot(idreglam, reglam, idcap, cap, idart, art);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List<Pivot> listarReporte2() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE2);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idproyecto = rs.getInt("idproyecto");
                String proyecto = rs.getString("proyecto");
                int presupuesto = rs.getInt("presupuesto");
                int tiempo = rs.getInt("tiempo");
                evalua = new Pivot(proyecto,idproyecto,presupuesto,tiempo);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List<Pivot> listarReporte3() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idarea = rs.getInt("idarea");
                String area = rs.getString("area");
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                evalua = new Pivot(idcontrol,control,idarea,area);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List<Pivot> listarReporte4() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcapitulo = rs.getInt("idcapitulo");
                String capitulo = rs.getString("capitulo");
                int idarticulo = rs.getInt("idarticulo");
                String articulo = rs.getString("articulo");
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                evalua = new Pivot(idcapitulo,capitulo,idarticulo,articulo,idcontrol,control);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List<Pivot> listarReporte5() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE5);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                int idnivel = rs.getInt("idnivel");
                String nivel = rs.getString("nivel");
                String implementado = rs.getString("implementado");
                evalua = new Pivot(idcontrol,control,idnivel,nivel,implementado);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List<Pivot> listarReporte6() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pivot> controles = new ArrayList<Pivot>();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(REPORTE6);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                int idnivel = rs.getInt("idnivel");
                String nivel = rs.getString("nivel");
                String periodo = rs.getString("periodo");
                String hallazgo = rs.getString("hallazgo");
                String recomendacion = rs.getString("recomendacion");
                String usuario_evaluacion = rs.getString("usuario_evaluacion");
                String fecha_evaluacion = rs.getString("fecha_evaluacion");
                evalua = new Pivot(idcontrol,control,periodo,idnivel,nivel,hallazgo,recomendacion,usuario_evaluacion,fecha_evaluacion);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

    public List listarGrafica1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List controles = new ArrayList();
        Pivot evalua = null;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA1);
            rs = ps.executeQuery();
            while (rs.next()) {
                int impl = rs.getInt("implementa");
                int noimpl = rs.getInt("noimplementa");
                evalua = new Pivot(impl, noimpl);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }
    public int listarGrafica1_1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int impl = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA1_1);
            rs = ps.executeQuery();
            while (rs.next()) {
                impl = rs.getInt("implementa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return impl;
    }
    public int listarGrafica1_2() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int noimpl = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA1_2);
            rs = ps.executeQuery();
            while (rs.next()) {
                noimpl = rs.getInt("noimplementa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return noimpl;
    }

    public int listarGrafica2_0() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_0);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }
    public int listarGrafica2_1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_1);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }
    public int listarGrafica2_2() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_2);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }
    public int listarGrafica2_3() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_3);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }
    public int listarGrafica2_4() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_4);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }
    public int listarGrafica2_5() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nivel = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA2_5);
            rs = ps.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return nivel;
    }

    public int listarGrafica3_1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_1);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_2() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_2);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_3() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_3);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_4() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_4);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_5() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_5);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_6() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_6);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_7() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_7);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_8() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_8);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_9() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_9);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_10() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_10);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_11() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_11);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica3_12() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA3_12);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }

    public int listarGrafica4_1() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_1);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica4_2() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_2);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica4_3() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_3);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica4_4() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_4);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica4_5() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_5);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
    public int listarGrafica4_6() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int porc = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(GRAFICA4_6);
            rs = ps.executeQuery();
            while (rs.next()) {
                porc = rs.getInt("porc");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return porc;
    }
}
