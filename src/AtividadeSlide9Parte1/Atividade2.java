/*
 * Crie uma classe SharedFifoQueue e use Conditions para controlar se a fila 
 * esta vazia ou cheia. Teste usando threads produtoras e consumidoras
 */
package AtividadeSlide9Parte1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 {
    
    public static void main(String[] args) {
        Atividade2 atividade2 = new Atividade2(20);
        atividade2.init();
    }

    ArrayList<Integer> lista;
    int tamLista;
    Lock lock;
    Condition notFull;
    Condition notEmpty;

    public Atividade2(int tamLista) {
        this.lista = new ArrayList<>();
        this.tamLista = tamLista;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public int getValorLista() {
        int valor = -1;
        this.lock.lock();

        try {
            while (this.lista.size() <= 0) {
                System.out.println("Thread Consumidor Esperando...");
                this.notEmpty.await();
            }

            valor = this.lista.get(0);
            this.lista.remove(0);
            this.tamLista--;
            this.notFull.signal();

        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.lock.unlock();
        }
        return valor;
    }

    public void setValorLista(int valor) {
        this.lock.lock();
        try {
            while (this.lista.size() >= this.tamLista) {
                System.out.println("Thread Produtor Esperando...");
                this.notFull.await();
            }

            this.tamLista++;
            this.lista.add(valor);
            this.notEmpty.signal();

        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.lock.unlock();
        }
    }

    public void init() {
        Thread_Consumidor tConsu = new Thread_Consumidor(this);
        Thread_Produtor tProdu = new Thread_Produtor(this);

        tConsu.start();
        tProdu.start();
    }
}

class Thread_Consumidor extends Thread {

    Atividade2 ex_02;

    public Thread_Consumidor(Atividade2 at2) {
        this.ex_02 = at2;
    }

    @Override
    public void run() {
        int valor;
        while (true) {
            valor = this.ex_02.getValorLista();
            System.out.println("Valor Removido: " + valor);
            sleep(valor);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Thread_Produtor extends Thread {

    Atividade2 at2;
    Random gerador;

    public Thread_Produtor(Atividade2 at2) {
        this.gerador = new Random();
        this.at2 = at2;
    }

    @Override
    public void run() {
        int valor;
        while (true) {
            valor = this.gerador.nextInt(9) + 1;
            at2.setValorLista(valor);
            System.out.println("Valor Inserido: " + valor);
            sleep(valor);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thread_Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
