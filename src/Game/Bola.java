package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
public class Bola {

	private int x, y, width = 10, height = 10;   	// Posición, altura y anchura de la bola.
	private int movX, movY;			// movX y movY
	private Random random;
	private int golpes;								// Golpes para aumentar velocidad a partir de 5
	private boolean cambiaScore1 = false;
	private boolean cambiaScore2 = false;
	private Sonido sonido1 = new Sonido();
	private Sonido sonido2 = new Sonido();
	
	/**
	 * Constructor.
	 * 
	 */
	public Bola(int width, int height) {
		this.random = new Random();
		this.reset(width, height);
	}
	
	/**
	 * Getters and Setters.
	 * @return
	 * 
	 */
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getGolpes() {
		return golpes;
	}

	public void setGolpes(int golpes) {
		this.golpes = golpes;
	}
	
	public boolean isCambiaScore1() {
		return cambiaScore1;
	}

	public void setCambiaScore1(boolean cambiaScore) {
		this.cambiaScore1 = cambiaScore;
	}
	
	public boolean isCambiaScore2() {
		return cambiaScore2;
	}

	public void setCambiaScore2(boolean cambiaScore) {
		this.cambiaScore2 = cambiaScore;
	}
	/**
	 * Actualiza el movimiento de la pelota conforme a los parámetros.
	 * @param Raqueta raqueta1
	 * @param Raqueta raqueta2
	 * @param int anchura
	 * @param int altura
	 * 
	 */
	public void update(Raqueta raqueta1, Raqueta raqueta2, int anchura, int altura) {
		int speed = 15;

		this.x += movX * speed;
		this.y += movY * speed;

		if (this.y + height - movY > altura || this.y + movY < 0) {	// La pelota colisiona con las paredes y movimiento
			if (this.movY < 0) {
				this.y = 0;
				this.movY = 1; //random.nextInt(4);

				if (movY == 0) {
					movY = 1;
				}
			} else {
				this.movY = -1; //random.nextInt(4);
				this.y = altura - height;

				if (movY == 0) {
					movY = -1;
				}
			}
			Sonido.playSound("Recursos/Sonidos/pared.wav");
			sonido1.run();
		}

		if (checkCollision(raqueta1) == 1) {			// Si colisiona con raqueta
		
			this.movX = 1 + (golpes / 5);
			this.movY = -2 + random.nextInt(4);

			if (movY == 0) {
				movY = 1;
			}
			Sonido.playSound("Recursos/Sonidos/pongblip.wav");
			sonido1.run();
			golpes++;
		} else if (checkCollision(raqueta2) == 1) {		// La pelota colision con la raqueta 2
			
			this.movX = -1 - (golpes / 5);
			this.movY = -2 + random.nextInt(4);

			if (movY == 0) {
				movY = 1;
			}
			Sonido.playSound("Recursos/Sonidos/pongblip2.wav");
			sonido1.run();
			golpes++;
		}

		if (checkCollision(raqueta1) == 2) {	// Si colisiona con los bordes izquierdo y derecho sumamos +1
			raqueta2.setScore(raqueta2.getScore() + 1);
			cambiaScore1 = true;
			reset(anchura, altura);
			Sonido.playSound("Recursos/Sonidos/point.wav");
			sonido2.run();
		} else if (checkCollision(raqueta2) == 2) {
			raqueta1.setScore(raqueta1.getScore()+1);
			cambiaScore2 = true;
			reset(anchura, altura);
			Sonido.playSound("Recursos/Sonidos/point.wav");
			sonido2.run();
		}
	}

	

	/**
	 * Resetea valores una vez se ha establecido el punto.
	 * @param int anchura
	 * @param int altura
	 */
	public void reset(int anchura, int altura) {
		this.golpes = 0;
		this.x = anchura / 2 - this.width / 2;	// Pelota en el centro
		this.y = altura / 2 - this.height / 2;
		
		this.movY = -2 + random.nextInt(4);

		if (movY == 0) {
			movY = 1;
		}

		if (random.nextBoolean()) {  // Devuelve true o false de una forma pseudoaleatoria
			movX = 1;
		} else {
			movX = -1;
		}
	}
	
	/**
	 * Comprueba si hay colisiones.
	 * @return 0 "no pasa nada"
	 * @return 1 "rebota en los bordes superior e inferior"
	 * @return 2 "puntúa en los bordes izquierdo y derecho"
	 * 
	 */
	public int checkCollision(Raqueta raqueta) {
		if (this.x < raqueta.getX() + raqueta.getWidth() && 
				this.x + width > raqueta.getX() && 
				this.y < raqueta.getY() + raqueta.getHeight() &&
				this.y + height > raqueta.getY()) {
			return 1; 	// rebotar con raqueta
		} else if ((raqueta.getX() > this.x+10 && raqueta.getNumeroRaqueta() == 1) || 
				(raqueta.getX() < x - width && raqueta.getNumeroRaqueta() == 2)) {
			return 2; 	// Puntúa en los bordes izquierdo y derecho
		}
		return 0; 		// nada
	}

	/**
	 * Dibuja la pelota en el Gráfico pasado por parámetro.
	 * @param Graphics g
	 * 
	 */
	public void dibujar(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}

}
