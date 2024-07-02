package tpe;
import java.util.ArrayList;
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
        this.id_procesador = p.id_procesador;
        this.codigo_procesador = p.codigo_procesador;
        this.esta_refrigerado = p.esta_refrigerado;
        this.año_funcionamiento = p.año_funcionamiento;
        this.tareas_asignadas = new ArrayList<>(p.tareas_asignadas);
        this.tiempo_ejecucion = p.tiempo_ejecucion;
        this.cantCriticas = p.cantCriticas;
    }
    public List<Tarea> getTareas_asignadas() {
        return tareas_asignadas;
    }
    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }
    public String getId_procesador() {
        return id_procesador;
    }
    public int getCantCriticas() {
        return cantCriticas;
    }
    public boolean Esta_refrigerado() {
        return esta_refrigerado;
    }
    public boolean cumpleRestricciones(Tarea tarea, int tiempoX) {
        if (tarea.Es_critica() && cantCriticas >= 2) {
            return false;
        }
        if (!Esta_refrigerado() && (this.tiempo_ejecucion + tarea.getTiempo_ejecucion() > tiempoX)) {
            return false;
        }
        return true;
    }

    public void asignarTarea(Tarea tarea) {
        tareas_asignadas.add(tarea);
        tiempo_ejecucion += tarea.getTiempo_ejecucion();
        if (tarea.Es_critica()) {
            cantCriticas++;
        }
    }

    public void removerTarea(Tarea tarea) {
        tareas_asignadas.remove(tarea);
        tiempo_ejecucion -= tarea.getTiempo_ejecucion();
        if (tarea.Es_critica()) {
            cantCriticas--;
        }
    }
}


