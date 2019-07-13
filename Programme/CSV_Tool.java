import java.io.*;

public class CSV_Tool {

	public static void main(String[] args) throws IOException {
		/*
		 * zeilenweises Einlesen einer csv-Datei
		 */
		BufferedReader br = 
			new BufferedReader(
					new FileReader(
						new File("data//swedish_royalty.csv")));
		String zeile = "";
		String[] split;
		zeile = br.readLine(); // lesen der Kopfzeile
		split = zeile.split(";"); // zerlegen der Kopfzeile
		String c1 = split[0];
		String c2 = split[1];
		String c3 = split[2];
		String c4 = split[3];
		String c5 = split[4];

		while ((zeile = br.readLine()) != null) { // lesen jeder Zeile
			split = zeile.split(";"); // Zeile zerlegen mit Trennzeichen ;
			System.out.println(c1 + ": \t\t" + split[0]); // erste Spalte ueber
               							      // index 0 erreichbar
			System.out.println(c2 + ": \t" + split[1]);
			System.out.println(c3 + ": \t\t" + split[2]);
			System.out.println(c4 + ": \t" + split[3]);
			System.out.println(c5 + ": \t" + split[4]);
			System.out.println();
		}
		br.close();
		System.out.println("Programmende");
	}
}
