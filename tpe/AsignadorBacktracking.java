package tpe;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AsignadorBacktracking {

     /* 
     * Estrategia backtracking:
     * Se crea un estado inicial con todos los procesadores sin tareas asignadas.
     * Se invoca a un método privado de backtracking pasándole por parámetro el estado inicial y la lista de tareas disponibles para asignar.
     * Este método, de manera recursiva, genera todas las combinaciones posibles de tareas y procesadores, comparándolas y retornando la mejor solución, si
     * es que existe al menos alguna solución.
     * La mejor solución es la que minimiza el tiempo máximo de ejecución de todos los procesadores.
     * En caso de no haber una solución, se retorna null.
     */
    
    public static void asignarTareas(HashMap<String, Tarea> tareas, HashMap<String, Procesador> procesadores, int tiempoX) {
        List<Procesador> mejorAsignacion = new LinkedList<>();
        List<Tarea> tareasSinAsignar = new LinkedList<>(tareas.values());
        AtomicInteger mejorTiempoFinal = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger estados = new AtomicInteger(0);
        asignarTareasRecursivo(estados, tareasSinAsignar, mejorAsignacion, tiempoX, procesadores, tareas, mejorTiempoFinal);
        Solucion.imprimirResultadoBacktracking(mejorAsignacion, mejorTiempoFinal.get(), estados.get());
    }
    
    private static void asignarTareasRecursivo(AtomicInteger estados, List<Tarea> tareasSinAsignar, List<Procesador> mejorAsignacion, int tiempoX, HashMap<String, Procesador> procesadores, HashMap<String, Tarea> tareas, AtomicInteger mejorTiempoFinal) {
        estados.incrementAndGet();
        if (tareasSinAsignar.isEmpty()) {
            int tiempoFinal = calcularTiempoFinal(procesadores);
            if (mejorTiempoFinal.get() > tiempoFinal) {
                mejorTiempoFinal.set(tiempoFinal);
                mejorAsignacion.clear();
                for (Procesador p : procesadores.values()) {
                    mejorAsignacion.add(new Procesador(p));
                }
            }
        } else {
            Tarea tarea = tareasSinAsignar.remove(0);
            for (Procesador procesador : procesadores.values()) {
                if (procesador.cumpleRestricciones(tarea, tiempoX)) {
                    procesador.asignarTarea(tarea);
                    asignarTareasRecursivo(estados, tareasSinAsignar, mejorAsignacion, tiempoX, procesadores, tareas, mejorTiempoFinal);
                    procesador.removerTarea(tarea);
                }
            }
            tareasSinAsignar.add(0, tarea);
        }
    }
    
    private static int calcularTiempoFinal(HashMap<String, Procesador> procesadores) {
        int tiempoMaximo = 0;
        for (Procesador pro : procesadores.values()) {
            if (pro.getTiempo_ejecucion() > tiempoMaximo) {
                tiempoMaximo = pro.getTiempo_ejecucion();
            }
        }
        return tiempoMaximo;
    }

}