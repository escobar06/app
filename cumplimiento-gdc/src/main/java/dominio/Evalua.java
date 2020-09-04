package dominio;

import java.io.Serializable;
import java.util.*;

public class Evalua implements Serializable{
    private String hallazgo;
    private String recomendacion;
    private String usuario_evaluacion;
    private Date fecha_evaluacion;
    private int idcontrol;
    private int idnivel;
    private int idproyecto;
    private int idperiodo;
    private int idimpacto;
    private int idsolucion;

    public Evalua() {

    }

    public Evalua(int idcontrol, int idnivel) {
        this.idcontrol = idcontrol;
        this.idnivel = idnivel;
    }

    public Evalua(String hallazgo, String recomendacion, String usuario_evaluacion, Date fecha_evaluacion, int idcontrol, int idproyecto, int idperiodo, int idimpacto, int idsolucion) {
        this.hallazgo = hallazgo;
        this.recomendacion = recomendacion;
        this.usuario_evaluacion = usuario_evaluacion;
        this.fecha_evaluacion = fecha_evaluacion;
        this.idcontrol = idcontrol;
        this.idproyecto = idproyecto;
        this.idperiodo = idperiodo;
        this.idimpacto = idimpacto;
        this.idsolucion = idsolucion;
    }

    public int getIdnivel() {
        return idnivel;
    }

    public void setIdnivel(int idnivel) {
        this.idnivel = idnivel;
    }

    public String getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getUsuario_evaluacion() {
        return usuario_evaluacion;
    }

    public void setUsuario_evaluacion(String usuario_evaluacion) {
        this.usuario_evaluacion = usuario_evaluacion;
    }

    public Date getFecha_evaluacion() {
        return fecha_evaluacion;
    }

    public void setFecha_evaluacion(Date fecha_evaluacion) {
        this.fecha_evaluacion = fecha_evaluacion;
    }

    public int getIdcontrol() {
        return idcontrol;
    }

    public void setIdcontrol(int idcontrol) {
        this.idcontrol = idcontrol;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(int idperiodo) {
        this.idperiodo = idperiodo;
    }

    public int getIdimpacto() {
        return idimpacto;
    }

    public void setIdimpacto(int idimpacto) {
        this.idimpacto = idimpacto;
    }

    public int getIdsolucion() {
        return idsolucion;
    }

    public void setIdsolucion(int idsolucion) {
        this.idsolucion = idsolucion;
    }

    @Override
    public String toString() {
        return "Evaluacion{" + "hallazgo=" + hallazgo + ", recomendacion=" + recomendacion + ", usuario_evaluacion=" + usuario_evaluacion + ", fecha_evaluacion=" + fecha_evaluacion + ", idcontrol=" + idcontrol + ", idproyecto=" + idproyecto + ", idperiodo=" + idperiodo + ", idimpacto=" + idimpacto + ", idsolucion=" + idsolucion + '}';
    }
}
