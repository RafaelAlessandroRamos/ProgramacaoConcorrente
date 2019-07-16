/*
 * Implemente uma solucao para o problema do Barbeiro Dorminhoco usando monitores.
 */
package AtividadeSlide6a;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade4 {

    public static void main(String[] args) {
        Atividade4 atividade4 = new Atividade4(5);
        atividade4.init();
    }

    int[] fila_cadeiras;
    int cadeiras_ocupadas;
    int tam;
    Thread_Barbeiro thr_barbe;
    Thread_GeradorClientes thr_gera;

    public Atividade4(int cadeiras) {
        this.fila_cadeiras = new int[cadeiras];
        this.cadeiras_ocupadas = 0;
        this.tam = cadeiras;
        this.thr_barbe = new Thread_Barbeiro(this);
        this.thr_gera = new Thread_GeradorClientes(this);
    }

    public synchronized void addClient(int timeCutHair) {
        while (this.cadeiras_ocupadas >= (this.tam - 1)) {
            this.thr_gera.sleep(1000);
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Atividade4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.notify();
        System.out.println("Cliente Chegou!");
        this.fila_cadeiras[this.cadeiras_ocupadas] = timeCutHair;
        this.cadeiras_ocupadas++;
    }

    public synchronized void removeClient() {
        while (this.cadeiras_ocupadas <= 0) {
            this.thr_barbe.sleep(1000);
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Atividade4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.notify();
        int aux = this.fila_cadeiras[this.cadeiras_ocupadas - 1];
        this.fila_cadeiras[this.cadeiras_ocupadas - 1] = -1;
        this.cadeiras_ocupadas--;
        this.thr_barbe.cutHair(aux);
    }

    public void init() {
        this.thr_barbe.start();
        this.thr_gera.start();

    }

}

class Thread_Barbeiro extends Thread {

    Atividade4 at4;

    public Thread_Barbeiro(Atividade4 at4) {
        this.at4 = at4;
    }

    @Override
    public void run() {

        while (true) {
            this.at4.removeClient();
        }

    }

    public void sleep(int val) {
        try {
            System.out.println("Thr. Barbeiro Dormiu!");
            Thread.sleep(val);
            System.out.println("Thr. Barbeiro Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cutHair(int timeToCut) {
        try {
            System.out.println("Thr. Barbeiro Ocupado!");
            Thread.sleep(timeToCut);
            System.out.println("Thr. Barbeiro Livre!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class Thread_GeradorClientes extends Thread {

    Atividade4 at4;

    public Thread_GeradorClientes(Atividade4 at4) {
        this.at4 = at4;
    }

    @Override
    public void run() {
        Random gerador = new Random();
        while (true) {
            this.at4.addClient((gerador.nextInt(3) + 1));
        }
    }

    public void sleep(int val) {
        try {
            System.out.println("GeradorClient Dormiu!");
            Thread.sleep(val);
            System.out.println("GeradorClient Acordou!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_GeradorClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
