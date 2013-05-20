package adt;
import number.CompRational;

//wird als Liste realisiert
public class BinarySearchTree{
	//Anfang der Liste
	BSTNode list = null;
	
	public BSTNode search(CompRational k){ 
		return search(list, k); 
	}
	
    public BSTNode search(BSTNode x, CompRational k) {
    	//x == null: Knoten ist nicht vorhanden, also wird null zurückgegeben
		if ( x == null || x.data.compareTo(k) == 0) 
		    return x;
		//k < x.data: im linken Teilbaum weitersuchen
		if ( k.compareTo(x.data) < 0) 
		    return search(x.left, k);
		//sonst im rechten
		else 
		    return search(x.right, k);
    }
	
	public void insert(CompRational c){
		//erst überprüfen, ob Knoten schon vorhanden
		BSTNode a = search(c);
		if(a != null)
			throw new IllegalArgumentException("Der Knoten ist schon vorhanden!");
		
		BSTNode z = new BSTNode(c);
		BSTNode x = list;
    	BSTNode y = null;
    	
    	while ( x != null) {
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
    	else if ( z.data.compareTo(y.data) < 0)
    	    y.left = z;
    	else y.right = z;
	}
	
	public void remove(CompRational x){
		//ist x nicht vorhanden, kann es nicht entfernt werden
		BSTNode a = search(x);
		if(a == null)
			throw new IllegalArgumentException("Knoten nicht gefunden!");
		
		BSTNode z = a.p;
		
		//a hat nur ein Kind: Teilbaum hochziehen
		if(a.left != null && a.right == null){
			if(z == null)
				list = a.left;
			else if(z.data.compareTo(a.left.data) < 0)
				z.left = a.left;
			else
				z.right = a.left;
			a.left.p = z;
		}
		if(a.left == null && a.right != null){
			if(z == null)
				list = a.right;
			else if(z.data.compareTo(a.right.data) < 0)
				z.left = a.right;
			else
				z.right = a.right;
			a.right.p = z;
		}
		
		//a ist Blatt
		if(a.left == null && a.right == null){
			if(z == null)
				list = null;
			else if(z.data.compareTo(a.data) < 0)
				z.left = null;
			else
				z.right = null;
		}
		
		//a hat zwei Kinder: Minimumknoten des rechten Teilbaums an Stelle von a
		if(a.left != null && a.right != null){
			BSTNode aright = a.right;
			BSTNode aleft = a.left;
			BSTNode y2 = aright;
			BSTNode y1 = a;
			//Suche nach Minimumknoten y1
			while(y2 != null){
				y1 = y2;
				y2 = y2.left;
			}
			if(z == null)
				list = y1;
			else if(z.data.compareTo(a.data) < 0)
				z.left = y1;
			else
				z.right = y1;
			y1.p = z;
			if(aright != y1)
				y1.right = aright;
			if(aright != null)
				aright.p = y1;
			if(aleft != null)
				aleft.p = y1;
			y1.left = aleft;
		}
	}
	
	//rekursiv bst ausgeben
	public static String inorder(BSTNode x) {
		if ( x!= null) {
		    inorder(x.left);
		    System.out.println(x.data);
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
