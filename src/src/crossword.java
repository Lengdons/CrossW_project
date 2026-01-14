package src;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.database;

public class crossword {

	//crossword layout
	
//	public static void grid(ArrayList<String> vards, String def){
//		String grid="";
//		// JOptionPane.showMessageDialog(null, teksts+"\n"+def);
//		
//		for(int i=0; i<vards.size(); i++) {
//			//System.out.println(i);
//			grid = grid+(i+1)+". ";
//			if((i+1)%2!=0) 
//				for(int j=0; j<vards.get(i).length(); j++) grid= grid+" [?]";
//			else
//				for(int j=0; j<vards.get(i).length(); j++) grid= grid+"\n [?]";
//			
//		//grid=grid+(vards.get(i));
//		grid=grid+"\n";
//		}
//		grid=grid+"\n";
//		
//		String indexV = JOptionPane.showInputDialog(null, grid+def+"\nIzvelies kuru vardu velies aizpildit");
//		int index = Integer.parseInt(indexV)-1;
//		String letterInput = JOptionPane.showInputDialog("Enter a letter:");
//		char letter = letterInput.charAt(0);
//		JOptionPane.showMessageDialog(null, "Word: \nLetter chosen: " + letter);
//
//
//	}
	    
	
	public static void veidot() {
		String a, teksts, msg;
		ArrayList<String> vards = new ArrayList<>();
		do {
		teksts = String.join("\n", vards); //lieto lai nebutu [] un , kas rodas ja izmanto arraylist vards nevis atsevisku string
		
		if (teksts.isEmpty())
			msg = "Ieraksti vardu";
		else
			msg = teksts+"\nIeraksti vardu";
		
		
		a = JOptionPane.showInputDialog(null, msg);
		
		
		
		 // ESC / Cancel
	    if (a == null) {

	        // 0 vārdi → atpakaļ uz galveno izvēlni
	        if (vards.isEmpty()) {
	            return;
	        }

	        // 1 vārds → kļūda
	        if (vards.size() == 1) {
	            JOptionPane.showMessageDialog(
	                null,
	                "Jābūt vismaz diviem vārdiem",
	                "Kļūda",
	                JOptionPane.WARNING_MESSAGE
	            );
	            continue;
	        }

	        // 2+ vārdi → pabeigt ievadi
	        break;
	    }

	    a = a.trim();

	    if (a.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nevar būt tukšs");
	        continue;
	    }

	    if (!a.matches("\\p{L}+")) {
	        JOptionPane.showMessageDialog(null, "Vārds drīkst saturēt tikai burtus");
	        continue;
	    }

	    if (a.length() < 2) {
	        JOptionPane.showMessageDialog(null, "Vārdam jābūt vismaz 2 burtus garam");
	        continue;
	    }

	    boolean jauIr = false;

	    for (String v : vards) {
	        if (v.equalsIgnoreCase(a)) {
	            jauIr = true;
	            break;
	        }
	    }

	    if (jauIr) {
	        JOptionPane.showMessageDialog(null, "Šāds vārds jau ir ievadīts!");
	        continue;
	    }

	    vards.add(a);


	} while (true);
		
		//delete 
		
		do {
		a = JOptionPane.showInputDialog(null, teksts+"\nKuru no siem vardiem velies dzest?");

		if (a == null) break;

		a = a.trim();

		if (a.isEmpty()) {
		    JOptionPane.showMessageDialog(null, "Nevar būt tukšs");
		    continue;
		}
		boolean atrasts = false;

		for (int i=0; i<vards.size(); i++) {
		    if (vards.get(i).equalsIgnoreCase(a)) {

		        if (vards.size() <= 2) {
		            JOptionPane.showMessageDialog(null, "Vārdu skaits nevar būt mazāks par 2", "Kļūda", JOptionPane.WARNING_MESSAGE
		            );
		        } else {
		            vards.remove(i);
		        }
		        atrasts = true;
		        break;
		    }
		}

		if (!atrasts) {
		    JOptionPane.showMessageDialog(null, "Šāds vārds neeksistē!");
		}

		teksts = String.join("\n", vards);
		teksts = String.join("\n", vards);
		}while(a!=null);
		
		//definition
		
		ArrayList<String> definicijas = new ArrayList<>();
		String def="";
		
		for(int i=0; i<vards.size(); i++) {
			do {
		a = JOptionPane.showInputDialog(null , vards.get(i)+"\nIevadi definiciju prieks si varda");
		if (a==null || a.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Definīcija nevar būt tukša",
                "Kļūda",
                JOptionPane.WARNING_MESSAGE
            );
		}
			}while (a==null || a.trim().isEmpty());
		definicijas.add(a);
		def = def+(i+1+". "+definicijas.get(i)+"\n");
		}
		
