package run;
import java.util.Stack;
import java.util.EmptyStackException;

import number.NormRational;

public class RPN{
	public static void main(String[] args){
		try{
			NormRational x = rpn(args[0]);
			if(x.getNenner() == 0)
				return;
			System.out.println("Ergebnis: "+ x);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static NormRational rpn(String s){
		//neuen Stack erstellen
		Stack<NormRational> stack = new Stack<NormRational>();
		
		//Zeichen einzeln einlesen
		for(int i = 0; i < s.length(); i++){
			//Ziffern auf Stack legen
			if(Character.isDigit(s.charAt(i))){
				stack.push(NormRational.toNR(s.charAt(i)));
			}
			else{
				//bei Operator: zwei Ziffern vom Stack holen
				try{
					NormRational y = (NormRational) stack.pop();
					NormRational x = (NormRational) stack.pop();
				
					//Operation ausführen
					if(s.charAt(i) == '+')
						stack.push(NormRational.add(x, y));
					else if(s.charAt(i) == '-')
						stack.push(NormRational.sub(x, y));
					else if(s.charAt(i) == '*')
						stack.push(NormRational.mult(x, y));
					else if(s.charAt(i) == '/')
						stack.push(NormRational.div(x, y));
					//kein passender Operator: Exception
					else
						throw new IllegalArgumentException("Keine Zahl bzw. kein Operator!");
				}
				catch(EmptyStackException e){
					System.out.println("Zu wenige Ziffern: " + e.getMessage());
					return new NormRational(0, 0);
				}
				
			}
		}
		//Ergebnis liegt oben auf Stack
		return (NormRational) stack.pop();
	}
}