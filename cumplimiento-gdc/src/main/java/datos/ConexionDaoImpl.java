package datos;

import dominio.*;
import java.sql.*;
import java.util.*;

public class ConexionDaoImpl {

    private final String SQL_SELECT_EVALUACION = "SELECT e.idcontrol as idcontrol,pr.descripcion as proyecto,c.descripcion as control,concat(pe.descripcion,'-',a.descripcion) as periodo,i.descripcion as impacto,s.descripcion as solucion FROM evaluacion e,control c,proyecto pr,periodo pe,impacto i,solucion s,anio a WHERE e.idcontrol = c.idcontrol AND e.idproyecto = pr.idproyecto AND e.idperiodo = pe.idperiodo AND e.idimpacto = i.idimpacto AND e.idsolucion = s.idsolucion AND pe.idanio = a.idanio ORDER BY e.idevaluacion DESC";
    private final String SQL_SELECT_CONTROL = "SELECT idcontrol,descripcion control FROM control ORDER BY idcontrol";
    private final String SQL_SELECT_CONTROL_EV = "SELECT idcontrol FROM evaluacion WHERE idcontrol = ?";
    private final String SQL_INSERT = "INSERT INTO evaluacion(idcontrol,idproyecto,idperiodo,idimpacto,idsolucion,hallazgo,recomendacion,usuario_evaluacion,fecha_evaluacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE control SET idnivel = ? WHERE idcontrol = ?";
    //private final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

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
            rs = ps.executeQuery();
            while (rs.next()) {
                int idcontrol = rs.getInt("idcontrol");
                String control = rs.getString("control");
                evalua = new Pivot(idcontrol, control);
                controles.add(evalua);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.close(cnn, ps, rs);
        }
        return controles;
    }

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
        int resultado = 0;

        try {
            cnn = ConexionDao.getConnection();
            ps = cnn.prepareStatement(SQL_UPDATE);
            ps.setInt(1, ev.getIdnivel());
            ps.setInt(2, ev.getIdcontrol());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionDao.closeInsert(cnn, ps);
        }
        return resultado;
    }
}