		//edit
		
		do {
		
		a = JOptionPane.showInputDialog(def+"Kuru no siem definicijam velies rediget?");
		
		if(a==null)
			break;
		
		if (!a.matches("\\d+")) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Ievadi numuru",
	            "Kļūda",
	            JOptionPane.WARNING_MESSAGE
	        );
	        continue;
	    }
		
			
		int b = Integer.parseInt(a)-1;
		
		if (b < 0 || b >= definicijas.size()) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Šāds definīcijas numurs neeksistē",
	            "Kļūda",
	            JOptionPane.WARNING_MESSAGE
	        );
	        continue;
	    }
		
	
		
		definicijas.get(b);
		
		
		do {
		a = (String) JOptionPane.showInputDialog(null, "Redige definiciju prieks "+vards.get(b), "Rediget", JOptionPane.QUESTION_MESSAGE, null, null, definicijas.get(b));
		if(a != null && !(a.trim().isEmpty()))
		definicijas.set(b, a);
		else JOptionPane.showMessageDialog(null, "Definicija nevar but tuksa");
		}while(a==null || a.trim().isEmpty());
		def = ""; //lai attiritu ieprieksejas definicijas
		for(int i=0; i<definicijas.size(); i++) //ielikt jaunas definicijas
			def = def+(i+1+". "+definicijas.get(i)+"\n");
		
		}while(a!=null);
		
		//iziet ara no cikla ja neieraksta index error
		
		//creation
		
		laukums.grid(vards, definicijas);
	}
	
//	StringBuilder build = new StringBuilder();
//	for (int i=0; i<10; i++) {
//		for (int j=0; j<10; j++) {
//			build.append("⬜ \t");
//		}
//		build.append("\n");
//	}
//	JOptionPane.showMessageDialog(null, build.toString());
	
	// kods lai izveidotu tabulu uz joptionpane
	
	
	
	public static void izveleties() {
		String a="";
		do {
		a = JOptionPane.showInputDialog(null, "Izvelies grutibas pakapi - Viegli - 1, Videji - 2, Gruti - 3").toLowerCase();
	        
	        if(!(a.equals("viegli") || a.equals("videji") || a.equals("gruti") || a.equals("1") || a.equals("2") || a.equals("3")))
	        JOptionPane.showMessageDialog(null, "Nepareizi ievaditi dati");
	        
	        }while(!(a.equals("viegli") || a.equals("videji") || a.equals("gruti") || a.equals("1") || a.equals("2") || a.equals("3")));
		switch(a) {
		case "1":
		case "viegli":
			
			break;
		case "2":
		case "videji":
			break;
		case "3":
		case "gruti":
			break;
		}
	}
	
	public static void main(String[] args) {
		
		Connection conn = database.getConnection();
		if (conn != null) {
            System.out.println("Savienojums veiksmigs");
        }
		String a = "";
		try {
			do {
		a = JOptionPane.showInputDialog(null, "1 - Veidot savu crossword \n2 - Izveleties gatavu crossword");
		
		switch (a) {
		case "1": 
			veidot();
		break;
		case "2":
			izveleties();
		break;
		default:
			JOptionPane.showMessageDialog(null, "Nav tada izvele", null, JOptionPane.WARNING_MESSAGE);
	}
			}while(true);
		}catch(NullPointerException e) {}
}
}


