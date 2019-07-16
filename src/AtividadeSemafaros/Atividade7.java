/*
 * Semáforos podem ser usadas para representar uma fila.
 * Faça um código que implemente duas filas (F1 e F2) com semáforos. 
 * As threads colocadas na F1 só podem executar se tiver um par na F2. 
 * Nesse caso, ambas as threads na primeira fila são executadas.
 */
package AtividadeSemafaros;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade7 {

    public static void main(String[] args) {
        Atividade7 atividade7 = new Atividade7(5, 3);
        atividade7.init();
    }

    Semaphore cankeep01;
    Semaphore cankeep02;
    int numThreads1;
    int numThreads2;
    String concat;
    boolean hasThread2;

    public Atividade7(int numThreads1, int numThreads2) {
        this.cankeep01 = new Semaphore(0);
        this.cankeep02 = new Semaphore(0);
        this.numThreads1 = numThreads1;
        this.numThreads2 = numThreads2;
        this.hasThread2 = false;
    }

    public void semaphore01(String message) {
        this.cankeep02.release();
        try {
            this.cankeep01.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade7.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(message + "|" + this.concat);
        this.concat = "";
    }

    public void semaphore02(String message) {
        this.cankeep01.release();
        try {
            this.cankeep02.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade7.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.concat = message;
    }

    public void init() {
        for (int i = 0; i < this.numThreads1; i++) {
            NothingThread1 thread1 = new NothingThread1(this, "Fila01(id:" + i + ")");
            thread1.start();
        }

        for (int i = 0; i < this.numThreads2; i++) {
            NothingThread2 thread2 = new NothingThread2(this, "Fila02(id:" + i + ")");
            thread2.start();
        }
    }
}

class NothingThread1 extends Thread {

    Atividade7 at7;
    String name;

    public NothingThread1(Atividade7 at7, String name) {
        this.at7 = at7;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            at7.semaphore01(name);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(NothingThread1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class NothingThread2 extends Thread {

    Atividade7 at7;
    String name;

    public NothingThread2(Atividade7 at7, String name) {
        this.at7 = at7;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            at7.semaphore02(name);
            this.sleep(4000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(NothingThread2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
