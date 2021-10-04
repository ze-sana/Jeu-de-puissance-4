package fr.sana.puissance4;
import java.util.Scanner;


public class Puissance4 {

	public static void main(String[] args) {		
		Scanner scanner = new Scanner(System.in);
		
		//On demande le nom des joueurs
		System.out.println("Joueur 1, saisis ton prénom: ");
	    String nomJoueur1 = scanner.nextLine();
	    System.out.println("Bonjour " + nomJoueur1 + " !");
	    System.out.println("Joueur 2, saisis ton prénom: ");
	    String nomJoueur2 = scanner.nextLine();
	    System.out.println("Bonjour " + nomJoueur2 + " !");
		
	    
		//Le plateau du jeu :
		//alignement requis pour gagner(qu'on peut modifier) :
		int alignementRequis = 4;
		
		//Dimensions colonnes et lignes (qu'on peut modifier):
		int C = 7;
		int L = 6;
		
		//tableau du plateau :
		//("." = emplacement vide  / "X" = joueur 1  /  "O  = joueur 2)
		char[][] plateau = new char[C][L];
		
		//remplissage des cases avec des emplacements vides "."
		for(int x = 0 ; x < C ; x++)
			for(int y = 0 ; y < L ; y++)
				plateau[x][y] = '.';
				
		int gagnant = 0;
		
		//boucle de jeu, s'arrête en cas de victoire de J1 ou J2 
		//ou si le plateau est plein avec égalité
		for(int i = 1 ; i <= C*L ; i++){
			
			//affichage du plateau:
			System.out.println("Tour " + i + ", Etat du plateau :");
			
			for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
			System.out.println();
			
			for(int y = 0 ; y < L ; y++){
				System.out.print('|');
				for(int x = 0 ; x < C ; x++){
					System.out.print(" " + plateau[x][y] + " ");
				}
				System.out.print('|');
				System.out.println();
			}
			
			for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
			System.out.println();
			
			//Placements du jeton:
			System.out.println((i%2==1 ? nomJoueur1 : nomJoueur2) + ", à toi de jouer!" );
			System.out.println("Saisis un numéro de colonne entre 1 et " + C + " :");
			boolean placement = false;
			int colonne = -1;
			while(!placement){
				colonne = -1;
				String ligne = scanner.nextLine();
				//vérification que la ligne est un entier entre 1 et C:
				try{
					colonne = Integer.valueOf(ligne);
					
					if(colonne >= 1 && colonne <= C){
						if(plateau[colonne - 1][0] != '.'){
							System.out.println("Colonne pleine, resaisis");
						} else {
							placement = true;
						}
					} else {
						System.out.println("Nombre incorrect, resaisis");
					}
					
				}catch(Exception e){System.out.println("Nombre incorrect, resaisis");}
				
			}
			//placement du jeton:
			int rang = L-1;
			while(plateau[colonne - 1][rang] != '.'){
				rang--;
			}
			plateau[colonne - 1][rang] = (i%2==1 ? 'X' : 'O');
			
			
			
			//Détection de victoire:
			
			//symbole en cours:
			char symbole = (i%2==1 ? 'X' : 'O');
			//nombre alignés maximal: 
			int max = 0;
			int x; int y;
			int somme;
			
			//diagonale HG-BD
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && x >= 0 && plateau[x][y] == symbole){ y--; x--; somme++;}
			x = colonne-1; y = rang;
			while(y < L && x < C && plateau[x][y] == symbole){ y++; x++; somme++;}
			if(somme > max) max= somme;
			
			//diagonale HD-BG
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && x < C && plateau[x][y] == symbole){ y--; x++; somme++;}
			x = colonne-1; y = rang;
			while(y < L && x >= 0 && plateau[x][y] == symbole){ y++; x--; somme++;}
			if(somme > max) max= somme;
			
			//colonne:
			x = colonne-1; y = rang; somme=-1;
			while(y >= 0 && plateau[x][y] == symbole){ y--; somme++;}
			y = rang;
			while(y < L && plateau[x][y] == symbole){ y++; somme++;}
			if(somme > max) max= somme;
			
			//ligne :
			x = colonne-1; y = rang; somme=-1;
			while(x >= 0 && plateau[x][y] == symbole){ x--; somme++;}
			x = colonne-1;
			while(x < C && plateau[x][y] == symbole){ x++; somme++;}
			if(somme > max) max= somme;
			
			
			if(max >= alignementRequis){
				gagnant = (i%2==1 ? 1 : 2);
				i = C*L+1;
			}
		}
		
		
//affichage des résultats:
// si gagnant == 0 c'est que tout le plateau s'est remplis sans gagnant, il y a donc égalité
		System.out.println("**** La partie est terminée ****");
		
		if(gagnant == 0)
			System.out.println("******* Partie nulle *******");
		if(gagnant == 1)
			System.out.println("**** Bravo " + nomJoueur1 + ", tu as gagné! ****");
		if(gagnant == 2)
			System.out.println("**** Bravo " + nomJoueur1 + ", tu as gagné! ****");
		
		
		
		for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
		System.out.println();
		
		for(int y = 0 ; y < L ; y++){
			System.out.print('|');
			for(int x = 0 ; x < C ; x++){
				System.out.print(" " + plateau[x][y] + " ");
			}
			System.out.print('|');
			System.out.println();
		}
		
		for(int loop = 0 ; loop < C+2+2*C ; loop++)System.out.print('-');
		System.out.println();
	
	}

}