package datos;

import java.util.*;
import dominio.*;

public interface ControlDao {

    public List<Control> listar();

    public List<Control> contarImplementa();
    
    public List<Control> contarNoImplementa();

    public Control encontrar(Control control);

    public void insertar(Control control);

    public void actualizar(Control control);

    public void eliminar(Control control);
}
