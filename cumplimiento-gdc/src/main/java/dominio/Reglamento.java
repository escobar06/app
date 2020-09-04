package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Reglamento.findAll", query = "SELECT p FROM Reglamento p")})
public class Reglamento implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreglamento")
    private Integer idReglamento;
    @Size(max = 45)
    private String descripcion;
    @OneToMany(mappedBy = "reglamento", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Capitulo> capituloList;

    public Reglamento() {

    }

    public Reglamento(int idReglamento, String descripcion) {
        this.idReglamento = idReglamento;
        this.descripcion = descripcion;
    }

    public Integer getIdReglamento() {
        return idReglamento;
    }

    public void setIdReglamento(Integer idReglamento) {
        this.idReglamento = idReglamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Capitulo> getCapituloList() {
        return capituloList;
    }

    public void setCapitulooList(List<Capitulo> capituloList) {
        this.capituloList = capituloList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReglamento != null ? idReglamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reglamento)) {
            return false;
        }
        Reglamento other = (Reglamento) object;
        if ((this.idReglamento == null && other.idReglamento != null) || (this.idReglamento != null && !this.idReglamento.equals(other.idReglamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reglamento{" + "idReglamento=" + idReglamento + ", descripcion=" + descripcion + '}';
    }
}
