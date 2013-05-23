package adt;

import java.util.Iterator;

import adt.RBTNode;
import adt.IteratorIn;
import adt.IteratorRe;

public class RedBlackTree<T extends java.lang.Comparable<T>> extends java.util.AbstractCollection<RedBlackTree<T>>{
	public RBTNode<T> list;

	public RedBlackTree(){
		list = null;
	}
	
	public RBTNode<T> search(T k){ 
		return search(list, k); 
	}
	
    public RBTNode<T> search(RBTNode<T> x, T k) {
    	//x.left/x.right .data == null: es kommen nur noch Blätter, Knoten ist nicht vorhanden
		if(x == null || x.data == null) 
		    return null;
		if(x.data.compareTo(k) == 0)
			return x;
		//k < x.data: im linken Teilbaum weitersuchen
		if ( k.compareTo(x.data) < 0) 
		    return search(x.left, k);
		//sonst im rechten
		else 
		    return search(x.right, k);
    }
	
    //Linksrotation
    private void rotateleft(RBTNode<T> x){
    	RBTNode<T> xp = x.p;
    	RBTNode<T> y = x.right;
    	x.right = y.left;
    	y.left.p = y;
    	y.left = x;
    	x.p = y;
    	y.p = xp;
    }
    
    //Rechtsrotation
    private void rotateright(RBTNode<T> y){
    	RBTNode<T> yp = y.p;
    	RBTNode<T> x = y.left;
    	y.left = x.right;
    	x.right.p = x;
    	x.right = y;
    	y.p = x;
    	x.p = yp;
    }
    
	public void insert(T c){
		//erst überprüfen, ob Knoten schon vorhanden
		RBTNode<T> a = search(c);
		if(a != null)
			throw new IllegalArgumentException("Der Knoten ist schon vorhanden!");
		
		RBTNode<T> z = new RBTNode<T>(c, "red");
		RBTNode<T> x = list;
		RBTNode<T> y = null;
    	
		//solange suchen, bis Blatt gefunden wird
    	while (x != null && x.data != null) {
    		//Vaterknoten merken
    	    y = x;
    	    if ( z.data.compareTo(x.data) < 0)
    	    	x = x.left;
    	    else 
    	    	x = x.right;
    	}
    	//Referenzen umbiegen
    	z.p = y;
    	if (y == null)
    	    list = z;
    	else if(z.data.compareTo(y.data) < 0)
    	    y.left = z;
    	else
    		y.right = z;
    	
    	//z ist problematischer Knoten:
    	repareInsert(z);
	}
	
	//rekursive Methode zum reparieren nach dem Einfügen eines neuen Knotens
	private void repareInsert(RBTNode<T> z){
    	RBTNode<T> onkel = null;
    	
		if(z.p == null || z.p.color == "black")
    		return;
		
    	if(z.p.color == "red" && z.p.p == null){
    		z.p.color = "black";
    		return;
    	}
    	else{
    		
			if(z.p.p.data.compareTo(z.p.data) > 0)
				onkel = z.p.p.right;
			else
				onkel = z.p.p.left;
				
    		if(z.p.color == "red" && onkel.color == "red"){
	    		z.p.p.color = "red";
	    		z.p.color = "black";
	    		onkel.color = "black";
	    		//erneute Reparatur mit Großvater als problematischem Knoten
	    		repareInsert(z.p.p);
	    		return;
    		}
    	}
    	if(z.p.color == "red" && onkel.color == "black"){
	    	if(z.p.data.compareTo(z.p.p.data) < 0 && z.data.compareTo(z.p.data) > 0){
				rotateleft(z.p);
	   			repareInsert(z.left);
	   		}
	   		else if(z.p.data.compareTo(z.p.p.data) > 0 && z.data.compareTo(z.p.data) < 0){
	   			rotateright(z.p);
	   			repareInsert(z);
	   		}
	   		else if(z.p.data.compareTo(z.p.p.data) < 0 && z.data.compareTo(z.p.data) < 0){
	   			String color = z.p.color;
	   			z.p.color = z.p.p.color;
	   			z.p.p.color = color;
	   			rotateright(z.p.p);
	   		}
	   		else{
	   			String color = z.p.color;
	   			z.p.color = z.p.p.color;
	   			z.p.p.color = color;
	   			rotateleft(z.p.p);
	   		}
    	}
	}
	
