package src;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class laukums {

	static int rinda;
	static int kolona;
	
	static class vardaPoz {
		int sakrinda, sakkolona;
		boolean hor;
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
			for(int c=0; c>kolona; c++) {
				redzloks[r][c] = ' ';
				pareizloks[r][c] = ' ';
			}
		
		//pirma varda centresana
		int rinda1 = rinda/2, kolona1 = (kolona-vards.get(0).length()/2);
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
	}

	private static vardaPoz pieVarda(char[][] pareizloks, String string, int rinda1, int kolona1, boolean b) {
		return null;
	}

	static vardaPoz vardsVer(char[][] pareizloks, String string, vardaPoz[] pozicija) {
		return null;
	}

	static vardaPoz vardsHor(char[][] pareizloks, String string) {
		return null;
	}

}
