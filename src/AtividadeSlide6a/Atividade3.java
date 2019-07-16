/*
 * Escreva um monitor BoundedCounter que possui um valor m´ınimo e m´aximo. A 
 * classe possui dois m´etodos: increment() e decrement(). Ao alcancar os limites 
 * mınimo ou m´aximo, a thread que alcan¸cou deve ser bloqueada.
 */
package AtividadeSlide6a;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade3 {

    public static void main(String[] args) {
        Atividade3 atividade3 = new Atividade3(50, 200);
        atividade3.init();
    }

    Thread_Decrement thr_decre;     //Tread apenas para decrementar
    Thread_Increment thr_incre;     //Tread apenas para incrementar
    SeeValues_Thread thr_see;       //Tread apenas para ver valores

    int min;
    int max;
    int boundedCounter;

    public Atividade3(int min, int max) {
        this.thr_decre = new Thread_Decrement(this, 10); //2º Atributo é duração
        this.thr_incre = new Thread_Increment(this, 8); //2º Atributo é duração
        this.thr_see = new SeeValues_Thread(this, 80); //2º Atributo é duração
        this.min = min;     //Valor minimo
        this.max = max;     //Valor maximo
        this.boundedCounter = (min + max) / 2;     //Counter
    }

    public synchronized void increment() {

        while (this.boundedCounter > this.max) {
            try {
                this.notify();
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Atividade3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.notify();
        this.boundedCounter++;
    }

    public synchronized void decrement() {

        while (this.boundedCounter < this.min) {
            try {
                this.notify();
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Atividade3.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        this.boundedCounter--;
    }

    public void init() {
        this.thr_incre.start();
        this.thr_decre.start();
        this.thr_see.start();

    }

    public int getBoundedCounter() {
        return this.boundedCounter;
    }
}

class Thread_Decrement extends Thread {

    Atividade3 at3;
    int time;

    public Thread_Decrement(Atividade3 at3, int time) {
        this.at3 = at3;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            this.at3.decrement();
            sleep(this.time);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            //System.out.println("Thread Decrement Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Decrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Thread_Increment extends Thread {

    Atividade3 at3;
    int time;

    public Thread_Increment(Atividade3 at3, int time) {
        this.at3 = at3;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            this.at3.increment();
            sleep(this.time);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            //System.out.println("Thread Increment Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Increment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class SeeValues_Thread extends Thread {

    Atividade3 at3;
    int time;

    public SeeValues_Thread(Atividade3 at3, int time) {
        this.at3 = at3;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Bounded Value: " + this.at3.getBoundedCounter());
            sleep(this.time);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(SeeValues_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
