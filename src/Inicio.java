
public class Inicio {

	public static void main(String[] args) {
		GestorAutomata gestor=new GestorAutomata();
		gestor.leerArchivoInput();
		gestor.verificarSiEsAFD();
		gestor.ejecutar();

	}

}
