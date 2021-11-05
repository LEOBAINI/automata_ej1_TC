import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class GestorAutomata {
	
	String[] alfabeto;
	String[] estadosFinales;
	String[] tempdefTransiciones;
	ArrayList<String[]> defTransiciones=new ArrayList<String[]>();
	int cantidadEstados=0;
	ArrayList<Estado>estados;
	ArrayList<Transicion>transiciones;
	
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

	public void ejecutar() {
	
		
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
	
	
}
