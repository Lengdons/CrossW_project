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
