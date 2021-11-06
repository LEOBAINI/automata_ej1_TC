import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class GestorAutomata {

	String[] alfabeto;
	String[] estadosFinales;	
	ArrayList<String[]> defTransiciones=new ArrayList<String[]>();
	int cantidadEstados=0;
	ArrayList<Estado>estados;
	ArrayList<Transicion>transiciones;
	boolean esDeterministico=true;

	boolean verificarSiEsAFD(){
		return false;

	}

	public  String  leerArchivoInput(String archivo) throws InterruptedException, FileNotFoundException {

		int nroLinea=0;	  	
		FileReader f = new FileReader(archivo);

		System.out.println("Intentando leer archivo "+archivo);
		String resultado=null;    
		String strLinea=null;

		// Creamos el Buffer de Lectura
		BufferedReader buffer = new BufferedReader(f); 

		// Leer el archivo linea por linea
		try {
			while ((strLinea = buffer.readLine()) != null)   {
				// Imprimimos la línea por pantalla
				if(resultado==null){
					resultado=strLinea;
				}else{
					resultado=resultado+" "+strLinea;
				}

				nroLinea++;		
				crearEstructura(nroLinea,strLinea);





			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : " +e.getMessage());
		}
		// Cerramos el archivo
		try {
			System.out.println("Fin de lectura de archivo y set de parámetros");
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error : " +e.getMessage());
		}    			
		return resultado;



	}

	public void ejecutar(String palabra) {
		if(contieneElementosValidos(palabra)) {
			System.out.println("Ejecutar automata, todos los caracteres pertenecen al lenguaje");
		}
		
		recorrerdefTransiciones();

	}

	private boolean contieneElementosValidos(String palabra) {
		int i=0;
		boolean resultado=false;
		while(i!=palabra.length()) {
			resultado=contieneAlfabeto(String.valueOf(palabra.charAt(i)));
			if(resultado==false) {
				System.out.println("El caracter "+String.valueOf(palabra.charAt(i))+" en la posición "+i+" no pertenece al lenguaje, no se ejecutará el autómata");
				break;
			}
			i++;
		}
		return resultado;
	}
	private boolean contieneAlfabeto(String caracter) {
		boolean resultado = false;
		for(int i=0;i<alfabeto.length;i++) {
			if(!caracter.equals(alfabeto[i].trim())) {
				resultado= false;
			}else {
				resultado=true;
				break;
			}
		}
		return resultado;
	}
		
		
	

	private void crearEstructura(int lineaArchivo,String linea) {

		switch(lineaArchivo) {
		case 1: System.out.println("Alfabeto "+linea); alfabeto=linea.split(",");break; 
		case 2: System.out.println("Cantidad Estados "+linea);cantidadEstados=Integer.parseInt(linea);break;
		case 3: System.out.println("Estados finales "+linea); estadosFinales=linea.split(",");break;
		default : System.out.println("Transiciones "+linea);cargarDefTransiciones(lineaArchivo-2,linea);break;
		}


	}

	private void cargarDefTransiciones(int i, String linea) {
		defTransiciones.add(linea.split(","));		

	}
	private void esDeterministico() {
		int cantCaracteresAlfabeto=alfabeto.length;
		int cantEstadosTotal=cantidadEstados;

		esDeterministico=true;		
	}

	private void recorrerdefTransiciones(){
		boolean transicionesValidas;
		String caracterTemp;
		String estadoTemp;
		for(int i=0;i<defTransiciones.size();i++) {
			caracterTemp=defTransiciones.get(i)[1].substring(0,defTransiciones.get(i)[1].indexOf("->"));
			estadoTemp=defTransiciones.get(i)[1].substring(defTransiciones.get(i)[1].indexOf("->")+2,defTransiciones.get(i)[1].length());

			System.out.println(
					"Estado "+defTransiciones.get(i)[0]+
					"Transicion "+defTransiciones.get(i)[1]+
					"Caracter "+caracterTemp.trim()+" "+contieneAlfabeto(caracterTemp.trim())+" "+
					"Estado al que va al estado "+estadoTemp.stripLeading());
			transicionesValidas=contieneAlfabeto(caracterTemp.trim());
			if(!transicionesValidas) {
				System.out.println("Transicion inválida, caracter no válido en "+defTransiciones.get(i)[1]);
				break;
			}
		}
	}


}