/*
import java.util.Scanner;
import java.util.InputMismatchException;


class Main {
    static String[] definition;
    static int counter = 0;
    
    public static void gridv(String cw, String def){
        // System.out.println(cw);
        definition[counter] = def;
        counter++;
        for(int i=0; i<cw.length(); i++){
            for(int j=0; j<1; j++){
                System.out.print("[]");
            }
            System.out.println();
        }
        System.out.println(def);
    }
   /* public static void ver(){
        System.out.println("verti"); 
    } */
    
    
 /*   
    public static void gridh(String cw, String def){
        // System.out.println(cw);
        definition[counter] = def;
        counter++;
        for(int i=0; i<1; i++){
            for(int j=0; j<cw.length(); j++){
                System.out.print("[]");
            }
            System.out.println();
        }
        System.out.println(def);
    }
    /* public static void horz(){
        System.out.println("horiz");
    } */
    
 /*   
    public static void random(int n, String scale) {
        do{
        int a = (int)((Math.random()*100)+1);
        n--;
        // System.out.print(a+" "+a%2);
        
        if(a%2==0){
            //ver();
            gridv(vards(scale).cw, vards(scale).def);
        }else{
            //horz();
            gridh(vards(scale).cw, vards(scale).def);
        }
        
        }while(n>0);
        
    }
    
    public static vards vards(String scale){
        String cw="", def="";
        int time = (int)((Math.random()*4)+1);
        switch(scale){
            case "1":
            case "viegli":
                switch(time) {
            case 1: cw = "banans"; def = "dzeltens"; break;
            case 2: cw = "banans"; def = "dzeltens"; break;
            case 3: cw = "abols"; def = "aug koka"; break;
            case 4: cw = "abols"; def = "aug koka"; break;
        }   break;
            case "2":
            case "videji":
                switch(time) {
            case 1: cw = "apelsins"; def = "oranza krasa"; break;
            case 2: cw = "apelsins"; def = "oranza krasa"; break;
            case 3: cw = "apelsins"; def = "oranza krasa"; break;
            case 4: cw = "apelsins"; def = "oranza krasa"; break;
        }   break;
            case "3":
            case "gruti":
                switch(time) {
            case 1: cw = "mandarins"; def = "oranza krasa bet mazaks"; break;
            case 2: cw = "mandarins"; def = "oranza krasa bet mazaks"; break;
            case 3: cw = "mandarins"; def = "oranza krasa bet mazaks"; break;
            case 4: cw = "mandarins"; def = "oranza krasa bet mazaks"; break;
        }   break;
        }
        return new vards(cw, def); //with String[] or with class vards(cw, def)
    }
    
    static class vards{
        String cw, def;
        
        vards(String cw, String def){
            this.cw = cw;
            this.def = def;
        }
    }
    
    public static void definicijas(String[] definition, int n){
        for(int i=0; i<n; i++){
            System.out.print(i+" "+definition[i]);
            //System.out.println("check");
        }
    }
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String scale = "";
        int n=-1;
        do{
        System.out.println("Izvelies grutibas pakapi - Viegli - 1, Videji - 2, Gruti - 3");
        scale = scan.next().toLowerCase();
        
        if(!(scale.equals("viegli") || scale.equals("videji") || scale.equals("gruti") || scale.equals("1") || scale.equals("2") || scale.equals("3")))
        System.out.println("Nepareizi ievaditi dati");
        
        }while(!(scale.equals("viegli") || scale.equals("videji") || scale.equals("gruti") || scale.equals("1") || scale.equals("2") || scale.equals("3")));
        
        do{
        try{
        System.out.println("Ievadi vardu skaitu");
        n = scan.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Kautkas nesanaca");
            scan.nextLine();
        }
        }while(n<1);
        
        definition = new String[n];
        
        
        random(n, scale);
        definicijas(definition, n);
        scan.close();
    }
}
*/