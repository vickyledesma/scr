package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private HashMap<String, Tarea> tareas = new HashMap<String, Tarea>();
	private HashMap<String, Procesador> procesadores = new HashMap<String, Procesador>();
	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.procesadores = reader.readProcessors(pathProcesadores);
		this.tareas = reader.readTasks(pathTareas);
	}
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) { // complejidad de O(1)
		return tareas.get(ID);

	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> servicio2(boolean esCritica) {
		ArrayList<Tarea> resultado = new ArrayList();
		Iterator<String> tarea = tareas.keySet().iterator(); //Complejidad O(n) por cada tarea
		while(tarea.hasNext()){
			String idvecino = tarea.next();
			if(tareas.get(idvecino).isEs_critica()== esCritica){ //Complejidad O(1) ver si la tarea es critica 
				resultado.add(tareas.get(idvecino));
			}

		}
		return resultado;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		ArrayList<Tarea> resultado = new ArrayList();
		Iterator<String> tarea = tareas.keySet().iterator(); //Complejidad O(n) n = a cada tarea 
		while(tarea.hasNext()){
			String idvecino = tarea.next();
			if(tareas.get(idvecino).getNivel_prioridad()>prioridadInferior && //Complejidad O(1) preguntar prioridad 
			 	tareas.get(idvecino).getNivel_prioridad()<prioridadSuperior){
				resultado.add(tareas.get(idvecino));
			}

		}
		return resultado;
	} 

}