	public void remove(T x){
		//ist x nicht vorhanden, kann es nicht entfernt werden
		RBTNode<T> a = search(x);
		if(a == null)
			throw new IllegalArgumentException("Knoten nicht gefunden!");
		
		RBTNode<T> z = a.p;
		String acolor = a.color;
		
		//a hat nur ein Blatt als Kind: Teilbaum hochziehen
		if(a.left != null && a.right == null){
			a.left.p = z;
			if(z == null){
				list = a.left;
				if(acolor == "black"){
					repareRemove(list);
				}
			}
			else if(z.data.compareTo(a.left.data) < 0){
				z.left = a.left;
				if(acolor == "black"){
					repareRemove(z.left);
				}
			}
			else{
				z.right = a.left;
				if(acolor == "black"){
					repareRemove(z.right);
				}
			}
		}
		if(a.left == null && a.right != null){
			a.right.p = z;
			if(z == null){
				list = a.right;
				if(acolor == "black"){
					repareRemove(list);
				}
			}
			else if(z.data.compareTo(a.right.data) < 0){
				z.left = a.right;
				if(acolor == "black"){
					repareRemove(z.left);
				}
			}
			else{
				z.right = a.right;
				if(acolor == "black"){
					repareRemove(z.right);
				}
			}
		}
		
		//Kinder von a sind Blätter: a wird durch Blatt ersetzt
		if(a.left.data == null && a.right.data == null){
			if(z == null)
				list = a.left;
			else if(z.data.compareTo(a.data) > 0)
				z.left = a.left;
			else
				z.right = a.left;
		}
		
		//a hat zwei Kinder: Wert des Minimumknotens y1 des rechten Teilbaums in Wert von a, y1 wird gelöscht 
		else if(a.left != null && a.right != null){
			RBTNode<T> aright = a.right;
			RBTNode<T> y2 = aright;
			RBTNode<T> y1 = a;
			//Suche nach Minimumknoten y1
			while(y2.data != null){
				y1 = y2;
				y2 = y2.left;
			}
			T adata = y1.data;
			remove(y1.data);
			a.data = adata;
		}
	}
	
	//rekursive Methode zum reparieren nach dem Löschen eines Knotens
	public void repareRemove(RBTNode<T> z){
		if(z == list)
			return;
		if(z.color == "red"){
			z.color = "black";
			return;
		}
		//problematischer Knoten ist linkes Kind:
		if(z.data.compareTo(z.p.data) < 0){
			if(z.p.right.color == "red"){
				z.p.color = "red";
				z.p.right.color = "black";
				rotateleft(z.p);
			}
			if(z.p.right.color == "black" && z.p.right.right.color == "black" && z.p.right.left.color == "black"){
				z.p.right.color = "red";
				repareRemove(z.p);
			}
			if(z.p.right.color == "black" && z.p.right.right.color == "black" && z.p.right.left.color == "red"){
				z.p.right.color = "red";
				z.p.right.left.color = "black";
				rotateright(z.p.right);
			}
			if(z.p.right.color == "black" && z.p.right.right.color == "red" && z.p.right.left.color == "black"){
				String vcolor = z.p.color;
				z.p.color = z.p.right.color;
				z.p.right.color = vcolor;
				z.p.right.right.color = "black";
				rotateleft(z.p);
			}
		}
		//problematischer Knoten ist rechtes Kind:
		else{
			if(z.p.left.color == "red"){
				z.p.color = "red";
				z.p.left.color = "black";
				rotateright(z.p);
			}
			if(z.p.left.color == "black" && z.p.left.left.color == "black" && z.p.left.right.color == "black"){
				z.p.left.color = "red";
				repareRemove(z.p);
			}
			if(z.p.left.color == "black" && z.p.left.left.color == "black" && z.p.left.right.color == "red"){
				z.p.left.color = "red";
				z.p.left.right.color = "black";
				rotateleft(z.p.left);
			}
			if(z.p.left.color == "black" && z.p.left.left.color == "red" && z.p.left.right.color == "black"){
				String vcolor = z.p.color;
				z.p.color = z.p.left.color;
				z.p.left.color = vcolor;
				z.p.left.left.color = "black";
				rotateright(z.p);
			}
		}
		
	}
	
	//läuft einen Pfad bis zum Ende und zählt die schwarzen Knoten
	public int tiefe(){
		int tiefe = 0;
		RBTNode<T> y2 = list;
		if(list.color == "black")
			tiefe++;
		while(y2.data != null){
			y2 = y2.left;
			if(y2.color == "black")
				tiefe++;
		}
		return tiefe;
	}
	
	//versucht Knoten einzufügen, gibt zurück ob etwas geändert wurde
	public boolean add(RBTNode<T> rbt){
		boolean b;
		try{
			insert(rbt.data);
			b = true;
		}
		catch(IllegalArgumentException e){
			b = false;
		}
		return b;
	}
	
	public int size(){
		return count(list, 0);
	}
	
	//rekursiv Knoten zählen
	private int count(RBTNode<T> x, int i){
		//linker Teilbaum + rechter Teilbaum + aktueller Knoten
		if(x.data != null) {
		    i = count(x.left, i);
		    i = count(x.right, i);
		    i++;
		}
		return i;
	}
	
	//implementierte Methode als in-order
	public Iterator<RedBlackTree<T>> iterator(){	//...
		Iterator<RedBlackTree<T>> x = new IteratorIn<T>(this);
		return x;
	}
	
	//in-order-Iterator
	public IteratorIn<T> iteratorIn(){
		IteratorIn<T> x = new IteratorIn<T>(this);
		return x;
	}
	
	//reverse-in-order-Iterator
	public IteratorRe<T> iteratorRe(){
		IteratorRe<T> x = new IteratorRe<T>(this);
		return x;
	}
	
	//rekursiv rbt ausgeben
	public String inorder(RBTNode<T> x) {
		if(x != null) {
		    inorder(x.left);
		    if(x.p == null && x.data != null)
		    	System.out.println(x + " Wurzel");
		    else
		    	System.out.println(x + " Vater: " + x.p);
		    inorder(x.right);
		}
		return "";
	}
	
	//Aufruf der Ausgabe
	public String toString(){
		String s = inorder(this.list);
		return s;
	}
}