package tpe;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AsignadorGreedy {
    /*
     * Estrategia greedy:
     * Se crea una lista inicial de tareas sin asignar y se ordena en orden decreciente para asignar primero las que mayor tiempo de ejecucion tengan.
     * Se invoca un método privado greedy que itera sobre esta lista, asignando cada tarea al procesador más adecuado en cada paso.
     * Se selecciona siempre el procesador que puede ejecutar la tarea con el menor incremento en el tiempo de ejecución total.
     * Si se encuentra una asignación factible para todas las tareas, se imprime la solución y las métricas relevantes.
     * En caso de no poder asignar una tarea a ningún procesador, se indica que no se encontró solución.
     */ 

    public static void asignarTareas(HashMap<String, Tarea> tareas, HashMap<String, Procesador> procesadores, int tiempoX) {
        List<Tarea> tareasSinAsignar = new LinkedList<>(tareas.values());
        List<Tarea> solucion = new LinkedList<>();
        AtomicInteger candidatosConsiderados = new AtomicInteger(0);
        Collections.sort(tareasSinAsignar, Collections.reverseOrder());
        asignarTareasGreedy(tareasSinAsignar, solucion, procesadores, tiempoX, candidatosConsiderados);
    }

    private static void asignarTareasGreedy(List<Tarea> tareasSinAsignar, List<Tarea> solucion, HashMap<String, Procesador> procesadores, int tiempoX, AtomicInteger candidatosConsiderados) {
        while (!tareasSinAsignar.isEmpty()) {
            Tarea tarea = seleccionar(tareasSinAsignar);
            Procesador mejorProcesador = factible(tarea, procesadores, tiempoX, candidatosConsiderados);
            if (mejorProcesador != null) {
                mejorProcesador.asignarTarea(tarea);
                solucion.add(tarea);
            } else {
                Solucion.imprimirErrorGreedy(tarea);
                return;
            }
            tareasSinAsignar.remove(tarea);
        }
        
        Solucion.imprimirResultadoGreedy(procesadores, candidatosConsiderados, solucion, calcularTiempoMaximo(procesadores));
    }

    private static Tarea seleccionar(List<Tarea> tareasSinAsignar) {
        return tareasSinAsignar.get(0); 
    }

    private static Procesador factible(Tarea tarea, HashMap<String, Procesador> procesadores, int tiempoX, AtomicInteger candidatosConsiderados) {
        Procesador mejorProcesador = null;
        for (Procesador procesador : procesadores.values()) {
            candidatosConsiderados.incrementAndGet();
            if (procesador.cumpleRestricciones(tarea, tiempoX) && 
                (mejorProcesador == null || procesador.getTiempo_ejecucion() < mejorProcesador.getTiempo_ejecucion())) {
                mejorProcesador = procesador;
            }
        }
        return mejorProcesador;
    }

    private static int calcularTiempoMaximo(HashMap<String, Procesador> procesadores) {
        int tiempoMaximo = 0;
        for (Procesador pro : procesadores.values()) {
            if (pro.getTiempo_ejecucion() > tiempoMaximo) {
                tiempoMaximo = pro.getTiempo_ejecucion();
            }
        }
        return tiempoMaximo;
    }
}
