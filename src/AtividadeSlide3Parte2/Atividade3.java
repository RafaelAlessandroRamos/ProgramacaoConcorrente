/*
 * Faca um programa Java que envia interrupcoes para as threads dos exercıcios
 * anteriores. As threads devem fazer o tratamento dessas interrupcoes e realizar
 * uma finalizacao limpa.
 */
package AtividadeSlide3Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Ativ1());
        Thread t2 = new Thread(new Ativ2());

        t1.start();
        t2.start();

        t1.interrupt();
        t2.interrupt();

        if (t1.isInterrupted()) {
            System.out.println("t1 interrompida");
        }
        if (t1.isInterrupted()) {
            System.out.println("t2 interrompida");
        }

    }

}

class Ativ1 implements Runnable {

    @Override
    public void run() {
        System.out.println("thread id: " + Thread.currentThread().getId());
        while (true) {
            try {
                long x = (long) (Math.random() * 9000);
                Thread.sleep(x);
                System.out.println(" After " + x + " seconds , I am here " + Thread.currentThread().getId());
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(Ativ1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class Ativ2 implements Runnable {

    @Override
    public void run() {
        FileReader arq;
        BufferedReader lerArq = null;
        try {
            arq = new FileReader("src/AtividadeSlide3Parte2/frases.txt");
            lerArq = new BufferedReader(arq);
            while (lerArq.readLine() != null) {
                System.out.println(lerArq.readLine());
                Thread.sleep(10000);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
