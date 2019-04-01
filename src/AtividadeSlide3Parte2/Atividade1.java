/* Faca um programa em Java que inicie tres threads e, cada thread, espere um 
 * tempo aleatorio para terminar.
 */
package AtividadeSlide3Parte2;

import java.util.Random;

/**
 *
 * @author rafael
 */
public class Atividade1 implements Runnable {

    @Override
    public void run() {
        System.out.println("thread id: " + Thread.currentThread().getId());
        while (true) {
            try {
                long x = (long) (Math.random() * 9000);
                Thread.sleep(x);
                System.out.println(" After " + x + " seconds , I am here " + Thread.currentThread().getId());
                break;
            } catch (InterruptedException e) {
                System.out.println( "My sleep time was interrupted");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Atividade1());
        Thread t2 = new Thread(new Atividade1());
        Thread t3 = new Thread(new Atividade1());
        
        t1.start();
        t2.start();
        t3.start();
    }
}
