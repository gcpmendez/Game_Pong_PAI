package es.ull.etsii.pai.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germ�n Paz M�ndez gcpmendez@gmail.com
 *
 */
public class Raqueta {

	private int velocidadRaqueta = 30;				// Velocidad movimiento raqueta
	private int NumeroRaqueta;						// N�mero de raqueta
	private int x, y, width = 20, height = 180;		// Posici�n, altura y anchura de la raqueta.
	private int score;								// Puntuaci�n
	
	/**
	 * Constructor.
	 * @param int anchura
	 * @param int altura
	 * @param int NumeroRaqueta
	 * 
	 */
	public Raqueta(int anchura, int altura, int NumeroRaqueta) {
		this.NumeroRaqueta = NumeroRaqueta;
		if (NumeroRaqueta == 1) {					// Raqueta 1 
			this.x = 10;		
			this.score=0;
		}
		if (NumeroRaqueta == 2) {					// Raqueta 2
			this.x = anchura - this.width - 10;
			this.score=0;
		}
		this.y = altura / 2 - this.height / 2;
	}

	/**
	 * Getters and Setters.
	 * @return
	 * 
	 */
	public int getScore() { 
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumeroRaqueta() {
		return NumeroRaqueta;
	}

	public void setNumeroRaqueta(int paddleNumber) {
		this.NumeroRaqueta = paddleNumber;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getVelocidadRaqueta() {
		return velocidadRaqueta;
	}

	public void setVelocidadRaqueta(int velocidadRaqueta) {
		this.velocidadRaqueta = velocidadRaqueta;
	}
	
	/**
	 * Dibuja el JPANEL. Repaint().
	 * @param Graphics g
	 * 
	 */
	public void dibujar(Graphics g) {			//DIBUJAR RAQUETA
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

	/**
	 * Mueve la raqueta.
	 * @param boolean up
	 * @param int altura
	 * 
	 */
	public void mover(boolean up, int altura) {	// MOVER RAQUETA
		if (up) {								// IF (RAQUETA UP)
			if (y - velocidadRaqueta > 0) {
				y -= velocidadRaqueta;				
			} else {
				y = 0;								// m�nimo EJE Y
			}
		} else {								// IF (RAQUETA DOWN)
			if (y + height + velocidadRaqueta < altura) {
				y += velocidadRaqueta;
			} else {
				y = altura - this.height;			// m�ximo EJE Y
			}
		}
	}



}
