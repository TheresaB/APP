package run;
import adt.BSTNode;
import adt.BinarySearchTree;
import number.CompRational;

public class TestBST{
	public static void main(String[] args){
		if(args.length % 2 != 0){
			System.out.println("keine gerade Anzahl!");
			return;
		}
		
		BinarySearchTree bst = new BinarySearchTree();
		//erste Liste, Abbruch bei zwei Nullen oder Ende von args
		int i = 0;
		while(i < args.length){
			//zwei Zahlen einlesen, zur Überprüfung
			char a = args[i].charAt(0);
			char b = args[i+1].charAt(0);
			//Abbruch bei zwei Nullen
			if((a == '0' && b == '0'))
				break;
			//Aus Zahlen CompRational erstellen
			CompRational c = new CompRational(Long.parseLong(args[i]), Long.parseLong(args[i+1]));
			try{
				bst.insert(c);
				System.out.println(bst + "\n");
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage() + " " + c);
				return;
			}
			i += 2;
		}
		i += 2;
		//zweite Liste
		for(; i < args.length; i += 2){
			CompRational c = new CompRational(Long.parseLong(args[i]), Long.parseLong(args[i+1]));
			BSTNode test = bst.search(c);
			if(test != null){
				try{
					bst.remove(c);
				}
				catch(IllegalArgumentException e){}
			}
		}
		System.out.println(bst);
	}
}