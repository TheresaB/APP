package run;

import number.CompRational;
import adt.RedBlackTree;

public class TestRBT {
	public static void main(String[] args) {
		if(args.length % 2 != 0){
			System.out.println("keine gerade Anzahl!");
			return;
		}
		
		RedBlackTree bst = new RedBlackTree();
		
		int i = 0;
		while(i < args.length){
			//Aus Zahlen CompRational erstellen
			CompRational c = new CompRational(Long.parseLong(args[i]), Long.parseLong(args[i+1]));
			try{
				bst.insert(c);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage() + " " + c);
				return;
			}
			i += 2;
		}
		System.out.println(bst);
	}
}
