package servicio;

import dominio.*;
import datos.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EvaluacionServiceImpl implements EvaluacionService {
    
    @Inject
    private EvaluacionDao evaluacionDao;
            
    @Override
    public List listar() {
        return evaluacionDao.listar();
    }
    
    @Override
    public List<Evaluacion> listarControl(Integer control) {
        return evaluacionDao.listarControl(control);
    }

    /*@Override
    public Evaluacion encontrar(Evaluacion evaluacion) {
        return evaluacionDao.encontrar(evaluacion);
    }*/
    
    @Override
    public void insertar(Evaluacion evaluacion) {
        evaluacionDao.insertar(evaluacion);
    }
    
    @Override
    public void actualizar(Evaluacion evaluacion) {
        evaluacionDao.actualizar(evaluacion);
    }
    
    @Override
    public void eliminar(Evaluacion evaluacion) {
        evaluacionDao.eliminar(evaluacion);
    }
}
