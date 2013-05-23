package adt;

import number.CompRational;

public class RBTNode<T extends java.lang.Comparable<T>>{
	public T data;
	protected String color;
	RBTNode<T> left, 
    		right, 
    		p = null;

    //color: Eingabe "black" oder "red"
    public RBTNode(T x, String color) {
    	this.data = x;
    	this.color = color;
    	this.left = new RBTNode<T>();
    	this.right = new RBTNode<T>();
    }
    
    //Konstruktor für Blätter
    public RBTNode(){
    	this.data = null;
    	this.color = "black";
    	this.left = null;
    	this.right = null;
    }
    
    public String toString() {
    	return "Knoteninhalt: " +  this.data + " " + this.color;
    }
}
