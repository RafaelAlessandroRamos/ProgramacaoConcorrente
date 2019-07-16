/*
 * Faça a implementação do produtor consumidor com fila circular usando 
 * o bloqueio com semaforos
 */
package AtividadeSlide7;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 {
    public static void main(String[] args) {
        Buffer_Circular buffer = new Buffer_Circular(5);
        buffer.init();   
    }
}

class Buffer_Circular {

    int[] buffer;
    int pos;
    int tam;

    Semaphore mutex;
    Semaphore vazio;
    Semaphore cheio;

    public Buffer_Circular(int tam) {
        this.buffer = new int[tam];
        this.pos = 0;
        this.tam = tam;
        this.mutex = new Semaphore(1);
        this.vazio = new Semaphore(0);
        this.cheio = new Semaphore(tam);
    }

    public void addValue(int val) {
        try {
            this.cheio.acquire();
            this.mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer_Circular.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (this.pos >= this.tam) {
            this.pos = 0;
            System.out.println("Zerou!");
        }
        this.buffer[this.pos] = val;
        this.pos++;
        this.mutex.release();
        this.vazio.release();

    }

    public int getValeu() {
        int saida;
        try {
            this.vazio.acquire();
            this.mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer_Circular.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida = this.buffer[this.pos - 1];
        this.pos--;
        this.mutex.release();
        this.cheio.release();
        return saida;
    }

    public void init() {
        Consumidor thread_Cons = new Consumidor(this);
        Produtor thread_Prod = new Produtor(this);
        thread_Prod.start();
        thread_Cons.start();
    }
}

class Produtor extends Thread {

    Buffer_Circular buffer;

    public Produtor(Buffer_Circular buff) {
        this.buffer = buff;
    }

    @Override
    public void run() {
        Random gerador = new Random();
        int number;

        while (true) {
            number = gerador.nextInt(10) + 1;
            this.buffer.addValue(number);
            sleep(number);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            System.out.println("Thread Produtor Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Consumidor extends Thread {

    Buffer_Circular buffer;

    public Consumidor(Buffer_Circular buff) {
        this.buffer = buff;
    }

    @Override
    public void run() {
        int value;
        while (true) {
            System.out.println("Removendo....");
            value = this.buffer.getValeu();
            System.out.println("Numero: " + value);
            sleep(value * 10);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            System.out.println("Thread Consumidor Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
