package util;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
/**Clase que te sirve para escribir y para leer archivos
@author Antonio Martinez Cruz
@version 1.0
*/
public class ManejadorDeArchivos{
	/**Metodo que escribe un archivo dado un url y el texto a escribir
	@param url la url donde vamos a guardar el texto
	       texto texto donde guardaremos la url
	*/
	
	public static void escribe(String url,String texto){
		if(new File(url).exists())
			sobreEscribe(url,texto);
		else
			escribeAux(url,texto);
	}
	
	public static void escribeAux(String url,String texto){
		try{
			PrintWriter pw= new PrintWriter(new BufferedWriter(new FileWriter(new File(url))));
			pw.write(texto);
			pw.close();	
		}catch(Exception e){}		
	}
	
	public static void sobreEscribe(String url,String texto){
		try {
			Files.write(Paths.get(url), texto.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {}
	}
	
	/**Metodo que te recibe una url y te regresa un String con el texto que leiste*/
	public static String[] lee(String url){
	try{		
	String resultado="";	
	String texto="";	
	BufferedReader fr = new BufferedReader(new FileReader(new File(url)));
    while((texto=fr.readLine())!=null)
      	resultado+=texto+"\n";
	
	return resultado.split("\n");
	}catch(Exception e){}
	return null;
	}
}

