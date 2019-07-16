/*
 * Fa¸ca um programa usando Lock para simular a atualizacao de um contador que e
 * acessado por multiplas threads. O contador pode diminuir e aumentar.
 */
package AtividadeSlide9Parte1;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 {
    
    public static void main(String[] args) {
        Atividade1 atividade1 = new Atividade1();
        atividade1.init();
    }

    int contador;
    ReentrantLock lock;

    public Atividade1() {
        this.contador = 0;
        this.lock = new ReentrantLock();
    }

    public void incrementDecrement(boolean increase) {
        this.lock.lock();
        try {
            if (increase) {
                this.contador++;
            } else {
                this.contador--;
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void verValor() {
        System.out.println("Valor: " + this.contador);
    }

    public void init() {
        Thread_IncrementDecrement thread1 = new Thread_IncrementDecrement(this, true);
        Thread_IncrementDecrement thread2 = new Thread_IncrementDecrement(this, false);

        thread1.start();
        thread2.start();
    }
}

class Thread_IncrementDecrement extends Thread {

    Atividade1 at1;
    boolean increase;

    public Thread_IncrementDecrement(Atividade1 at1, boolean increase) {
        this.at1 = at1;
        this.increase = increase;
    }

    @Override
    public void run() {
        while (true) {
            this.at1.incrementDecrement(this.increase);
            this.at1.verValor();
            //sleep(2000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_IncrementDecrement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
