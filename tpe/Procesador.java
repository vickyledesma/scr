package tpe;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Procesador {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int año_funcionamiento;
    private List<Tarea> tareas_asignadas;
    private int tiempo_ejecucion;

    
    public Procesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, Integer anio,List<Tarea> tareas_asignadas, int tiempo_ejecucion) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.año_funcionamiento = anio;
        this.tareas_asignadas = tareas_asignadas;
        tiempo_ejecucion = 0;
    }
    public List<Tarea> getTareas_asignadas() {
        return tareas_asignadas;
    }
    public void setTareas_asignadas(List<Tarea> tareas_asignadas) {
        this.tareas_asignadas = tareas_asignadas;
    }
    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }
    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }
    public String getId_procesador() {
        return id_procesador;
    }
    public void setId_procesador(String id_procesador) {
        this.id_procesador = id_procesador;
    }
    public String getCodigo_procesador() {
        return codigo_procesador;
    }
    public void setCodigo_procesador(String codigo_procesador) {
        this.codigo_procesador = codigo_procesador;
    }
    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }
    public void setEsta_refrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }
    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }
    public void setAño_funcionamiento(int año_funcionamiento) {
        this.año_funcionamiento = año_funcionamiento;
    }

    public int tiempo_ejecucion(Procesador procesador){
        Iterator<Tarea> iterator = procesador.getTareas_asignadas().iterator();
        while(iterator.hasNext()){
            Tarea tarea = iterator.next();
            tiempo_ejecucion += tarea.getTiempo_ejecucion();
        }
        return tiempo_ejecucion;
    }

    public boolean cumpleRestricciones(Procesador procesador, Tarea tarea, int tiempoX) {
        int criticasAsignadas = 0;
        int tiempomaximo = procesador.tiempo_ejecucion(procesador);
        Iterator<Tarea> iterator = procesador.getTareas_asignadas().iterator();
        while (iterator.hasNext()) {
            Tarea tareaAsignada = iterator.next();
            if (tareaAsignada.isEs_critica()) {
                criticasAsignadas++;
            }
        }
        if (tarea.isEs_critica()) {
            criticasAsignadas++;
        }
        if (criticasAsignadas < 2 && procesador.isEsta_refrigerado() && tiempomaximo < tiempoX) {
            return true;
        }
        return false;
    }
    public void asignarTarea(Tarea tarea){
        tareas_asignadas.add(tarea);

    }
}


