package es.ull.etsii.pai.TestGame;


import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;

import es.ull.etsii.pai.Game.Bola;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
public class Test_Bola {
	private Bola bola;
		
	@Before
	public void before() {
		bola = new Bola(1200, 700);
	}
	
	@Test 
	public void resetBola() {
		bola.reset(1200, 700);				//Ponemos la bola en el centro
		assertEquals("Resetear variables: bola.golpes = 0", bola.getGolpes(), 0);
		assertEquals("Resetear variables: bola.x = 595", bola.getX(), 595);
		assertEquals("Resetear variables: bola.y = 345", bola.getY(), 345);
	}
	

}
