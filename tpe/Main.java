package tpe;

import java.util.HashMap;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		Tarea tarea1 = servicios.servicio1("T1");
		System.out.println(tarea1);


		    

	}
}
