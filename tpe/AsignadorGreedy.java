package tpe;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
    /*
     * Estrategia greedy:
     * Se crea una lista inicial de tareas sin asignar y se ordena en orden decreciente para asignar primero las que mayor tiempo de ejecucion tengan.
     * Se invoca un método privado greedy que itera sobre esta lista, asignando cada tarea al procesador más adecuado en cada paso.
     * Se selecciona siempre el procesador que puede ejecutar la tarea con el menor incremento en el tiempo de ejecución total.
     * Si se encuentra una asignación factible para todas las tareas, se imprime la solución y las métricas relevantes.
     * En caso de no poder asignar una tarea a ningún procesador, se indica que no se encontró solución.
     */

public class AsignadorGreedy {

    public static void asignarTareas(HashMap<String, Tarea> tareas, HashMap<String, Procesador> procesadores, int tiempoX) {
        List<Tarea> tareasSinAsignar = new LinkedList<>(tareas.values());
        List<Tarea> solucion = new LinkedList<>();
        AtomicInteger candidatosConsiderados = new AtomicInteger(0);
        Collections.sort(tareasSinAsignar, Collections.reverseOrder());
        asignarTareasGreedy(tareasSinAsignar, solucion, tareas, procesadores, tiempoX, candidatosConsiderados);
    }

    private static void asignarTareasGreedy(List<Tarea> tareasSinAsignar, List<Tarea> solucion, HashMap<String, Tarea> tareas, HashMap<String, Procesador> procesadores, int tiempoX, AtomicInteger candidatosConsiderados) {
        while (!solucionCompleta(tareasSinAsignar)) {
            Tarea x = seleccionar(tareasSinAsignar);
            tareasSinAsignar.remove(x);

            Procesador mejorProcesador = factible(x, procesadores, tiempoX, candidatosConsiderados);
            if (mejorProcesador != null) {
                mejorProcesador.asignarTarea(x);
                solucion.add(x);
            } 
        }

        if (solucionCompleta(tareasSinAsignar)) {
            Solucion.imprimirResultadoGreedy(procesadores, candidatosConsiderados, solucion);
        }
    }

    private static boolean solucionCompleta(List<Tarea> tareasSinAsignar) {
        return tareasSinAsignar.isEmpty();
    }

    private static Tarea seleccionar(List<Tarea> tareasSinAsignar) {
        return tareasSinAsignar.get(0);
    }

    private static Procesador factible(Tarea x, HashMap<String, Procesador> procesadores, int tiempoX, AtomicInteger candidatosConsiderados) {
        Procesador mejorProcesador = null;
        for (Procesador p : procesadores.values()) {
            candidatosConsiderados.incrementAndGet();
            if (p.cumpleRestricciones(x, tiempoX) && (mejorProcesador == null || p.getTiempo_ejecucion() < mejorProcesador.getTiempo_ejecucion())) {
                mejorProcesador = p;
            }
        }
        return mejorProcesador;
    }
    
}
