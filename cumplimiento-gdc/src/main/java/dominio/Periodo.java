package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p")})
public class Periodo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperiodo")
    private Integer idPeriodo;
    @Size(max = 45)
    private String descripcion;
    @JoinColumn(name = "idanio", referencedColumnName = "idanio")
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL)//persistencia en cascada - al guardar la persona guarda tambien el usuario
    //@ManyToOne(fetch = FetchType.EAGER)//Carga inmediata
    private Anio anio;
    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Evaluacion> evaluacionList;

    public Periodo() {

    }

    public Periodo(int idPeriodo, String descripcion) {
        this.idPeriodo = idPeriodo;
        this.descripcion = descripcion;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
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
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Periodo{" + "idPeriodo=" + idPeriodo + ", descripcion=" + descripcion + ", anio=" + anio + '}';
    }
}
