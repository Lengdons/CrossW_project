package src;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class laukums {

	static int rinda;
	static int kolona;
	
	static class vardaPoz {
		int sakrinda, sakkolona, garums;
		boolean hor, notiek, meginat, aizpildits;
		
		vardaPoz(int r, int c, boolean hor, int garums){
			this.sakrinda = r;
			this.sakkolona = c;
			this.hor = hor;
			this.garums = garums;
			this.aizpildits = false;
			this.meginat = false;
		}
	}
	
	public static void grid(ArrayList<String> vards, ArrayList<String> definicijas) {
		HashMap<String, String> definicija = new HashMap<>();
        
		for (int i=0; i<vards.size(); i++) {
            String vardi = vards.get(i).toUpperCase(); 
            definicija.put(vardi, definicijas.get(i));
            vards.set(i, vardi); 
        }
		
		//grid
		
		int vardaGarums = 0;
		for (int i=0; i<vards.size(); i++) {
			vardaGarums = Math.max(vardaGarums, vards.get(i).length());
		}
		
		//grid izmers noteikts pec varda daudzuma
		rinda = vardaGarums*2+vards.size();
		kolona = vardaGarums*2+vards.size();
		
		
		char[][] redzloks = new char[rinda][kolona];
		char[][] pareizloks = new char[rinda][kolona];
		
		vardaPoz[] pozicija = new vardaPoz[vards.size()];
		
		//grid tukss
		for (int r=0; r<rinda; r++)
			for(int c=0; c<kolona; c++) {
				redzloks[r][c] = ' ';
				pareizloks[r][c] = ' ';
			}
		
		//pirma varda centresana
		int rinda1 = rinda/2;
		int kolona1 = (kolona-vards.get(0).length()) / 2;
		pozicija[0] = pieVarda(pareizloks, vards.get(0), rinda1, kolona1, true);
		
		//ieliek vardus
		for(int i=1; i<vards.size(); i++) {
			pozicija[i] = vardsVer(pareizloks, vards.get(i), pozicija);
			
			if (pozicija[i] == null) {
				pozicija[i] = vardsHor(pareizloks, vards.get(i));
			}
			
			if (pozicija[i] == null) {
				JOptionPane.showMessageDialog(null, "Nav vietas vardam: "+vards.get(i));
			}
		}
		
		//galvena dala prieks input
		while(true) {
			String a = JOptionPane.showInputDialog(null, gridString(redzloks, vards, definicija, pozicija, -1, -1, false)+"\n\nIzvelies varda nr kuru rakstit");
			
			if (a == null) break;
			
			int vardaIndex;
			try {
				vardaIndex = Integer.parseInt(a)-1;
				if(vardaIndex<0 || vardaIndex >= vards.size()) {
					JOptionPane.showMessageDialog(null, "Nederigs varda nr");
					continue;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ievadi derigu nr");
				continue;
			}
			
			//Noliek vardu
			vardaPoz pos = pozicija[vardaIndex];
			pos.notiek = true;
			
			String vardi = vards.get(vardaIndex);
			int burtiaizpild = 0;
			while (burtiaizpild < vardi.length()) {
				int r,c;
				if (pos.hor) {
					r = pos.sakrinda; //paliek uz vietas
					c = pos.sakkolona+burtiaizpild; //parvietojas
				}else {
					r = pos.sakrinda+burtiaizpild;
					c = pos.sakkolona;
				}
				
				String burts = JOptionPane.showInputDialog(null,
                        gridString(redzloks, vards, definicija, pozicija, r, c, false) +
                                "\n\nIevadi burtu "+(burtiaizpild+1)+" vārdam "+(vardaIndex+1)+" (Atcelt - atpakal");

                if (burts == null) {
                    pos.notiek = false;
                    pos.meginat = true;
                    break;
                }
                
                burts = burts.toUpperCase();

             // Ja lietotājs ievada visu vārdu
             if (burts.length() == vardi.length()) {
                 if (burts.equals(vardi)) {

                     // Aizpilda visu vārdu uzreiz
                     for (int i = 0; i < vardi.length(); i++) {
                         int rr, cc;
                         if (pos.hor) {
                             rr = pos.sakrinda;
                             cc = pos.sakkolona + i;
                         } else {
                             rr = pos.sakrinda + i;
                             cc = pos.sakkolona;
                         }
                         redzloks[rr][cc] = vardi.charAt(i);
                     }

                     burtiaizpild = vardi.length(); // vārds pabeigts
                     break;
                 } else {
                     JOptionPane.showMessageDialog(null, "Nepareizs vārds!");
                     continue;
                 }
             }

             // Ja ievada vienu burtu
             if (burts.length() == 1 && Character.isLetter(burts.charAt(0))) {
                 redzloks[r][c] = burts.charAt(0);
                 burtiaizpild++;
                 continue;
             }

             // Viss cits ir kļūda
             JOptionPane.showMessageDialog(null, "Ievadi 1 burtu vai visu vārdu");

			}
			boolean pareizi = true;
			
			for(int i=0; i<pos.garums; i++) {
				int r, c;
				if (pos.hor) {
					r = pos.sakrinda; //paliek uz vietas
					c = pos.sakkolona+i; //parvietojas
				}else {
					r = pos.sakrinda+i;
					c = pos.sakkolona;
				}
				
				if (redzloks[r][c] != pareizloks[r][c]) {
					pareizi = false;
					break;
				}
			}
			
			pos.aizpildits = pareizi;
			pos.meginat = true; //pabeigts meginajums
			
			boolean gatavs = true;
			for(int i=0; i<pozicija.length; i++) {
				vardaPoz p = pozicija[i];
				
				if (p != null && !p.aizpildits) {
					gatavs = false;
					break;
				}
			}
			
			if(gatavs) break; //iziet ara no loop kad beidzas 
		}
		
		JOptionPane.showMessageDialog(null, "Crossword ir pabeigts\n"+gridString(redzloks, vards, definicija, pozicija, -1, -1, true));
		
		for (int r=0; r<redzloks.length; r++) {
		    for (int c=0; c<redzloks[r].length; c++) {
		        System.out.print(redzloks[r][c] + " ");
		    }
		    System.out.println();
		}

	}
	
	static vardaPoz pieVarda(char[][] pareizloks, String vardi, int r, int c, boolean hor) {
		int rin, kol;
		for (int i=0; i<vardi.length(); i++) {
            if (hor) {
                rin = r;
                kol = c + i;
            } else {
                rin = r + i;
                kol = c;
            }
            if (rin < rinda && kol < kolona) {
                pareizloks[rin][kol] = vardi.charAt(i);  // Ieliek burtu ieksa grid
            }
        }
        return new vardaPoz(r, c, hor, vardi.length());
    }

	// Liek vardu vertikali, un parbauda vienados burtus ar jau esosiem vardiem
    static vardaPoz vardsVer(char[][] pareizloks, String vardi, vardaPoz[] pozicija) {
        // Try to find intersections with existing words
        for (int r = 0; r < rinda; r++) {
            for (int c = 0; c < kolona; c++) {
                // Izlaiz ja ir aiznemta vieta
                if (pareizloks[r][c] == ' ') continue;

                for (int i = 0; i < vardi.length(); i++) {
                    if (pareizloks[r][c] == vardi.charAt(i)) {
                        int sakrinda = r - i;

                        // Parabaude vai iespejams nolikt vertikali
                        if (vardsVerLikt(pareizloks, vardi, sakrinda, c)) {
                            for (int k = 0; k < vardi.length(); k++) {
                            	pareizloks[sakrinda + k][c] = vardi.charAt(k);
                            }
                            return new vardaPoz(sakrinda, c, false, vardi.length());
                        }
                    }
                }
            }
        }
        return null; // Ja nevar nolikt vardu
    }

    static boolean vardsVerLikt(char[][] pareizloks, String vardi, int rin, int kol) {
        if (rin < 0 || rin + vardi.length() > rinda) return false;

        
        if (rin > 0 && pareizloks[rin-1][kol] != ' ') return false;
        if (rin + vardi.length() < rinda && pareizloks[rin+vardi.length()][kol] != ' ') return false;

        for (int k = 0; k < vardi.length(); k++) {
            int r = rin+ k;
            char ch = vardi.charAt(k);

            // Letter conflict
            if (pareizloks[r][kol] != ' ' && pareizloks[r][kol] != ch) return false;

            // Neatlauj pieskarties burtiem
            if (pareizloks[r][kol] == ' ') {   // ja nav savienojuma vieta (vienads burts)
                if (kol > 0 && pareizloks[r][kol - 1] != ' ') return false;
                if (kol < kolona - 1 && pareizloks[r][kol + 1] != ' ') return false;
            }
        }
        return true;
    }
    
    static vardaPoz vardsHor(char[][] pareizloks, String vardi) {
        for (int r = 0; r < rinda; r++) {
            for (int c = 0; c < kolona; c++) {

                if (pareizloks[r][c] == ' ') continue;

                for (int i = 0; i < vardi.length(); i++) {
                    if (pareizloks[r][c] == vardi.charAt(i)) {
                        int sakkolona = c - i;

                        if (vardsHorLikt(pareizloks, vardi, r, sakkolona)) {
                            for (int k = 0; k < vardi.length(); k++) {
                            	pareizloks[r][sakkolona + k] = vardi.charAt(k);
                            }
                            return new vardaPoz(r, sakkolona, true, vardi.length());
                        }
                    }
                }
            }
        }
        return null;
    }
    
    static boolean vardsHorLikt(char[][] grid, String vardi, int rin, int kol) {
        if (kol < 0 || kol + vardi.length() > kolona) return false;

        if (kol > 0 && grid[rin][kol - 1] != ' ') return false;
        if (kol + vardi.length() < kolona && grid[rin][kol + vardi.length()] != ' ') return false;

        for (int i = 0; i < vardi.length(); i++) {
            int c = kol + i;
            char ch = vardi.charAt(i);

            if (grid[rin][c] != ' ' && grid[rin][c] != ch) return false;

         // Neatlauj pieskarties burtiem
            if (grid[rin][c] == ' ') {
                if (rin > 0 && grid[rin - 1][c] != ' ') return false;
                if (rin < rinda - 1 && grid[rin + 1][c] != ' ') return false;
            }
        }
        return true;
    }




    // Parvers grid uz String lai varetu paradit vinu ar JOptionPane
    static String gridString(
            char[][] grid,
            ArrayList<String> vards,
            HashMap<String, String> definicijas,
            vardaPoz[] pozicija,
            int Tagatrinda,
            int Tagatkolona,
            boolean pabeigts 
    ) {
    	
    	StringBuilder sb = new StringBuilder();

        for (int r = 0; r < rinda; r++) {
            for (int c = 0; c < kolona; c++) {
            	char ch = grid[r][c];

            	boolean show = false;
            	

            	// Check if this cell belongs to any filled word
            	for (vardaPoz p : pozicija) {
            		if (p == null) continue;


            	    for (int i=0; i<p.garums; i++) {
            	        int rr, cc;
            	        if (p.hor) {
            	            rr = p.sakrinda;
            	            cc = p.sakkolona + i;
            	        } else {
            	            rr = p.sakrinda + i;
            	            cc = p.sakkolona;
            	        }
            	        if (rr == r && cc == c) {
            	            show = true;
            	            break;
            	        }
            	    }
            	    if (show) break;
            	}

            	if (show) {
            	    if (r == Tagatrinda && c == Tagatkolona) {
            	        sb.append("[");
            	        sb.append(ch == ' ' ? "?" : ch);
            	        sb.append("]");
            	    } else {
            	        sb.append(" ");
            	        sb.append(ch == ' ' ? "?" : ch);
            	        sb.append(" ");
            	    }
            	} else {
            	    sb.append(" 🔲 ");
            	}

            }
            sb.append("\n");
        }

        sb.append("\nDefinicijas:\nHorizontali:\n");
        for (int i = 0; i < vards.size(); i++) {
            vardaPoz p = pozicija[i];
            if (p != null && p.hor) {
                sb.append((i + 1) + ". ");
                sb.append(definicijas.get(vards.get(i)));

                if (pabeigts) {
                    if (p.aizpildits) {
                        sb.append(" ✔");
                    } else {
                        sb.append(" ✘ (Atbilde: ").append(vards.get(i).toUpperCase()).append(" )");
                    }
                }

                sb.append("\n");
            }
        }


        sb.append("Vertikali:\n");
        for (int i = 0; i < vards.size(); i++) {
            vardaPoz p = pozicija[i];
            if (p != null && !p.hor) {
                sb.append((i + 1) + ". ");
                sb.append(definicijas.get(vards.get(i)));

                if (pabeigts) {
                    if (p.aizpildits) {
                        sb.append(" ✔");
                    } else {
                        sb.append(" ✘ (Atbilde: ").append(vards.get(i).toUpperCase()).append(" )");
                    }
                }

                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    static void print(char[][] grid) {
        System.out.println("\nBeigu Crossword:\n");

        for (int r=0; r<rinda; r++) {
            for (int c=0; c<kolona; c++) {
                char ch = grid[r][c];

                if (ch == ' ')
                    System.out.print("_\t");
                else
                    System.out.print(ch + "\t");
            }
            System.out.println();
        }
    }

        
}
