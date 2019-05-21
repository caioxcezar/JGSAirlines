package utils;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextNumber {
	public static int textInteger(String str) {
		try {
			int converted = Integer.parseInt(str);
			return converted;
		} catch (Exception z) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Somente Numeros Inteiros",
				    "Aviso",
				    JOptionPane.WARNING_MESSAGE);
        	return -1;
       }
	}
	public static double textDouble(String str) {
		try {
			double converted = Double.parseDouble(str);
			return converted;
		} catch (Exception z) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Somente Numeros",
				    "Aviso",
				    JOptionPane.WARNING_MESSAGE);
        	return -1;
       }
	}
}
