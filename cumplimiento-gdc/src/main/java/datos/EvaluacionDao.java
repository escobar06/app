package datos;

import java.util.*;
import dominio.*;

public interface EvaluacionDao {

    public List<Evaluacion> listar();

    public List<Evaluacion> listarControl(Integer control);

    //public Evaluacion encontrar(Evaluacion evaluacion);

    public void insertar(Evaluacion evaluacion);

    public void actualizar(Evaluacion evaluacion);

    public void eliminar(Evaluacion evaluacion);
}
