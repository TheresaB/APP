package number;
import algebra.Rational;

public class NormRational extends Rational{
	//Konstruktor
	public NormRational(long z, long n){
		super(z, n);
		this.normieren();
	}
	
	public void normieren(){
		this.kuerzen();
		//Nenner immer positiv:
		if(this.getNenner() < 0){
			this.setZaehler(this.getZaehler() * -1);
			this.setNenner(this.getNenner() * -1);
		}
	}
	
	public static NormRational add(NormRational a, NormRational b){
		return new NormRational(b.getZaehler() * a.getNenner() + b.getNenner() * a.getZaehler(), b.getNenner() * a.getNenner());
	}
	
	public static NormRational sub(NormRational a, NormRational b){
		return new NormRational(a.getZaehler() * b.getNenner() - a.getNenner() * b.getZaehler(), b.getNenner() * a.getNenner());
	}
	
	public static NormRational mult(NormRational a, NormRational b){
		return new NormRational(a.getZaehler()*b.getZaehler(), a.getNenner()*b.getNenner());
	}
	
	public static NormRational div(NormRational a, NormRational b){
		return new NormRational(a.getZaehler()*b.getNenner(), a.getNenner()*b.getZaehler());
	}
	
	public static NormRational toNR(char c){
		String s = "" + c;
		return new NormRational(Long.parseLong(s), 1);
	}
}