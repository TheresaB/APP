package run;

import number.CompRational;
import adt.IteratorIn;
import adt.IteratorRe;
import adt.RedBlackTree;

public class TestRBT {
	public static void main(String[] args) {
		if(args.length % 2 != 0){
			System.out.println("keine gerade Anzahl!");
			return;
		}
		
		
		//CompRational
		RedBlackTree<CompRational> rbt = new RedBlackTree<CompRational>();
		
		int i = 0;
		while(i < args.length/3*2){
			//Aus Zahlen CompRational erstellen
			CompRational c = new CompRational(Long.parseLong(args[i]), Long.parseLong(args[i+1]));
			try{
				rbt.insert(c);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage() + " " + c);
				return;
			}
			i += 2;
		}
		
		int tiefe = rbt.tiefe();
		System.out.println("Schwarztiefe: " + tiefe);
		
		IteratorIn<CompRational> in = rbt.iteratorIn();
		System.out.println("\nin-order-Reihenfolge:");
		while(in.hasNext()){
			RedBlackTree<CompRational> rbt2 = in.next();
			System.out.println(rbt2.list);
		}
		
		IteratorRe<CompRational> re = rbt.iteratorRe();
		System.out.println("\nreverse-in-order-Reihenfolge:");
		while(re.hasNext()){
			RedBlackTree<CompRational> rbt2 = re.next();
			System.out.println(rbt2.list);
		}
		
		IteratorIn<CompRational> in2 = rbt.iteratorIn();
		RedBlackTree<CompRational> rbt3 = in2.next();
		while(rbt3.list.data.compareTo(new CompRational(1, 1)) < 0){
			in2.remove();
			rbt3 = in2.next();
		}
		
		System.out.println("\nBaum ohne Knoten < 1:");
		System.out.println(rbt);
		
		
		//Integer
		RedBlackTree<Integer> rbt1 = new RedBlackTree<Integer>();
		
		while(i < args.length){
			int c = Integer.parseInt(args[i]);
			try{
				rbt1.insert(c);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage() + " " + c);
				return;
			}
			i++;
		}
		
		int tiefe1 = rbt1.tiefe();
		System.out.println("Schwarztiefe: " + tiefe1);
		
		IteratorIn<Integer> ini = rbt1.iteratorIn();
		System.out.println("\nin-order-Reihenfolge:");
		while(ini.hasNext()){
			RedBlackTree<Integer> rbt2 = ini.next();
			System.out.println(rbt2.list);
		}
		
		IteratorRe<Integer> rei = rbt1.iteratorRe();
		System.out.println("\nreverse-in-order-Reihenfolge:");
		while(rei.hasNext()){
			RedBlackTree<Integer> rbt2 = rei.next();
			System.out.println(rbt2.list);
		}
		
		IteratorIn<Integer> ini2 = rbt1.iteratorIn();
		RedBlackTree<Integer> rbt4 = ini2.next();
		while(rbt4.list.data.compareTo(1) < 0){
			ini2.remove();
			rbt4 = ini2.next();
		}
		
		System.out.println("\nBaum ohne Knoten < 1:");
		System.out.println(rbt1);
	}	
}
