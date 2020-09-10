package dominio;

public class Pivot {

    private int idcontrol;
    private String control;
    private String proyecto;
    private String periodo;
    private String impacto;
    private String solucion;
    private int implementa;
    private int noimplementa;
    private int idreglamento;
    private String reglamento;
    private int idcapitulo;
    private String capitulo;
    private int idarticulo;
    private String articulo;
    private int nivel;
    private String level;
    private String impl;
    private int idproyecto;
    private int presupuesto;
    private int tiempo;
    private int idarea;
    private String area;
    private String hallazgo;
    private String recomendacion;
    private String usuario;
    private String fecha;
    
    
    public Pivot(int idcontrol, String control, String periodo, int nivel, String level, String hallazgo, String recomendacion, String usuario, String fecha) {
        this.idcontrol = idcontrol;
        this.control = control;
        this.periodo = periodo;
        this.nivel = nivel;
        this.level = level;
        this.hallazgo = hallazgo;
        this.recomendacion = recomendacion;
        this.usuario = usuario;
        this.fecha = fecha;
    }
    
    public String getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(String hallazgo) {
        this.hallazgo = hallazgo;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Pivot(int idcontrol, String control, int nivel, String level, String impl) {
        this.idcontrol = idcontrol;
        this.control = control;
        this.nivel = nivel;
        this.level = level;
        this.impl = impl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImpl() {
        return impl;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }

    public Pivot(int idcontrol, String control, int idarea, String area) {
        this.idcontrol = idcontrol;
        this.control = control;
        this.idarea = idarea;
        this.area = area;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
    public Pivot(String proyecto, int idproyecto, int presupuesto, int tiempo) {
        this.proyecto = proyecto;
        this.idproyecto = idproyecto;
        this.presupuesto = presupuesto;
        this.tiempo = tiempo;
    }
    
    public Pivot(int idreglamento, String reglamento, int idcapitulo, String capitulo, int idarticulo, String articulo) {
        this.idreglamento = idreglamento;
        this.reglamento = reglamento;
        this.idcapitulo = idcapitulo;
        this.capitulo = capitulo;
        this.idarticulo = idarticulo;
        this.articulo = articulo;
    }    
    
    public Pivot(int idcontrol, String control, int idarticulo) {
        this.idcontrol = idcontrol;
        this.control = control;
        this.idarticulo = idarticulo;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdreglamento() {
        return idreglamento;
    }

    public void setIdreglamento(int idreglamento) {
        this.idreglamento = idreglamento;
    }

    public String getReglamento() {
        return reglamento;
    }

    public void setReglamento(String reglamento) {
        this.reglamento = reglamento;
    }

    public int getIdcapitulo() {
        return idcapitulo;
    }

    public void setIdcapitulo(int idcapitulo) {
        this.idcapitulo = idcapitulo;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return "EvaluacionPivot{" + "idcontrol=" + idcontrol + ", control=" + control + ", proyecto=" + proyecto + ", periodo=" + periodo + ", impacto=" + impacto + ", solucion=" + solucion + '}';
    }

    public Pivot(int implementa, int noimplementa) {
        this.implementa = implementa;
        this.noimplementa = noimplementa;
    }

    public int getImplementa() {
        return implementa;
    }

    public void setImplementa(int implementa) {
        this.implementa = implementa;
    }

    public int getNoimplementa() {
        return noimplementa;
    }

    public void setNoimplementa(int noimplementa) {
        this.noimplementa = noimplementa;
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
