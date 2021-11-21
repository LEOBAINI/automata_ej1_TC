import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
//Algoritmo sacado de https://www.youtube.com/watch?v=wacz5J40h9A
/*
a, b, c, d
10
1, 2, 3
1, a -> 3
1, a -> 4
1, a -> 5
1, d -> 4
4, E -> 4
*/
import java.util.HashMap;

public class GestorAutomata {
	
	
	
	public GestorAutomata() {
		
	}
	String[] alfabeto;
	int cantidadEstados;
	String estadoActual="0";
	ArrayList<String>estadosFinales=new ArrayList<String>();
	ArrayList<String[]> defTransiciones=new ArrayList<String[]>();
	
	boolean stringAceptado = false;	
	HashMap<String, HashMap<String,String>> reglas=new HashMap<String, HashMap<String,String>>();
	//se usa como variable temporal solo para cargar estado y transicion;
	HashMap<String,String> transicion=new HashMap<String, String>();

	
	
	HashMap<String,ArrayList<String>>clausuras=new HashMap<String, ArrayList<String>>();
	HashMap<String,ArrayList<String>>TransicionEpsilosDirecta=new HashMap<String, ArrayList<String>>();
	HashMap<String,ArrayList<String>>TablaConversion=new HashMap<String, ArrayList<String>>();
	HashMap<String,ArrayList<String>>TablaConversionEtiquetada=new HashMap<String, ArrayList<String>>();
	
	
	
	
	public HashMap<String,ArrayList<String>> crearClausuras(){
		return null;		
	}
	public HashMap<String,ArrayList<String>> crearTablaTransicionEpsilosDirecta(){
		return null;		
	}
	public HashMap<String,ArrayList<String>> crearTablaConversion(){
		return null;		
	}
	public HashMap<String,ArrayList<String>>etiquetarTablaConversion(){
		return null;		
	}
	
	private ArrayList<String>limpiarRepetidos(ArrayList<String> listaRepe){
		ArrayList<String>aux=new ArrayList<String>();
		
		for(int i=0;i<listaRepe.size();i++) {
			if(!aux.contains(listaRepe.get(i))) {
				aux.add(listaRepe.get(i));
			}
		}
		Collections.sort(aux);
		return aux;
		
	}
	/*
	 * Si acepta antes de finalizar de recorrer todo el string que finalice
	 * */
	public void procesar(String w) throws FileNotFoundException, InterruptedException{
		String auxEstado;
		leerArchivoInput(".\\SRC\\INPUT\\INPUT1.txt");
		cargarReglas();
		
		for(int i=0;i<w.length();i++) {
			
			 if(esEstadoFinal(estadoActual)) {
				 stringAceptado=true;
				 i=w.length();
			 }else {
			     auxEstado=estadoActual;
			     estadoActual=aQueEstadoVoy(estadoActual,w.charAt(i));
			     
			     
			     if(estadoActual.equals("inexistente")) {
			    	 System.out.println("No existe transicion para ese estado,* "+auxEstado+" * fin del programa");
			    	 i=w.length();
			     }if(esEstadoFinal(estadoActual)){
			    	 
						 stringAceptado=true;
						 i=w.length();
			     }
			 }
					
		}
			if(stringAceptado) {
				System.out.println("¡El string fue aceptado por el autómata!");
			}else {
				System.out.println("El string NO fue aceptado por el autómata");
			}
		
		
	}
	
	
	private boolean esEstadoFinal(String estadoActual) {
		return estadosFinales.contains(estadoActual);
	}
	
	private String aQueEstadoVoy(String estadoAhora,char a) {
		String nuevoestado=null;
		try{
			nuevoestado=reglas.get(estadoAhora).get(String.valueOf(a));
		}catch(Exception e) {
			e.getCause();
		}
		if(nuevoestado==null) {
			nuevoestado="inexistente";
		}
		return nuevoestado;
		
	}
	
	private void crearEstructura(int lineaArchivo,String linea) {
		String[]aux;
		switch(lineaArchivo) {
		case 1: System.out.println("Alfabeto "+linea);linea+=", E";alfabeto = linea.split(", ");break; 
		case 2: System.out.println("Cantidad Estados "+linea);cantidadEstados=Integer.parseInt(linea);break;
		case 3: System.out.println("Estados finales "+linea); aux=linea.split(",");
		for(int i=0;i<aux.length;i++) {
			estadosFinales.add(aux[i].trim());			
		}
		break;
		default : System.out.println("Transiciones "+linea);cargarDefTransiciones(lineaArchivo-2,linea);break;
		
		}


	
    }
	
	private void cargarDefTransiciones(int i, String linea) {
		defTransiciones.add(linea.split(","));	

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
	private void cargarReglas() {
		String estadoaux;
		String caracterTemp;
		String estadoTemp;
		for(int i=0;i<defTransiciones.size();i++) {
			estadoaux=defTransiciones.get(i)[0];
			caracterTemp=defTransiciones.get(i)[1].substring(0,defTransiciones.get(i)[1].indexOf("->"));
			estadoTemp=defTransiciones.get(i)[1].substring(defTransiciones.get(i)[1].indexOf("->")+2,defTransiciones.get(i)[1].length());
		transicion=new HashMap<String, String>();
		transicion.put(caracterTemp.trim(), estadoTemp.trim());		
		reglas.put(estadoaux.trim(), transicion);
		
		}
	}

	

}
