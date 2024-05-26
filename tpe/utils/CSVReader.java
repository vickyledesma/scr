package tpe.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tpe.Procesador;
import tpe.Tarea;



public class CSVReader {

	
	public CSVReader() {
	}
	
	public HashMap<String, Tarea> readTasks(String taskPath) {
		
		HashMap<String, Tarea> tareas = new HashMap<String, Tarea>();
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(taskPath);
		
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			// Aca instanciar lo que necesiten en base a los datos leidos
			Tarea tarea = new Tarea(id, nombre, tiempo, critica, prioridad);
			tareas.put(id, tarea);
		}
		return tareas;
	}
	
	public HashMap<String, Procesador> readProcessors(String processorPath) {
		HashMap<String, Procesador> procesadores = new HashMap<String, Procesador>();
		ArrayList<String[]> lines = this.readContent(processorPath);
		
		for (String[] line: lines) {
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			List<Tarea> tareas_asignadas = new ArrayList<>(); 
			
			// Aquí puedes agregar lógica para leer y crear instancias de tareas asignadas si es necesario
			
			Procesador procesador = new Procesador(id, codigo, refrigerado, anio, tareas_asignadas, 0); // Pasar la lista de tareas asignadas y el tiempo de ejecución
			procesadores.put(id, procesador);
		}
		return procesadores;
	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
}