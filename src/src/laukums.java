package src;

import java.util.ArrayList;
import java.util.HashMap;

public class laukums {

	static int rinda;
	static int kolona;
	
	static class vardaPoz {
		
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
	}

}
