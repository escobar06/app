package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Capitulo.findAll", query = "SELECT p FROM Capitulo p")})
public class Capitulo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcapitulo")
    private Integer idCapitulo;
    @Size(max = 45)
    private String descripcion;
    @JoinColumn(name = "idreglamento", referencedColumnName = "idreglamento")
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL)//persistencia en cascada - al guardar la persona guarda tambien el usuario
    //@ManyToOne(fetch = FetchType.EAGER)//Carga inmediata
    private Reglamento reglamento;
    @OneToMany(mappedBy = "capitulo", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Articulo> articuloList;

    public Capitulo() {

    }

    public Capitulo(int idCapitulo, String descripcion) {
        this.idCapitulo = idCapitulo;
        this.descripcion = descripcion;
    }

    public Integer getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(Integer idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Reglamento getReglamento() {
        return reglamento;
    }

    public void setReglamento(Reglamento reglamento) {
        this.reglamento = reglamento;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapitulo != null ? idCapitulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capitulo)) {
            return false;
        }
        Capitulo other = (Capitulo) object;
        if ((this.idCapitulo == null && other.idCapitulo != null) || (this.idCapitulo != null && !this.idCapitulo.equals(other.idCapitulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Capitulo{" + "idCapitulo=" + idCapitulo + ", descripcion=" + descripcion + ", reglamento=" + reglamento + '}';
    }
}
