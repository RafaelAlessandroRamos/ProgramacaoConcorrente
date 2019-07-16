/*
 * Enviar sinalização para um ponto de encontro entre threads.
 * Faça um código que uma thread t1 e t2 são inicializadas e t1 espera por t2 
 * e vice-versa.

 */
package AtividadeSemafaros;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 {
    
    public static void main(String[] args) {
        Atividade2 atividade2 = new Atividade2();
        atividade2.init();
    }
    
    Semaphore semaphore;
    Trecho_Thread thread1;
    Trecho_Thread thread2;
    
    public Atividade2() {
        this.semaphore = new Semaphore(1);
        this.thread1 = new Trecho_Thread(this,"1");
        this.thread2 = new Trecho_Thread(this,"2");
    }
    
    public void trecho(String message){
        try {
            this.semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(message);
        this.semaphore.release();
    }
    
    public void init(){
        this.thread1.start();
        this.thread2.start();
    }
}

class Trecho_Thread extends Thread {

    Atividade2 at2;
    String name;
    int trecho;
    
    public Trecho_Thread(Atividade2 at2, String name) {
        this.at2 = at2;
        this.trecho = 0;
        this.name = name;
    }
    
    @Override
    public void run() {
        while (true) {
            this.trecho += 1;
            at2.trecho("Trecho"+this.name+"."+this.trecho);
            sleep(2000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Trecho_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}