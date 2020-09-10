package servicio;

import dominio.*;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ControlService {

    public List<Control> listar();
    
    public List<Control> contarImplementa();

    public List<Control> contarNoImplementa();

    public Control encontrar(Control control);

    public void insertar(Control control);

    public void actualizar(Control control);

    public void eliminar(Control control);    
}
