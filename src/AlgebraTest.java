import algebra.Algebra;
import algebra.Matrix;
import algebra.Rational;

public class AlgebraTest{
	public static void main(String[] args){
		Matrix x = new Matrix(3, 3);
		x.setV(0, 0, new Rational(1, 1));
		x.setV(0, 1, new Rational(2, 1));
		x.setV(0, 2, new Rational(3, 1));
		x.setV(1, 0, new Rational(5, 1));
		x.setV(1, 1, new Rational(6, 1));
		x.setV(1, 2, new Rational(7, 1));
		x.setV(2, 0, new Rational(9, 1));
		x.setV(2, 1, new Rational(10, 1));
		x.setV(2, 2, new Rational(5, 1));
		Matrix y = new Matrix(3, 1);
		y.setV(0, 0, new Rational(4, 1));
		y.setV(1, 0, new Rational(8, 1));
		y.setV(2, 0, new Rational(12, 1));
		
		System.out.println(x);
		System.out.println(y);
		try{
			System.out.println(Algebra.gaussJordan(x, y));
		}
		catch(IllegalArgumentException e){
			System.out.println("Matrix nicht lösbar: " + e.getMessage());
		}
	}
}