package servicio;

import dominio.*;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EvaluacionService {

    public List<Evaluacion> listar();

    public List<Evaluacion> listarControl(Integer control);

    //public Evaluacion encontrar(Evaluacion evaluacion);

    public void insertar(Evaluacion evaluacion);

    public void actualizar(Evaluacion evaluacion);

    public void eliminar(Evaluacion evaluacion);    
}
