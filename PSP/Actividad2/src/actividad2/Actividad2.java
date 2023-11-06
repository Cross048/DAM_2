/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad2;

import static java.lang.Thread.sleep;

class Hilo1 extends Thread {
	public void run() {
		while (true) {
			System.out.print(" TIC ");
			try {
				sleep(30);					
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}//tic

class Hilo2 extends Thread {
	public void run() {
		while (true) {
			System.out.print(" TAC ");
			try {
				sleep(40);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}//tac

public class Actividad2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
		Hilo1 h1 = new Hilo1();
		Hilo2 h2 = new Hilo2();
		h1.start();
		h2.start();
	}// main

    }
