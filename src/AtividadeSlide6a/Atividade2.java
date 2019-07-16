/*
 * Escreva uma monitor Counter que possibilita um processo dormir ate o contador
 * alcancar um valor. A classe Counter permite duas opera¸c˜oes: increment() e 
 * sleepUntil(int x).
 */
package AtividadeSlide6a;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 {
    
    public static void main(String[] args) {
        Atividade2 atividade2 = new Atividade2(4);
        atividade2.init();
    }
    
    
    int time_toWeakUp;
    int i;
    Dummy_Thread dummy;

    public Atividade2(int time) {
        this.i = 0;
        this.dummy = new Dummy_Thread(this);
        this.time_toWeakUp = time;
    }

    public synchronized void increment() {
        this.i++;
    }

    public synchronized void dummy() {
        System.out.println("Thread Dummy Dormiu!");

        while (this.i <= this.time_toWeakUp) {
            this.dummy.sleep(1000);
            increment();

            //this.wait();
        }

        //this.notify();
        System.out.println("Thread Dummy Acordou!");
        System.out.println("Fanzendo nada....");
        this.i = 0;
    }

    public void init() {
        this.dummy.start();
    }

}

class Dummy_Thread extends Thread {

    Atividade2 at02;

    public Dummy_Thread(Atividade2 ex) {
        this.at02 = ex;
    }

    @Override
    public void run() {
        while (true) {
            this.at02.dummy();
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            //System.out.println("Thread Dummy Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Dummy_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
}