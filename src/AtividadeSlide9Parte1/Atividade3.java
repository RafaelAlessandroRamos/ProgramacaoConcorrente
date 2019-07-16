/*
 * Faca uma classe ArrayListThreadSafe usando ReadWriteLock. Teste usando threads
 * que realizam leitura e escrita para essa estrutura.
 */
package AtividadeSlide9Parte1;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade3 {
    
    public static void main(String[] args) {
        Atividade3 atividade3 = new Atividade3();
        atividade3.init(3, 5);
    }

    String texto;
    ReentrantReadWriteLock readWriteLock;

    public Atividade3() {
        this.texto = "";
        this.readWriteLock = new ReentrantReadWriteLock();

    }

    public void escrever(String texto) {
        this.readWriteLock.writeLock().lock();
        try {
            this.texto += texto;
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    public String ler() {
        this.readWriteLock.readLock().lock();
        try {
            return this.texto;
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void init(int numLeitores, int numEscritores) {
        for (int i = 0; i < numLeitores; i++) {
            Thread_Leitor threadLeitores = new Thread_Leitor(this);
            threadLeitores.start();
        }

        for (int i = 0; i < numEscritores; i++) {
            Thread_Escritor threadEscritores = new Thread_Escritor(this);
            threadEscritores.start();
        }
    }
}

class Thread_Leitor extends Thread {

    Atividade3 at3;
    Random gerador;

    public Thread_Leitor(Atividade3 at3) {
        this.at3 = at3;
        this.gerador = new Random();
    }

    @Override
    public void run() {
        while (true) {
            String str = at3.ler();
            System.out.println("Leitor> " + str);
            sleep((gerador.nextInt(9) + 1) * 1000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


class Thread_Escritor extends Thread {

    Atividade3 at3;
    Random gerador;
    
    public Thread_Escritor(Atividade3 at3) {
        this.at3 = at3;
        this.gerador = new Random();
    }

    @Override
    public void run() {
        while (true) {
            this.at3.escrever("a ");
            System.out.println("Escritor> a");
            sleep((gerador.nextInt(9)+1)*1000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
