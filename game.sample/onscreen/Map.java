package onscreen;

import java.io.File;
import java.util.Scanner;

/*
Class Map nous permet de représenter la carte de jeu
 */

public class Map {

    int n;
    public Entity map[][];
    public char color[][];

    /*
     * Constructeur de base créant une carte
     * Les limites du terrains ( premiere colonne, derniere colonne, premiere ligne, derniere ligne)
     * sont des murs, le reste sont des case libres (free)
     */
    public Map(int n) {
        this.n = n;
        this.map = new Entity[n][n];
        int i, j;
        for (i = 1; i < n; i++)
            for (j = 1; j < n; j++)
                this.insert(new Entity('F', i, j));

        for (i = 0, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = n - 1, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = 0; i < n; i++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = n - 1; i < n; i++)
            this.insert(new Entity('W', i, j));

    }

    /*
     * Création d'une carte à partir d'un fichier txt filepath
     */
    public Map(String filepath) {
        File f = null;
        Scanner scan = null;
        try {
            f = new File(filepath);
            scan = new Scanner(f);
        } catch (Exception e) {
            System.exit(0);
        }

        this.n = scan.nextInt();
        this.map = new Entity[n][n];
        this.color = new char[n][n];
        String s;
        for (int i = 0; i < this.n; i++) {
            s = scan.next();
            for (int j = 0; j < this.n; j++) {
                insert(new Entity(s.charAt(j), i, j));
            }
        }
        for (int c = 0; c < this.n; c++) {
        	for (int l = 0; l < this.n; l++) {
        		
        		if(this.map[c][l].type == 'F') {
        			this.color[c][l] = 'W';
        		}else if(this.map[c][l].type == 'W') {
        			this.color[c][l] = 'M';
        		}else if(this.map[c][l].type == 'T') {
        			this.color[c][l] = 'W';
        		}else if(this.map[c][l].type == 'I') {
        			this.color[c][l] = 'I';
        		}
        	}
        }

    }

    /*
     * Remplissage aléatoire de la carte, la carte est rempli d'un pourcentage de mur
     * Remarque ici aucun test n'est réalisé pour vérifier que les tanks ne soient bloqué
     */
    public Map(int n, int percentage) {
        this.n = n;
        this.map = new Entity[n][n];
        int i, j, rand;
        for (i = 1; i < n; i++)
            for (j = 1; j < n; j++) {
                rand = (int) (Math.random() * 100);
                if (rand < percentage)
                    this.insert(new Entity('W', i, j));
                else
                    this.insert(new Entity('F', i, j));
            }

        for (i = 0, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = n - 1, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = 0; i < n; i++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = n - 1; i < n; i++)
            this.insert(new Entity('W', i, j));
    }

    //retourn vrai si la case (i,j) est de type 'F' (free)
    public boolean isfree(int i, int j) {
        return this.map[i][j].type == 'F';
    }

    //retourn vrai si la case (i,j) est de type 'B' (bonus)
    public boolean isbonus(int i, int j) {
        return this.map[i][j].type == 'I';
    }

    //insere une case de type 'F' (free) à l'emplacement (i,j)
    public void free(int i, int j) {
        this.insert(new Entity('F', i, j));
    }
    
    //Place l'entité e dans la map en fonction de ces coordonné (contenu dans e)
    public void insert(Entity e) {
        map[e.p.i][e.p.j] = e;
    }
    
	public boolean ismine(int i, int j) {
		return this.map[i][j].type == 'M';
	}
    
    public boolean insertMineOK(Entity e) {
		switch (e.dir) {
			case 'D':
				return isfree(e.p.i-1,e.p.j);
			case 'L':
				return isfree(e.p.i,e.p.j+1);
			case 'R':
				return isfree(e.p.i,e.p.j-1);
			default:
				return isfree(e.p.i+1,e.p.j);
		}
	}

    //affichage dans la console
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].type == 'T') {
                    switch (map[i][j].dir) {
                    case 'D':
                        System.out.printf("v ");
                        break;
                    case 'L':
                        System.out.printf("< ");
                        break;
                    case 'R':
                        System.out.printf("> ");
                        break;
                    default:
                        System.out.printf("A ");
                        break;
                    }
                } else {
                    System.out.printf("%c ", map[i][j].type);
                }
            }
            System.out.print("\n");
        }
    }
    
    /*
     * Fonction Indiquant dans la console la possession de chacun des joueurs
     */
    public void possession() {
    	int nb_bleu = 0;
    	int nb_rouge = 0;
    	for(int i=0; i<n ;i++) {
    		for(int j=0; j<n ; j++) {
    			if(color[i][j]=='B') {
    				nb_bleu ++;
    			}
    			else if(color[i][j]=='R') {
    				nb_rouge ++;
    			}
    		}
    	}
    	System.out.printf("Score : Rouge "+nb_rouge+" / "+nb_bleu +" Bleu\n" );
    }
}
