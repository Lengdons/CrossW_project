package src;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import db.database;

import javax.swing.JOptionPane;

public class crossword {

	public static void veidot() {
		StringBuilder build = new StringBuilder();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				build.append("⬜ \t");
			}
			build.append("\n");
		}
		JOptionPane.showMessageDialog(null, build.toString());
	}
	
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

class Main {

    public static void gridv(String cw){
        // System.out.println(cw);
        for(int i=0; i<cw.length(); i++){
            for(int j=0; j<1; j++){
                System.out.print("[]");
            }
            System.out.println();
        }
    }
   /* public static void ver(){
        System.out.println("verti"); 
    } /



    public static void gridh(String cw){
        // System.out.println(cw);
        for(int i=0; i<1; i++){
            for(int j=0; j<cw.length(); j++){
                System.out.print("[]");
            }
            System.out.println();
        }
    }
    / public static void horz(){
        System.out.println("horiz");
    } /


    public static void random(int n) {
        do{
        int a = (int)((Math.random()100)+1);
        n--;
        // System.out.print(a+" "+a%2);

        if(a%2==0){
            //ver();
            gridv(vards());
        }else{
            //horz();
            gridh(vards());
        }

        }while(n>0);

    }

    public static String vards(){
        String cw=" ", def;
        int time = (int)((Math.random()*4)+1);
        switch(time) {
            case 1: cw = "banans"; def = "dzeltens"; break;
            case 2: cw = "apelsins"; def = "oranza krasa"; break;
            case 3: cw = "abols"; def = "aug koka"; break;
            case 4: cw = "mandarins"; def = "oranza krasa bet mazaks"; break;
        }
        return cw; //with String[] or with class vards(cw, def)
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ievadi vardu skaitu");
        int n = scan.nextInt();
        random(n);
        scan.close();
    }
}
*/