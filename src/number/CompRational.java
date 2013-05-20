package number;
import algebra.Rational;

public class CompRational extends NormRational implements java.lang.Comparable<CompRational>{	
	public CompRational(long x, long y){
		//NormRational-Konstruktor
		super(x, y);
	}
		
	//Vergleich
	public int compareTo(CompRational x){
		//Differenz wird zurückgegeben -> 0 bei Gleichheit
		return (int)(this.getZaehler() * x.getNenner() - this.getNenner() * x.getZaehler());
	}

	public boolean equals(CompRational a){
		if(a == this)
			return true;
		if(a == null)
			return false;
		Rational b = this.gekuerzt();
		Rational a2 = a.gekuerzt();
		return b.getZaehler() == a2.getZaehler() && b.getNenner() == a2.getNenner();
	}
}