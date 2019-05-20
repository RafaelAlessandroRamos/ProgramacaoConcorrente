/*
 * Faça um programa em Java que use Threads para encontrar os números primos
 * dentro de um intervalo. O método que contabiliza os números primos deve 
 * possuir como entrada: valor inicial e final do intervalo, número de threads
 */
package AtividadeSlide4;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Atividade1 extends Thread {

    ListaCompartilhada lista;

    public Atividade1(ListaCompartilhada l, ThreadGroup tg, String t1) {
        super(tg, t1);
        this.lista = l;
    }

    @Override
    public void run() {
        int numero;
        int count = 0;

        while (this.lista.getIndice() < this.lista.getSize()) {
            numero = this.lista.getNumber();
            count = 0;

            if (numero != -1) {
                for (int i = 1; i <= numero; i++) {
                    if (numero % i == 0) {
                        count++;
                    }
                }
                if (count == 2) {
                    System.out.println("Numero Primo: " + numero);
                }
            }
            sleep(1000);
        }
    }

    public void sleep(int val) {
        try {
            Thread.sleep(val);
        } catch (InterruptedException ex) {
            Logger.getLogger(Atividade1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ListaCompartilhada {

    ArrayList<Integer> lista;
    int indice;
    int tam_MAX;
    int tam_MIN;
    int num_threads;
    int size;
    ThreadGroup threadGroup;

    public ListaCompartilhada(int inic, int fim, int num_threads) {
        this.indice = -1;
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
        this.indice++;

        if (this.indice == this.lista.size()) {
            return -1;
        } else {
            return this.lista.get(this.indice);
        }
    }
    
    public int getIndice(){
        return this.indice;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void numerosPrimos(){
        for(int i=0;i<this.num_threads;i++){
            Atividade1 atv1 = new Atividade1(this,this.threadGroup,"t"+i);
            atv1.start();
        }
    }

}
