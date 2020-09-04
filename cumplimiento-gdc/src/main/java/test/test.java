package test;

import datos.ConexionDaoImpl;
import dominio.Evaluacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class test {
    
    public static void main(String[] args) {
        ConexionDaoImpl operacion = new ConexionDaoImpl();
        
        //select
        List evalua = operacion.listarEvaluacion();
        //Iterator<Evaluacion> iterator = (Iterator<Evaluacion>) operacion.listarEvaluacion();
        for(int i = 0;i < evalua.size();i++){
            System.out.println(evalua);
        }
}
}