package Game;

import javax.swing.JFrame;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germ�n Paz M�ndez gcpmendez@gmail.com
 *
 */

public class Main {

	public static void main(String[] args) {
		PongJFrame frame = new PongJFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
