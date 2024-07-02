package tpe;

public class Main {

	public static void main(String args[]) {

		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");
		int tiempoX = 80;

		System.out.println("BACKTRACKING");
		servicios.servicioBackTraking(tiempoX);
		System.out.println("-----------------------------------");
		System.out.println("-----------------------------------");
		System.out.println("GREEDY");
		servicios.servicioGreedy(tiempoX);

	}
}
