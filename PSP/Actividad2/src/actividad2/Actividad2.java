package actividad2;

import static java.lang.Thread.sleep;

/* Tic */
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
}

/* Tac */
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
}

public class Actividad2 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        
		Hilo1 h1 = new Hilo1();
		Hilo2 h2 = new Hilo2();
		h1.start();
		h2.start();
	}
}
