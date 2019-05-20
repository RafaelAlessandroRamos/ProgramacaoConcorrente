/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtividadeSlide6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(40);
        buffer.init();
    }
}

class Buffer {

    int[] buffer;
    int qtd;
    int tam;

    public Buffer(int tam) {
        this.buffer = new int[tam];
        this.qtd = 0;
        this.tam = tam;
    }

    public synchronized void addValue(int val) {
        while (this.qtd >= this.tam) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.buffer[this.qtd] = val;
        this.qtd++;
        this.notify();
    }

    public synchronized int getValeu() {
        int saida;

        while (this.qtd <= 0) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        saida = this.buffer[this.qtd - 1];
        this.qtd--;
        this.notifyAll();

        return saida;
    }

    public int getTam() {
        return this.tam;
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
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
