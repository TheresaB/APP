package adt;
import number.CompRational;

//Listenelemente für Binärbaum-Liste
public class BSTNode{
	CompRational data;
    BSTNode left = null, 
    		right = null, 
    		p = null;

    public BSTNode(CompRational x) {
    	this.data = x;
    }
    
    public String toString() {
    	return "Knoteninhalt: " +  this.data;
    }
}