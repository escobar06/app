package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT p FROM Articulo p")})
public class Articulo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticulo")
    private Integer idArticulo;
    @Size(max = 45)
    private String descripcion;
    @JoinColumn(name = "idcapitulo", referencedColumnName = "idcapitulo")
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL)//persistencia en cascada - al guardar la persona guarda tambien el usuario
    //@ManyToOne(fetch = FetchType.EAGER)//Carga inmediata
    private Capitulo capitulo;
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Control> controlList;

    public Articulo() {

    }

    public Articulo(int idArticulo, String descripcion) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
    }

    public List<Control> getControlList() {
        return controlList;
    }

    public void setControlList(List<Control> controlList) {
        this.controlList = controlList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.idArticulo == null && other.idArticulo != null) || (this.idArticulo != null && !this.idArticulo.equals(other.idArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", descripcion=" + descripcion + ", capitulo=" + capitulo + '}';
    }
}
