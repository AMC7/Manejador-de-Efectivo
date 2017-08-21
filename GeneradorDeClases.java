import static util.ManejadorDeArchivos.*;
import static util.Print.*;
import util.Dupla;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
public class GeneradorDeClases{
	static String[] estaticos={"util.Print","util.ManejadorDeArchivos","util.MyString"};
	static String[] imports={"util.Dupla","util.Punto"};
	static String nombreClase;
	static ArrayList<Dupla> listaMetodos= new ArrayList<Dupla>();
	public static String importa(String a){
		return "import "+a+";\n";
	}
	public static String importaS(String a){
		return "import static "+a+".*;\n";
	}
	
	public static String importaMultiple(String[]a,boolean caso){
		String cadena="";
		for(String s:a)
			cadena+=caso?importaS(s):importa(s);
		return cadena;
	}
	public static String addImports(String[]a,String[]b){
		return importaMultiple(a,true)+importaMultiple(b,false);
	}
	
	public static String addHeading(){
	 return addImports(estaticos,imports)
			+"/**@version 1.0\n"
	        +"   @author Antonio Martinez Cruz*/\n"
	        +"public class "+nombreClase+"{\n";
	 }
	 public static String termina(){
		 return "\tpublic static void main(String [] args){\n\t}\n}\n";
	 }
	
	public static  String ponAtributo(Dupla d){
		return "\tprivate "+d.fst+" "+d.snd+";\n";
	}
	public static String setAtributos(){
		String s="";
		for(Dupla d:listaMetodos)
			s+=ponAtributo(d);
		return s+"\n";
		
	}
	public static String setAtribute(Dupla d){
		return "\t\tthis."+d.snd+"="+d.snd+";\n";
	}
	
	public static String upper(String prueba){
		return prueba.substring(0,1).toUpperCase()+prueba.substring(1);
	}
	public static String hazGetter(Dupla d){
		String cadena="\t public "+d.fst+" get";
		cadena+=upper((String)d.snd)+"(){\n";
		cadena+="\t\treturn "+d.snd+";\n";
		cadena+="\t}\n";
		return cadena;
	}
	
	public static String hazSetter(Dupla d){
		String cadena="\t public void set";
		cadena+=upper((String)d.snd)+"(){\n";
		cadena+=setAtribute(d);
		cadena+="\t}\n";
		return cadena;
	}
	
	public static String setContructor(){
		String cadena= "\tpublic "+nombreClase+"(){\n\t}\n\n";
		cadena+="\tpublic "+nombreClase+"(";
		int i=listaMetodos.size();
		for(int j=0;j<i-1;j++){
			Dupla a=listaMetodos.get(j);
			cadena+=a.fst+" "+a.snd+",";
		}
		
		Dupla r=listaMetodos.get(i-1);
		cadena+=r.fst+" "+r.snd+"){\n";
		
		for(Dupla a:listaMetodos)
			cadena+=setAtribute(a);
		cadena+="\t}\n\n";
		
		for(Dupla a:listaMetodos){
			cadena+=hazGetter(a);
			cadena+=hazSetter(a);
		}	
		return cadena;
	}
	public static void lee(){
	Scanner scanner = new Scanner(System.in);
	 while(true){
		String temp=scanner.nextLine();
		if(temp.equals(""))
			break;		
		String [] a=temp.split(" ");
		listaMetodos.add(new Dupla(a[0],a[1]));
	 }	
	}
    public static void main(String[]args){
	try{
     BufferedReader br= new BufferedReader(new InputStreamReader(System.in));	 
	 nombreClase=br.readLine();
	 lee();
	 String texto=addHeading();
	 texto+=setAtributos();
	 texto+=setContructor();
	 texto+=termina();
	 escribe(nombreClase+".java",texto);
	 }catch(Exception e){}
  }
}