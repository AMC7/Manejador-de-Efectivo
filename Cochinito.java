import static util.Print.*;
import static util.ManejadorDeArchivos.*;
import static util.MyString.*;
import java.util.Scanner;
import util.Dupla;
import java.math.BigDecimal;
import util.Punto;
import java.text.NumberFormat;
import java.io.File;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
/**@version 1.0
   @author Antonio Martinez Cruz*/
public class Cochinito{
	private BigDecimal capital;
	private String nombre;
	private String archivo;
	private String archivoActivo;
	private String archivoPasivos;
	private Scanner scanner;
	
	public Cochinito(){
		scanner = new Scanner (System.in);
		nombre=scanner.nextLine();
		archivo="capital"+nombre+".txt";
		archivoPasivos="pasivos"+nombre+".txt";
		archivoActivo="activos"+nombre+".txt";
	}
	
	public String getArchivo(){
		return archivo;
	}
	public String getNombre(){
		return nombre;
	}
	
	public String toString(){
		return "Actualmente tienes "+NumberFormat.getCurrencyInstance().format(capital)+" pesos";
	}
	
	 public BigDecimal getCapital(){
		return capital;
	}
	 public void setCapital(){
		capital=new BigDecimal(scanner.nextLine());
		guarda();
		scanner.nextLine();
	}
	
	public void guarda(){
		escribeAux(archivo,String.valueOf(capital));
		p(this);
	}
	
	public void leer(){
		capital= new BigDecimal(lee("capital"+nombre+".txt")[0]);
		p(this);
	}
	
	public void auxSuma(BigDecimal temp){
		capital=capital.add(temp);
		cls();
		guarda();
		p("presiona enter");
		scanner.nextLine();
	}
	
	public void sumaActivosPasivos(boolean booleano){
		ArrayList<BigDecimal> lista = new ArrayList<BigDecimal>();
		 String [] temp;
		if(booleano)
			temp = lee(archivoActivo);	
		else
		    temp = lee(archivoPasivos);
		
		for(String a:temp)
			lista.add(new BigDecimal(a.split(" ")[1]));
		
		BigDecimal total = new BigDecimal("0");
		for(BigDecimal numero:lista)
			total=total.add(numero);
		
		total = booleano?total:total.negate(); 
		auxSuma(total);
	}
	public void creaActivoPasivo(boolean booleano){
		String act="";
		while(true){
		   String temp = scanner.nextLine();
		   if(temp.equals(""))
			   break;
		   else
			   act+=temp+"\n";
		}
		
		if(booleano)
			escribe(archivoActivo,act);
		else
			escribe(archivoPasivos,act);
	}
	public void suma(){
		auxSuma(new BigDecimal(scanner.nextLine()));
	}
	
	public void resta(){
		auxSuma(new BigDecimal(scanner.nextLine()).negate());
	}
	public int getOpcion(){
		int opcion =scanner.nextInt();
		scanner.nextLine();
		cls();
		return opcion;
	}
	public static void cls(){
		try{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}catch(Exception e){}
	}
	
	
	public static void main(String [] args){
	p("Bienvenido a cochinito by Toritsman");
	p("Escribe tu nombre");
	Cochinito cochinito = new Cochinito();
	while(true){
		cls();
		p("Selecciona el numero de la opcion que deseas ejecutar");
		p("\n0:Consulta tu saldo");
		p("1:Crea un nuevo cochinito\n");
		p("2:Sumale a cochinito");
		p("3:Quitale al cochinito\n");
		p("4:Agrega un pasivo");
		p("5:Agrega un activo\n");
		p("6:Restale los pasivos");
		p("7:Sumale los activos");
		p("8:Salir");
		switch(cochinito.getOpcion()){
				case 0:
					cochinito.leer();
					p("presiona enter");
					cochinito.scanner.nextLine();
					break;
				case 1:
					p("Cual es tu capital?");
					cochinito.setCapital();
					break;
				case 2:
					cochinito.leer();
					p("Cuanto le meteras al cochinito?");
					cochinito.suma();
					break;
				case 3:
					cochinito.leer();
					p("Cuanto le quitaras al cochinito?");
					cochinito.resta();
					break;
				case 4:
					p("Ingresa el nombre del pasivo a Agregar");
					cochinito.creaActivoPasivo(false);
					break;
				case 5:
					p("Ingresa el nombre del activo a Agregar");
					cochinito.creaActivoPasivo(true)	;
					break;
				case 6:
					cochinito.leer();
					cochinito.sumaActivosPasivos(false);
					break;
				case 7:
					cochinito.leer();
					cochinito.sumaActivosPasivos(true);
					break;
				case 8:
					return;
		}
	  }
	}
}
