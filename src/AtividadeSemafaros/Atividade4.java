/*
 * Garantir acesso à seção crítica para no máximo N threads.
 * Faça um código que possibilite que N threads estejam na seção crítica 
 * simultaneamente.
 */
package AtividadeSemafaros;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade4 {
    
    public static void main(String[] args) {
        Atividade4 atividade4 = new Atividade4(3, 9);
        atividade4.init();
    }
    
    Semaphore semaphore;
    int numThreads;
    int count;

    public Atividade4(int sizeSemaphoro, int numThreads) {
        this.semaphore = new Semaphore(sizeSemaphoro);
        this.numThreads = numThreads;
        this.count = 0;
    }

    public void increments(String name) {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade4.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.count++;
        System.out.println(name + "|Contador: " + this.count);
        this.semaphore.release();
    }

    public void init() {
        for (int i = 0; i < this.numThreads; i++) {
            Increment_Thread thread = new Increment_Thread(this, "Thread" + i);
            thread.start();
        }
    }

}


class Increment_Thread extends Thread {

    Atividade4 at4;
    String name;

    public Increment_Thread(Atividade4 at4, String name) {
        this.at4 = at4;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {            
            this.at4.increments(this.name);
            sleep(3000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Increment_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
