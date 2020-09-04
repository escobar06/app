package datos;

import java.util.*;
import dominio.*;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class EvaluacionDaoImpl implements EvaluacionDao {

    @PersistenceContext(unitName = "GDCProdPU")//Se inyecta la unidad de persistencia a la variable de tipo EntityManager
    EntityManager em;
    TypedQuery<Evaluacion> consultaEvaluacion;

    @Override
    public List<Evaluacion> listar() {
        consultaEvaluacion = em.createNamedQuery("Evaluacion.findAll", Evaluacion.class);
        List<Evaluacion> listaEvaluacion = consultaEvaluacion.getResultList();
        return listaEvaluacion;
    }

    @Override
    public List<Evaluacion> listarControl(Integer control) {
        consultaEvaluacion = em.createNamedQuery("Evaluacion.findControl", Evaluacion.class);
        consultaEvaluacion.setParameter("control", control);
        List<Evaluacion> listaControl = consultaEvaluacion.getResultList();
        return listaControl;
    }

    /*@Override
    public Evaluacion encontrar(Evaluacion evaluacion) {
        return em.find(Evaluacion.class, evaluacion.getIdEvaluacion());
    }*/

    @Override
    public void insertar(Evaluacion evaluacion) {
        em.persist(evaluacion);//insert
    }

    @Override
    public void actualizar(Evaluacion evaluacion) {
        em.merge(evaluacion);//update
    }

    @Override
    public void eliminar(Evaluacion evaluacion) {
        em.remove(em.merge(evaluacion));//delete
    }
}
