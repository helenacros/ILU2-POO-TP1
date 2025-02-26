package histoire;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		etal.acheterProduit(2, null);
		System.out.println("Fin du test");
		}

}
