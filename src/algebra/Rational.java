package algebra;
public class Rational{
	private long zaehler;
	private long nenner;

	public Rational(long z, long n){
		this.setZaehler(z);
		this.setNenner(n);
	}

	public static long ggT(long a, long b){
		if(a == 0)
			return 1;
		if(a < 0)
			a *= -1;
		if(b < 0)
			b *= -1;
		while(b != 0){
			if(a > b)
				a -= b;
			else
				b -= a;
		}
		return a;
	}

	public Rational gekuerzt(){
		long x = ggT(this.getZaehler(), this.getNenner());
		return new Rational(this.getZaehler()/x, this.getNenner()/x);
	}


	public void kuerzen(){
		long x = ggT(this.getZaehler(), this.getNenner());
		this.setZaehler(this.getZaehler()/x);
		this.setNenner(this.getNenner()/x);
	}

	public String toString(){
		Rational a = this.gekuerzt();
		this.setZaehler(a.getZaehler());
		this.setNenner(a.getNenner());
		if(this.getNenner() == 1)
			return this.getZaehler() + "";
		if(this.getZaehler() == 0)
			return "0";
		return this.getZaehler() + "/" + this.getNenner();
	}

	public boolean equals(Rational a){
		if(a == this)
			return true;
		if(a == null)
			return false;
		Rational b = this.gekuerzt();
		Rational a2 = a.gekuerzt();
		return b.getZaehler() == a2.getZaehler() && b.getNenner() == a2.getNenner();
	}

	//this + a
	public void addTo(Rational a){
		this.setNenner(this.getNenner() * a.getNenner());
		//this.nenner wurde verändert, daher /a.nenner, weil der alte gebraucht wird
		this.setZaehler(this.getZaehler()*a.getNenner() + this.getNenner()*a.getZaehler()/a.getNenner());
		this.kuerzen();
	}

	//this - a
	public void subFrom(Rational a){
		this.setNenner(this.getNenner() * a.getNenner());
		this.setZaehler(this.getZaehler()*a.getNenner() - this.getNenner()*a.getZaehler()/a.getNenner());
	}

	public static Rational mult(Rational a, Rational b){
		return new Rational(a.getZaehler()*b.getZaehler(), a.getNenner()*b.getNenner());
	}

	public static Rational div(Rational a, Rational b){
		return new Rational(a.getZaehler()*b.getNenner(), a.getNenner()*b.getZaehler());
	}

	public long getZaehler() {
		return zaehler;
	}

	public void setZaehler(long zaehler) {
		this.zaehler = zaehler;
	}

	public long getNenner() {
		return nenner;
	}

	public void setNenner(long nenner) {
		this.nenner = nenner;
	}
}