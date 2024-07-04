package tpe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
     * 
     * Lectura de procesadores: O(n) 
     * Lectura de tareas: O(n) 
     * Iteración sobre las tareas: O(n)
     * O(n)+O(n)+O(n)=O(3n)= O(n)
     */

    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader(); 
        this.procesadores = reader.readProcessors(pathProcesadores); // O(n)
        this.tareas = reader.readTasks(pathTareas); // O(n)
        for (Tarea tarea : tareas.values()) { // O(n)
            if (tarea.Es_critica()) {
                tareasCriticas.add(tarea); //O(1)
            }
			else{
				tareasNoCriticas.add(tarea);//O(1)
			}
        }
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     * Complejidad O(1) porque es obtener una tarea por id.
     */
    public Tarea servicio1(String ID) { 
        return tareas.get(ID);
    }
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     *  Complejidad O(1) porque solo tiene que retornar la lista.
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
     * Complejidad O(n) n son las tareas que hay que recorrer. 
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) { 
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