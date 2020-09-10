package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Control.findAll", query = "SELECT p FROM Control p WHERE p.estado = :estado ORDER BY p.nivel ASC"),
    @NamedQuery(name = "Control.findImpl", query = "SELECT p FROM Control p WHERE p.implementa = :implementa AND p.estado = :estado")})
public class Control implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrol")
    private Integer idControl;
    @JoinColumn(name = "idarea", referencedColumnName = "idarea")
    @ManyToOne
    private Area area;
    @JoinColumn(name = "idnivel", referencedColumnName = "idnivel")
    @ManyToOne
    private Nivel nivel;
    @JoinColumn(name = "idarticulo", referencedColumnName = "idarticulo")
    @ManyToOne
    private Articulo articulo;    
    @Size(max = 45)
    private String descripcion;
    private Integer implementa;
    @Size(max = 45)
    private String estado;
    @OneToMany(mappedBy = "control", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Evaluacion> evaluacionList;

    public Control() {

    }

    public Control(Integer idControl, String descripcion, Integer implementa, String estado) {
        this.idControl = idControl;
        this.descripcion = descripcion;
        this.implementa = implementa;
        this.estado = estado;
    }

    public Integer getIdControl() {
        return idControl;
    }

    public void setIdControl(Integer idControl) {
        this.idControl = idControl;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getImplementa() {
        return implementa;
    }

    public void setImplementa(Integer implementa) {
        this.implementa = implementa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControl != null ? idControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Control)) {
            return false;
        }
        Control other = (Control) object;
        if ((this.idControl == null && other.idControl != null) || (this.idControl != null && !this.idControl.equals(other.idControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Control{" + "idControl=" + idControl + ", area=" + area + ", nivel=" + nivel + ", articulo=" + articulo + ", descripcion=" + descripcion + ", implementa=" + implementa + ", estado=" + estado + '}';
    }
}
