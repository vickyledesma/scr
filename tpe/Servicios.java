package tpe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
    private HashMap<String, Tarea> tareas = new HashMap<>();
    private HashMap<String, Procesador> procesadores = new HashMap<>();
    private List<Tarea> tareasCriticas = new ArrayList<>(); // Lista para tareas críticas
	private List<Tarea> tareasNoCriticas = new ArrayList<>();

    /*
     * Expresar la complejidad temporal del constructor.
     */

    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();
        this.procesadores = reader.readProcessors(pathProcesadores);
        this.tareas = reader.readTasks(pathTareas);
        for (Tarea tarea : tareas.values()) {
            if (tarea.Es_critica()) {
                tareasCriticas.add(tarea);
            }
			else{
				tareasNoCriticas.add(tarea);
			}
        }
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) { // Complejidad O(1)
        return tareas.get(ID);
    }
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    public List<Tarea> servicio2(boolean esCritica) { // Complejidad O(1)
        if (!esCritica) {
			return tareasNoCriticas; 
		} else {
			return tareasCriticas;
		}
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) { // Complejidad O(n)
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea tarea : tareas.values()) {
            if (tarea.getNivel_prioridad() >= prioridadInferior && tarea.getNivel_prioridad() <= prioridadSuperior) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    public void servicioBackTraking(int tiempoX) {
        AsignadorBacktracking.asignarTareas(tareas, procesadores, tiempoX);
    }
    public void servicioGreedy(int tiempoX) {
        AsignadorGreedy.asignarTareas(tareas, procesadores, tiempoX);
    }

    public HashMap<String, Tarea> getTareas() {
        return tareas;
    }
    public HashMap<String, Procesador> getProcesadores() {
        return procesadores;
    }
    
}