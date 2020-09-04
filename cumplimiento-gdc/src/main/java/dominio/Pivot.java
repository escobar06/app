package dominio;

public class Pivot {

    private int idcontrol;
    private String control;
    private String proyecto;
    private String periodo;
    private String impacto;
    private String solucion;

    @Override
    public String toString() {
        return "EvaluacionPivot{" + "idcontrol=" + idcontrol + ", control=" + control + ", proyecto=" + proyecto + ", periodo=" + periodo + ", impacto=" + impacto + ", solucion=" + solucion + '}';
    }

    public Pivot(int idcontrol, String control) {
        this.idcontrol = idcontrol;
        this.control = control;
    }

    public Pivot(int idcontrol) {
        this.idcontrol = idcontrol;
    }

    public int getIdcontrol() {
        return idcontrol;
    }

    public void setIdcontrol(int idcontrol) {
        this.idcontrol = idcontrol;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Pivot(int idcontrol, String control, String proyecto, String periodo, String impacto, String solucion) {
        this.idcontrol = idcontrol;
        this.control = control;
        this.proyecto = proyecto;
        this.periodo = periodo;
        this.impacto = impacto;
        this.solucion = solucion;
    }    
}
