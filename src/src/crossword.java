package src;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.database;

public class crossword {

	public static void veidot() {
		String a, teksts, msg;
		ArrayList<String> vards = new ArrayList<>();
		do {
		teksts = String.join("\n", vards);
		
		if(teksts.isEmpty())
			msg = "Ieraksti vardu";
		else
			msg = teksts+"\nIeraksti vardu";
		
		
		a = JOptionPane.showInputDialog(null, msg);
		
		if(a != null) {
			a = a.trim();
		if(!a.isEmpty()) 
			vards.add(a);
		}
		
		if (a == null && vards.isEmpty())
			JOptionPane.showMessageDialog(null, "Ievadi vismaz vienu vardu", "Opa", JOptionPane.ERROR_MESSAGE);
			
		
		}while(a != null || vards.isEmpty()); 
		
		do {
		a = JOptionPane.showInputDialog(null, teksts+"\nKuru no siem vardiem velies dzest? (Atstaj tuksu lai ietu uz prieksu");
		}while(a.isEmpty());
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
		String a = JOptionPane.showInputDialog(null, "1 - Vieglu 2 - Videju 3 - Grutu");
	}
	
	public static void main(String[] args) {
		
		Connection conn = database.getConnection();
		if (conn != null) {
            System.out.println("Savienojums veiksmigs");
        }
		
		String a = JOptionPane.showInputDialog(null, "1 - Veidot savu crossword \n2 - Izveleties gatavu crossword");
		
		switch (a) {
		case "1": 
			veidot();
		break;
		case "2":
			izveleties();
		break;
	}
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