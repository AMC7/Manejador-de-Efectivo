package util;
import java.util.Arrays;
public class MyString {
	    
    public static String str(Object o){
        return String.valueOf(o);
    }
	public static String str(int [] arreglo){
		return Arrays.toString(arreglo);
	}
    
}
