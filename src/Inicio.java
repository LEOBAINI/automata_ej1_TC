import java.io.FileNotFoundException;

public class Inicio {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput(".\\settings.txt");
		gestor.verificarSiEsAFD();
		gestor.ejecutar();

	}

}
