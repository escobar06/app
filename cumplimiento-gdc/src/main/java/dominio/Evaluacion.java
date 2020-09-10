package dominio;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT p FROM Evaluacion p"),
    @NamedQuery(name = "Evaluacion.findControl", query = "SELECT p FROM Evaluacion p WHERE p.control = :control")})
public class Evaluacion implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevaluacion")
    private Integer idEvaluacion;
    //@Size(max = 45)
    private String hallazgo;
    //@Size(max = 45)
    private String recomendacion;
    @Size(max = 45)
    private String usuario_evaluacion;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_evaluacion;
    @JoinColumn(name = "idcontrol", referencedColumnName = "idcontrol")
    @ManyToOne
    private Control control;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
    @ManyToOne
    private Proyecto proyecto;
    @JoinColumn(name = "idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne
    private Periodo periodo;    
    @JoinColumn(name = "idimpacto", referencedColumnName = "idimpacto")
    @ManyToOne
    private Impacto impacto;    
    @JoinColumn(name = "idsolucion", referencedColumnName = "idsolucion")
    @ManyToOne
    private Solucion solucion;    

    public Evaluacion() {

    }

    public Evaluacion(Integer idEvaluacion, String hallazgo, String recomendacion, String usuario_evaluacion, Date fecha_evaluacion) {
        this.idEvaluacion = idEvaluacion;
        this.hallazgo = hallazgo;
        this.recomendacion = recomendacion;
        this.usuario_evaluacion = usuario_evaluacion;
        this.fecha_evaluacion = fecha_evaluacion;
    }

    public Evaluacion(Control control, Proyecto proyecto, Periodo periodo, Impacto impacto, Solucion solucion, String hallazgo, String recomendacion, String usuario_evaluacion, Date fecha_evaluacion) {
        this.control = control;
        this.proyecto = proyecto;
        this.periodo = periodo;
        this.impacto = impacto;
        this.solucion = solucion;
        this.hallazgo = hallazgo;
        this.recomendacion = recomendacion;
        this.usuario_evaluacion = usuario_evaluacion;
        this.fecha_evaluacion = fecha_evaluacion;
    }
    
    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Impacto getImpacto() {
        return impacto;
    }

    public void setImpacto(Impacto impacto) {
        this.impacto = impacto;
    }

    public Solucion getSolucion() {
        return solucion;
    }

    public void setSolucion(Solucion solucion) {
        this.solucion = solucion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluacion{" + "idEvaluacion=" + idEvaluacion + ", control=" + control + ", proyecto=" + proyecto + ", periodo=" + periodo + ", impacto=" + impacto + ", solucion=" + solucion + ", hallazgo=" + hallazgo + ", recomendacion=" + recomendacion + ", usuario_evaluacion=" + usuario_evaluacion + ", fecha_evaluacion=" + fecha_evaluacion + '}';
    }    
}
