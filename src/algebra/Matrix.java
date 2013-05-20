package algebra;

public class Matrix{
	//dim[0] = m, dim[1] = n (mxn)
	int[] dim = new int[2];
	Rational[][] matrix;

	//Konstruktor ohne Daten
	public Matrix(int m, int n){
		dim[0] = m;
		dim[1] = n;
		matrix = new Rational[dim[0]][dim[1]];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				matrix[i][j] = new Rational(0, 1);
			}
		}
	}

	public int getM(){
		return dim[0];
	}

	public int getN(){
		return dim[1];
	}

	public Rational getV(int i, int j){
		return matrix[i][j];
	}

	public void setV(int i, int j, Rational x){
		matrix[i][j] = x;
	}

	public boolean equals(Matrix m1){
		if(m1 == this)
			return true;
		if(m1 == null)
			return false;
		//Vergleich der Dimensionen
		if(this.dim[0] != m1.dim[0] || this.dim[1] != m1.dim[1])
			return false;
		//Vergleich der Daten
		for(int i = 0; i < this.dim[0]; i++){
			for(int j = 0; j < this.dim[1]; j++){
				if(this.matrix[i][j] != m1.matrix[i][j])
					return false;
			}
		}
		return true;
	}

	public String toString(){
		String s = "\n";
		for(int i = 0; i < dim[0]; i++){
			for(int j = 0; j < dim[1]; j++){
				s += matrix[i][j] + "\t";
			}
			s += "\n";
		}
		return s;
	}
	
	//clone
	public Matrix clone(){
		Matrix neu = new Matrix(this.dim[0], this.dim[1]);
		for(int i = 0; i < dim[0]; i++){
			for(int j = 0; j < dim[1]; j++){
				//kopieren der einzelnen Werte
				neu.matrix[i][j] = this.matrix[i][j];
			}
		}
		return neu;
	}
	
	//Vertauschen zweier Zeilen
	public void vertauscheZeile(int m1, int m2){
		Rational[] x = this.matrix[m1];
		this.matrix[m1] = this.matrix[m2];
		this.matrix[m2] = x;
	}
	
	//Multiplikation zweier Matrizen, Klassenmethode, Rückgabe null bei Fehler
	public static Matrix mult(Matrix a, Matrix b){
		if(a.dim[1] != b.dim[0])
			return null;
		Matrix c = new Matrix(a.dim[0], b.dim[1]);
		//c[i][j] = sum(a[i][k]*b[k][j]), k=0,...,n-1 für a mxn und b nxk
		for(int i = 0; i < c.dim[0]; i++){
			for(int j = 0; j < c.dim[1]; j++){
				for(int k = 0; k < a.dim[1]; k++){
					c.matrix[i][j].addTo(Rational.mult(a.matrix[i][k], b.matrix[k][j]));
				}
			}
		}
		return c;
	}
	
	//Multiplikation zweier Matrizen, Instanzmethode, Rückgabe null bei Fehler
	public Matrix mult(Matrix a){
		return Matrix.mult(this, a);
	}	
}