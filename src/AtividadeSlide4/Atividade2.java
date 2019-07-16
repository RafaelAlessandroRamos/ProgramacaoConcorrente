/*
 * Modifique o código para garantir que será thread-safe. Implemente três 
 * versões: Usando Atomic, sincronizando o método e sincronizando o bloco.
 */
package AtividadeSlide4;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade2 extends Thread{
    
    
    
    ListaCompartilhada_Atomic lista_Atomic;
    ListaCompartilhada_Block lista_Block;
    
    public Atividade2(ListaCompartilhada_Atomic l, ThreadGroup tg, String t1) {
        super(tg, t1);
        this.lista_Atomic = l;
    }

    public Atividade2(ListaCompartilhada_Block l, ThreadGroup tg, String t1) {
        super(tg, t1);
        this.lista_Block = l;
    }

    @Override
    public void run() {
        int numero;
        int count = 0;

        while (this.lista_Atomic.getIndice().get() < this.lista_Atomic.getSize()) {
            numero = this.lista_Atomic.getNumber();
            count = 0;

                for (int i = 1; i <= numero; i++) {
                    if (numero % i == 0) {
                        count++;
                    }
                }
                
                if (count == 2) {
//                    System.out.println("Numero Primo: " + numero);
                }
                
//            sleep(1000);
        }
        while (this.lista_Block.getIndice().get() < this.lista_Block.getSize()) {
            numero = this.lista_Block.getNumber();
            count = 0;

                for (int i = 1; i <= numero; i++) {
                    if (numero % i == 0) {
                        count++;
                    }
                }
                
                if (count == 2) {
//                    System.out.println("Numero Primo: " + numero);
                }
                
//            sleep(1000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


class ListaCompartilhada_Atomic {

    ArrayList<Integer> lista;
    
    AtomicInteger indice;
    int tam_MAX;
    int tam_MIN;
    int num_threads;
    int size;
    ThreadGroup threadGroup;

    public ListaCompartilhada_Atomic(int inic, int fim, int num_threads) {
        this.indice = new AtomicInteger(-1);
        
        this.tam_MAX = fim;
        this.tam_MIN = inic;
        this.num_threads = num_threads;
        this.threadGroup = new ThreadGroup("Grupo01");
        
        this.lista = new ArrayList<>();

        for (int i = tam_MIN; i < tam_MAX; i++) {
            this.lista.add(i);
        }
        
        this.size = this.lista.size();

    }
    
    public int getNumber() {
        this.indice.addAndGet(1);

        if (this.indice.get() == this.lista.size()) {
            return -1;
        } else {
            return this.lista.get(this.indice.get());
        }
    }
    
    public AtomicInteger getIndice(){
        return this.indice;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void numerosPrimos(){
        for(int i=0;i<this.num_threads;i++){
            Atividade2 ex_02 = new Atividade2(this,this.threadGroup,"t"+i);
            
            ex_02.start();
        }
        
        
    }

}

class ListaCompartilhada_Block {

    ArrayList<Integer> lista;

    AtomicInteger indice;
    int tam_MAX;
    int tam_MIN;
    int num_threads;
    int size;
    ThreadGroup threadGroup;

    public ListaCompartilhada_Block(int inic, int fim, int num_threads) {
        this.indice = new AtomicInteger(-1);

        this.tam_MAX = fim;
        this.tam_MIN = inic;
        this.num_threads = num_threads;
        this.threadGroup = new ThreadGroup("Grupo01");

        this.lista = new ArrayList<>();

        for (int i = tam_MIN; i < tam_MAX; i++) {
            this.lista.add(i);
        }

        this.size = this.lista.size();

    }

    public int getNumber() {
        synchronized (this) {
            this.indice.addAndGet(1);

            if (this.indice.get() == this.lista.size()) {
                return -1;
            } else {
                return this.lista.get(this.indice.get());
            }
        }

    }

    public AtomicInteger getIndice() {
        return this.indice;
    }

    public int getSize() {
        return this.size;
    }

    public void numerosPrimos() {
        for (int i = 0; i < this.num_threads; i++) {
            Atividade2 ex_02 = new Atividade2(this, this.threadGroup, "t" + i);

            ex_02.start();
        }
    }

}

class ListaCompartilhada_Method {

    ArrayList<Integer> lista;

    AtomicInteger indice;
    int tam_MAX;
    int tam_MIN;
    int num_threads;
    int size;
    ThreadGroup threadGroup;

    public ListaCompartilhada_Method(int inic, int fim, int num_threads) {
        this.indice = new AtomicInteger(-1);

        this.tam_MAX = fim;
        this.tam_MIN = inic;
        this.num_threads = num_threads;
        this.threadGroup = new ThreadGroup("Grupo01");

        this.lista = new ArrayList<>();

        for (int i = tam_MIN; i < tam_MAX; i++) {
            this.lista.add(i);
        }

        this.size = this.lista.size();

    }

    public synchronized int getNumber() {
        synchronized (this) {
            this.indice.addAndGet(1);

            if (this.indice.get() == this.lista.size()) {
                return -1;
            } else {
                return this.lista.get(this.indice.get());
            }
        }

    }

    public AtomicInteger getIndice() {
        return this.indice;
    }

    public int getSize() {
        return this.size;
    }

    public void numerosPrimos() {
        for (int i = 0; i < this.num_threads; i++) {
            Atividade2 atividade2 = new Atividade2(this, this.threadGroup, "t" + i);
            atividade2.start();
        }
    }

}
