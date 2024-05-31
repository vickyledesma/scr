package tpe;
import java.util.List;

public class Procesador {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int año_funcionamiento;
    private List<Tarea> tareas_asignadas;
    private int tiempo_ejecucion;
    private int cantCriticas;

    
    public Procesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, Integer anio,List<Tarea> tareas_asignadas, int tiempo_ejecucion) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.año_funcionamiento = anio;
        this.tareas_asignadas = tareas_asignadas;
        tiempo_ejecucion = 0;
    }
    public Procesador(Procesador p) {
        //TODO Auto-generated constructor stub
    }
    //getter y setter
    public List<Tarea> getTareas_asignadas() {
        return tareas_asignadas;
    }
    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }
    public String getId_procesador() {
        return id_procesador;
    }
    public String getCodigo_procesador() {
        return codigo_procesador;
    }
    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }
    public int getCantCriticas() {
        return cantCriticas;
    }
    public void setCantCriticas(int cantCriticas) {
        this.cantCriticas = cantCriticas;
    }
    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }
    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }
   

    public boolean cumpleRestricciones(Tarea tarea, int tiempoX) {
        if (cantCriticas < 2 && this.isEsta_refrigerado() && this.getTiempo_ejecucion() < tiempoX) {
            return true;
        }
        return false;
    }
    
    public void asignarTarea(Tarea tarea){
        tareas_asignadas.add(tarea);
        tiempo_ejecucion += tarea.getTiempo_ejecucion();
        if(tarea.isEs_critica()){
            cantCriticas++;
        }
    }
    public void removerTarea(Tarea tarea) {
        tareas_asignadas.remove(tarea);
        tiempo_ejecucion -= tarea.getTiempo_ejecucion();
        if (tarea.isEs_critica()) {
            cantCriticas--;        
        }
    }
}


