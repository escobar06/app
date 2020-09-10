package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT p FROM Area p")})
public class Area implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarea")
    private Integer idArea;
    @Size(max = 45)
    private String descripcion;
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "persona")
    private List<Control> controlList;

    public Area() {

    }

    public Area(int idArea, String descripcion) {
        this.idArea = idArea;
        this.descripcion = descripcion;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Area{" + "idArea=" + idArea + ", descripcion=" + descripcion + ", controlList=" + controlList + '}';
    }
}
