package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
@SuppressWarnings("serial")
public class PongJPanel extends JPanel {
	private static String PONGGAME = "PONG GAME";
	private static String PRESSSPACETOPLAY = "Press SPACE to play";
	private static String TECLASDELJUEGO = "Teclas del juego";
	
	
	private int var1 = 200;
	
	// Jugadores
	public Raqueta player1;
	public Raqueta player2;
	public boolean player; // Nos indica el jugador.
	public Bola ball;

	// Datos Juego
	public int gameStatus = 0, scoreLimit = 7, playerWon;
	boolean blinkState = true;

	public void resetVar() {
		var1=200;
	}
	/**
	 * Dibuja el JPANEL. Repaint().
	 * @param Graphics g
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Quita
		// efecto pantalla

		if (gameStatus == 0) { // MENÚ

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 100));
			g.drawString(PONGGAME, 250, 200);

			blinkState = !blinkState;
			if (blinkState) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 40));
				g.drawString(PRESSSPACETOPLAY, this.getWidth() / 2 - 230, this.getHeight() / 2 - 25);
			} else {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Press Any Key", this.getWidth() / 2 - 150, this.getHeight() / 2 - 25);
			}

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 20));
			g.drawString(TECLASDELJUEGO, this.getWidth() / 2 - this.getWidth() / 12, 400);
			g.drawString("Player 1:", this.getWidth() / 4, 440);
			g.drawString("[w] - Subir raqueta.", this.getWidth() / 4, 480);
			g.drawString("[s] - Bajar raqueta.", this.getWidth() / 4, 500);
			g.drawString("[space] - Pausar juego.", this.getWidth() / 4, 520);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 20));
			g.drawString("Player 2:", this.getWidth() - 500, 440);
			g.drawString("[up] - Subir raqueta.", this.getWidth() - 620, 480);
			g.drawString("[down] - Bajar raqueta.", this.getWidth() - 620, 500);
			g.drawString("[start] - Pausar juego.", this.getWidth() - 620, 520);
		}

		// PAUSED
		if (gameStatus == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 50));
			if (player) {
				g.drawString("PAUSED", this.getWidth() / 2 - (this.getWidth() / 4) - 100, this.getHeight() / 2 + 50);
			} else {
				g.drawString("PAUSED", this.getWidth() / 2 + (this.getWidth() / 4) - 100, this.getHeight() / 2 + 50);
			}
		}

		if (gameStatus == 1 || gameStatus == 2) {
			g.setColor(Color.WHITE);
//			 g.setStroke(new BasicStroke(2f));
			g.drawLine(this.getWidth() / 2, this.getWidth() / 100, this.getWidth() / 2, this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			g.drawLine(this.getWidth() / 100, this.getWidth() / 100, this.getWidth() - (this.getWidth() / 100), this.getWidth() / 100); // Línea
			// superior
			g.drawLine(this.getWidth() / 100, this.getHeight() - (this.getWidth() / 100), this.getWidth() - (this.getWidth() / 100), this.getHeight() - (this.getWidth() / 100)); // Línea
			// inferior
			g.drawLine(this.getWidth() / 100, this.getWidth() / 100, this.getWidth() / 100, this.getHeight() - (this.getWidth() / 100)); // Línea
			// izquierda
			g.drawLine(this.getWidth() - (this.getWidth() / 100), this.getWidth() / 100, this.getWidth() - (this.getWidth() / 100), this.getHeight() - (this.getWidth() / 100)); // Línea
			// izquierda
			g.drawLine((this.getWidth() / 2) - (this.getWidth() / 8), this.getWidth() / 100, (this.getWidth() / 2) - (this.getWidth() / 8), this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			// izquierda
			g.drawLine((this.getWidth() / 2) + (this.getWidth() / 8), this.getWidth() / 100, (this.getWidth() / 2) + (this.getWidth() / 8), this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			// derecha

			
			AffineTransform orig = g2d.getTransform();
			AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
			
			g2d.setTransform(at);
			g.setFont(new Font("Cantarell", 1, 35));
			g.drawString("PAI  PROJECT", this.getWidth() / 100, -(this.getWidth() / 2) + (this.getWidth() / 45));
			at = AffineTransform.getQuadrantRotateInstance(3);
			g2d.setTransform(at);
			g.drawString("PAI  PROJECT", -this.getHeight() + (this.getWidth() / 100), this.getWidth() / 2 + (this.getWidth() / 45));
			g2d.setTransform(orig);

			g2d.rotate(90);
			g.drawString(String.valueOf(player1.getScore()), 100, 100);
			g2d.rotate(-90);

			// Dibujar Player1 y Score
			if (ball.isCambiaScore2()) {
				g.setFont(new Font("Cantarell", 1, 70));
				g.drawString("Point!", this.getWidth() / 2 - (this.getWidth() / 4) - 180, this.getHeight() / 2 - var1);
				var1 = var1 + 10;
			}
			
			if (ball.isCambiaScore1()) {
				g.setFont(new Font("Cantarell", 1, 70));
				g.drawString("Point!", this.getWidth() / 2 + (this.getWidth() / 4) -40, this.getHeight() / 2 - var1);
				var1 = var1 + 10;
			}
			
			g.setFont(new Font("Cantarell", 1, 12));
			g.drawString("Player 1", this.getWidth() / 2 - (this.getWidth() / 4) - 6, this.getHeight() / 2 - 50);
			g.setFont(new Font("Cantarell", 1, 50));
			g.drawString(String.valueOf(player1.getScore()), this.getWidth() / 2 - (this.getWidth() / 4), this.getHeight() / 2);

			// Dibujar Player2 y Score
			g.setFont(new Font("Cantarell", 1, 12));
			g.drawString("Player 2", this.getWidth() / 2 + (this.getWidth() / 4) - 6, this.getHeight() / 2 - 50);
			g.setFont(new Font("Cantarell", 1, 50));
			g.drawString(String.valueOf(player2.getScore()), this.getWidth() / 2 + (this.getWidth() / 4), this.getHeight() / 2);

			// Creamos jugadores y bola
			player1.dibujar(g);
			player2.dibujar(g);
			if (!ball.isCambiaScore1() && !ball.isCambiaScore2()) {
				ball.dibujar(g);
			}
		}

		if (gameStatus == 3) { // JUEGO TERMINADO
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 70));
			g.drawString("Player " + playerWon + " Wins!", this.getWidth()/2 - 250, 350);
			g.setFont(new Font("Cantarell", 1, 17));
			g.drawString("Press SPACE to play again!", this.getWidth()/2 - 100, 400);

		}
	}

}
