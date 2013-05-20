package algebra;

public class Algebra{
	public static Matrix gaussJordan(Matrix a, Matrix b){
		//überprüft ob Matri quadratisch, Matrix und Vektor gleiche Zeilenanzahl,...
		//...ist b überhaupt ein Vektor
		if(a.getM() != a.getN() || a.getM() != b.getM() || b.getN() != 1){
			throw new IllegalArgumentException("Falsche Dimensionen.");
		}
		
		//1
		Matrix x = new Matrix(b.getM(), b.getN());
		Matrix aa = a.clone();
		Matrix bb = b.clone();
		Matrix c = new Matrix(a.getM(), 1);
		
		//2
		for(int k = 0; k < a.getN(); k++){
			//2a
			Rational l = new Rational(0, 1);
			int lz = 0;
			for(int kk = k; kk < aa.getN(); kk++){
				//l wird auf gleichen Nenner gebracht, um Zähler vergleichen zu können
				if(Math.abs(aa.getV(kk, k).getZaehler()) > Math.abs(l.getZaehler()*(aa.getV(kk, k).getNenner()/l.getNenner()))){
					l = aa.getV(kk, k);
					//Zeile merken fürs Vertauschen
					lz = kk;
				}
			}
			
			//2b
			if(l.getZaehler() == 0 && l.getNenner() == 1){
				throw new IllegalArgumentException("Nicht lösbar.");
			}
			
			//2c
			if(k != lz){
				aa.vertauscheZeile(k, lz);
				bb.vertauscheZeile(k, lz);
			}
			
			//2d
			for(int i = 0; i < c.getM(); i++){
				c.setV(i, 0, Rational.div(aa.getV(i, k), aa.getV(k, k)));
			}
			
			for(int i = 0; i < c.getM(); i++){
				for(int j = 0; j < aa.getN(); j++){
					if(i != k && j == k){
						aa.setV(i, j, new Rational(0, 1));
					}
					if(j > k && i > k){
						aa.getV(i, j).subFrom(Rational.mult(aa.getV(k, j), c.getV(i, 0)));
					}
				}
			}
			
			//2e
			for(int i = 0; i < bb.getM(); i++){
				if(i != k)
					bb.getV(i, 0).subFrom(Rational.mult(bb.getV(k, 0), c.getV(i, 0)));
			}
		}
		//3
		for(int kk = 0; kk < x.getM(); kk++){
			x.setV(kk, 0, Rational.div(bb.getV(kk, 0), aa.getV(kk, kk)));
		}
		
		return x;
	}
}