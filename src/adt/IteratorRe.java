package adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorRe<T extends java.lang.Comparable<T>> implements Iterator<RedBlackTree<T>> {
	private RedBlackTree<T> rbt;
	private int i, laenge;
	private RBTNode<T> actual, last, parent, child;
	
	//Konstruktor
	public IteratorRe(RedBlackTree<T> rbt){
		this.rbt = rbt;
		i = 0;
		laenge = rbt.size();
		//größter Wert zuerst, wird in actual gespeichert
		actual = rbt.list;
		while(actual.right.data != null){
			actual = actual.right;
		}
		parent = actual.p;
	}
	
	public boolean hasNext(){
		return i < laenge;
	}
	
	public void remove(){
		//versuchen, den letzten zu löschen
		try{
			rbt.remove(last.data);
		}
		catch(IllegalArgumentException e){
			throw new NoSuchElementException("Error");
		}
	}
	
	public RedBlackTree<T> next(){
		last = actual;
		//wenn es noch linke Kinder gibt, die zuerst ausgeben
		if(last.left.data != null){
			parent = actual.p;
			actual = last.left;
			while(actual.right.data != null){
				actual = actual.right;
				parent = actual.p;
			}
		}
		else{
			//zurück zum nächst größeren
			child = last;
			parent = last.p;
			while(parent != null && child == parent.left){
				child = parent;
				parent = parent.p;
			}
			actual = parent;
		}
		//i erhöhen für hasNext() 
		i++;
		RedBlackTree<T> rbt2 = new RedBlackTree<T>();
		rbt2.list = last;
		return rbt2;
	}
}
