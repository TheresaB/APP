import algebra.Matrix;
import algebra.Rational;

public class MatrixTest{
	public static void main(String[] args){
		Matrix m1 = new Matrix(3, 3);
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				m1.setV(i, i, new Rational(1, 1));
			}
		}
		Matrix m2 = new Matrix(3, 3);
		System.out.println(m1.equals(m2));
		System.out.println(m1.toString());
		Matrix m3 = m1.clone();
		System.out.println(m1.equals(m3));
		m3.vertauscheZeile(2, 1);
		System.out.println(m3);
		System.out.println(Matrix.mult(m1, m3));
		System.out.println(m3.mult(m1));
	}
}