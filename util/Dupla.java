package util;
import static util.MyString.*;
/**@version 1.0
   @author Antonio Martinez Cruz*/
public class Dupla{
	public Object fst;
	public Object snd;
	
	public Dupla(Object fst,Object snd){
		this.fst=fst;
		this.snd=snd;
	}
	public String toString(){
		return "("+str(fst)+","+str(snd)+")";
	}

}
