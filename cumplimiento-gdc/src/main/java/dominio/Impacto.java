package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Impacto.findAll", query = "SELECT p FROM Impacto p")})
public class Impacto implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idimpacto")
    private Integer idImpacto;
    @Size(max = 45)
    private String descripcion;
    @OneToMany(mappedBy = "impacto", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Evaluacion> evaluacionList;

    public Impacto() {

    }

    public Impacto(int idImpacto, String descripcion) {
        this.idImpacto = idImpacto;
        this.descripcion = descripcion;
    }

    public Integer getIdImpacto() {
        return idImpacto;
    }

    public void setIdImpacto(Integer idImpacto) {
        this.idImpacto = idImpacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idImpacto != null ? idImpacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impacto)) {
            return false;
        }
        Impacto other = (Impacto) object;
        if ((this.idImpacto == null && other.idImpacto != null) || (this.idImpacto != null && !this.idImpacto.equals(other.idImpacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Impacto{" + "idImpacto=" + idImpacto + ", descripcion=" + descripcion + '}';
    }
}
