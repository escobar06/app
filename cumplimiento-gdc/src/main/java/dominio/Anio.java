package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Anio.findAll", query = "SELECT p FROM Anio p")})
public class Anio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idanio")
    private Integer idAnio;
    @Size(max = 45)
    private String descripcion;
    @OneToMany(mappedBy = "anio", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Periodo> periodoList;

    public Anio() {

    }

    public Anio(int idAnio, String descripcion) {
        this.idAnio = idAnio;
        this.descripcion = descripcion;
    }

    public Integer getIdAnio() {
        return idAnio;
    }

    public void setIdAnio(Integer idAnio) {
        this.idAnio = idAnio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Periodo> getPeriodoList() {
        return periodoList;
    }

    public void setPeriodoList(List<Periodo> periodoList) {
        this.periodoList = periodoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnio != null ? idAnio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anio)) {
            return false;
        }
        Anio other = (Anio) object;
        if ((this.idAnio == null && other.idAnio != null) || (this.idAnio != null && !this.idAnio.equals(other.idAnio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Anio{" + "idAnio=" + idAnio + ", descripcion=" + descripcion + '}';
    }
}
