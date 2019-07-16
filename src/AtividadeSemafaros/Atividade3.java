/*
 * Garantir acesso exclusivo à seção crítica.
 * Faça um código que possibilite que 2 ou mais threads realizem o incremento 
 * de um contador. Faça a exclusão mútua com semáforo.
 */
package AtividadeSemafaros;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade3 {
    
    public static void main(String[] args) {
        Atividade3 atividade3 = new Atividade3();
        atividade3.init();
    }
    
    int count;
    Semaphore semaphore;
    Increments_Threads thr_01;
    Increments_Threads thr_02;
    Increments_Threads thr_03;

    public Atividade3() {
        this.count = 0;
        this.semaphore = new Semaphore(1);

        this.thr_01 = new Increments_Threads(this,"thr01");
        this.thr_02 = new Increments_Threads(this,"thr02");
        this.thr_03 = new Increments_Threads(this,"thr03");
    }

    public void increment(String name) {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade3.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.count++;
        System.out.println(name+"|Contador: " + this.count);
        this.semaphore.release();
    }
    
    public void init(){
        this.thr_01.start();
        this.thr_02.start();
        this.thr_03.start();
    }
}

class Increments_Threads extends Thread {

    Atividade3 at3;
    String name;

    public Increments_Threads(Atividade3 at3, String name) {
        this.at3 = at3;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {            
            this.at3.increment(this.name);
            sleep(3000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Increments_Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}