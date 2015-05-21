package es.ull.etsii.pai.Game;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Germán Paz Méndez gcpmendez@gmail.com
 *
 */
public class Sonido extends Thread {
	public static synchronized void playSound(String music) {
		  new Thread(new Runnable() {

		    public void run() {
		    	File file = new File(music); 
		      try {
		    	    
		        Clip sound = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
		        sound.open(AudioSystem.getAudioInputStream(file));
		        sound.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}