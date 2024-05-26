package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AsignadorTareas {

     public static void asignarTareas(int tiempoX, HashMap<String, Tarea> tareas,HashMap<String, Procesador> procesadores) {
        int mejorTiempoFinal = Integer.MAX_VALUE;
        List<Tarea> mejorAsignacion = new ArrayList<>();
        List<Tarea> tareasSinAsignar = new ArrayList<>(tareas.values()); // guardo todas las tareas y a medida que asigno a un procesador la borro
        asignarTareasRecursivo(tareasSinAsignar, mejorTiempoFinal, mejorAsignacion, tiempoX ,tareas,procesadores);
    }

    private static void asignarTareasRecursivo(List<Tarea> tareasSinAsignar, int mejorTiempoFinal, List<Tarea> mejorAsignacion, int tiempoX, HashMap<String, Tarea> tareas,HashMap<String, Procesador> procesadores) {
            int tiempoActual = 0;
            if(tareasSinAsignar.size()==0){ //entro aca una vez que ya asigne todas las tareas, seria el mejor de los casos? 
                if (tiempoActual < mejorTiempoFinal) {
                    mejorTiempoFinal = tiempoActual;
                    mejorAsignacion.clear();
                    mejorAsignacion.addAll(mejorAsignacion);
                }
            }
            Iterator<Tarea> tareaIterator = tareasSinAsignar.iterator();
            while (tareaIterator.hasNext()) {
            Tarea tarea = tareaIterator.next();
            Iterator<String> procesadorIterator = procesadores.keySet().iterator();
                while (procesadorIterator.hasNext()) {
                    String procesadorId = procesadorIterator.next();
                    Procesador procesador = procesadores.get(procesadorId);
                    if (procesador.cumpleRestricciones(procesador, tarea, tiempoX)) {
                        procesador.asignarTarea(tarea);
                        tareasSinAsignar.remove(tarea);
                    }
                }
            }
        }
    }
