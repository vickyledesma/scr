package tpe;

public class Tarea implements Comparable<Tarea>{
    private String id_tarea;
    private String nombre_tarea;
    private int tiempo_ejecucion;
    private boolean es_critica;
    private int nivel_prioridad;
    
    public Tarea(String id_tarea, String nombre_tarea, int tiempo_ejecucion, boolean es_critica, int nivel_prioridad) {
        this.id_tarea = id_tarea;
        this.nombre_tarea = nombre_tarea;
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.es_critica = es_critica;
        this.nivel_prioridad = nivel_prioridad;
    }

    public String getId_tarea() {
        return id_tarea;
    }
    public String getNombre_tarea() {
        return nombre_tarea;
    }
    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }
    public boolean Es_critica() {
        return es_critica;
    }
    public int getNivel_prioridad() {
        return nivel_prioridad;
    }
    @Override
    public String toString() {
        return "Tarea [id_tarea=" + id_tarea + ", nombre_tarea=" + nombre_tarea + ", tiempo_ejecucion="
                + tiempo_ejecucion + ", es_critica=" + es_critica + ", nivel_prioridad=" + nivel_prioridad + "]";
    }

    @Override
    public int compareTo(Tarea o) {
        return this.getTiempo_ejecucion()-o.getTiempo_ejecucion();
    }

    
}
