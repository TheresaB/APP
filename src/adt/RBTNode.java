package adt;

import number.CompRational;

public class RBTNode {
	protected CompRational data;
	protected String color;
	RBTNode left, 
    		right, 
    		p = null;

    //color: Eingabe "black" oder "red"
    public RBTNode(CompRational x, String color) {
    	this.data = x;
    	this.color = color;
    	this.left = new RBTNode();
    	this.right = new RBTNode();
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
