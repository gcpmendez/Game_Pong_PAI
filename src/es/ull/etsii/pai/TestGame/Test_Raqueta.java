package es.ull.etsii.pai.TestGame;


import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;



import org.junit.Before;
import org.junit.Test;

import es.ull.etsii.pai.Game.Raqueta;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
public class Test_Raqueta {
	private Raqueta player1;
		
	@Before
	public void before() {
		player1 = new Raqueta(1200, 700, 1);
		
	}
	
	@Test 
	public void variables() {
		assertEquals("Variable velocidad de la raqueta = 15", player1.getVelocidadRaqueta(), 30);
	}
	
	@Test 
	public void moverArriba() {
		int player1yAnterior = player1.getY();
		player1.mover(true, 700);
		assertEquals("Comprobar que sube la raqueta su posición con v = 15", player1.getY(), player1yAnterior-30); 
	}
	
	@Test 
	public void moverAbajo() {
		int player1yAnterior = player1.getY();
		player1.mover(false, 700);
		assertEquals("Comprobar que baja la raqueta su posición con v = 15", player1.getY(), player1yAnterior+30); 
	}

}
