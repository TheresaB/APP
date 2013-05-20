import java.io.*;

import algebra.Algebra;
import algebra.Matrix;
import algebra.Rational;

class GJTest{
	private static Matrix datenvektor = null;
	private static Matrix datenmatrix = null;

	public static void main(String[] args){
		Matrix gaussvektor = null;
		Matrix gaussmatrix = null;

		if(args.length == 0) 
			return;
		GJTest.readFile(args[0]);
		if(datenvektor == null || datenmatrix == null) 
			return;

		try{
			gaussmatrix = datenmatrix.clone();
			System.out.println(gaussmatrix);
		}
		catch(IllegalArgumentException e){
			System.out.println("Datafile Matrix: "+ e.getMessage());	
		}

		try{
			gaussvektor = datenvektor.clone();
			System.out.println(gaussvektor);
		}
		catch(IllegalArgumentException e){
			System.out.println("Datafile Matrix: "+ e.getMessage());	
		}

		try{
			System.out.println("Gauß:\n"+Algebra.gaussJordan(gaussmatrix, gaussvektor));
		}
		catch(IllegalArgumentException e){
			System.out.println("Matrix nicht lösbar: "+ e.getMessage());	
		}
	}

	private static void readFile(String filename){
		try{
			FileInputStream fStream = new FileInputStream(GJTest.class.getProtectionDomain().getCodeSource().getLocation().getFile() + filename);
			DataInputStream fInputStream = new DataInputStream(fStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fInputStream));
			String[] line_numbers;

			//erste Zeile lesen
			String line = bufferedReader.readLine();
			String[] firstLine = line.split(" ");
			datenvektor = new Matrix(firstLine.length,1);

			//Vektor einlesen aus erster Zeile
			for(int i=0; i < datenvektor.getM(); i++){
				datenvektor.setV(i, 0, new Rational(Long.parseLong(firstLine[i]), 1));
			}

			//Matrix einlesen aus restlichen Zeilen
			datenmatrix = new Matrix(firstLine.length, firstLine.length);
			int i = 0;
			while((line = bufferedReader.readLine()) != null){
				//nicht zu viel lesen um in der Matrix zu bleiben
				if(i >= firstLine.length) 
					break;
				line.replace("\t", " ");
				line_numbers = line.split(" ");
				if(line_numbers.length != firstLine.length){
					datenmatrix = null;
					fInputStream.close();
					bufferedReader.close();
					throw new IllegalArgumentException("File Format incorrect");
				}
				for(int j=0; j < line_numbers.length; j++){
					datenmatrix.setV(i, j, new Rational(Long.parseLong(line_numbers[j]), 1));
				}
				i++;
			}
			if(i != firstLine.length){
				datenmatrix = null;
				fInputStream.close();
				bufferedReader.close();
				throw new IllegalArgumentException("File Format incorrect");
			}
			fInputStream.close();
			bufferedReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Datei nicht gefunden: "+e.getMessage());
			return;
		}
		catch(IllegalArgumentException e){
			System.out.println("Datei Fehler: "+e.getMessage());
			return;
		}
		catch(IOException e){
			System.out.println("Lese Fehler: "+e.getMessage());
			return;
		}
	}
}