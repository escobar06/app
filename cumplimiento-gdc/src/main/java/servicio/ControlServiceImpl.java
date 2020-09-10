package servicio;

import dominio.*;
import datos.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ControlServiceImpl implements ControlService {
    
    @Inject
    private ControlDao controlDao;
    
    @Override
    public List<Control> listar() {
        return controlDao.listar();
    }
    
    @Override
    public List<Control> contarImplementa() {
        return controlDao.contarImplementa();
    }

    @Override
    public List<Control> contarNoImplementa() {
        return controlDao.contarNoImplementa();
    }

    @Override
    public Control encontrar(Control control) {
        return controlDao.encontrar(control);
    }
    
    @Override
    public void insertar(Control control) {
        controlDao.insertar(control);
    }
    
    @Override
    public void actualizar(Control control) {
        controlDao.actualizar(control);
    }
    
    @Override
    public void eliminar(Control control) {
        controlDao.eliminar(control);
    }
}
