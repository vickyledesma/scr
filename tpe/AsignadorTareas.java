package tpe;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AsignadorTareas {

    public static void asignarTareas(HashMap<String, Tarea> tareas,HashMap<String, Procesador> procesadores,int tiempoX) {
        List<Procesador> mejorAsignacion = new LinkedList<>(); 
        List<Tarea> tareasSinAsignar = new LinkedList<>(tareas.values());
        int mejorTiempoFinal = Integer.MAX_VALUE;
        int contador = 0;
        asignarTareasRecursivo(contador, tareasSinAsignar, mejorAsignacion, tiempoX, procesadores, tareas, mejorTiempoFinal);
    }

    private static void asignarTareasRecursivo(int contador,List<Tarea> tareasSinAsignar, List<Procesador> mejorAsignacion, int tiempoX, HashMap<String, Procesador> procesadores, HashMap<String, Tarea> tareas, int mejorTiempoFinal) {
        contador++;
        if (tareasSinAsignar.isEmpty()) {
            int tiempoFinal = calcularTiempoFinal(procesadores);
            if (tiempoFinal < mejorTiempoFinal) {
                mejorTiempoFinal = tiempoFinal;
                mejorAsignacion.clear();
                for (Procesador p : procesadores.values()) {
                    mejorAsignacion.add(new Procesador(p)); 
                }
            }
        }
        else {}
        Tarea tarea = tareasSinAsignar.get(0); 
        for (Procesador procesador : procesadores.values()) {
            if (procesador.cumpleRestricciones(tarea,tiempoX)) {
                procesador.asignarTarea(tarea);
                tareasSinAsignar.remove(tarea);

                asignarTareasRecursivo(contador,tareasSinAsignar, mejorAsignacion, tiempoX, procesadores,tareas,mejorTiempoFinal);

                procesador.removerTarea(tarea);
                tareasSinAsignar.add(0, tarea);
            }
    }
}
    public static int calcularTiempoFinal(HashMap<String, Procesador> procesadores){
        int tiempoFinal = 0;
        for (Procesador pro : procesadores.values()) {
            tiempoFinal += pro.getTiempo_ejecucion();
        }
        return tiempoFinal;
    }
    
}
