package tpe;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solucion {

    public static void imprimirResultadoBacktracking(List<Procesador> mejorAsignacion, int mejorTiempoFinal, int estados) {
        if (mejorAsignacion != null && !mejorAsignacion.isEmpty()) {
            for (Procesador p : mejorAsignacion) {
                System.out.println("Procesador " + p.getId_procesador() + ": " + p.getTiempo_ejecucion() + " unidades de tiempo, " + p.getCantCriticas() + " tareas críticas");
                for (Tarea t : p.getTareas_asignadas()) {
                    System.out.println("  Tarea " + t.getId_tarea() + ": " + t.getTiempo_ejecucion() + " unidades de tiempo, " + (t.Es_critica() ? "crítica" : "no crítica"));
                }
            }
            System.out.println("Solución obtenida :" + mejorTiempoFinal);
            System.out.println("Métrica para analizar el costo de la solución (cantidad de estados generados): " + estados);
        } else {
            System.out.println("No se encontró solución");
        }
    }

    public static void imprimirResultadoGreedy(HashMap<String, Procesador> procesadores, AtomicInteger candidatosConsiderados, List<Tarea> solucion) {
        if(solucion!=null){
            for (Procesador p : procesadores.values()) {
                System.out.println("Procesador " + p.getId_procesador() + ": " + p.getTiempo_ejecucion() + " unidades de tiempo, " + p.getCantCriticas() + " tareas críticas");
                for (Tarea t : p.getTareas_asignadas()) {
                    System.out.println("  Tarea " + t.getId_tarea() + ": " + t.getTiempo_ejecucion() + " unidades de tiempo, " + (t.Es_critica() ? "crítica" : "no crítica"));
                }
            }
            System.out.println("Solucion obtenida : " + Solucion.calcularTiempoMaximo(procesadores));
            System.out.println("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): " + candidatosConsiderados);
        } else {
            System.out.println("No hay solucion");
        }
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
