package util;

/**@version 1.0
   @author Antonio Martinez Cruz*/
public class Punto{
		private int x;
		private int y;
		
		public Punto(){
			
		}
		 
		public Punto(int x,int y){
		   this.x=x;
		   this.y=y;
		}
		
		@Override
		public String toString(){
			return "("+String.valueOf(getX())+","+String.valueOf(getY())+")";
		}
		public void setX(int x){
			this.x=x;
		}
		
		public void setY(int y){
			this.y=y;
		}
		
		public void addY(int aSumar){
			y+=aSumar;
		}
		public void addX(int aSumar){
			x+=aSumar;
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
			   
		public boolean equals(Punto otro){
			return getX()==otro.getX()&&getY()==otro.getY();
		}
			   
	public static void main(String [] args){
	}
}
