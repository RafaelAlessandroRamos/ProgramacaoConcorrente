/*
 * Implemente uma solucao com monitor para o problema do Produtor-Consumidor 
 * usando um buffer circular.
 */
package AtividadeSlide6a;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        buffer.init();

    }
}

class Buffer {

    int[] buffer;
    int indiceAtual;
    int limite;

    public Buffer(int tam) {
        this.buffer = new int[tam];
        this.indiceAtual = 0;
        this.limite = tam;
    }

    public synchronized void addValue(int val) {

        this.buffer[this.indiceAtual] = val;
        this.indiceAtual = (this.indiceAtual + 1) % limite;
        this.notify();

    }

    public synchronized int getValeu() {
        int saida;

        while (this.indiceAtual <= 0) {
            try {
                this.wait();
                System.out.println("Buffer Vazio!");
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        saida = this.buffer[this.indiceAtual - 1];
        this.indiceAtual--;
        this.notifyAll();

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

    Buffer buffer;

    public Produtor(Buffer buff) {
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

    Buffer buffer;

    public Consumidor(Buffer buff) {
        this.buffer = buff;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Removendo....");
            sleep(1000);

            System.out.println("Numero: " + this.buffer.getValeu());
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
            System.out.println("Thread Produtor Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
