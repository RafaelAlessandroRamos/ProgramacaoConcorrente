/*
 * Enviar sinal para outra thread para indicar que um evento ocorreu.
 * Faça um código que uma thread t1 e t2 são inicializadas simultaneamente, 
 * mas a t2 pode somente continuar a execução após a sinalização de t1.
 */
package AtividadeSemafaros;

import java.util.concurrent.Semaphore;
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

    Semaphore semaphore;
    Dummy_Thread thread1;
    Dummy_Thread thread2;

    public Atividade1() {
        this.semaphore = new Semaphore(1);
        this.thread1 = new Dummy_Thread(this, "Thread01");
        this.thread2 = new Dummy_Thread(this, "Thread02");
    }

    public void message(String message) {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(message);
        this.semaphore.release();
    }

    public void init() {
        this.thread1.start();
        this.thread2.start();
    }
}

class Dummy_Thread extends Thread {

    Atividade1 at1;
    String name;

    public Dummy_Thread(Atividade1 at1, String name) {
        this.at1 = at1;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            this.at1.message(this.name + " Fazendo nada...");
            this.sleep(2000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Dummy_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
