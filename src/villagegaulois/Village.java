package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nb_etals){
			etals=new Etal[nb_etals];
			for (int i = 0; i < etals.length; i++) {
				etals[i]=new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			// TO DO 
			if(etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur,produit, nbProduit);
			}
		}
		
		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalProduit=0;
			for (int i = 0; i < etals.length; i++) {
				if( etals[i].contientProduit(produit)) {
					nbEtalProduit++;
				}
			}
			
			Etal[] etalsProduit= new Etal[nbEtalProduit];
			int compt=0; 
			for (int i = 0; i < etals.length && compt<nbEtalProduit ; i++) {
					if( etals[i].contientProduit(produit)) {
						etalsProduit[compt]=etals[i];
						compt++;
				 }
			}
			return etalsProduit;
			
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if(etals[i]!=null && etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			int nbEtalVide=0;
			String retour="";
			for (int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe() ) {
					retour+=etals[i].afficherEtal();
				}
				else {
					nbEtalVide++;
				}
			}
			retour+="Il reste "+nbEtalVide+" étals non utilisés dans le marché. \n";
			return retour;
		}
		
		
	}
	
	
	
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		String retour;
		retour=vendeur.getNom()+" cherche un endroit pour vendre "+ nbProduit+" "+produit+".\n";
		int indice_etal=marche.trouverEtalLibre();
		if(indice_etal>=0) {
			marche.utiliserEtal(indice_etal, vendeur, produit, nbProduit);
			retour+="Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+indice_etal+1+"\n";
		}
		else {
			retour+="Le vendeur "+vendeur.getNom()+" reviendra plus tard, quand une étal sera libre ... \n";
		}
		return retour;
	}
	
	 public String rechercherVendeursProduit(String produit) {
		 Etal[] etalsProduit= marche.trouverEtals(produit);
		 String retour;
		 if(etalsProduit.length==0) {
			 retour="Pas d'étal qui vende ce produit \n";
		 }
		 else {
			 retour="Les vendeurs qui proposent des "+produit+" sont :\n";
			 for (int i = 0; i < etalsProduit.length; i++) {
				 retour+="- "+etalsProduit[i].getVendeur()+"\n";
			}
			
		 }
		 return retour;
	 }

	 public Etal rechercherEtal(Gaulois vendeur) {
		 return marche.trouverVendeur(vendeur);
	 }

	 public String partirVendeur(Gaulois vendeur) {
		 Etal etal_vendeur= marche.trouverVendeur(vendeur);
		 String retour;
		if(etal_vendeur!=null) {
			retour= etal_vendeur.libererEtal();
		}
		else {
			retour= "Le vendeur "+vendeur.getNom()+" n'a pas d'etal \n";
		}
		return retour;
	 }
	
	 public String afficherMarche() {
		 return "Le marche du village \" "+nom+" \" possède plusieurs étals "+ marche.afficherMarche()+"\n";
		
	 }
	 
	 
	 
	 
}