package tpe;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
        int candidatosConsiderados = 0; 
        Collections.sort(tareasSinAsignar, Collections.reverseOrder());
        asignarTareasGreedy(tareasSinAsignar, solucion, tareas, procesadores, tiempoX, candidatosConsiderados);
    }

    private static void asignarTareasGreedy(List<Tarea> tareasSinAsignar, List<Tarea> solucion, HashMap<String, Tarea> tareas, HashMap<String, Procesador> procesadores, int tiempoX, int candidatosConsiderados) {
        while (!solucionCompleta(tareasSinAsignar)) {
            Tarea x = seleccionar(tareasSinAsignar); 
            tareasSinAsignar.remove(x);
            candidatosConsiderados++; 
    
            Procesador mejorProcesador = factible(x, procesadores, tiempoX); 
            if(mejorProcesador != null){
                mejorProcesador.asignarTarea(x);
                solucion.add(x); 
            } else {
                System.out.println("No se encontró solución");
                return;
            }
        }
    
        if (solucionCompleta(tareasSinAsignar)) {
            System.out.println("Solución obtenida:");
            imprimirAsignacion(procesadores);
            System.out.println("Tiempo máximo de ejecución: " + calcularTiempoMaximo(procesadores));
            System.out.println("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): " + candidatosConsiderados);
        } else {
            System.out.println("No se encontró solución");
        }
    }
    
    private static boolean solucionCompleta(List<Tarea> tareasSinAsignar) {
        return tareasSinAsignar.isEmpty();
        
    }
    private static Tarea seleccionar(List<Tarea> tareasSinAsignar) {
        return tareasSinAsignar.get(0);
    }

    private static Procesador factible(Tarea x, HashMap<String, Procesador> procesadores, int tiempoX) {
        Procesador mejorProcesador = null;
        for (Procesador p : procesadores.values()) {
            if (p.cumpleRestricciones(x, tiempoX) && (mejorProcesador == null || p.getTiempo_ejecucion() < mejorProcesador.getTiempo_ejecucion())) {
                mejorProcesador = p;
            }
        }
        return mejorProcesador;
    }

    private static int calcularTiempoMaximo(HashMap<String, Procesador> procesadores) {
        int tiempoMaximo = 0;
        for (Procesador p : procesadores.values()) {
            if (p.getTiempo_ejecucion() > tiempoMaximo) {
                tiempoMaximo = p.getTiempo_ejecucion();
            }
        }
        return tiempoMaximo;
    }
    private static void imprimirAsignacion(HashMap<String, Procesador> procesadores) {
        for (Procesador p : procesadores.values()) {
            System.out.println("Procesador " + p.getId_procesador() + ": " + p.getTiempo_ejecucion() + " unidades de tiempo, " + p.getCantCriticas() + " tareas críticas");
            for (Tarea t : p.getTareas_asignadas()) {
                System.out.println("  Tarea " + t.getId_tarea() + ": " + t.getTiempo_ejecucion() + " unidades de tiempo, " + (t.Es_critica() ? "crítica" : "no crítica"));
            }
        }
    }
}