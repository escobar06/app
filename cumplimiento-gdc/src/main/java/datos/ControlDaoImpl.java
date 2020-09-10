package datos;

import java.util.*;
import dominio.*;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class ControlDaoImpl implements ControlDao {

    @PersistenceContext(unitName="GDCProdPU")//Se inyecta la unidad de persistencia a la variable de tipo EntityManager
    EntityManager em;
    TypedQuery<Control> consultaControles;

    @Override
    public List<Control> listar() {
        consultaControles = em.createNamedQuery("Control.findAll", Control.class);
        consultaControles.setParameter("estado", "A");
        List<Control> listaControl = null;
        listaControl = consultaControles.getResultList();
        return listaControl;
    }

    @Override
    public List<Control> contarImplementa() {
        consultaControles = em.createNamedQuery("Control.findImpl", Control.class);
        consultaControles.setParameter("implementa", 1);
        consultaControles.setParameter("estado", "A");
        List<Control> listaControl = consultaControles.getResultList();
        return listaControl;
    }

    @Override
    public List<Control> contarNoImplementa() {
        consultaControles = em.createNamedQuery("Control.findImpl", Control.class);
        consultaControles.setParameter("implementa", 0);
        consultaControles.setParameter("estado", "A");
        List<Control> listaControl = consultaControles.getResultList();
        return listaControl;
    }

    @Override
    public Control encontrar(Control control) {
        return em.find(Control.class, control.getIdControl());
    }

    @Override
    public void insertar(Control control) {
        em.persist(control);//insert
    }

    @Override
    public void actualizar(Control control) {
        em.merge(control);//update
    }

    @Override
    public void eliminar(Control control) {
        em.remove(em.merge(control));//delete
    }
}
