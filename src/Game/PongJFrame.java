package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
@SuppressWarnings("serial")
public class PongJFrame extends JFrame implements ActionListener, KeyListener {

	// Objetos
	public static PongJFrame pong;
	public PongJPanel renderer;
	
	// Timer
	Timer timer = new Timer(20, this);		// Para el juego
	Timer timer2 = new Timer(500, this);	// Para el menu
	Timer timer3 = new Timer(500, this);	// Para el point!
	private int contador = 0;
	
	// Botones
	private boolean w, s, up, down;

	// {0, Menu}, {1, Paused}, {2, Playing}, {3, finish}

	/**
	 * Constructor
	 * 
	 */
	public PongJFrame() {

		new Random();

		renderer = new PongJPanel();

		add(renderer);
		addKeyListener(this);

		timer2.start();
	}

	/**
	 * Empezamos el juego creando los objetos necesarios.
	 * 
	 */
	public void start() { // EMPEZAR JUEGO
		renderer.gameStatus = 2;
		renderer.player1 = new Raqueta(this.getWidth(), this.getHeight(), 1);
		renderer.player2 = new Raqueta(this.getWidth(), this.getHeight(), 2);
		renderer.ball = new Bola(this.getWidth(), this.getHeight());
	}

	/**
	 * Actualiza el juego.
	 * 
	 */
	public void update() { // ACTUALIZAR JUEGO
		
		if (renderer.player1.getScore() >= renderer.scoreLimit) {
			renderer.playerWon = 1;
			renderer.gameStatus = 3;
		}

		if (renderer.player2.getScore() >= renderer.scoreLimit) {
			renderer.gameStatus = 3;
			renderer.playerWon = 2;
		}
		if (renderer.ball.isCambiaScore2() == false && renderer.ball.isCambiaScore1() == false) {
			contador = 0;
			renderer.resetVar();
		}
		if (renderer.ball.isCambiaScore1() == true) {
			timer.stop();
			timer3.start();
			renderer.ball.reset(this.getWidth(), this.getHeight());
//			System.out.println("timer: "+timer.wait(timer3, 1000););
			System.out.println("contador: "+contador);
			if (contador == 3) {
				timer3.stop();
				timer.start();
				renderer.ball.setCambiaScore1(false);
			}
		}
		
		if (renderer.ball.isCambiaScore2() == true) {
			timer.stop();
			timer3.start();
			renderer.ball.reset(this.getWidth(), this.getHeight());
//			System.out.println("timer: "+timer.wait(timer3, 1000););
			System.out.println("contador: "+contador);
			if (contador == 3) {
				timer3.stop();
				timer.start();
				renderer.ball.setCambiaScore2(false);
			}
		}
		if (w) {
			renderer.player1.mover(true, this.getHeight());
		}
		if (s) {
			renderer.player1.mover(false, this.getHeight());
		}

		if (up) {
			renderer.player2.mover(true, this.getHeight());
		}
		if (down) {
			renderer.player2.mover(false, this.getHeight());
		}
		System.out.println("width: "+this.getWidth());
		System.out.println("width: "+this.getHeight());
		renderer.ball.update(renderer.player1, renderer.player2, this.getWidth(), this.getHeight());
	}

	/**
	 * Donde actúan los timer.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (renderer.gameStatus == 2) { // PLAYING A 0,02seg
			renderer.repaint();
			update();
		}
		if (renderer.gameStatus == 0) { // MENÚ A 1seg
			renderer.repaint();
		}
		if (renderer.gameStatus == 1) { // MENÚ A 1seg
			renderer.repaint();
		}
		contador++;
	}

	/**
	 * Listener. Cuando presionemos la tecla.
	 * @param KeyEvent e
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			w = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_SPACE:
			if (renderer.gameStatus == 0 || renderer.gameStatus == 3) { // Menú
																		// o
																		// Terminado
				timer2.stop();
				timer.start();
				start();
			} else if (renderer.gameStatus == 1) { // Paused
				renderer.gameStatus = 2;
			} else if (renderer.gameStatus == 2) { // Poner PAUSED en Player1
				renderer.gameStatus = 1;
				renderer.player = true;
			}
			break;
		case KeyEvent.VK_ENTER:
			if (renderer.gameStatus == 2) { // IF (PLAYING - Player2) THEN
											// (PAUSED)
				renderer.gameStatus = 1;
				renderer.player = false;
			} else if (renderer.gameStatus == 1) { // IF (PAUSED - Player2) THEN
				// (PLAYING)
				renderer.gameStatus = 2;
			}
			break;
		}

	}

	/**
	 * Listener. Cuando soltemos la tecla.
	 * @param KeyEvent e
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W) {
			w = false;
		} else if (id == KeyEvent.VK_S) {
			s = false;
		} else if (id == KeyEvent.VK_UP) {
			up = false;
		} else if (id == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
